package com.example.jpa;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import com.example.jpa.ex02_persistence_context.Book;
import com.example.jpa.util.JpaUtil;

@SpringBootTest
class Ex02PersistenceContextTests {

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

  // 이제부터 테스트
  @Test
  @DisplayName("1차 캐시 테스트")
  void identityAndCacheTest(){
    //엔티티 생성
    Book book = new Book("어린왕자", "생택쥐베리");
    Book book2 = new Book("늙은왕자", "김형준");

    //엔티티 관리 시작(영속상태의 엔티티는 1차 캐시에 저장된다.)
    em.persist(book);

    //엔티티 조회하기 (find()메서드: 오직 ID를 이용해서만 조회)  -> 1차 캐시에서 갖고온다.
    Book findBook1 = em.find(Book.class, book.getId());
    Book findBook2 = em.find(Book.class, book.getId());

    //주소비교를 통해 동일한 Entity인지 확인
    assertTrue(findBook1 == findBook2);
  }

  @Test
  @DisplayName("변경 감지(Dirty Check) 테스트")
  void dirtyCheckTest() {
    //엔티티 생성
    Book book = new Book("소나기", "황순원");
    
    //영속 상태로 변경
    em.persist(book);
    
    //DB반영  //여기서는 의미없음
    // em.flush(); //쓰기 지연 SQL저장소에 있는 모든 쿼리문을 DB로 날림
    
    // 준영속 상태로 전환(관리 안되는 상태)
    em.clear();
    //DB로부터 조회(조회 결과는 영속상태가 됨/1차캐시)
    Book findBook = em.find(Book.class, book.getId());

    // //영속 상태의 엔티티 수정(변경 감지에 의해서 update문 자동생성 -> 쓰기지연 sql저장소에 보관 - 수정 메서드 따로 없음!!)
    findBook.changeTitle("쏘나기");

    //트랜잭션 커밋(tx.commt()) 또는 저장소 비우기(em.flush())를 통해 DB로 쓰기지연 sql저장소의 모든 쿼리를 날림/
    em.flush();


  }
}
