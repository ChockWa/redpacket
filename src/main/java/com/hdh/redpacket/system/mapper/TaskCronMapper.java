package com.hdh.redpacket.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hdh.redpacket.system.model.TaskCron;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskCronMapper extends BaseMapper<TaskCron>{
    int deleteByPrimaryKey(Integer id);

    int insertSelective(TaskCron record);

    TaskCron selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaskCron record);

    int updateByPrimaryKey(TaskCron record);

    TaskCron getByType(String type);
}