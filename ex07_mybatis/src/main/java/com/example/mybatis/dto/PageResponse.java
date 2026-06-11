package com.example.mybatis.dto;

import java.util.List;

import com.example.mybatis.domain.Post;

public record PageResponse<T>(
  //포스트나 유저 등 다 받을라고 제네릭
  List<T> contents,
  int page,
  int size,
  int totalPages,
  Long totalElements,
  String sort

  
) {

}
