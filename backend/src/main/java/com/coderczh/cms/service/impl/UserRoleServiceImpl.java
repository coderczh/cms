package com.coderczh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderczh.cms.dao.UserRoleDao;
import com.coderczh.cms.entity.UserRole;
import com.coderczh.cms.service.UserRoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Resource
    private UserRoleDao userRoleDao;

    @Override
    public Integer getRoleIdByUserId(Integer userId) {
        QueryWrapper<UserRole> wrapper = new QueryWrapper<>();
        wrapper.select("role_id").eq("user_id", userId);
        return userRoleDao.selectOne(wrapper).getRoleId();
    }
}
