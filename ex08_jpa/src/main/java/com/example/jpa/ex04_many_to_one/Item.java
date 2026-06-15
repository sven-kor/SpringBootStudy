package com.example.jpa.ex04_many_to_one;

import org.hibernate.annotations.Fetch;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String itemName;

  //연관 관계 핵심 부분
  //FetchType.EAGER(즉시 로딩) : find() 호출 시 연관관계를 가진 테이블을 함께 조회하는것 (디폴트)
  //FetchType.LAZY(지연 로딩) :  find() 호출 시 자신만 조회하고, 연관관계 테이블은 실제로 필요할때 다시 조회하는 것.(연관 관계의 데이터는 프록시 객체 사용)  -> 실무 표준!!
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_item_to_category"))
  private Category category;
  //단방향 조회임! 아이템-> 카테고리 조회 가능 / 카테고리 -> 아이템 조회 안됨.


  public Item(String itemName, Category category){
    this.itemName = itemName;
    this.category = category;
  }

  @Override
  public String toString() {
    return "Item [id=" + id + ", itemName=" + itemName + ", category=" + category + "]";
  }

  



}
