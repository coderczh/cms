package com.coderczh.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderczh.cms.dao.ResourceSecondDao;
import com.coderczh.cms.entity.ResourceSecond;
import com.coderczh.cms.service.ResourceSecondService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceSecondServiceImpl implements ResourceSecondService {

    @Resource
    private ResourceSecondDao resourceSecondDao;

    @Override
    public ResourceSecond getResourceSecondById(Integer id) {
        return resourceSecondDao.selectById(id);
    }

    @Override
    public List<ResourceSecond> getResourceSecondByFirstId(Integer firstId) {
        QueryWrapper<ResourceSecond> wrapper = new QueryWrapper<>();
        wrapper.eq("first_id", firstId);
        return resourceSecondDao.selectList(wrapper);
    }
}
