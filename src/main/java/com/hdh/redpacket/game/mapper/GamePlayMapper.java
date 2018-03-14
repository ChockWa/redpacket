package com.hdh.redpacket.game.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hdh.redpacket.game.model.GamePlay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GamePlayMapper extends BaseMapper<GamePlay> {

    GamePlay selectBySelective(GamePlay gamePlay);

    /**
     * 根据状态查询场次信息
     * @param status
     * @return
     */
    List<GamePlay> selectByStatus(@Param("status")List<Integer> status);

    int updateByPlayNoSelective(GamePlay gamePlay);

    /**
     * 根据场次编号查询场次信息
     * @param playNo
     * @return
     */
    GamePlay selectByPlayNo(@Param("playNo")String playNo);

    /**
     * 根据胜出用户id查询记录
     * @param winUserId
     * @return
     */
    List<GamePlay> selectByWinUserId(@Param("winUserId")String winUserId);
}
