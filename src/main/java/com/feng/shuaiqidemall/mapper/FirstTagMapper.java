package com.feng.shuaiqidemall.mapper;

import com.feng.shuaiqidemall.entity.FirstTag;

import java.util.List;

public interface FirstTagMapper {
    List<FirstTag> findTag();
    List<FirstTag> findTagByName(String name);
    int insert(FirstTag firstTag);
}