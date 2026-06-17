package com.example.spring_data_jpa.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String title;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String content;

  @OneToMany(mappedBy = "post")
  private List<Comment> comments = new ArrayList<>();

  //엔티티를 생성하는 방법은 생성자, 빌더, 정적메서드 패턴 등 무엇을 활용하든 ok
  public Post(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public void addComment(Comment comment){
    this.comments.add(comment); //현재 게시글의 댓글 목록에 등록
    comment.setPost(this);
  }


  //변경 감지를 위한 비즈니스 메서드
  public void updatePost(String title, String content){
    this.title = title;
    this.content = content;
  }


}
