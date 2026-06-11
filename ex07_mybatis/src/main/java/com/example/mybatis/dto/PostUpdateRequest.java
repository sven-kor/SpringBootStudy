package com.example.mybatis.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record PostUpdateRequest(
  @NotNull(message = "글의 id는 필수입니다.") Long id,
  @NotBlank(message = "제목은 필수입니다.") String title,
  String content
) {

}
