package com.hdh.redpacket.user.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hdh.redpacket.user.dto.UserPropertyDto;
import com.hdh.redpacket.user.model.UserProperty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserPropertyMapper extends BaseMapper<UserProperty>{

    /**
     * 根据用户id获取用户属性
     * @param userId
     * @return
     */
    UserPropertyDto getUserPropertiesByUserId(@Param("userId")String userId);

    /**
     * 根据用户id选择性更新信息
     * @param userProperty
     * @return
     */
    int updateByUserIdSelective(UserProperty userProperty);

    /**
     * 根据userId列表查询用户属性列表
     * @param userIds
     * @return
     */
    List<UserProperty> selectUserProByUserIds(@Param("userIds")List<String> userIds);
}
