package com.coderczh.cms.common.util;

import cn.hutool.core.util.StrUtil;
import com.coderczh.cms.common.Constant;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 账户端Redis工具类
 *
 * @author Charles
 * @since 2024-09-05 13:45:15
 */
@Component
public class RedisUtil {
    /**
     * Redis操作类
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置过期时间
     *
     * @param key  键
     * @param date 过期时间
     */
    public void setExpire(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    /**
     * 添加字符串值
     *
     * @param key   键
     * @param value 值
     */
    public void addValue(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 添加字符串值
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间
     */
    public void addValue(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS);
    }

    /**
     * 获取字符串值
     *
     * @param key 键
     * @return 值
     */
    public Object getValue(String key) {
        return StrUtil.isBlank(key) ? Constant.DEFAULT_STRING : redisTemplate.opsForValue().get(key);
    }
}
