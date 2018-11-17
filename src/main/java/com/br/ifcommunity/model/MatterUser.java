package com.br.ifcommunity.model;

import java.io.Serializable;
import java.util.Objects;

public class MatterUser implements Serializable {

    private String userId;
    private String matter1;
    private String matter2;
    private String matter3;
    private String matter4;
    private String matter5;
    private String matter6;
    private String matter7;

    public MatterUser() {
    }

    public MatterUser(String userId, String matter1, String matter2, String matter3, String matter4, String matter5, String matter6, String matter7) {
        this.userId = userId;
        this.matter1 = matter1;
        this.matter2 = matter2;
        this.matter3 = matter3;
        this.matter4 = matter4;
        this.matter5 = matter5;
        this.matter6 = matter6;
        this.matter7 = matter7;
    }

    public String getUserId() {
        return userId;
    }

    public String getMatter1() {
        return matter1;
    }

    public String getMatter2() {
        return matter2;
    }

    public String getMatter3() {
        return matter3;
    }

    public String getMatter4() {
        return matter4;
    }

    public String getMatter5() {
        return matter5;
    }

    public String getMatter6() {
        return matter6;
    }

    public String getMatter7() {
        return matter7;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMatter1(String matter1) {
        this.matter1 = matter1;
    }

    public void setMatter2(String matter2) {
        this.matter2 = matter2;
    }

    public void setMatter3(String matter3) {
        this.matter3 = matter3;
    }

    public void setMatter4(String matter4) {
        this.matter4 = matter4;
    }

    public void setMatter5(String matter5) {
        this.matter5 = matter5;
    }

    public void setMatter6(String matter6) {
        this.matter6 = matter6;
    }

    public void setMatter7(String matter7) {
        this.matter7 = matter7;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatterUser that = (MatterUser) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(matter1, that.matter1) &&
                Objects.equals(matter2, that.matter2) &&
                Objects.equals(matter3, that.matter3) &&
                Objects.equals(matter4, that.matter4) &&
                Objects.equals(matter5, that.matter5) &&
                Objects.equals(matter6, that.matter6) &&
                Objects.equals(matter7, that.matter7);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, matter1, matter2, matter3, matter4, matter5, matter6, matter7);
    }

    @Override
    public String toString() {
        return "MatterUser{" +
                "userId=" + userId +
                ", matter1='" + matter1 + '\'' +
                ", matter2='" + matter2 + '\'' +
                ", matter3='" + matter3 + '\'' +
                ", matter4='" + matter4 + '\'' +
                ", matter5='" + matter5 + '\'' +
                ", matter6='" + matter6 + '\'' +
                ", matter7='" + matter7 + '\'' +
                '}';
    }
}
