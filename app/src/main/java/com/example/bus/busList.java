package com.example.bus;

public class busList {

    int id, busSeat, busPrice;
    String busType, busName, destination_from, destination_to,
            busTime, driver_responsible, plate_no, operator, date;

    public busList(int id, int busSeat, int busPrice, String busType, String busName, String destination_from, String destination_to, String busTime, String driver_responsible, String plate_no, String operator, String date) {
        this.id = id;
        this.busSeat = busSeat;
        this.busPrice = busPrice;
        this.busType = busType;
        this.busName = busName;
        this.destination_from = destination_from;
        this.destination_to = destination_to;
        this.busTime = busTime;
        this.driver_responsible = driver_responsible;
        this.plate_no = plate_no;
        this.operator = operator;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBusSeat() {
        return busSeat;
    }

    public void setBusSeat(int busSeat) {
        this.busSeat = busSeat;
    }

    public int getBusPrice() {
        return busPrice;
    }

    public void setBusPrice(int busPrice) {
        this.busPrice = busPrice;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getDestination_from() {
        return destination_from;
    }

    public void setDestination_from(String destination_from) {
        this.destination_from = destination_from;
    }

    public String getDestination_to() {
        return destination_to;
    }

    public void setDestination_to(String destination_to) {
        this.destination_to = destination_to;
    }

    public String getBusTime() {
        return busTime;
    }

    public void setBusTime(String busTime) {
        this.busTime = busTime;
    }

    public String getDriver_responsible() {
        return driver_responsible;
    }

    public void setDriver_responsible(String driver_responsible) {
        this.driver_responsible = driver_responsible;
    }

    public String getPlate_no() {
        return plate_no;
    }

    public void setPlate_no(String plate_no) {
        this.plate_no = plate_no;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
