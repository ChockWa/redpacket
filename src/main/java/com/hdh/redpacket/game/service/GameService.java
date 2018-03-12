package com.hdh.redpacket.game.service;

import com.hdh.redpacket.game.mapper.GamePlayMapper;
import com.hdh.redpacket.game.model.GamePlay;
import com.hdh.redpacket.system.constant.DictEnum;
import com.hdh.redpacket.system.constant.GameStatusEnum;
import com.hdh.redpacket.system.model.ConfigDic;
import com.hdh.redpacket.system.service.ConfigDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private ConfigDicService configDicService;

    @Autowired
    private GamePlayMapper gamePlayMapper;

    /**
     * 获取可投的钻石列表
     * @return
     */
    public List<ConfigDic> getDiamondList(){
        return configDicService.getDicsByCode(DictEnum.DIAMOND_LIST.getCode());
    }

    public Integer getCurrentGameStatus(){
        return null;
    }
}
