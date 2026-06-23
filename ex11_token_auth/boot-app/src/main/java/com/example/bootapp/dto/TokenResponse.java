package com.example.bootapp.dto;

import lombok.Builder;

@Builder
public record TokenResponse(
  String accessToken
) {

}
