package com.example.mybatis.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;


@Getter //Exception에서 3개 내용을 꺼내서 사용해야하기때문에 Getter가 있어야함. 
public enum ErrorCode {
  //400 Bad Request
  INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "올바르지 않은 입력값입니다"),

  //404 Not Found
  POST_NOT_FOUND(HttpStatus.NOT_FOUND, "P001", "일치하지 게시글이 존재하지 않습니다"),

  //500 Internal Server Error
  INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S001", "서버 내부에 예기치 않은 오류가 발생했습니다.");

  private HttpStatus status;
  private String code;
  private String message;
  
  private ErrorCode(HttpStatus status, String code, String message) {
    this.status = status;
    this.code = code;
    this.message = message;
  }

  







  
}
