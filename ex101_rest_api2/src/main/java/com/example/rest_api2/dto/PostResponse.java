package com.example.rest_api2.dto;

import com.example.rest_api2.domain.Post;

public record PostResponse(
  Long id,
  Long userId,
  String title,
  String content
) {

  public static PostResponse from(Post post){
    PostResponse postResponse = new PostResponse(post.getId(), post.getUserId(), post.getTitle(), post.getContent());
    return postResponse;
  }

}
