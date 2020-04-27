package com.ysj;

import com.ysj.vhr.mapper.HrMapper;
import com.ysj.vhr.model.Hr;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTest {
    @Autowired
    private HrMapper hrMapper;

    @Test
    public void testLogin(){
        Hr hr = hrMapper.loadUserByUsername("李白");
        System.out.println(hr.getId());
    }
}