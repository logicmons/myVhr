package com.ysj.vhr.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysj.vhr.mapper.EmployeeMapper;
import com.ysj.vhr.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private MailSendLogService mailSendLogService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private SimpleDateFormat yearSimple = new SimpleDateFormat("yyyy");
    private SimpleDateFormat monthSimple = new SimpleDateFormat("MM");
    private DecimalFormat decimalFormat = new DecimalFormat("##.00");

    public RespPageBean findEmployeesByPage(Integer page, Integer size, Employee employee, Date[] beginDateScope){
        PageHelper.startPage(page,size);
        List<Employee> employeeList = employeeMapper.findEmployeesByPage(employee, beginDateScope);
        //解析分页
        PageInfo<Employee> pageInfo = new PageInfo<>(employeeList);
        RespPageBean bean = new RespPageBean();

        bean.setTotal(pageInfo.getTotal());
        bean.setData(employeeList);
        return bean;
    }

    public RespBean deleteById(Integer id) {
        try {
            int i = employeeMapper.deleteByPrimaryKey(id);
            if (i != 1){
                log.info("删除employee失败，请检查参数");
               return RespBean.error("删除employee失败，请检查参数");
            }else {
               return RespBean.ok("删除成功");
            }
        } catch (Exception e) {
           return RespBean.error("该数据有关联数据，操作失败");
        }
    }

    public RespBean updateEmployee(Employee employee) {

        Employee transfer = transfer(employee);
        try {
            int i = employeeMapper.updateByPrimaryKey(transfer);
            if (i != 1){
                return RespBean.error("修改employee失败");
            }
            return RespBean.ok("修改成功");
        } catch (Exception e) {
            log.info("修改employee失败:" +e.getMessage());
            return RespBean.error("修改employee失败");
        }

    }

    public RespBean maxWorkID() {
        try {
            Integer maxWorkID = employeeMapper.selectMaxWorkID();
           return RespBean.ok(null,String.format("%08d",maxWorkID + 1));
        } catch (Exception e) {
            log.error("服务器出错。。"+ e.getMessage());
            throw new RuntimeException("服务器开小差去了。。。");
        }
    }



    /**
     * 持久化对象
     */
    public Employee transfer(Employee employee){
        Employee employeeDao = new Employee();
        employeeDao.setId(employee.getId());
        employeeDao.setName(employee.getName());
        employeeDao.setGender(employee.getGender());
        employeeDao.setBirthday(employee.getBirthday());
        employeeDao.setIdCard(employee.getIdCard());
        employeeDao.setWedlock(employee.getWedlock());
        employeeDao.setNationId(employee.getNationId());
        employeeDao.setNativePlace(employee.getNativePlace());
        employeeDao.setPoliticId(employee.getPoliticId());
        employeeDao.setEmail(employee.getEmail());
        employeeDao.setPhone(employee.getPhone());
        employeeDao.setAddress(employee.getAddress());
        employeeDao.setDepartmentId(employee.getDepartmentId());
        employeeDao.setJobLevelId(employee.getJobLevelId());
        employeeDao.setPosId(employee.getPosId());
        employeeDao.setEngageForm(employee.getEngageForm());
        employeeDao.setTiptopDegree(employee.getTiptopDegree());
        employeeDao.setSpecialty(employee.getSpecialty());
        employeeDao.setSchool(employee.getSchool());
        employeeDao.setBeginDate(employee.getBeginDate());
        employeeDao.setWorkState(employee.getWorkState());
        employeeDao.setWorkID(employee.getWorkID());
        employeeDao.setContractTerm(employee.getContractTerm());
        employeeDao.setConversionTime(employee.getConversionTime());
        employeeDao.setNotWorkDate(employee.getNotWorkDate());
        employeeDao.setBeginContract(employee.getBeginContract());
        employeeDao.setEndContract(employee.getEndContract());
        employeeDao.setWorkAge(employee.getWorkAge());
        return employeeDao;
    }


    public RespBean addEmp(Employee employee) {
        //计算合同期限
        Date beginContract = employee.getBeginContract();
        Date endContract = employee.getEndContract();
        double month = (Double.parseDouble(yearSimple.format(endContract))
                - Double.parseDouble(yearSimple.format(beginContract))) * 12 +
                (Double.parseDouble(monthSimple.format(endContract))
                        - Double.parseDouble(monthSimple.format(beginContract)));
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(month / 12)));
        //持久化
        int i = employeeMapper.insertSelective(employee);
        if (i != 1){
            return RespBean.error("新增employee失败");
        }
        Employee emp = employeeMapper.selectEmpById(employee.getId());
        //给员工发送offer邮件
        MailSendLog mailSendLog = new MailSendLog();
        String msgId = UUID.randomUUID().toString();
        mailSendLog.setMsgId(msgId);
        mailSendLog.setCreateTime(new Date());
        mailSendLog.setEmpId(emp.getId());
        mailSendLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
        mailSendLog.setRouteKey(MailConstants.MAIL_ROUTING_KEY_NAME);
        mailSendLog.setTryTime(new Date(System.currentTimeMillis() + 1000 * 60 * MailConstants.MSG_TIMEOUT));
        mailSendLogService.addMailSendLog(mailSendLog);

        //调用消息队列
        CorrelationData data = new CorrelationData(msgId);
        rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,
                MailConstants.MAIL_ROUTING_KEY_NAME,
                emp,data);

        return RespBean.ok("新增emp成功");
    }
}
