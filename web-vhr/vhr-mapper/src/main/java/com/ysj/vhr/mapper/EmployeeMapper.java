package com.ysj.vhr.mapper;

import com.ysj.vhr.model.Employee;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;


public interface EmployeeMapper extends Mapper<Employee> {

    //todo 配置动态sql分页
    @Select({
            ""
    })
    List<Employee> findEmployeesByPage(Employee employee, Date[] beginDateScope);
}
