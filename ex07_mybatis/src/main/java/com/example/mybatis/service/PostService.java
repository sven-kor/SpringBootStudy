package com.example.mybatis.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.mybatis.domain.Post;
import com.example.mybatis.dto.PageResponse;
import com.example.mybatis.dto.PostCreateRequest;
import com.example.mybatis.dto.PostResponse;
import com.example.mybatis.dto.PostUpdateRequest;
import com.example.mybatis.exception.CustomException;
import com.example.mybatis.exception.ErrorCode;
import com.example.mybatis.mapper.PostMapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor // final 전용 생성자 주입
@Transactional(readOnly = true, rollbackFor = Exception.class) // 트랜잭션 처리(Aop프록시 기술 이용)
public class PostService {

  private final PostMapper postMapper;

  //생성
  @Transactional
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
  //단건 조회
  public PostResponse findById(Long id){
    //null처리 : Optional 반환값-> 바로 orElseThrow함수 사용 가능
    Post post = postMapper.findById(id)
    .orElseThrow(()-> new CustomException(ErrorCode.POST_NOT_FOUND));
    
  

    //return값은 PostResponse
    //post -> PostRestponse 얻기!! -> from으로 만듦.
    return PostResponse.from(post);
  }
  //전체 조회
  public PageResponse<PostResponse> getPosts(int page, int size, String sort) {
    Long offset = (long) ((page -1) * size);
    Long totalElements = postMapper.countAll();
    int totalPages = (int) Math.ceil((double) totalElements / size);

    List<Post> posts = postMapper.findAll(offset, size, sort);
    List<PostResponse> contents = posts.stream()
    .map(post -> PostResponse.from(post)) //PosTResponse::from (전달만 하니까)
    .collect(Collectors.toList());

    return new PageResponse<PostResponse>(contents, page, size, totalPages, totalElements, sort);
    
  }
  //포스트 삭제
  public void deletePost(Long id){
    postMapper.deleteById(id);
  }

  //post수정
  @Transactional
  public PostResponse updatePost(@Valid @RequestBody PostUpdateRequest request){
    
    Post post = postMapper.findById(request.id())
   .orElseThrow(()-> new CustomException(ErrorCode.POST_NOT_FOUND));
    Post updatePost = Post.builder()
    .id(request.id())
    .title(request.title())
    .content(request.content()!=null? request.content():post.getContent())
    .createdAt(post.getCreatedAt())
    .user(post.getUser())
    .build();
    postMapper.update(updatePost);
    return PostResponse.from(updatePost);
  }

}
