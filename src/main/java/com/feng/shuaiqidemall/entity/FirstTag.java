package com.feng.shuaiqidemall.entity;

import java.util.List;

public class FirstTag {
    private Integer id;

    private String tagName;

    private List<SecondTag> secondTags;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName == null ? null : tagName.trim();
    }

    public List<SecondTag> getSecondTags() {
        return secondTags;
    }

    public void setSecondTags(List<SecondTag> secondTags) {
        this.secondTags = secondTags;
    }

    @Override
    public String toString() {
        return "FirstTag{" +
                "id=" + id +
                ", tagName='" + tagName + '\'' +
                ", secondTags=" + secondTags +
                '}';
    }
}