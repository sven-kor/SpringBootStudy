package com.example.spring_data_jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.spring_data_jpa.domain.Post;

//Spring Data JPA는 JpaRopository<> 인터페이스를 상속받으면 필요한 구현체를 알아서 만들어준다.

//이미 완성된 메서드
//1. 저장 : save(T entity)
//2. 조회 : findById(ID id),findAll(), findAll(Pageable, pageable), count(), existsById(ID id)
//3. 삭제 : deleteById(ID id), delete(T entity)
//4. 수정 : JpaRepository가 지원하지 않음(변경 감지를 이용함)

//<엔티티타입, ID타입>
public interface PostRepository extends JpaRepository<Post, Long> {

  //게시글 단건 조회(게시글과 댓글을 조인하여 한 번에 조회하도록 JPQL작성)
  @Query("select p from Post p left join fetch p.comments where p.id = :id")
  Post findPostWithComments(@Param("id") Long id);

  //제목에 특정 키워드가 포함된 게시글 목록 조회(paging)
  Page<Post> findByTitleContaining(String keyword, Pageable pageable);
}
