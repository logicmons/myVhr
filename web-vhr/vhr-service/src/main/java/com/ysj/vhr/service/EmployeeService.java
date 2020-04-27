package com.ysj.vhr.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysj.vhr.mapper.EmployeeMapper;
import com.ysj.vhr.model.Employee;
import com.ysj.vhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("employeeService")
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public RespPageBean findEmployeesByPage(Integer page, Integer size, Employee employee, Date[] beginDateScope){
        PageHelper.startPage(page,size);
        List<Employee> employeeList =  employeeMapper.findEmployeesByPage(employee,beginDateScope);

        //解析分页
        PageInfo<Employee> pageInfo = new PageInfo<>(employeeList);

        return new RespPageBean(pageInfo.getTotal(),employeeList);
    }
}
