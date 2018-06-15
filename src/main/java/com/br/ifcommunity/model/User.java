package com.br.ifcommunity.model;

public class User {
    private int id;
    private String user;
    private String password;
    private String name;
    private String phone;
    private String mail;
    private String typeUser;

    public User(int id, String user, String password, String name, String phone, String mail, String typeUser) {
        this.id = id;
        this.user = user;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.typeUser = typeUser;
    }




}
