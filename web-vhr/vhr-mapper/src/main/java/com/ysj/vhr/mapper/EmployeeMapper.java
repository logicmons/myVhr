package com.ysj.vhr.mapper;
import com.ysj.vhr.config.BaseMapper;
import com.ysj.vhr.model.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

public interface EmployeeMapper extends BaseMapper<Employee> {

    /**分页查询*/
    @SelectProvider(type = EmpMapperProvider.class,method = "findEmployeesByPage")
    @Results(id = "empMapwithAll",value = {
            @Result(property = "nationId",column = "nationId"),
            @Result(property = "nation",
                    column = "nationId",
                    javaType = Nation.class,
                    one = @One(select = "com.ysj.vhr.mapper.NationMapper.selectByPrimaryKey",
                            fetchType = FetchType.EAGER)),
            @Result(property = "departmentId",column = "departmentId"),
            @Result(property = "department",
                    column = "departmentId",
                    javaType = Department.class,
                    one = @One(select = "com.ysj.vhr.mapper.DepartmentMapper.selectByPrimaryKey",
                            fetchType = FetchType.EAGER)),
            @Result(property = "jobLevelId",column = "jobLevelId"),
            @Result(property = "jobLevel",
                    column = "jobLevelId",
                    javaType = JobLevel.class,
                    one = @One(select = "com.ysj.vhr.mapper.JobLevelMapper.selectByPrimaryKey",
                            fetchType = FetchType.EAGER)),
            @Result(property = "posId",column = "posId"),
            @Result(property = "position",
                    column = "posId",
                    javaType = Position.class,
                    one = @One(select = "com.ysj.vhr.mapper.PositionMapper.selectByPrimaryKey",
                            fetchType = FetchType.EAGER)),
            @Result(property = "politicId",column = "politicId"),
            @Result(property = "politicsstatus",
                    column = "politicId",
                    javaType = Politicsstatus.class,
                    one = @One(select = "com.ysj.vhr.mapper.PoliticsstatusMapper.selectByPrimaryKey",
                            fetchType = FetchType.EAGER))


    })
    List<Employee> findEmployeesByPage(Employee employee, Date[] beginDateScope);

    class EmpMapperProvider{
        public String findEmployeesByPage(Employee employee,Date[] beginDateScope){
            return new SQL(){
                {
                    SELECT(
                            " e.*");
                    FROM("employee e ");
                    if (employee != null && !StringUtils.isEmpty(employee.getName())) {
                    WHERE("name like concat('%',#{employee.name},'%')");
                    }
                    if (beginDateScope != null){
                        WHERE(" beginDate between #{beginDateScope[0]} AND #{beginDateScope[1]}");
                    }
                }
            }.toString();
        }
    }
}
