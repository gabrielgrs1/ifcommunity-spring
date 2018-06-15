package com.br.ifcommunity.model;

import java.util.Objects;

public class Comment {

    private int postId;
    private String commentText;
    private User author;
    private boolean isExcluded;
    private String registerDate;

    public Comment(int postId, String commentText, User author, boolean isExcluded, String registerDate) {
        this.postId = postId;
        this.commentText = commentText;
        this.author = author;
        this.isExcluded = isExcluded;
        this.registerDate = registerDate;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public boolean isExcluded() {
        return isExcluded;
    }

    public void setExcluded(boolean excluded) {
        isExcluded = excluded;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "postId=" + postId +
                ", commentText='" + commentText + '\'' +
                ", author=" + author +
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
                isExcluded == comment.isExcluded &&
                Objects.equals(commentText, comment.commentText) &&
                Objects.equals(author, comment.author) &&
                Objects.equals(registerDate, comment.registerDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(postId, commentText, author, isExcluded, registerDate);
    }
}
