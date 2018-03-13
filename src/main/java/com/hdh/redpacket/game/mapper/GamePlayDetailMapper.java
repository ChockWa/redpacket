package com.hdh.redpacket.game.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hdh.redpacket.game.model.GamePlayDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GamePlayDetailMapper extends BaseMapper<GamePlayDetail> {

    int insertOrUpdate(GamePlayDetail gamePlayDetail);

    List<GamePlayDetail> selectByPlayNo(@Param("playNo") String playNo);
}
