package com.br.ifcommunity.model;

import java.io.Serializable;
import java.util.Objects;

public class MatterUser implements Serializable {

    private int studentId;
    private String matter1;
    private String matter2;
    private String matter3;
    private String matter4;
    private String matter5;
    private String matter6;

    public MatterUser() {
    }

    public MatterUser(int studentId, String matter1, String matter2, String matter3, String matter4, String matter5, String matter6) {
        this.studentId = studentId;
        this.matter1 = matter1;
        this.matter2 = matter2;
        this.matter3 = matter3;
        this.matter4 = matter4;
        this.matter5 = matter5;
        this.matter6 = matter6;
    }

    public int getStudentId() {
        return studentId;
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

    @Override
    public String toString() {
        return "MatterUser{" +
                "studentId=" + studentId +
                ", matter1='" + matter1 + '\'' +
                ", matter2='" + matter2 + '\'' +
                ", matter3='" + matter3 + '\'' +
                ", matter4='" + matter4 + '\'' +
                ", matter5='" + matter5 + '\'' +
                ", matter6='" + matter6 + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatterUser that = (MatterUser) o;
        return studentId == that.studentId &&
                Objects.equals(matter1, that.matter1) &&
                Objects.equals(matter2, that.matter2) &&
                Objects.equals(matter3, that.matter3) &&
                Objects.equals(matter4, that.matter4) &&
                Objects.equals(matter5, that.matter5) &&
                Objects.equals(matter6, that.matter6);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, matter1, matter2, matter3, matter4, matter5, matter6);
    }
}
