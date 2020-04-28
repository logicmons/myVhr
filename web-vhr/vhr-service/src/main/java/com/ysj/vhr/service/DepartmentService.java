package com.ysj.vhr.service;

import com.ysj.vhr.mapper.DepartmentMapper;
import com.ysj.vhr.model.Department;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;


    public List<Department> findAllDepartment() {
        List<Department> departments = departmentMapper.selectAll();
        if (departments == null){
            log.info("departments is null");
            return  null;
        }
        return departments;
    }
}
