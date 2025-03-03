package com.coderczh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderczh.cms.dao.RoleResourceDao;
import com.coderczh.cms.entity.RoleResource;
import com.coderczh.cms.service.RoleResourceService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleResourceServiceImpl implements RoleResourceService {

    @Resource
    private RoleResourceDao roleResourceDao;

    @Override
    public List<RoleResource> getResourceIdByRoleId(Integer roleId) {
        QueryWrapper<RoleResource> wrapper = new QueryWrapper<>();
        wrapper.select("first_id", "second_id", "third_id").eq("role_id", roleId);
        return roleResourceDao.selectList(wrapper);
    }
}
