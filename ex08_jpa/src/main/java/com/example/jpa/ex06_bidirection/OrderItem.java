package com.example.jpa.ex06_bidirection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "order_items")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String itemName;
  private Integer count;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id") 
  //OrderItem 테이블에 생성될 order_id칼럼을 외래키로 지정한다.
  //외래키를 가진 자식이 항상 양방향 연관관계의 주인이 된다. 
  private Order order;

  public OrderItem(String itemName, Integer count) {
    this.itemName = itemName;
    this.count = count;
  }
  public void setOrder(Order order){
    this.order = order;
    if (!this.order.getOrderItems().contains(this))
    this.order.addOrderItem(this);
  }


}
