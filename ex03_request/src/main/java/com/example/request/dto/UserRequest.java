package com.example.request.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserRequest {
  private String name;
  private int age;

}
