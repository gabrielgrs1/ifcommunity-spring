package com.br.ifcommunity.model;

import java.util.Objects;

public class PostEdited {

    private int postId;
    private int userId;
    private String title;
    private String postText;
    private String programmingLanguage;

    public PostEdited() {
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    public String toString() {
        return "PostEdited{" +
                "postId=" + postId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", postText='" + postText + '\'' +
                ", programmingLanguage='" + programmingLanguage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostEdited that = (PostEdited) o;
        return postId == that.postId &&
                userId == that.userId &&
                Objects.equals(title, that.title) &&
                Objects.equals(postText, that.postText) &&
                Objects.equals(programmingLanguage, that.programmingLanguage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(postId, userId, title, postText, programmingLanguage);
    }
}
