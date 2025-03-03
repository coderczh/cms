package com.coderczh.cms.controller.login;

import com.coderczh.cms.component.login.ResourceComponent;
import com.coderczh.cms.dto.login.ResourceOutputDto;
import com.coderczh.cms.resp.ResultData;
import com.coderczh.cms.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @Resource
    private ResourceComponent resourceComponent;

    @GetMapping("/resource/{roleId}")
    public ResultData<ResourceOutputDto> getMenu(@PathVariable("roleId") Integer roleId) {
        if (roleId == null) {
            return ResultData.fail(ReturnCodeEnum.PARAM_ERROR.getCode(), ReturnCodeEnum.PARAM_ERROR.getDescription());
        }
        return ResultData.success(resourceComponent.getResourceByRoleId(roleId));
    }
}
