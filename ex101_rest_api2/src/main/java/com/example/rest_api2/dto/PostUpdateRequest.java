package com.example.rest_api2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public record PostUpdateRequest(
  @NotNull(message = "id는 필수") Long id,
  @NotBlank(message = "title은 필수") String title,
  String content
) {
}
