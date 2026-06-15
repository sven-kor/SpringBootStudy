package com.example.jpa.ex02_persistence_context;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;
  
  private String auther;

  @Override
  public String toString() {
    return "Book [id=" + id + ", title=" + title + ", auther=" + auther + "]";
  }

  public Book(String title, String auther){
    this.title = title;
    this.auther = auther;
  }

  //책이름을 바꿔주는 비즈니스 메서드, 실질적으로는 setter지만 사용자 친화적으로 쓰자~
  public void changeTitle(String title){
    this.title = title;

  }

  

}
