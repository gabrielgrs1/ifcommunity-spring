package com.br.ifcommunity.model;

import java.util.Objects;

public class Teacher extends User {

    private int teacherId;
    private String website;

    private int userId;
    private String user;
    private String name;
    private String phone;
    private String mail;
    private String typeUser;

    public Teacher(int id, String user, String password, String name, String phone, String mail, String typeUser, int teacherId, String website, int userId) {
        super(id, user, password, name, phone, mail, typeUser);
        this.teacherId = teacherId;
        this.website = website;
        this.userId = userId;
        this.user = user;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.typeUser = typeUser;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
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
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", website='" + website + '\'' +
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
        Teacher teacher = (Teacher) o;
        return teacherId == teacher.teacherId &&
                userId == teacher.userId &&
                Objects.equals(website, teacher.website) &&
                Objects.equals(user, teacher.user) &&
                Objects.equals(name, teacher.name) &&
                Objects.equals(phone, teacher.phone) &&
                Objects.equals(mail, teacher.mail) &&
                Objects.equals(typeUser, teacher.typeUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(teacherId, website, userId, user, name, phone, mail, typeUser);
    }
}
