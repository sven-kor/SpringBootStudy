package com.example.valid.dto;
import java.time.LocalDateTime;

import lombok.Builder;


//DB랑 통신할 dto(ios모델 개념)
@Builder
public record MemberDto(
  Long id,
  String username,
  String email,
  LocalDateTime createdAt
) {

}
