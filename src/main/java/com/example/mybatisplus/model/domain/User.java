package com.example.mybatisplus.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author clt
 * @since 2024-07-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId("username")
    @ApiModelProperty(value = "用户名", example = "10001", required = true)
    private String username;
    @ApiModelProperty(value = "密码", example = "12345", required = true)
    private String password;

    private String name;

    private int usertype;

    private Boolean isDeleted;
    private Boolean isEnabled;


    @Override
    protected Serializable pkVal() {
        return this.username;
    }

    public void setLoginName(String 模拟) {
    }

    public void setUserType(int userType) {
        this.usertype=userType;
    }

    public void setStatus(boolean status) {
        this.isEnabled=status;
    }
}
