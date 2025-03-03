package com.coderczh.cms.controller.login;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.coderczh.cms.dto.login.UserInputDto;
import com.coderczh.cms.dto.login.UserOutputDto;
import com.coderczh.cms.entity.RoleInfo;
import com.coderczh.cms.entity.UserInfo;
import com.coderczh.cms.resp.ResultData;
import com.coderczh.cms.resp.ReturnCodeEnum;
import com.coderczh.cms.service.RoleService;
import com.coderczh.cms.service.UserRoleService;
import com.coderczh.cms.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private UserRoleService userRoleService;

    @PostMapping("/login")
    public ResultData<UserOutputDto> getUserInfo(@RequestBody UserInputDto userInputDto) {
        if (ObjectUtil.isEmpty(userInputDto)) {
            return ResultData.fail(ReturnCodeEnum.PARAM_ERROR.getCode(), ReturnCodeEnum.PARAM_ERROR.getDescription());
        }
        UserInfo userInfo = userService.getUserInfo(Convert.convert(UserInfo.class, userInputDto));
        if (ObjectUtil.isEmpty(userInfo)) {
            return ResultData.fail(ReturnCodeEnum.USER_INFO_ERROR.getCode(),
                    ReturnCodeEnum.USER_INFO_ERROR.getDescription());
        }
        Integer roleId = userRoleService.getRoleIdByUserId(userInfo.getId());
        if (roleId == null) {
            return ResultData.fail(ReturnCodeEnum.ROLE_INFO_ERROR.getCode(),
                    ReturnCodeEnum.ROLE_INFO_ERROR.getDescription());
        }
        RoleInfo roleInfo = roleService.getRoleInfo(roleId);
        if (ObjectUtil.isEmpty(roleInfo)) {
            return ResultData.fail(ReturnCodeEnum.ROLE_INFO_ERROR.getCode(),
                    ReturnCodeEnum.ROLE_INFO_ERROR.getDescription());
        }
        UserOutputDto userOutputDto = new UserOutputDto();
        userOutputDto.setUserInfo(userInfo).setRoleInfo(roleInfo).setToken(IdUtil.fastSimpleUUID());
        return ResultData.success(userOutputDto);
    }
}
