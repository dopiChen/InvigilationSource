package com.example.mybatisplus.model.domain;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class FlowItem {
    private Personnel personnel;
    private User user;
}
