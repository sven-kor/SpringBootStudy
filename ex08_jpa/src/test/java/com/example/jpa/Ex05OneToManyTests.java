package com.example.jpa;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import com.example.jpa.ex04_many_to_one.Category;
import com.example.jpa.ex04_many_to_one.Item;
import com.example.jpa.ex05_one_to_many.Post;
import com.example.jpa.ex05_one_to_many.PostComment;
import com.example.jpa.util.JpaUtil;

@SpringBootTest
class Ex05OneToManyTests {

	 // 엔티티 매니저 (영속성 컨텍스트 관리자)
  private EntityManager em;
  private EntityTransaction tx;

  // 테스트 시작 전 엔티티 매니저를 만들기 위해 팩토리(공장)부터 지어둠
  @BeforeAll
  static void setUpBeforeClass() {
    JpaUtil.initFactory();
  }

  // 각 테스트 시작 전 엔티티 매니저를 생성
  @BeforeEach
  void setUp() {
    em = JpaUtil.getEntityManager();
    tx = em.getTransaction();
    tx.begin();

  
  }

  // 각 테스트 종료 후 엔티티 메니저를 닫아줌
  @AfterEach
  void tearDown() {
    if (tx != null && tx.isActive() ) {
      tx.rollback();
    }
    if (em != null && em.isOpen()) {
    
      em.close();

    }
  }

  // 전체 테스트 종료 후 엔티티 매니저 팩토리를 닫아줌
  @AfterAll
  static void tearDownAfterClass() {
    JpaUtil.closeFactory();
  }

  // 이제부터 테스트 진행
  @Test
  @DisplayName("일대다 단방향 저장 및 조회 테스트")
  void OneToManyTest() {


    //new Post(jpa연관관계)
    //insert into postt (title) values(?)
    //new PostComment("다대다 단방향")
    //insert into post_comments(content) values(?)

    //[중요 : 일대다 단방향의 특징]
    //부모 엔티티가 자식 엔티티 FK값을 바꾸기 위해 update쿼리를 추가로 날림
    //update post_comments set post_id =? where id = ?


    //저장(부모먼저)
    Post post = new Post("java 프로젝트 팀원 구합니다");
    
    PostComment comment1 = new PostComment("32살 이경석 지원합니다");
    PostComment comment2 = new PostComment("36살 김형준 지원합니다.");
    PostComment comment3 = new PostComment("지원합니다");
    post.addComment(comment1);
    post.addComment(comment2);
    post.addComment(comment3);
    

    em.persist(post);
    //@OneToMany(cascade = CascadeType.PERSIST) 설정시 아래 코드 생략가능
    em.persist(comment1);
    em.persist(comment2);
    em.persist(comment3);

    


    em.flush();
    em.clear();

    //조회
    Post findPost = em.find(Post.class, post.getId());
    System.out.println(findPost.getTitle());
    System.out.println(findPost.getComments());



  }
  
}
