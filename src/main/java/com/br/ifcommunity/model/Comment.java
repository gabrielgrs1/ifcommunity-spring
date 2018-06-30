package com.br.ifcommunity.model;

import java.io.Serializable;
import java.util.Objects;

public class Comment implements Serializable {

    private int postId;
    private String userId;
    private int commentId;
    private String authorName;
    private String commentText;
    private String registerDate;
    private String authorPhoto;

    public Comment() {
    }

    public Comment(String commentText) {
        this.commentText = commentText;
    }

    public Comment(int postId, String userId, int commentId, String authorName, String commentText, String registerDate, String authorPhoto) {
        this.postId = postId;
        this.userId = userId;
        this.commentId = commentId;
        this.authorName = authorName;
        this.commentText = commentText;
        this.registerDate = registerDate;
        this.authorPhoto = authorPhoto;
    }

    public Comment(int postId, String userId, String commentText) {
        this.postId = postId;
        this.userId = userId;
        this.commentText = commentText;
    }

    public String getAuthorPhoto() {
        return authorPhoto;
    }

    public int getPostId() {
        return postId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getUserId() {
        return userId;
    }

    public String getCommentText() {
        return commentText;
    }


    public String getRegisterDate() {
        return registerDate;
    }

    public int getCommentId() {
        return commentId;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "postId=" + postId +
                ", userId='" + userId + '\'' +
                ", commentId=" + commentId +
                ", authorName='" + authorName + '\'' +
                ", commentText='" + commentText + '\'' +
                ", registerDate='" + registerDate + '\'' +
                ", authorPhoto='" + authorPhoto + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return postId == comment.postId &&
                commentId == comment.commentId &&
                Objects.equals(userId, comment.userId) &&
                Objects.equals(authorName, comment.authorName) &&
                Objects.equals(commentText, comment.commentText) &&
                Objects.equals(registerDate, comment.registerDate) &&
                Objects.equals(authorPhoto, comment.authorPhoto);
    }

    @Override
    public int hashCode() {

        return Objects.hash(postId, userId, commentId, authorName, commentText, registerDate, authorPhoto);
    }
}
