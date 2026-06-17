package com.example.spring_data_jpa.Service;

import com.example.spring_data_jpa.Ex09SpringDataJpaApplication;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.spring_data_jpa.domain.Comment;
import com.example.spring_data_jpa.domain.Post;
import com.example.spring_data_jpa.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) //읽기전용 트랜잭션
public class PostService {

  private final Ex09SpringDataJpaApplication ex09SpringDataJpaApplication;
  private final PostRepository postRepository;

  //생성
  @Transactional // 읽기 전용이 아님
  public Long createPost(String title, String content) { 
    Post post = new Post(title, content);
    return postRepository.save(post).getId();
  }

  //단 건 조회
  public Post getPost(Long id) {
    Post post = postRepository.findPostWithComments(id);
    //findPostWithComments가 inner join으로 되어있어서 comments없으면 조회가 안됨...ex09SpringDataJpaApplication//-> 외부조인(left조인) 으로 바꿔야됨
    return post;
  }
  //목록 조회(페이징, 제목 키워드 포함)
  public Page<Post> getPosts(String keyword, Pageable pageable){
    if(keyword!= null && !keyword.isBlank()) {
      return postRepository.findByTitleContaining(keyword, pageable);
    }
    return postRepository.findAll(pageable);
  }


  //수정(조회 후(조회결과가 영속화 됨) 엔티티 수정(변경 감지로 인해 UPDATE 자동 생성))
  @Transactional // 읽기전용 아님
  public void updatePost(Long id, String title, String content){
    Post findPost = postRepository.findById(id)
    .orElseThrow(() -> new IllegalArgumentException());
    findPost.updatePost(title, content);
  }

  //삭제(역시 똑같이 조회후 삭제)
  @Transactional //읽기 전용 아님
  public void deletePost(Long id){
    Post findPost = postRepository.findById(id)
    .orElseThrow(() -> new IllegalArgumentException());
    postRepository.delete(findPost);  
  }

  //댓글 등록
  @Transactional//읽기 전용 아님
  public void addComment(Long postId, String content){
    Post findPost = postRepository.findById(postId)
    .orElseThrow(() -> new IllegalArgumentException());
    Comment comment = new Comment(content);
    findPost.addComment(comment);
  }
  



}
