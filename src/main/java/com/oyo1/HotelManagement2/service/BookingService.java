package com.oyo1.HotelManagement2.service;

import com.oyo1.HotelManagement2.Interfaces.NotificationService;
import com.oyo1.HotelManagement2.dto.requestDto.BookingRequestDto;
import com.oyo1.HotelManagement2.dto.responseDto.BookingResponseDto;
import com.oyo1.HotelManagement2.dto.responseDto.NotificationDto;
import com.oyo1.HotelManagement2.entity.Booking;
import com.oyo1.HotelManagement2.entity.Hotel;
import com.oyo1.HotelManagement2.entity.Room;
import com.oyo1.HotelManagement2.enums.BookingStatus;
import com.oyo1.HotelManagement2.exception.*;
import com.oyo1.HotelManagement2.repo.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PriceInvetoryService priceInvetoryService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private CustomerService customerService;



    public BookingResponseDto createBooking(BookingRequestDto bookingRequestDto) throws BookingDateInvalidException, OccupencyNotAvailableException, RoomDoesNotExistException, HotelNotFoundException, RoomInventoryNotAvailable {

        Room room = roomService.getRoomByRoomId(bookingRequestDto.getRoomId());

        Hotel hotel = hotelService.getHotelByHotelId(bookingRequestDto.getHotelId());
        if(hotel == null){
            throw new HotelNotFoundException("Hotel with hotel id " + bookingRequestDto.getHotelId() + " does not exist");
        }

        if(room == null){
            throw new RoomDoesNotExistException("Room with room id " + bookingRequestDto.getRoomId() + " does not exist");
        }

        if(priceInvetoryService.getPriceInvetoryDetailsByRoomIdAndHotelId(room.getRoomId(),bookingRequestDto.getHotelId())){
            throw new RoomInventoryNotAvailable("Room with id : " + room.getRoomId()+ " can not have more bookings for hotel id : " + bookingRequestDto.getHotelId());
        }

        if(bookingRequestDto.getNoOfGuests() > room.getMaxOccupancy()){
            throw new OccupencyNotAvailableException("This room only has maximum occupancy of " + room.getMaxOccupancy());
        }

        if(bookingRequestDto.getCheckIn() != null && ChronoUnit.MONTHS.between(LocalDate.now(), bookingRequestDto.getCheckIn()) > 1){
            throw new BookingDateInvalidException("Booking for this date is not available, try creating a booking on previous dates than selected");
        }

        Booking booking = convertBookingRequestDtoToBooking(bookingRequestDto);
        if(!booking.getIsPrepaid()) booking.setBookingStatus(BookingStatus.Pending);
        else booking.setBookingStatus(BookingStatus.Booked);
        bookingRepository.save(booking);
        priceInvetoryService.updatePriceInventory(booking.getRoomId(), booking.getHotelId(), booking.getCheckIn());
        BookingResponseDto bookingResponseDto = convertBookingToBookingResponseDto(booking);

        System.out.println(Thread.currentThread());

        NotificationDto notificationDto = convertToNotificationDto(bookingRequestDto, booking);
        notificationService.sendNotification(notificationDto);

        System.out.println(Thread.currentThread());
        return bookingResponseDto;
    }

    private NotificationDto convertToNotificationDto(BookingRequestDto bookingRequestDto, Booking booking) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setBody("your booking with booking id : " + booking.getId() + " has been successfully booked and the checkin date is for " + booking.getCheckIn());
        notificationDto.setEmail(customerService.getEmail(bookingRequestDto.getCustomerId()));
        notificationDto.setNumber(customerService.getNumber(bookingRequestDto.getCustomerId()));
        return notificationDto;
    }


    public BookingResponseDto getBooking(Integer bookingId) {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        return convertBookingToBookingResponseDto(booking.get());
    }

    private BookingResponseDto convertBookingToBookingResponseDto(Booking booking) {
        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setBookingStatus(booking.getBookingStatus());
        bookingResponseDto.setHotelId(booking.getHotelId());
        bookingResponseDto.setRoomId(booking.getRoomId());
        bookingResponseDto.setCheckOut(booking.getCheckOut());
        bookingResponseDto.setCheckIn(booking.getCheckIn());
        return bookingResponseDto;
    }

    private Booking convertBookingRequestDtoToBooking(BookingRequestDto bookingRequestDto) {
        Booking booking = new Booking();
        booking.setBookingAmount(bookingRequestDto.getBookingAmount());
        booking.setRoomId(bookingRequestDto.getRoomId());
        booking.setHotelId(bookingRequestDto.getHotelId());
        booking.setCheckIn(bookingRequestDto.getCheckIn());
        booking.setCheckOut(bookingRequestDto.getCheckOut());
        booking.setIsPrepaid(bookingRequestDto.getIsPrepaid());
        return booking;
    }


    public BookingResponseDto updateBooking (Integer bookingId, BookingRequestDto bookingRequestDto) throws BookingNotFound {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if(!booking.isPresent()){
            throw new BookingNotFound("Booking with booking id " + bookingId + " does not exist");
        }

        if(bookingRequestDto.getIsPrepaid() != null) booking.get().setIsPrepaid(bookingRequestDto.getIsPrepaid());
        if(bookingRequestDto.getBookingAmount() != null) booking.get().setBookingAmount(bookingRequestDto.getBookingAmount());
        if(bookingRequestDto.getCheckOut() != null) booking.get().setCheckOut(bookingRequestDto.getCheckOut());
        if(bookingRequestDto.getRoomId() != null) booking.get().setRoomId(bookingRequestDto.getRoomId());
        if(bookingRequestDto.getHotelId() != null) booking.get().setHotelId(bookingRequestDto.getHotelId());
        if(bookingRequestDto.getCheckIn() != null) booking.get().setCheckIn(bookingRequestDto.getCheckIn());

        if(!booking.get().getIsPrepaid()) booking.get().setBookingStatus(BookingStatus.Pending);
        else booking.get().setBookingStatus(BookingStatus.Booked);
        bookingRepository.save(booking.get());

        return convertBookingToBookingResponseDto(booking.get());
    }

    public BookingResponseDto cancelBooking(Integer bookingId) throws BookingNotFound, BookingAlreadyCancelledException {
        Optional<Booking> booking = bookingRepository.findById(bookingId);
        if(!booking.isPresent()) throw new BookingNotFound("The booking with booking id " + bookingId + " does not exist");
        if(booking.get().getBookingStatus() == BookingStatus.Booked)throw new BookingAlreadyCancelledException("Booking with bookinng id : " + booking.get().getId() + " is already cancelled");
        booking.get().setBookingStatus(BookingStatus.Cancelled);
        bookingRepository.save(booking.get());
        priceInvetoryService.increasePriceInventory(booking.get().getCheckIn(), booking.get().getRoomId(), booking.get().getHotelId());
        return convertBookingToBookingResponseDto(booking.get());
    }
}
