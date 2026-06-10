package com.example.mybatis.dto;

import jakarta.validation.constraints.NotBlank;

public record PostCreateRequest(
  //PK는 참조값으로 써라~ null확인해야하니까!!
  @NotBlank(message = "작성자 id는 필수 항목입니다.") Long userId,
  @NotBlank(message = "게시글 제목은 필수 항목입니다.")String title,
  String content
) {

}
