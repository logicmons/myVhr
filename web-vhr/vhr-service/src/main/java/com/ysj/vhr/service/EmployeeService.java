package com.ysj.vhr.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysj.vhr.mapper.EmployeeMapper;
import com.ysj.vhr.model.Employee;
import com.ysj.vhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public RespPageBean findEmployeesByPage(Integer page, Integer size, Employee employee, Date[] beginDateScope){
        PageHelper.startPage(page,size);

        //过滤
//        Example example = new Example(Employee.class);
//        Example.Criteria criteria = example.createCriteria();
//        if (!StringUtils.isEmpty(employee.getName())){
//            criteria.andLike("name","%"+ employee.getName() +"%");
//        }
//        if (employee.getPoliticId() != null){
//            criteria.andEqualTo("politicId",employee.getPoliticId());
//        }
//        if (employee.getNationId() != null){
//            criteria.andEqualTo("nationId",employee.getNationId());
//        }
//        if (employee.getJobLevelId() != null){
//            criteria.andEqualTo("jobLevelId",employee.getJobLevelId());
//        }
//        if (employee.getPosId() != null){
//            criteria.andEqualTo("posId",employee.getPosId());
//        }
//        if (!StringUtils.isEmpty(employee.getEngageForm())){
//            criteria.andEqualTo("engageForm",employee.getEngageForm());
//        }
//        if (employee.getDepartmentId() != null){
//            criteria.andEqualTo("departmentId",employee.getDepartmentId());
//        }
//        if (beginDateScope != null) {
//            criteria.andBetween("beginDate", beginDateScope[0], beginDateScope[1]);
//        }
//        List<Employee> employeeList =  employeeMapper.selectByExample(example);
        List<Employee> employeeList = employeeMapper.findEmployeesByPage(employee, beginDateScope);
        //解析分页
        PageInfo<Employee> pageInfo = new PageInfo<>(employeeList);
        RespPageBean bean = new RespPageBean();

        bean.setTotal(pageInfo.getTotal());
        bean.setData(employeeList);
        return bean;
    }
}
