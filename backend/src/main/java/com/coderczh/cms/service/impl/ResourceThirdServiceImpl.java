package com.coderczh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderczh.cms.dao.ResourceThirdDao;
import com.coderczh.cms.entity.ResourceThird;
import com.coderczh.cms.service.ResourceThirdService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceThirdServiceImpl implements ResourceThirdService {

    @Resource
    private ResourceThirdDao resourceThirdDao;

    @Override
    public ResourceThird getResourceThirdById(Integer id) {
        return resourceThirdDao.selectById(id);
    }

    @Override
    public List<ResourceThird> getResourceThirdBySecondId(Integer secondId) {
        QueryWrapper<ResourceThird> wrapper = new QueryWrapper<>();
        wrapper.eq("second_id", secondId);
        return resourceThirdDao.selectList(wrapper);
    }
}
