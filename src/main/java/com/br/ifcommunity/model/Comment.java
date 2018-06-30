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

    public Comment() {
    }

    public Comment(String commentText) {
        this.commentText = commentText;
    }

    public Comment(int postId, String userId, int commentId, String authorName, String commentText, String registerDate) {
        this.postId = postId;
        this.userId = userId;
        this.commentId = commentId;
        this.authorName = authorName;
        this.commentText = commentText;
        this.registerDate = registerDate;
    }

    public Comment(int postId, String userId, String commentText) {
        this.postId = postId;
        this.userId = userId;
        this.commentText = commentText;
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
                ", userId=" + userId +
                ", authorName='" + authorName + '\'' +
                ", commentText='" + commentText + '\'' +
                ", registerDate='" + registerDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return postId == comment.postId &&
                Objects.equals(userId, comment.userId) &&
                Objects.equals(authorName, comment.authorName) &&
                Objects.equals(commentText, comment.commentText) &&
                Objects.equals(registerDate, comment.registerDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(postId, userId, authorName, commentText, registerDate);
    }
}
