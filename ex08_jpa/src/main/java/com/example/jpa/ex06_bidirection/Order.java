package com.example.jpa.ex06_bidirection;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String  orderNumber;
  
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  //양방향 연관 관계의 주인이 아니라고 명시한다. mappedBy 이용 -> 반대편(자식)의 필드이름을 작성한다.
  //영속성 전이(부모만 영속화해도 자식이 함께 영속화 됨), 고아(리스트에는 없지만 실제로는 존재하는 자식엔티티)
  //고아가 발생하면 해당 자식 엔티티를 삭제하기 위한 DELETE문 자동생성
  //고아 만드는법 리스트.remove(번호/엔티티자체)
  private List<OrderItem> orderItems = new ArrayList<>();

  public Order(String orderNumber) {
    this.orderNumber = orderNumber;
  }
//비즈니스 메서드(편의상 작성)
//비즈니스 메서드 작성 시 반대편 편의 메서드와 연동해서 만들기
  public void addOrderItem(OrderItem item){
    this.orderItems.add(item);
    if(item.getOrder() == null) item.setOrder(this);
  }

  

}
