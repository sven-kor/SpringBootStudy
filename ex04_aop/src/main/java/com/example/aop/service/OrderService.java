package com.example.aop.service;

import org.springframework.stereotype.Service;

@Service//컴포넌트 비슷한거인, 비즈니스 로직 관련
public class OrderService {

  //실제 업무 처리(비즈니스 메서드: 포인트컷 대상이 되는 타겟 메서드)
  public String createOrder(String item){
    System.out.println("주문생성 메서드 시작, 주문아이템 : " + item);
    try {
      Thread.sleep(1000); //1초 지연
    } catch (Exception e) {
      Thread.currentThread().interrupt();
    }
    System.out.println("주문생성 메서드 종료");
    return "Order" + item;
  }


}
