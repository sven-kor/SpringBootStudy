package com.example.restapi.service;

import com.example.restapi.controller.MemberController;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.restapi.dto.MemberRequest;
import com.example.restapi.dto.MemberResponse;

@Service
public class MemberServiceImpl implements MemberService{
  // 인 메모리 데이터베이스
  private final Map<Long, MemberResponse> members = new ConcurrentHashMap<>();
  //sequence -> Auto-increment 자동 숫자 증가 (sequence쓸때마다 하나씩 증가)
  private final AtomicLong sequence =  new AtomicLong();

  //Mock 데이터 email만 쓰고 나머지는 null값
  public MemberServiceImpl() {
    for (int i = 1; i < 11; i++) {
      save(MemberRequest
      .builder()
      .email("member" + i + "@test.con")
      .build());
    }

  }

  @Override
  public MemberResponse save(MemberRequest request) {
    Long id = sequence.incrementAndGet();
    String email = request.email();//record에서는 getter가 없고 필드이름 그대로 쓴다.
    LocalDateTime createdAt = LocalDateTime.now();
    MemberResponse memberResponse = new MemberResponse(id, email, createdAt);
    members.put(id, memberResponse);
    return memberResponse;
  }
  @Override
  public List<MemberResponse> findAll() {
    return new ArrayList<>(members.values());
  }

  
  
  @Override
  public MemberResponse findById(Long id) {
    MemberResponse response = members.get(id);
    if (response == null) {
      //지금 뭐 예외 안만들었지만, 실제로는 만들어야함(MemberIdNotFound 등)
      throw new RuntimeException("존재하지 않는 회원ID : " + id);
    }
    return response;
  }
  
  //PUT이라 실제로는 모든 정보 수정 (기존값을 채우기라도 해야함, 안하면 정보사라짐)
  @Override
  public MemberResponse update(MemberRequest request, Long id) {

    MemberResponse foundMember = findById(id);
    //수정 정보를 가진 MemberResponse 새로 생성 후 Map에 저장
    MemberResponse updatedMember = MemberResponse.builder()
        .id(id)
        .email(request.email())
        .createdAt(foundMember.createdAt())
        .build();
    members.put(id, updatedMember);
    return updatedMember;
  }
  
  @Override
  public void deleteById(Long id) {
    findById(id);
    members.remove(id);
    
  }


}
