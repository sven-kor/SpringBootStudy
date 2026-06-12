package com.example.rest_api2.dto;

import java.util.List;

public record PageResponse<T>(
  List<T> pages
) {

}
