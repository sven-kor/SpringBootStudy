package com.example.ex01_ioc.service;

import org.springframework.stereotype.Component;

@Component
public class EmailNotificationService implements NotificationService {

  @Override
  public void sendNotification(String message) {
    System.out.println("[이메일 발송]" + message );
    
  }
 
}
