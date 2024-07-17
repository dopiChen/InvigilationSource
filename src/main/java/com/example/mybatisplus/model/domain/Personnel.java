package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 人员表
 * </p>
 *
 * @author clt
 * @since 2024-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Personnel对象", description = "人员表")
public class Personnel extends Model<Personnel> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "单位")
    private String unit;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "学历")
    private String eduBackground;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "电话(备用)")
    private String backupPhone;

    @ApiModelProperty(value = "照片")
    private String photo;

    @TableId("username")
    private String username;

    private String address;


    @Override
    protected Serializable pkVal() {
        return this.username;
    }

}
