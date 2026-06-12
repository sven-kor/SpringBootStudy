package com.example.rest_api2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.rest_api2.domain.Post;
import com.example.rest_api2.dto.PostCreateRequest;
import com.example.rest_api2.dto.PostResponse;
import com.example.rest_api2.dto.PostUpdateRequest;

@Service
public class PostService {
  //인메모리 데이터베이스
  private final List<Post> list = new ArrayList<>();
  

  //추가
  public PostResponse savePost(PostCreateRequest request) {
    Post post = Post.builder()
    .id(request.id())
    .userId(request.userId())
    .title(request.title())
    .content(request.content())
    .build();
    return PostResponse.from(post);
  }

  //단건 조회
  public PostResponse findById(Long id){
    Post post = list.stream()
    .filter(p ->p.getId().equals(id))
    .findFirst()
    .orElse(null);
    return PostResponse.from(post);
    
  }

  //전체 조회
  public List<PostResponse> findAll() {
    return list.stream().map(p -> PostResponse.from(p)).toList();
  }

  //수정
  public PostResponse updatePost(PostUpdateRequest request) {
    PostResponse oldPost =  findById(request.id());
    Post updatePost = Post.builder()
    .id(request.id())
    .userId(oldPost.id())
    .title(request.title())
    .content(request.content() != null? request.content():oldPost.content())
    .build();
    return PostResponse.from(updatePost);
  }

  //삭제
  public void deletePost(Long id){
    PostResponse oldPost =  findById(id);
    list.remove(oldPost);
  }

}
