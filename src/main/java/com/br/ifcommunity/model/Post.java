package com.br.ifcommunity.model;

import java.io.Serializable;
import java.util.Objects;

public class Post implements Serializable {

    private int postId;
    private int matterId;
    private User author;
    private String title;
    private String postText;
    private String programmingLanguage;
    private boolean isSpam;
    private String registerDate;
    private String updateDate;

    public Post(int postId, int matterId, User author, String title, String postText, String programmingLanguage, boolean isSpam, String registerDate, String updateDate) {
        this.postId = postId;
        this.matterId = matterId;
        this.author = author;
        this.title = title;
        this.postText = postText;
        this.programmingLanguage = programmingLanguage;
        this.isSpam = isSpam;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getMatterId() {
        return matterId;
    }

    public void setMatterId(int matterId) {
        this.matterId = matterId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    public boolean isSpam() {
        return isSpam;
    }

    public void setSpam(boolean spam) {
        isSpam = spam;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", matterId=" + matterId +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", postText='" + postText + '\'' +
                ", programmingLanguage='" + programmingLanguage + '\'' +
                ", isSpam=" + isSpam +
                ", registerDate='" + registerDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return postId == post.postId &&
                matterId == post.matterId &&
                isSpam == post.isSpam &&
                Objects.equals(author, post.author) &&
                Objects.equals(title, post.title) &&
                Objects.equals(postText, post.postText) &&
                Objects.equals(programmingLanguage, post.programmingLanguage) &&
                Objects.equals(registerDate, post.registerDate) &&
                Objects.equals(updateDate, post.updateDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(postId, matterId, author, title, postText, programmingLanguage, isSpam, registerDate, updateDate);
    }
}
