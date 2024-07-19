package com.example.mybatisplus.model.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 批次表
 * </p>
 *
 * @author clt
 * @since 2024-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)

@ApiModel(value = "Batch对象", description = "批次表")
public class Batch extends Model<Batch> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "批次")
    @ExcelProperty("批次编号")
    @TableId(value = "batch_id", type = IdType.AUTO)
    private Integer batchId;

    @ExcelProperty("批次名称")
    @ApiModelProperty(value = "批次名称")
    private String batchName;
    @ExcelProperty("关联年份")
    @ApiModelProperty(value = "关联年份")
    private String year;
    @ExcelProperty("报名开始时间")
    @ApiModelProperty(value = "报名开始时间")
    private LocalDate startDate;
    @ExcelProperty("报名结束时间")
    @ApiModelProperty(value = "报名结束时间")
    private LocalDate endDate;
    @ExcelProperty("描述")
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "附件")
    private String attachment;


    @Override
    protected Serializable pkVal() {
        return this.batchId;
    }

}
