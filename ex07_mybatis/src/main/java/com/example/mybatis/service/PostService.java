package com.example.mybatis.service;

import org.springframework.stereotype.Service;

import com.example.mybatis.domain.Post;
import com.example.mybatis.dto.PostCreateRequest;
import com.example.mybatis.dto.PostResponse;
import com.example.mybatis.mapper.PostMapper;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor // final 전용 생성자 주입
public class PostService {

  private final PostMapper postMapper;

  public PostResponse createPost(PostCreateRequest request){
    //리퀘스트 받아서 여기서 포스트 만드는것
    Post post = Post.builder()
    .userId(request.userId())
    .title(request.title())
    .content(request.content())
    .build();

    //제약 조건 위배를 대비한 코드 필요
    System.out.println("이전"+post);
    postMapper.save(post);
    System.out.println("이후"+post);

    //save하고나면 MyBatis에서
    // <insert id="save" useGeneratedKeys="true" keyProperty="id"> 에 의해서
    //post에 id가 채워진다.
    //그래야 이어서 첨부파일 등이 가능
    //post리턴시 createdAt제외한 모든값 리턴 가능
    //createdAt -> 채우려면 select해야한다. 
    return findById(post.getId());
  }

  public PostResponse findById(Long id){
    return null;
  }

}
