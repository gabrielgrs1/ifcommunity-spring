package com.br.ifcommunity.model;

import java.io.Serializable;
import java.util.Objects;

public class Comment implements Serializable {

    private int postId;
    private int authorId;
    private String authorName;
    private String commentText;
    private boolean isExcluded;
    private String registerDate;

    public Comment() {
    }

    public Comment(int postId, String authorName, String commentText, boolean isExcluded, String registerDate) {
        this.postId = postId;
        this.authorName = authorName;
        this.commentText = commentText;
        this.isExcluded = isExcluded;
        this.registerDate = registerDate;
    }

    public Comment(int postId, int authorId, String commentText) {
        this.postId = postId;
        this.authorId = authorId;
        this.commentText = commentText;
    }

    public int getPostId() {
        return postId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getCommentText() {
        return commentText;
    }

    public boolean isExcluded() {
        return isExcluded;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "postId=" + postId +
                ", authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", commentText='" + commentText + '\'' +
                ", isExcluded=" + isExcluded +
                ", registerDate='" + registerDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return postId == comment.postId &&
                authorId == comment.authorId &&
                isExcluded == comment.isExcluded &&
                Objects.equals(authorName, comment.authorName) &&
                Objects.equals(commentText, comment.commentText) &&
                Objects.equals(registerDate, comment.registerDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(postId, authorId, authorName, commentText, isExcluded, registerDate);
    }
}
