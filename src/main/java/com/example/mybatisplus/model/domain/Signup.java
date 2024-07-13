package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.TableField;
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
 *
 * </p>
 *
 * @author clt
 * @since 2024-07-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Signup对象", description="")
public class Signup extends Model<Signup> {

    private static final long serialVersionUID = 1L;

    @TableId("exam_id")
    private Integer examId;

    private String username;

    private Integer approvalStatus;

    private String intendedCampus;

    private String reson;

    private String way;

    private String name;
    @TableField("is_out")
    private int isOut;
    @TableField(exist = false)
    private Personnel personnel;
    @TableField(exist = false)
    private Examination examination;
    @Override
    protected Serializable pkVal() {
        return this.examId;
    }

}
