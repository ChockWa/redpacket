package com.hdh.redpacket.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hdh.redpacket.system.model.AccessToken;
import com.hdh.redpacket.system.model.Log;
import com.hdh.redpacket.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LogMapper extends BaseMapper<Log> {

}
