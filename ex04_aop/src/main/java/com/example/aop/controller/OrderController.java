package com.example.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.aop.service.OrderService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

// @AllArgsConstructor //모든 필드를 매개변수로 하는 생성자 -> 결국 생성자 한개 만들어진것 -> @Autowired 쓸 필요 없음!!
//인데!!!! 실무에서는 final 쓰기로 했으니까
@RequiredArgsConstructor //final만 찾아서 생성자 만드는 어노테이션! 생성자 주입을 통한 DI
@RestController //일반 Controller랑 다르게 뷰응답 아니고 데이터 응답으로 바로 처리
public class OrderController {
  
  private final OrderService orderService;

  @GetMapping("aop-test")
  public String aopTest() {
    System.out.println("OrderService 클래스 : " + orderService.getClass());
    System.out.println("==========");
    String result = orderService.createOrder("item-01");
    System.out.println("==========");
    return result;
  }
}
