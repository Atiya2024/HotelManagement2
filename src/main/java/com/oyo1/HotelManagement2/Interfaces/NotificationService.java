package com.oyo1.HotelManagement2.Interfaces;


import com.oyo1.HotelManagement2.dto.responseDto.NotificationDto;

public interface NotificationService {

    void sendNotification(NotificationDto notificationDto);
}
