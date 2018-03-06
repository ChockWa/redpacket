package com.hdh.redpacket.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hdh.redpacket.system.model.AccessToken;
import com.hdh.redpacket.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccessTokenMapper extends BaseMapper<AccessToken> {

    AccessToken getByAccessToken(@Param("accessToken")String accessToken);

    int deleteByUserId(String userId);

    User getUserByAccessToken(@Param("accessToken")String accessToken);
}
