package com.ysj.vhr.service;

import com.ysj.vhr.mapper.HrMapper;
import com.ysj.vhr.mapper.RoleMapper;
import com.ysj.vhr.model.Hr;
import com.ysj.vhr.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class HrService implements UserDetailsService {
    @Autowired
    private HrMapper hrMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(s);
        if (hr == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        //设置用户权限
        List<Role> roles = roleMapper.findRolesByHid(hr.getId());
        hr.setRoles(roles);
        return hr;
    }
}
