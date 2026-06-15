package com.example.jpa.ex01_entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
  name = "Products", //Poduct 찾지말고 products랑 연결하겠다~
  uniqueConstraints = { //중복금지 제약조건 설정하겠다
    @UniqueConstraint(name = "UC_PRODUCT_CODE", columnNames = {"product_code"})})//제약조건1- 해당 칼럼에 해당 이름의 제약조건 설정하겠다.
public class Product {

  @Id// PK라고 알려주는 것
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  //기본키값 누가 설정할래? -> DB의 AUTO_INCREMENT(자동 증가)를 사용한다.
  private Long id;

  @Column(name = "product_code", nullable = false, length = 20)
  //column 이름 설정
  private String productCode;

  @Column(name = "product_name", nullable = false, length = 100)
  private String name;

  @Column(nullable = false)
  private Integer price;

  @Column(name = "registered_at", updatable = false)
  private LocalDateTime registeredAt;

  @Lob//Large Object(큰 데이터)
  private String description;

  @Transient //일시적인 데이터, 이필드는 DB에 저장하지 말라는 뜻
  private String tempSesstionId;

  //JPA 스펙상 기본 생성자는 필수 요소임.(public 권한 축소해서 protected권한 사용 권장)
  protected Product() {}

  public Product(String productCode, String name, Integer price, String description){
    this.productCode = productCode;
    this.name = name;
    this.price = price;
    this.description = description;
    this.registeredAt = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public String getProductCode() {
    return productCode;
  }

  public String getName() {
    return name;
  }

  public Integer getPrice() {
    return price;
  }

  public LocalDateTime getRegisteredAt() {
    return registeredAt;
  }

  public String getDescription() {
    return description;
  }

  public String getTempSesstionId() {
    return tempSesstionId;
  }

  //JPA 엔티티 설계씨 @ToString 사용을 권장하지 않음
  @Override
  public String toString() {
    return "Product [id=" + id + ", productCode=" + productCode + ", name=" + name + ", price=" + price
        + ", registeredAt=" + registeredAt + ", description=" + description + ", tempSesstionId=" + tempSesstionId
        + "]";
  }

  
  
}
