package com.coderczh.cms.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderczh.cms.common.util.RedisUtil;
import com.coderczh.cms.dao.RoleInfoDao;
import com.coderczh.cms.dao.UserInfoDao;
import com.coderczh.cms.dao.UserRoleDao;
import com.coderczh.cms.dto.login.AccountInputDto;
import com.coderczh.cms.dto.login.LoginOutputDto;
import com.coderczh.cms.dto.login.PhoneInputDto;
import com.coderczh.cms.entity.RoleInfo;
import com.coderczh.cms.entity.UserInfo;
import com.coderczh.cms.entity.UserRole;
import com.coderczh.cms.resp.ResultData;
import com.coderczh.cms.resp.ReturnCodeEnum;
import com.coderczh.cms.service.LoginService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserInfoDao userInfoDao;

    @Resource
    private UserRoleDao userRoleDao;

    @Resource
    private RoleInfoDao roleInfoDao;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public ResultData<LoginOutputDto> getLoginOutput(AccountInputDto accountInputDto) {
        // 获取用户信息
        QueryWrapper<UserInfo> userWrapper = new QueryWrapper<>();
        userWrapper.eq("account_no", accountInputDto.getAccountNo())
                .eq("password", accountInputDto.getPassword());
        UserInfo userInfo = userInfoDao.selectOne(userWrapper);
        if (userInfo == null) {
            return ResultData.fail(ReturnCodeEnum.USER_INFO_ERROR.getCode(),
                    ReturnCodeEnum.USER_INFO_ERROR.getDescription());
        }

        // 获取用户角色关联信息
        UserRole userRoleInfo = getUserRoleInfo(userInfo.getId());
        if (userRoleInfo == null) {
            return ResultData.fail(ReturnCodeEnum.ROLE_INFO_ERROR.getCode(),
                    ReturnCodeEnum.ROLE_INFO_ERROR.getDescription());
        }

        // 获取角色信息
        RoleInfo roleInfo = getRoleInfo(userRoleInfo.getRoleId());
        if (roleInfo == null) {
            return ResultData.fail(ReturnCodeEnum.ROLE_INFO_ERROR.getCode(),
                    ReturnCodeEnum.ROLE_INFO_ERROR.getDescription());
        }
        return getResultData(userInfo, roleInfo);
    }

    @Override
    public ResultData<LoginOutputDto> getLoginOutput(PhoneInputDto phoneInputDto) {
        String captcha = (String) redisUtil.getValue(phoneInputDto.getPhoneNo());
        if(!phoneInputDto.getCaptcha().equals(captcha)){
            return ResultData.fail(ReturnCodeEnum.CAPTCHA_ERROR.getCode(),
                    ReturnCodeEnum.CAPTCHA_ERROR.getDescription());
        }

        // 获取用户信息
        QueryWrapper<UserInfo> userWrapper = new QueryWrapper<>();
        userWrapper.eq("phone_no", phoneInputDto.getPhoneNo());
        UserInfo userInfo = userInfoDao.selectOne(userWrapper);
        if (userInfo == null) {
            return ResultData.fail(ReturnCodeEnum.USER_INFO_ERROR.getCode(),
                    ReturnCodeEnum.USER_INFO_ERROR.getDescription());
        }

        // 获取用户角色关联信息
        UserRole userRoleInfo = getUserRoleInfo(userInfo.getId());
        if (userRoleInfo == null) {
            return ResultData.fail(ReturnCodeEnum.ROLE_INFO_ERROR.getCode(),
                    ReturnCodeEnum.ROLE_INFO_ERROR.getDescription());
        }

        // 获取角色信息
        RoleInfo roleInfo = getRoleInfo(userRoleInfo.getRoleId());
        if (roleInfo == null) {
            return ResultData.fail(ReturnCodeEnum.ROLE_INFO_ERROR.getCode(),
                    ReturnCodeEnum.ROLE_INFO_ERROR.getDescription());
        }
        return getResultData(userInfo, roleInfo);
    }

    @Override
    public ResultData<String> getCaptcha(String phoneNo) {
        String captcha = RandomUtil.randomNumbers(6);
        redisUtil.addValue(phoneNo, captcha, 60 * 1000);
        return ResultData.success(captcha);
    }

    @Override
    public ResultData<JSONObject> getResource(String roleId) {
        List<String> resourceList = Convert.toList(String.class, redisUtil.getList("role_" + roleId));
        JSONObject resource = new JSONObject();
        resource.put("roleId", roleId);
        resource.put("resource", resourceList);
        return ResultData.success(resource);
    }

    private UserRole getUserRoleInfo(Integer userId) {
        QueryWrapper<UserRole> userRoleWrapper = new QueryWrapper<>();
        userRoleWrapper.select("role_id").eq("user_id", userId);
        return userRoleDao.selectOne(userRoleWrapper);
    }

    private RoleInfo getRoleInfo(Integer roleId) {
        QueryWrapper<RoleInfo> roleWrapper = new QueryWrapper<>();
        roleWrapper.eq("id", roleId);
        return roleInfoDao.selectOne(roleWrapper);
    }

    private ResultData<LoginOutputDto> getResultData(UserInfo userInfo, RoleInfo roleInfo) {
        LoginOutputDto loginOutputDto = new LoginOutputDto();
        loginOutputDto.setUserInfo(userInfo).setRoleInfo(roleInfo).setToken(IdUtil.fastSimpleUUID());
        return ResultData.success(loginOutputDto);
    }
}
