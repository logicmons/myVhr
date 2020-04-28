package com.ysj.vhr.service;

import com.ysj.vhr.mapper.JobLevelMapper;
import com.ysj.vhr.model.JobLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class JobLevelService {
    @Autowired
    private JobLevelMapper jobLevelMapper;

    public List<JobLevel> findAllJobLevel() {
        List<JobLevel> jobLevels = jobLevelMapper.selectAll();
        if (jobLevels == null){
            log.info("jobLevels is null");
        }
        return jobLevels;
    }
}
