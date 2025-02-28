package com.coderczh.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author coderczh
 * @since 2025-02-28 09:54:10
 */
@Data
@Accessors(chain = true)
@TableName("tbl_user_info")
public class UserInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 875310094248937896L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 帐号
     */
    @TableField("account_no")
    private String accountNo;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;

    /**
     * 手机号
     */
    @TableField("phone_num")
    private String phoneNum;

    /**
     * 创建时间
     */
    @TableField("create_date")
    private LocalDate createDate;

    /**
     * 更新时间
     */
    @TableField("update_date")
    private LocalDate updateDate;
}
