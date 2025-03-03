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
 * @since 2025-03-03 10:55:41
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tbl_resource_second")
public class ResourceSecond implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父菜单ID
     */
    @TableField("first_id")
    private Integer firstId;

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
