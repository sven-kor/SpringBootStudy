package com.example.valid.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.example.valid.dto.MemberCreateRequest;
import com.example.valid.dto.MemberDto;
import com.example.valid.dto.MemberUpdateRequest;
import com.example.valid.exception.CustomException;
import com.example.valid.exception.ErrorCode;

@Service

public class MemberService {
  private final Map<Long, MemberDto> store = new ConcurrentHashMap<>();
  private final AtomicLong sequence = new AtomicLong(0);
  public MemberService() {
    save(MemberCreateRequest.builder().username("kim").email("hjhj1@naver.com").build());
    save(MemberCreateRequest.builder().username("lee").email("hjhj2@naver.com").build());
    save(MemberCreateRequest.builder().username("choi").email("hjhj3@naver.com").build());

  }
  //save
  public MemberDto save(MemberCreateRequest request) {
    //이메일 중복 검증
    boolean isExistEmail = store.values().stream()
    .anyMatch(member -> member.email().equals(request.email()));
    
    if (isExistEmail) {
      throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
    }
    Long id = sequence.incrementAndGet();
    MemberDto member = MemberDto.builder()
    .id(id)
    .username(request.username())
    .email(request.email())
    .createdAt(LocalDateTime.now())
    .build();
    store.put(id, member);
    return member;
  }

  //Read All
  public List<MemberDto> findAll() {
    return new ArrayList<>(store.values());
  }

  //Read One
  public MemberDto findById(Long id){
    MemberDto foundMember = store.get(id);
    if (foundMember == null) {
      throw new CustomException(ErrorCode.MEMBER_NOT_FOUND);
    }
    //없으면 예외처리
    return foundMember;
  }

  //update
  public MemberDto updateMember(Long id, MemberUpdateRequest request){
    MemberDto foundMember = findById(id);
    MemberDto updatedMember = MemberDto.builder()
    .id(foundMember.id())
    .username(foundMember.username())
    .email(request.email())
    .createdAt(foundMember.createdAt())
    .build();
    store.put(id, updatedMember);
    return updatedMember;
  }

  //Delete
  public void deleteById(Long id){
    findById(id);
    store.remove(id);
  }

}
