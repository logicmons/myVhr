package com.ysj.vhr.service;

import com.ysj.vhr.mapper.PoliticsstatusMapper;
import com.ysj.vhr.model.Politicsstatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PoliticsstatusService {
    @Autowired
    private PoliticsstatusMapper politicsstatusMapper;


    public List<Politicsstatus> findAllPoliticsstatus() {
        List<Politicsstatus> politicsstatuses = politicsstatusMapper.selectAll();
        if (politicsstatuses == null){
            log.info("politicsstatuses is null");
        }
        return politicsstatuses;
    }
}
