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
import com.example.jpa.util.JpaUtil;

@SpringBootTest
class Ex04ManyToOneTests {

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
  @DisplayName("다대일 단방향 저장 및 조회 테스트")
  void manyToOneTest() {
    //저장(부모 엔티티를 먼저 영속화)
    Category electronics = new Category("Electronics");
    em.persist(electronics);

    Item iPad = new Item("iPad", electronics);
    em.persist(iPad);

    em.flush(); //쓰기지연 SQL저장소의 쿼리를 DB로 날림

    em.clear();//모든 Managed Entity를 준영속 상태로 변경(DB로부터 select하기 위함)

    //조회
    Item findItem = em.find(Item.class, iPad.getId());
    System.out.println("Category class : "+ findItem.getCategory().getClass().getName());
    System.out.println("Category name: " + findItem.getCategory().getName());




  }
  
}
