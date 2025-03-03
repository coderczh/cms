package com.coderczh.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author coderczh
 * @since 2025-03-03 13:38:40
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tbl_role_resource")
public class RoleResource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private Integer roleId;

    /**
     * 一级菜单ID
     */
    @TableField("first_id")
    private Integer firstId;

    /**
     * 二级菜单ID
     */
    @TableField("second_id")
    private Integer secondId;

    /**
     * 三级菜单ID
     */
    @TableField("third_id")
    private Integer thirdId;
}
