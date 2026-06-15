package com.example.jpa.ex01_entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Entity
@Table(name = "user")
@Getter
public class User {

  @Id
  @TableGenerator(
    name = "userIdGenerator",//자바상 이름,
    table = "user_id_seq",//(db상 테이블)
    pkColumnName = "entity",
    pkColumnValue = "User",
    valueColumnName = "nextval",
    initialValue = 0,
    allocationSize = 1
  )
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "userIdGenerator")
  private Long id;

  @Column(nullable = false, length = 100)
  private String username;

  @Column(nullable = false, unique = true, length = 100)
  private String email;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Enumerated(EnumType.STRING)
  @Column(length = 5)
  private Role role;

  public User(String username, String email, Role role){
    this.username = username;
    this.email = email;
    this.role = role;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username + ", email=" + email + ", createdAt=" + createdAt + ", role="
        + role + "]";
  }


}
