package com.br.ifcommunity.model;

import java.io.Serializable;
import java.util.Objects;

public class Administrator extends User implements Serializable {

    private int administratorId;
    private int permission;

    private int userId;
    private String user;
    private String name;
    private String phone;
    private String mail;
    private int typeUser;

    public Administrator(int id, String user, String password, String name, String phone, String mail, int typeUser, int administratorId, int permission, int userId) {
        super(id, user, password, name, phone, mail, typeUser);
        this.administratorId = administratorId;
        this.permission = permission;
        this.userId = userId;
        this.user = user;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.typeUser = typeUser;
    }

    public int getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(int administratorId) {
        this.administratorId = administratorId;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
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

    public int getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(int typeUser) {
        this.typeUser = typeUser;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "administratorId=" + administratorId +
                ", permission=" + permission +
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
        Administrator that = (Administrator) o;
        return administratorId == that.administratorId &&
                permission == that.permission &&
                userId == that.userId &&
                typeUser == that.typeUser &&
                Objects.equals(user, that.user) &&
                Objects.equals(name, that.name) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(administratorId, permission, userId, user, name, phone, mail, typeUser);
    }
}
