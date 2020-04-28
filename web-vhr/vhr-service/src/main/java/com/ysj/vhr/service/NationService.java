package com.ysj.vhr.service;

import com.ysj.vhr.mapper.NationMapper;
import com.ysj.vhr.model.Nation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NationService {
    @Autowired
    private NationMapper nationMapper;

    public List<Nation> findAllNations() {
        List<Nation> nations = nationMapper.selectAll();
        if (nations == null){
            log.info("nations is null");
        }
        return nations;
    }
}
