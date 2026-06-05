package com.example.request.controller;

import java.io.StringReader;
import java.nio.file.Path;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.request.dto.UserRequest;

import jakarta.servlet.http.HttpServletRequest;

@Controller
//url 매핑을 여기다가
@RequestMapping("/api/users")
public class RequestController {
  //테스트 요청 주소
  //http://localhost:8080/api/users/v1?name=홍길동&age=30

  //요청 파라미터1(HttpServletRequest 활용하기)
  @GetMapping("/v1")
  public void legacy(HttpServletRequest request) {
    //모든 요청 파라미터는 String 타입으로 전달
    String name = request.getParameter("name");
    String strAge = request.getParameter("age");
    //파라미터가 전달되지 않는 경우
    //1. 값이 없는 경우(빈 문자열 (" "))
    //  ?name=홍길동&age=
    //2. 파라미터가 없는 경우 : null
    //  ?name=홍길동

    int age =0;
    //null부터 확인해야 nullpointException안생김.
    if (strAge != null && !strAge.isBlank()) {
      age = Integer.parseInt(strAge);
    }
    System.out.println("name :" + name);
    System.out.println("age :" + age);
  }

  //요청 파라미터2 (@RequestParam)
  @GetMapping("v2")
  public void requestParam(
    @RequestParam("name") String name,
    @RequestParam(value = "age", required = false, defaultValue = "0") int age
  ) 
  {
    System.out.println("name :" + name);
    System.out.println("age :" + age);
  }

  //요청 파라미터3(커맨드 객체 이용 - 파라미터를 필드로 가진 객체)
  @GetMapping("v3")
  public void commandObject(UserRequest request) {
    System.out.println(request);
  }
  //요청 본문(요청을 본문에 담아서 보내는 POST 방식)
  //클라이언트 : JSON - (GSON, JACKSON)  서버 : 자바 객제
  //스프링 부트의 MessageConverter는 Jackson이 기본성정(Spring Web)
  @PostMapping("/v4")
  public void requestBody(@RequestBody UserRequest request) {
    System.out.println(request);
  }
  //파일 첨부 요청
  //Method:POST
  //EncType: multipart/form-data
  //부트 서버는 MultipartFile 파라미터로 파일 받음
  //파일을 제외한 나머지 파라미터는 커맨드 객체로 처리 추천
  @PostMapping("/v5")
  public void fileAttach(
    @RequestPart("profile") MultipartFile profile,
    UserRequest request
  ){
    if (profile.isEmpty()) {
      System.out.println("첨부파일이 없습니다.");
      return;
    }
    System.out.println("파일명 : " + profile.getOriginalFilename());
    System.out.println("파일 크기 : " + profile.getSize() + "Bytes");
    System.out.println("텍스트 데이터" + request);
  }

}
