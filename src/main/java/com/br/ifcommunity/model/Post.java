package com.br.ifcommunity.model;

import java.io.Serializable;
import java.util.Objects;

public class Post implements Serializable {

    private int postId;
    private int authorId;
    private String authorName;
    private String matterName;
    private String title;
    private String postText;
    private String programmingLanguage;
    private boolean isSpam;
    private String registerDate;
    private String updateDate;

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setMatterName(String matterName) {
        this.matterName = matterName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public void setSpam(boolean spam) {
        isSpam = spam;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Post() {

    }

    public Post(int postId, String matterName, String authorName, String title, String postText, String programmingLanguage, String registerDate) {
        this.postId = postId;
        this.matterName = matterName;
        this.authorName = authorName;
        this.title = title;
        this.postText = postText;
        this.programmingLanguage = programmingLanguage;
        this.registerDate = registerDate;
    }

    public Post(int authorId, String matterName, String title, String programmingLanguage, String postText) {
        this.authorId = authorId;
        this.matterName = matterName;
        this.title = title;
        this.programmingLanguage = programmingLanguage;
        this.postText = postText;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getPostId() {

        return postId;
    }

    public String getMatterName() {
        return matterName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getTitle() {
        return title;
    }

    public String getPostText() {
        return postText;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public boolean isSpam() {
        return isSpam;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", matterName='" + matterName + '\'' +
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
                authorId == post.authorId &&
                isSpam == post.isSpam &&
                Objects.equals(authorName, post.authorName) &&
                Objects.equals(matterName, post.matterName) &&
                Objects.equals(title, post.title) &&
                Objects.equals(postText, post.postText) &&
                Objects.equals(programmingLanguage, post.programmingLanguage) &&
                Objects.equals(registerDate, post.registerDate) &&
                Objects.equals(updateDate, post.updateDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(postId, authorId, authorName, matterName, title, postText, programmingLanguage, isSpam, registerDate, updateDate);
    }
}
