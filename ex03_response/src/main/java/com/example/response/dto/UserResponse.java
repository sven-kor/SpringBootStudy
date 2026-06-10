package com.example.response.dto;

import lombok.Builder;

//Java16에서 정식된 Record
//1. 모든 필드가 final처리됨(데이터 불변성 확보, setter 불가능) //매개변수 자리에 필드적음
//2. 컴파일러가 필요한 코드를 자동으로 생성(생성자, toString(), equals(), hashCode())
//3. Getter 자동생성 (get으로 시작하지 않고 필드명과 메서드명이 동일)
// @Builder //이렇게 해서 롬복하고 콜라보도 가능
public record UserResponse(String name, int age) {

}
