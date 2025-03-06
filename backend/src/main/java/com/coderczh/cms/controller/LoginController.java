package com.coderczh.cms.controller;

import com.coderczh.cms.dto.login.AccountInputDto;
import com.coderczh.cms.dto.login.LoginOutputDto;
import com.coderczh.cms.resp.ResultData;
import com.coderczh.cms.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("/account")
    public ResultData<LoginOutputDto> getLoginOutput(@RequestBody AccountInputDto accountInputDto) {
        return loginService.getLoginOutput(accountInputDto);
    }
}
