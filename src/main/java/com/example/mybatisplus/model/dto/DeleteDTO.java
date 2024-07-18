package com.example.mybatisplus.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
public class DeleteDTO {
    private List<Long> ids;

}
