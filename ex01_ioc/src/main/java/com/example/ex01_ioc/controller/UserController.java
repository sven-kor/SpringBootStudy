package com.example.ex01_ioc.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.ex01_ioc.dto.UserDto;

import com.example.ex01_ioc.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class UserController {
  // 알림 서비스 객체 - 
   
  //1.필드주입
  //@Autowired
  //private NotificationService notificationService;

  //2. Setter주입(Setterr의 매개변수로 주입)
  /*
  private NotificationService notificationService;
  
  @Autowired
  public void setNotificationService(NotificationService notificationService) {
    this.notificationService = notificationService;
  }
     */

  //3. Constructor 주입(Constructor의 매개변수로 주입)
  // private NotificationService notificationService;
  //@Autowired 생략가능(생성자1개니까)
  // public UserController(NotificationService notificationService) {
  //   this.notificationService = notificationService;
  // }

  //실무 DI
  //객체 NPE방지(NULL POINT EXCEPTION), 객체 불변성 유지, 순환참조 방지(a->b, b->a, a->b) ...) 
  //필드 선언 시 final 키워드를 추가합니다. 
  //final -> 처음부터 정하거나 생성자로만 초기화가능.

  private final NotificationService notificationService;
  private final ObjectMapper objectMapper;
  
  public UserController(
    //같은 타입이 2개 있는경우 이름으로 구분해서 가져올 수 있다.
    @Qualifier("smsNotificationService") NotificationService notificationService, 
    ObjectMapper objectMapper) {
    this.notificationService = notificationService;
    this.objectMapper = objectMapper;
  }

  // 회원 가입 시 알림 서비스 사용
  @RequestMapping(value = "/join", method = RequestMethod.GET)
  public void createUser(){
    notificationService.sendNotification("반갑습니다");
  }
  // 회원 정보 수정 시 알림 서비스 사용
  @RequestMapping(value = "/modify", method = RequestMethod.POST)
  public void modifyUser(){
     notificationService.sendNotification("수정되었습니다.");
  }

  //ObjectMapper 동작 테스트
  @RequestMapping("/json-test")
  public void jsonTest() {
    try {
      
      //1. java객체 -> json문자열(serialization)
      UserDto dto = new UserDto("김형준", 36);
      String jsonString = objectMapper.writeValueAsString(dto);
      System.out.println("생성된 json: " + jsonString);

      //2. json문자열 -> java객체 (DeSerialization)
      String inputJson = "{\"name\":\"김철수\", \"age\":40}";
      UserDto resulDto = objectMapper.readValue(inputJson, UserDto.class);
      System.out.println("생성된 DTO : " + resulDto);



    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("예외발생사유: " + e.getMessage());
    }
  }
  
}
