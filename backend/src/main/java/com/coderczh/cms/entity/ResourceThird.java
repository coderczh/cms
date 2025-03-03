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
 * @since 2025-03-03 10:56:26
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tbl_resource_third")
public class ResourceThird implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父菜单ID
     */
    @TableField("second_id")
    private Integer secondId;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * url地址
     */
    @TableField("url")
    private String url;

    /**
     * 图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 排序
     */
    @TableField("sort")
    private String sort;

    /**
     * 权限
     */
    @TableField("permission")
    private String permission;
}
