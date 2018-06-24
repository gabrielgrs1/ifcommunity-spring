package com.br.ifcommunity.model;

import java.io.Serializable;
import java.util.Objects;

public class LikeDeslikePost implements Serializable {
    private int idPost;
    private int idAuthor;
    private int isLike;
    private int isExclude;

    public LikeDeslikePost() {
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public int getIsExclude() {
        return isExclude;
    }

    public void setIsExclude(int isExclude) {
        this.isExclude = isExclude;
    }

    public int getIdPost() {
        return idPost;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public int getIsLike() {
        return isLike;
    }

    public int isExclude() {
        return isExclude;
    }


    @Override
    public String toString() {
        return "LikeDeslikePost{" +
                "idPost=" + idPost +
                ", idAuthor=" + idAuthor +
                ", isLike=" + isLike +
                ", isExclude=" + isExclude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikeDeslikePost that = (LikeDeslikePost) o;
        return idPost == that.idPost &&
                idAuthor == that.idAuthor &&
                isLike == that.isLike &&
                isExclude == that.isExclude;
    }

    @Override
    public int hashCode() {

        return Objects.hash(idPost, idAuthor, isLike, isExclude);
    }
}
