package com.ysj.vhr.service;

import com.ysj.vhr.mapper.MenuMapper;
import com.ysj.vhr.model.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @作者 江南一点雨
 * @公众号 江南一点雨
 * @微信号 a_java_boy
 * @GitHub https://github.com/lenve
 * @博客 http://wangsong.blog.csdn.net
 * @网站 http://www.javaboy.org
 * @时间 2019-09-27 7:13
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "menus_cache")
public class MenuService {
    @Autowired
     MenuMapper menuMapper;
    @Cacheable
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }

    public List<Menu> findMenusByHr(){
        try {
            List<Menu> menus = menuMapper.findMenusByPid1();
            return menus;
        } catch (Exception e) {
            log.error("查询menus出错：" + e);
            return null;
        }
    }
}
