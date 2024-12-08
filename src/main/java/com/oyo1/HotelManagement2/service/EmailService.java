package com.oyo1.HotelManagement2.service;

import com.oyo1.HotelManagement2.Interfaces.NotificationService;
import com.oyo1.HotelManagement2.dto.responseDto.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Primary

@Service
public class EmailService implements NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    @Async
    public void sendNotification(NotificationDto notificationDto) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        System.out.println(Thread.currentThread());
        simpleMailMessage.setTo(notificationDto.getEmail());
        simpleMailMessage.setText(notificationDto.getBody());
        simpleMailMessage.setSubject(notificationDto.getSubject());
//        javaMailSender.send(simpleMailMessage);
    }
}
