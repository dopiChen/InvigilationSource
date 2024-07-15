package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class FinalLiist {
    @TableField(exist = false)
    private Personnel personnel;
    @TableField(exist = false)
    private Examination examination;
}
