package org.example;

import java.util.Objects;

public class TagLink {
    private Integer id;
    private Integer tagId;
    private Integer targetId;
    private TargetType targetType;


    public TagLink(Integer id, Integer tagId, Integer targetId, TargetType targetType) {
        this.id = id;
        this.tagId = tagId;
        this.targetId = targetId;
        this.targetType = targetType;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getTagId() {
        return tagId;
    }


    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }


    public Integer getTargetId() {
        return targetId;
    }


    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }


    public TargetType getTargetType() {
        return targetType;
    }


    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }


    @Override
    public String toString() {
        return "TagLink{" +
                "id=" + id +
                ", tagId=" + tagId +
                ", targetId=" + targetId +
                ", targetType=" + targetType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagLink tagLink = (TagLink) o;
        return Objects.equals(id, tagLink.id) &&
                Objects.equals(tagId, tagLink.tagId) &&
                Objects.equals(targetId, tagLink.targetId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tagId, targetId);
    }


}
