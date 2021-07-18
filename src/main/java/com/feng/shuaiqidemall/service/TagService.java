package com.feng.shuaiqidemall.service;

import com.feng.shuaiqidemall.dto.ResultDTO;
import com.feng.shuaiqidemall.entity.FirstTag;
import com.feng.shuaiqidemall.entity.Good;
import com.feng.shuaiqidemall.entity.SecondTag;
import com.feng.shuaiqidemall.mapper.FirstTagMapper;
import com.feng.shuaiqidemall.mapper.SecondTagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private FirstTagMapper firstTagMapper;

    @Autowired
    private SecondTagMapper secondTagMapper;

    @Transactional
    public boolean insertTag(Good good){
        //获得商品的标签，并将其放进标签表，用于首页的查找
        String[] tags = good.getTag().split("，");
        if (tags.length < 2) return false;
        String firstTagName = tags[0];
        String sencondTagName = tags[1];
        List<FirstTag> firstTags = firstTagMapper.findTagByName(firstTagName);
        List<SecondTag> secondTags = secondTagMapper.findTagByName(sencondTagName);
        SecondTag secondTag = new SecondTag();
        FirstTag firstTag = new FirstTag();
        //若首标签表无该首标签则插入该首标签和在副标签处插入副标签
        if (firstTags.size() == 0){
            firstTag.setTagName(firstTagName);
            firstTagMapper.insert(firstTag);
            secondTag.setTagName(sencondTagName);
            secondTag.setFirstTagId(firstTag.getId());
            secondTagMapper.insert(secondTag);
        }
        //若有首标签无副标签
        if (firstTags.size() != 0 && secondTags.size() == 0){
            secondTag.setTagName(sencondTagName);
            secondTag.setFirstTagId(firstTags.get(0).getId());
            secondTagMapper.insert(secondTag);
        }
        return true;
    }
}
