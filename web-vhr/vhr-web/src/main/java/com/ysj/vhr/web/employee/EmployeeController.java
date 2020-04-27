package com.ysj.vhr.web.employee;

import com.ysj.vhr.model.Employee;
import com.ysj.vhr.model.RespPageBean;
import com.ysj.vhr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("employee/basic")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 分页查询Employee
     */
    @GetMapping("/")
    public RespPageBean getEmployeesByPage(
        @RequestParam(value = "page",defaultValue = "1") Integer page,
            @RequestParam(value = "size",defaultValue = "10") Integer size,
            Employee employee,
            Date[] beginDateScope
        ){
        return employeeService.findEmployeesByPage(page,size,employee,beginDateScope);
    }
}
