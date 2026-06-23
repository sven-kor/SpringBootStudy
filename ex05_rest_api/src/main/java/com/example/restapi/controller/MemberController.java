package com.example.restapi.controller;

import java.lang.reflect.Member;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.dto.MemberRequest;
import com.example.restapi.dto.MemberResponse;
import com.example.restapi.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 전용
@RequestMapping("/api/members")
@RestController
public class MemberController {
  private final MemberService memberService;

  //1.등록
  //ResponseEntity<T> -> 응답 전용 객체
  //HTTP 응답 전체를 직접 제어할 수 있게 해주는 객체(코드나 헤더 직접 지정가능)
  //@RequestBody  : 
  //@ResponseBody
  @PostMapping("/post")
  public ResponseEntity<MemberResponse> createMember(@RequestBody MemberRequest memberRequest){
    MemberResponse savedMember = memberService.save(memberRequest);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
  }
  //2. 회원 전체 조회
  @GetMapping
  public ResponseEntity<List<MemberResponse>> getAllMembers() {
    List<MemberResponse> members = memberService.findAll();
    return ResponseEntity.ok(members);
  }   
  //3. 단건 조회
  //상태 반환을 ok, not found 2가지 상태로 함
  //try-catch가 되는 이유는 service에서 throw던짐
  //PathVariable : URL 경로의 값을 가져오는 어노테이션
  @GetMapping("/{id}")
  public ResponseEntity<MemberResponse> getMemberById(@PathVariable("id") Long id){
    try {
      MemberResponse memberResponse = memberService.findById(id);
      return ResponseEntity.ok(memberResponse);
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
  //4. 회원 수정
  @PutMapping("/{id}")
  public ResponseEntity<MemberResponse> updatedMember(
    @PathVariable("id") Long id,
    @RequestBody MemberRequest request
  ){
    try {
      MemberResponse updateMember = memberService.update(request, id);
      return ResponseEntity.ok(updateMember);
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
  //5. 회원 삭제
  //제네릭에서 아무것도 안 보낼때 Void사용한다,.
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMember(@PathVariable("id") Long id){
    try {
      memberService.deleteById(id);
      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return ResponseEntity.notFound().build();
    }
  }
}
