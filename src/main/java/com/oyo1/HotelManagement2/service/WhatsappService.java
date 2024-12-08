package com.oyo1.HotelManagement2.service;

import com.oyo1.HotelManagement2.Interfaces.NotificationService;
import com.oyo1.HotelManagement2.dto.responseDto.NotificationDto;
import org.springframework.stereotype.Service;

@Service
public class WhatsappService implements NotificationService {
    @Override
    public void sendNotification(NotificationDto notificationDto) {
        System.out.println("This is whatsapp Notification");
    }
}
