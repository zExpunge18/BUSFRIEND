package com.example.bus;

public class busList {

    int busImage;

    String busType, busName, busLocation, busSeat, busPrice, busTime;

    public busList(int busImage, String busType, String busName, String busLocation, String busSeat, String busPrice, String busTime) {

        this.busImage = busImage;
        this.busType = busType;
        this.busName = busName;
        this.busLocation = busLocation;
        this.busSeat = busSeat;
        this.busPrice = busPrice;
        this.busTime = busTime;
    }

    public int getBusImage() {
        return busImage;
    }

    public String getBusType() {
        return busType;
    }

    public String getBusName() {
        return busName;
    }

    public String getBusLocation() {
        return busLocation;
    }

    public String getBusSeat() {
        return busSeat;
    }

    public String getBusPrice() {
        return busPrice;
    }

    public String getBusTime() {
        return busTime;
    }
}
