package com.ysj.vhr.mapper;

import com.ysj.vhr.model.Hr;

import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;



public interface HrMapper extends BaseMapper<Hr> {

    @Select("SELECT * FROM `hr` WHERE `username` = #{username}")
    Hr loadUserByUsername(String username);

}
