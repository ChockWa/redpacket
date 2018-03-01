package com.hdh.redpacket.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hdh.redpacket.system.dto.AccessTokenDto;
import com.hdh.redpacket.system.model.AccessToken;
import com.hdh.redpacket.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccessTokenMapper extends BaseMapper<AccessToken> {

    AccessTokenDto getByAccessToken(@Param("accessToken")String accessToken);

    int deleteByUserId(Long userId);

    User getUserByAccessToken(@Param("accessToken")String accessToken);
}
