package com.coderczh.cms.service.impl;

import com.coderczh.cms.dao.ResourceFirstDao;
import com.coderczh.cms.entity.ResourceFirst;
import com.coderczh.cms.service.ResourceFirstService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ResourceFirstServiceImpl implements ResourceFirstService {

    @Resource
    private ResourceFirstDao resourceFirstDao;

    @Override
    public ResourceFirst getResourceFirstById(Integer id) {
        return resourceFirstDao.selectById(id);
    }
}
