package com.ysj.vhr.mapper;


import com.ysj.vhr.config.BaseMapper;
import com.ysj.vhr.model.Menu;
import com.ysj.vhr.model.Meta;
import com.ysj.vhr.model.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;


public interface MenuMapper extends BaseMapper<Menu> {

    @Select("select * from menu where parentId = 1")
    @Results(
            id = "menuMap",value = {
            @Result(column = "id",property = "id",id = true),
            @Result(column = "url",property = "url"),
            @Result(column = "path",property = "path"),
            @Result(column = "component",property = "component"),
            @Result(column = "name",property = "name"),
            @Result(column = "iconCls",property = "iconCls"),
            @Result(column = "id",
                    property = "meta",
                    one = @One(select = "com.ysj.vhr.mapper.MenuMapper.findByMenuId",fetchType = FetchType.EAGER)),
            @Result(column = "parentId",property = "parentId"),
            @Result(column = "enabled",property = "enabled"),
            @Result(column = "id",
                    property = "children",
                    many = @Many(select = "com.ysj.vhr.mapper.MenuMapper.findMenusByPid"))
        }
    )
    List<Menu> findMenusByPid1();

    @Select("select keepAlive,requireAuth from menu where id = #{id}")
    Meta findByMenuId(Integer id);

    /**通过parentId查询*/
    @Select("select * from menu where parentId = #{pid}")
    List<Menu> findMenusByPid(Integer pid);

    /**通过id查询roles集合*/
    @Select("SELECT r.* FROM role r " +
            "INNER JOIN menu_role mr " +
            "ON r.id = mr.rid " +
            "JOIN menu m " +
            "on mr.mid = m.id " +
            "AND m.id = #{mid}")
    List<Role> findRolesByMid(Integer mid);

    @Select("select * from menu")
    @Results(
        id = "menuRoleMap",value =
            {
                @Result(column = "id",
                        property = "roles",
                        many = @Many(select = "com.ysj.vhr.mapper.MenuMapper.findRolesByMid",fetchType = FetchType.EAGER)),
                    @Result(column = "id",property = "id",id = true),
                    @Result(column = "url",property = "url"),
                    @Result(column = "path",property = "path"),
                    @Result(column = "component",property = "component"),
                    @Result(column = "name",property = "name"),
                    @Result(column = "iconCls",property = "iconCls"),
                    @Result(column = "id",
                            property = "meta",
                            one = @One(select = "com.ysj.vhr.mapper.MenuMapper.findByMenuId",fetchType = FetchType.EAGER)),
                    @Result(column = "parentId",property = "parentId"),
                    @Result(column = "enabled",property = "enabled")
            }
    )
    List<Menu> getAllMenusWithRole();
}