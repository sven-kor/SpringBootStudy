package com.example.mybatis.exception;

import lombok.Getter;

@Getter //꺼내서 써야하니까
public class CustomException extends RuntimeException {
  private final ErrorCode errorCode;

  public CustomException(ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorCode = errorCode;
  }

  
}
