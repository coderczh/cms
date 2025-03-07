package com.coderczh.cms.controller;

import com.coderczh.cms.dto.login.*;
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

    @PostMapping("/phone")
    public ResultData<LoginOutputDto> getLoginOutput(@RequestBody PhoneInputDto phoneInputDto) {
        return loginService.getLoginOutput(phoneInputDto);
    }

    @GetMapping("/captcha/{phoneNo}")
    public ResultData<String> getCaptcha(@PathVariable("phoneNo") String phoneNo) {
        return loginService.getCaptcha(phoneNo);
    }
}
