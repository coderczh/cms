package com.coderczh.cms.controller;

import com.coderczh.cms.dto.login.AccountInputDto;
import com.coderczh.cms.dto.login.AccountOutputDto;
import com.coderczh.cms.resp.ResultData;
import com.coderczh.cms.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public ResultData<AccountOutputDto> getUserInfo(@RequestBody AccountInputDto accountInputDto) {
        return loginService.getLoginOutput(accountInputDto);
    }
}
