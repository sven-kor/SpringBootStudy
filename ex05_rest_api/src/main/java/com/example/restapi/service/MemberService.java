package com.example.restapi.service;

import java.util.List;

import com.example.restapi.dto.MemberRequest;
import com.example.restapi.dto.MemberResponse;

public interface MemberService {
  //public abstract(인터페이스 추상메서드 한 개, 생략)
  MemberResponse save(MemberRequest request);
  List<MemberResponse> findAll();
  MemberResponse findById(Long id);
  MemberResponse update(MemberRequest request, Long id);
  void deleteById(Long id);
}
