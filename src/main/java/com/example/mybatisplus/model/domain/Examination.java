package com.example.mybatisplus.model.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 考试表
 * </p>
 *
 * @author clt
 * @since 2024-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Examination对象", description = "考试表")
public class Examination extends Model<Examination> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "考试编号")
    @TableId(value = "exam_id", type = IdType.AUTO)
    private Integer examId;
    @ExcelProperty("批次编号")
    @ApiModelProperty(value = "批次")
    private Integer batchId;
    @ExcelProperty("考场")
    @ApiModelProperty(value = "考场")
    private String examRoom;
    @ExcelProperty("校区")
    @ApiModelProperty(value = "校区")
    private String campus;
    @ExcelProperty("考试地点")
    @ApiModelProperty(value = "考试地点")
    private String address;
    @ExcelProperty("考试开始时间")
    @ApiModelProperty(value = "考试开始时间")
    private LocalDateTime fromTime;
    @ExcelProperty("考试结束时间")
    @ApiModelProperty(value = "考试结束时间")
    private LocalDateTime endTime;


    @Override
    protected Serializable pkVal() {
        return this.examId;
    }

}
