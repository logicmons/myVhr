package com.ysj.mailserver.receiver;

import com.rabbitmq.client.Channel;
import com.ysj.vhr.model.Employee;
import com.ysj.vhr.model.MailConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testMailSend() throws MessagingException {
//        MimeMessage msg = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(msg);
//        helper.setTo("1263984891@qq.com");
//        helper.setFrom(mailProperties.getUsername());
//        helper.setSubject("入职欢迎");
//        helper.setSentDate(new Date());
//        String mail = templateEngine.process("mail", new Context());
//        helper.setText(mail,true);
//        javaMailSender.send(msg);
//        System.out.println("成功");

//        stringRedisTemplate.opsForHash().put("mail_log","demo","ysj");

        System.out.println("ending");
    }

    @Test
    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void handler(Message message, Channel channel) throws IOException {
        Employee employee = (Employee) message.getPayload();
        MessageHeaders headers = message.getHeaders();
        Long tag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        //获取消息id
        String msgId = (String) headers.get("spring_returned_message_correlation");
        if (stringRedisTemplate.opsForHash().entries("mail_log").containsKey(msgId)) {
            //redis中有msgId，说明已经被消费
            channel.basicAck(tag, false); //确认被消费
            return;
        }
        //收到消息，发送邮件
        try {

            //将msgId存入 redis
            stringRedisTemplate.opsForHash().put("mail_log", msgId, "ysj");
            channel.basicAck(tag, false);
        } catch (Exception e) {
            channel.basicNack(tag, false, true);
            e.printStackTrace();
        }
    }
}
