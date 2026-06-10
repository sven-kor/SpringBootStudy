package com.example.valid.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

  private ErrorCode errorcode;

  public CustomException(ErrorCode errorCode){
    super(errorCode.getMessage());//부모 생성자 불러와야한다.
    this.errorcode = errorCode;
  }

}
