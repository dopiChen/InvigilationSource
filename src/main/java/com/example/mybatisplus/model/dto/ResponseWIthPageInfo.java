package com.example.mybatisplus.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseWIthPageInfo<T> {
    private T data;
    private long total;
}
