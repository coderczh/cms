package com.coderczh.cms.service;

import com.coderczh.cms.dto.registry.RegistryInputDto;
import com.coderczh.cms.resp.ResultData;

public interface RegistryService {
    ResultData<String> registry(RegistryInputDto registryInputDto);
}
