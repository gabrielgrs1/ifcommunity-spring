package com.br.ifcommunity.model;

import java.io.Serializable;
import java.util.ArrayList;
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
    private ArrayList<LikeDeslikePost> likeDeslikePosts;

    public Post() {
    }

    public Post(int postId, String authorName, String matterName, String title, String postText, String programmingLanguage, String registerDate) {
        this.postId = postId;
        this.authorName = authorName;
        this.matterName = matterName;
        this.title = title;
        this.postText = postText;
        this.programmingLanguage = programmingLanguage;
        this.registerDate = registerDate;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getMatterName() {
        return matterName;
    }

    public void setMatterName(String matterName) {
        this.matterName = matterName;
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

    public ArrayList<LikeDeslikePost> getLikeDeslikePosts() {
        return likeDeslikePosts;
    }

    public void setLikeDeslikePosts(ArrayList<LikeDeslikePost> likeDeslikePosts) {
        this.likeDeslikePosts = likeDeslikePosts;
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
