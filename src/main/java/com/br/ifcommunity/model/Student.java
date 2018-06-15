package com.br.ifcommunity.model;

import java.util.Objects;

public class Student extends User{

    private int studentId;
    private int period;
    private String enrolledNumber;

    private int userId;
    private String user;
    private String name;
    private String phone;
    private String mail;
    private String typeUser;

    public Student(String user, String password, String name, String phone, String mail, String typeUser, int studentId, int period, String enrolledNumber, int userId) {
        super(userId, user, password, name, phone, mail, typeUser);
        this.studentId = studentId;
        this.period = period;
        this.enrolledNumber = enrolledNumber;
        this.userId = userId;
        this.user = user;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.typeUser = typeUser;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getEnrolledNumber() {
        return enrolledNumber;
    }

    public void setEnrolledNumber(String enrolledNumber) {
        this.enrolledNumber = enrolledNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", period=" + period +
                ", enrolledNumber='" + enrolledNumber + '\'' +
                ", userId=" + userId +
                ", user='" + user + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                ", typeUser='" + typeUser + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentId == student.studentId &&
                period == student.period &&
                userId == student.userId &&
                Objects.equals(enrolledNumber, student.enrolledNumber) &&
                Objects.equals(user, student.user) &&
                Objects.equals(name, student.name) &&
                Objects.equals(phone, student.phone) &&
                Objects.equals(mail, student.mail) &&
                Objects.equals(typeUser, student.typeUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(studentId, period, enrolledNumber, userId, user, name, phone, mail, typeUser);
    }
}
