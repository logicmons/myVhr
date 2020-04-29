package com.ysj;

import com.ysj.vhr.mapper.*;
import com.ysj.vhr.model.*;
import com.ysj.vhr.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private NationMapper nationMapper;

    @Autowired
    private JobLevelMapper jobLevelMapper;

    @Autowired
    private PoliticsstatusMapper politicsstatusMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private PositionMapper positionMapper;
    @Test
    public void findEmployeesByPage() throws ParseException {
//        Employee employee = null;
//        employee.setName("江南");
//        Date[] dates = new Date[2];
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date parse1 = dateFormat.parse("2018-01-01");
//        Date parse2 = dateFormat.parse("2018-02-01");
        //日期测试
//        dates[0] = parse1;
//        dates[1] = parse2;

//        RespPageBean pageBean = employeeService.findEmployeesByPage(1, 10, null, null);
//        System.out.println(pageBean.getTotal());
//        Nation nation = nationMapper.selectByPrimaryKey(1);
//        System.out.println(nation.getName());

//        Department department = departmentMapper.selectByPrimaryKey(1);
//        JobLevel jobLevel = jobLevelMapper.selectByPrimaryKey(9);
//        Politicsstatus politicsstatus = politicsstatusMapper.selectByPrimaryKey(1);
//        Position position = positionMapper.selectByPrimaryKey(29);
//
//        System.out.println("over!");
        Employee employee = new Employee();
        employee.setName("张三");
        RespBean respBean = employeeService.addEmp(employee);

    }
}