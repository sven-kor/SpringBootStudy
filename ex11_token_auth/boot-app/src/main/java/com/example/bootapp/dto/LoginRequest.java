package com.example.bootapp.dto;

import lombok.Builder;

@Builder
public record LoginRequest(
   String username, String password
) {

}
