package com.br.ifcommunity.model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private String userId;
    private int studentId;
    private int teacherId;
    private int administratorId;
    private String user;
    private String password;
    private String name;
    private String phone;
    private String mail;
    private int typeUser;
    private int period;
    private String enrolledNumber;
    private int permission;
    private String website;
    private String photoHash;
    private String registerDate;
    private String updateDate;
    private String ip;


    public User() {
    }

    // ERROR CONSTRUCTOR
    public User(String name) {
        this.name = name;
    }

    // Login constructor
    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    // Update constructor
    public User(String userId, String name, String phone, String mail) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
    }

    // STUDENT constructor
    // Register constructor
    public User(String user, String password, String name, String phone, String mail, int typeUser, int period, String enrolledNumber) {
        this.user = user;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.typeUser = typeUser;
        this.period = period;
        this.enrolledNumber = enrolledNumber;
    }

    // Dashboard constructor
    public User(String userId, int studentId, String user, String name, String phone, String mail, int typeUser, int period, String enrolledNumber, String photoHash, String registerDate, String updateDate) {
        this.userId = userId;
        this.studentId = studentId;
        this.user = user;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.typeUser = typeUser;
        this.period = period;
        this.enrolledNumber = enrolledNumber;
        this.photoHash = photoHash;
        this.registerDate = registerDate;
        this.updateDate = updateDate;
    }

    // ADMIN constructor
    // Register constructor
    public User(String user, String password, String name, String phone, String mail, int typeUser, int period, int permission) {
        this.user = user;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.typeUser = typeUser;
        this.period = period;
        this.permission = permission;
    }

    // Dashboard constructor
    public User(int administratorId, String user, String name, String phone, String mail, int typeUser, int permission, String website) {
        this.administratorId = administratorId;
        this.user = user;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.typeUser = typeUser;
        this.permission = permission;
        this.website = website;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId(int administratorId) {
        this.administratorId = administratorId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
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

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhotoHash() {
        return photoHash;
    }

    public void setPhotoHash(String photoHash) {
        this.photoHash = photoHash;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", studentId=" + studentId +
                ", teacherId=" + teacherId +
                ", administratorId=" + administratorId +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                ", typeUser=" + typeUser +
                ", period=" + period +
                ", enrolledNumber='" + enrolledNumber + '\'' +
                ", permission=" + permission +
                ", website='" + website + '\'' +
                ", photoHash='" + photoHash + '\'' +
                ", registerDate='" + registerDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user1 = (User) o;
        return studentId == user1.studentId &&
                teacherId == user1.teacherId &&
                administratorId == user1.administratorId &&
                typeUser == user1.typeUser &&
                period == user1.period &&
                permission == user1.permission &&
                Objects.equals(userId, user1.userId) &&
                Objects.equals(user, user1.user) &&
                Objects.equals(password, user1.password) &&
                Objects.equals(name, user1.name) &&
                Objects.equals(phone, user1.phone) &&
                Objects.equals(mail, user1.mail) &&
                Objects.equals(enrolledNumber, user1.enrolledNumber) &&
                Objects.equals(website, user1.website) &&
                Objects.equals(photoHash, user1.photoHash) &&
                Objects.equals(registerDate, user1.registerDate) &&
                Objects.equals(updateDate, user1.updateDate) &&
                Objects.equals(ip, user1.ip);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, studentId, teacherId, administratorId, user, password, name, phone, mail, typeUser, period, enrolledNumber, permission, website, photoHash, registerDate, updateDate, ip);
    }
}
