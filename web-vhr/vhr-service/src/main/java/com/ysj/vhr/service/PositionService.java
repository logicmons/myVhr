package com.ysj.vhr.service;

import com.ysj.vhr.mapper.PositionMapper;
import com.ysj.vhr.model.Position;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PositionService {
    @Autowired
    private PositionMapper positionMapper;

    public List<Position> getAllPosition() {
        List<Position> positions = positionMapper.selectAll();
        if (positions == null){
            log.info("positions is null");
            return null;
        }
        return positions;
    }
}
