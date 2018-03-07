package com.hdh.redpacket.system.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hdh.redpacket.system.model.ConfigDic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConfigDicMapper extends BaseMapper<ConfigDic> {

    /**
     * 获取所有字典项
     * @return
     */
    List<ConfigDic> getAll();

    /**
     * 根据code获取字典
     * @param code
     * @return
     */
    ConfigDic getByCode(@Param("code") String code);

    /**
     * 根据字典类型获取字典列表
     * @param type
     * @return
     */
    List<ConfigDic> getByDicType(@Param("type") Integer type);
}
