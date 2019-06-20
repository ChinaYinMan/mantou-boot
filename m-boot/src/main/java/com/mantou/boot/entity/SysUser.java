package com.mantou.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 系统模块-用户表
 *  TODO @Accessors提供链式set方法
 *  TODO @RequiredArgsConstructor 提供静态的构造方法
 * @author mantou
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "newSysUser")
@ApiModel(value="SysUser对象", description="系统模块-用户表")
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键:用户ID")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @ApiModelProperty(value = "用户登录账号")
    private String userAccount;

    @ApiModelProperty(value = "用户登录密码(由英文字母, 数字, 下划线组成, 必须以英文字母开头)")
    private String userPassword;

    @ApiModelProperty(value = "用户真实名称")
    private String userName;

    @ApiModelProperty(value = "用户出生日期")
    private Date userBirthday;

    @ApiModelProperty(value = "1男  2女")
    private Integer userGender;

    @ApiModelProperty(value = "创建日期")
    private Date createdTime;

    @ApiModelProperty(value = "创建人")
    private Long createdUser;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

    @ApiModelProperty(value = "更新人")
    private Long updatedUser;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
