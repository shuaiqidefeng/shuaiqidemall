package com.feng.shuaiqidemall.mapper;

import com.feng.shuaiqidemall.entity.SecondTag;

import java.util.List;

public interface SecondTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SecondTag secondTag);

    SecondTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SecondTag record);

    int updateByPrimaryKey(SecondTag record);

    List<SecondTag> findTagByName(String name);
}