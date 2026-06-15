package com.example.jpa.util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
  //EntityMangerFactory(공장)
  private static EntityManagerFactory factory;

  //공장 시작 메서드
  public static void initFactory() {
    factory = Persistence.createEntityManagerFactory("jpa-learning");
  }
  //엔티티 매니저 생성
  public static EntityManager getEntityManager() {
    return factory.createEntityManager();
  }
  //공장 문닫기
  public static void closeFactory() {
    factory.close();
  }
}