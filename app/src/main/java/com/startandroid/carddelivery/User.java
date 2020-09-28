package com.startandroid.carddelivery;

public class User {
    private String name, email, pass, phone, surname, passport;

    public User(){}



    public User(String name, String email, String pass, String phone, String surname, String passport){
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.phone = phone;
        this.surname = surname;
        this.passport = passport;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPass(){
        return pass;
    }

    public void setPass(String pass){
        this.pass = pass;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }






}
