package com.example.response.controller;

import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.response.dto.UserResponse;

@RestController
@RequestMapping("/api/users")
public class ResponseController {

  //JSON 문자열 응답
@GetMapping("/v1")
// @ResponseBody //반환값을 뷰로 해석하지않고, 데이터 그대로 처리함
public String responseString(){
  String jsoString = "{\"name\":\"김형준\",\"age\":36}";
  return jsoString;
}
  //자바 객체 응답
  //(MessageConvertor인 Jackson이 JSON 문자열로 자동반환)
@GetMapping("/v2")
@ResponseBody
public UserResponse responseObject() {
  return new UserResponse("사만다", 500);
}
  //응답 전용 객체 ResponseEntity<T>
  //1. HTTP 상태 코드 반환 가능
  //2. 응답 본문 작성 가능
  //3. @ResponseBody명시 불필요
@GetMapping("/v3")
public ResponseEntity<Map<String,String>> responseEntity() {
  //정상 응답 //ok를 찍어서 200번을 직접 설정했음.
  // return ResponseEntity.ok(new UserResponse("이경석", 50));
  
  //예외 응답
  // return ResponseEntity.badRequest().body(Map.of("제시카", "20"));

  //예외 응답(일반 예외)
  // return new ResponseEntity<>(Map.of("message", "잘못된 요청"), HttpStatus.BAD_REQUEST);
  System.out.println("sdf");
  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "권한 없음911!"));
  
}
}
