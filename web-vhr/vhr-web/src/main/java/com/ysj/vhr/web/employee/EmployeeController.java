package com.ysj.vhr.web.employee;

import com.ysj.vhr.model.*;
import com.ysj.vhr.service.*;
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

    @Autowired
    private NationService nationService;

    @Autowired
    private JobLevelService jobLevelService;

    @Autowired
    private PoliticsstatusService politicsstatusService;
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

    /**
     *查询所有部门
     */
    @GetMapping("/deps")
    public List<Department> findAllDepartment(){
        return departmentService.findAllDepartment();
    }

    /**
     * 查询所有民族
     */
    @GetMapping("/nations")
    public List<Nation> findAllNations(){
        return nationService.findAllNations();
    }

    /**
     * 查询所有职位等级
     */
    @GetMapping("/joblevels")
    public List<JobLevel> findAllJobLevel(){
        return jobLevelService.findAllJobLevel();
    }

    /**
     * 查询所有职位等级
     */
    @GetMapping("/politicsstatus")
    public List<Politicsstatus> findAllPoliticsstatus(){
        return politicsstatusService.findAllPoliticsstatus();
    }

    /**
     * 根据id删除单个employee
     */

    @DeleteMapping("/{id}")
    public RespBean deleteEmpById(@PathVariable("id") Integer id){
        return employeeService.deleteById(id);
    }
}
