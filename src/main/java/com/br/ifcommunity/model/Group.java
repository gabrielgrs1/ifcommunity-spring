package com.br.ifcommunity.model;

import java.io.Serializable;
import java.util.Objects;

public class Group implements Serializable {

    private int groupId;
    private String groupName;
    private boolean isPrivate;
    private String createdDate;

    public Group(int groupId, String groupName, boolean isPrivate, String createdDate) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.isPrivate = isPrivate;
        this.createdDate = createdDate;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", isPrivate=" + isPrivate +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return groupId == group.groupId &&
                isPrivate == group.isPrivate &&
                Objects.equals(groupName, group.groupName) &&
                Objects.equals(createdDate, group.createdDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(groupId, groupName, isPrivate, createdDate);
    }
}
