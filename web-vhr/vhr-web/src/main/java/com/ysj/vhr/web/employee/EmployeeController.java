package com.ysj.vhr.web.employee;

import com.ysj.vhr.model.Department;
import com.ysj.vhr.model.Employee;
import com.ysj.vhr.model.Position;
import com.ysj.vhr.model.RespPageBean;
import com.ysj.vhr.service.DepartmentService;
import com.ysj.vhr.service.EmployeeService;
import com.ysj.vhr.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("employee/basic")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private DepartmentService departmentService;
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

    /**
     *查询所有籍贯
     */
    @GetMapping("/positions")
    public List<Position> getPositions(){
       return positionService.getAllPosition();
    }

    @GetMapping("/deps")
    public List<Department> findAllDepartment(){
        return departmentService.findAllDepartment();
    }
}
