package com.example.mybatis.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Post {
  private Long id;
  private Long userId;
  private String title;
  private String content;
  private LocalDateTime createdAt;

  private User user;
}
