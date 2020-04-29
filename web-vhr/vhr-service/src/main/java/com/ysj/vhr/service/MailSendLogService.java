package com.ysj.vhr.service;

import com.ysj.vhr.mapper.MailSendLogMapper;
import com.ysj.vhr.model.MailSendLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MailSendLogService {
    @Autowired
    private MailSendLogMapper mailSendLogMapper;

    public void addMailSendLog(MailSendLog mailSendLog){
        try {
            mailSendLogMapper.insertSelective(mailSendLog);
        } catch (Exception e) {
            log.info("新增mailSendLog失败 : " + e.getMessage());
        }
    }

    public void updateMailSendLogStatus(String msgId, int i) {
        MailSendLog sendLog = new MailSendLog();
        sendLog.setMsgId(msgId);
        sendLog.setStatus(i);
        int row = mailSendLogMapper.updateByPrimaryKey(sendLog);
        if (row != 1){
            log.info("修改邮件状态失败:" + msgId);
        }
    }
}
