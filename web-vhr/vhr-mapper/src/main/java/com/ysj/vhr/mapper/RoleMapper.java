package com.ysj.vhr.mapper;

import com.ysj.vhr.model.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {
    /**根据hr id查询角色 */
    @Select("SELECT r.* FROM role r inner JOIN hr_role h_r ON r.id = h_r.rid JOIN hr ON hr.id = h_r.hrid AND hr.id = #{hid}")
    List<Role> findRolesByHid(Integer hid);
}
