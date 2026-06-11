package com.example.mybatis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mybatis.dto.PageResponse;
import com.example.mybatis.dto.PostCreateRequest;
import com.example.mybatis.dto.PostResponse;
import com.example.mybatis.dto.PostUpdateRequest;
import com.example.mybatis.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

  private final PostService postService;

  @PostMapping
  public ResponseEntity<PostResponse> createPost(@Valid @RequestBody PostCreateRequest request) {
    //위에서 Valid 예외 가능, POST_NOT_FOUND 가능
    PostResponse Response = postService.createPost(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(Response);

  }
  //단건 조회
  @GetMapping("/{id}")
  public ResponseEntity<PostResponse> getPost(@PathVariable("id") Long id) {
    return ResponseEntity.ok(postService.findById(id));
  }
  //전체 조회
  @GetMapping
  public ResponseEntity<PageResponse<PostResponse>> getPosts(
    @RequestParam(value = "page", defaultValue = "1") int page,
    @RequestParam(value = "size", defaultValue = "2") int size,
    @RequestParam(value = "sort", defaultValue = "DESC") String sort)
    {
      return ResponseEntity.ok(postService.getPosts(page, size, sort) );

    }
    //Post 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id){
      postService.deletePost(id);
      return ResponseEntity.noContent().build();
    }

    //수정(put)
    @PutMapping
    public ResponseEntity<PostResponse> updatePost(
      @Valid @RequestBody PostUpdateRequest postUpdateRequest
    ){
      PostResponse postResponse = postService.updatePost(postUpdateRequest);
      return ResponseEntity.status(HttpStatus.CREATED).body(postResponse);
      
    }


}
