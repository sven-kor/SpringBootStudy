package com.example.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import com.example.jpa.ex03_embeddable.Address;
import com.example.jpa.ex03_embeddable.Company;
import com.example.jpa.util.JpaUtil;

@SpringBootTest
class Ex03EmbeddableTests {

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
  @DisplayName("임베디드 타입 테스트")
  void embeddedTest() {
    Address office = new Address("seoul", "문래대로", "12345");
    Address factory = new Address("seoul", "디지털로", "54321");

    Company company = new Company(1L, "새싹소프트", office, factory);
    em.persist(company);
    
    // em.flush();
    em.clear();
    Company findCompany = em.find(Company.class, 1L);
    System.out.println(findCompany.toString());
    assertEquals("새싹소프트", findCompany.getName());

  }
  
}
