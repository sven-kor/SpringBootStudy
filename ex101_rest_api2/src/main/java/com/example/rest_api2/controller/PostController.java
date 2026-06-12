package com.example.rest_api2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest_api2.dto.PageResponse;
import com.example.rest_api2.dto.PostResponse;
import com.example.rest_api2.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class PostController {
  private final PostService postService;

  //단건 조회
  @GetMapping("/{id}")
  public ResponseEntity<PostResponse> getById(@PathVariable("id") Long id){
   return ResponseEntity.ok(postService.findById(id));
  }

  //전체 조회
  @GetMapping
  public ResponseEntity<PageResponse<PostResponse>> getAll(){
    
  }

  //추가

  //수정

  //삭제


}
