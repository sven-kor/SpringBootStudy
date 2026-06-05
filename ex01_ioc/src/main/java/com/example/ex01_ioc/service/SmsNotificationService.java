package com.example.ex01_ioc.service;

import org.springframework.stereotype.Component;

@Component
public class SmsNotificationService implements NotificationService {

  @Override
  public void sendNotification(String message) {

    System.out.println("문자알림 : "+ message);
    
  }
  
}
