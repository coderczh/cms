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
 * @since 2025-02-28 09:54:22
 */
@Data
@Accessors(chain = true)
@TableName("tbl_role_info")
public class RoleInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 5560208124240710694L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色类型
     */
    @TableField("type")
    private String type;

    /**
     * 描述
     */
    @TableField("description")
    private String description;

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
