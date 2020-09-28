package com.startandroid.trashstatf;

public class Order {
    String cardType, cardName, city, street, home, korp, flat, status, entrance;



    public Order(String cardType, String cardName, String city, String street, String home, String korp, String entrance,  String status, String flat) {
        this.cardType = cardType;
        this.cardName = cardName;
        this.city = city;
        this.street = street;
        this.home = home;
        this.korp = korp;
        this.flat = flat;
        this.status = status;
        this.entrance = entrance;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getKorp() {
        return korp;
    }

    public void setKorp(String korp) {
        this.korp = korp;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }
}
