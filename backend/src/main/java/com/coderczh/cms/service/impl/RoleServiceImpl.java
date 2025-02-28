package com.coderczh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderczh.cms.dao.RoleInfoDao;
import com.coderczh.cms.entity.RoleInfo;
import com.coderczh.cms.service.RoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleInfoDao roleInfoDao;

    @Override
    public RoleInfo getRoleInfo(Integer roleId) {
        QueryWrapper<RoleInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("id", roleId);
        return roleInfoDao.selectOne(wrapper);
    }
}
