package com.coderczh.cms.controller;

import com.coderczh.cms.dto.registry.RegistryInputDto;
import com.coderczh.cms.resp.ResultData;
import com.coderczh.cms.service.RegistryService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistryController {

    @Resource
    private RegistryService registryService;

    @PostMapping("/registry")
    public ResultData<String> registry(@RequestBody RegistryInputDto registryInputDto) {
        return registryService.registry(registryInputDto);
    }
}
