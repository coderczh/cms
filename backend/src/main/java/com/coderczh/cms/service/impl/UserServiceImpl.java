package com.coderczh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderczh.cms.dao.UserInfoDao;
import com.coderczh.cms.entity.UserInfo;
import com.coderczh.cms.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo getUserInfo(UserInfo userInfo) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("account_no", userInfo.getAccountNo())
                .eq("password", userInfo.getPassword());
        return userInfoDao.selectOne(wrapper);
    }
}
