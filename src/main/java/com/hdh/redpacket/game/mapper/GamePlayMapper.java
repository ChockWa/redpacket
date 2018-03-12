package com.hdh.redpacket.game.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hdh.redpacket.game.model.GamePlay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GamePlayMapper extends BaseMapper<GamePlay> {

    GamePlay selectBySelective(GamePlay gamePlay);

    List<GamePlay> selectByStatus(@Param("status")List<Integer> status);
}
