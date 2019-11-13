package com.example.bus;

public class TransactionHistory {

    int id, payment, booked_seat, price;
    String fullname, destinationFrom, destinationTo, date, bus_name, email;

    public TransactionHistory(int id, int payment, int booked_seat, int price, String fullname, String destinationFrom, String destinationTo, String date, String bus_name, String email) {
        this.id = id;
        this.payment = payment;
        this.booked_seat = booked_seat;
        this.price = price;
        this.fullname = fullname;
        this.destinationFrom = destinationFrom;
        this.destinationTo = destinationTo;
        this.date = date;
        this.bus_name = bus_name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getBooked_seat() {
        return booked_seat;
    }

    public void setBooked_seat(int booked_seat) {
        this.booked_seat = booked_seat;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDestinationFrom() {
        return destinationFrom;
    }

    public void setDestinationFrom(String destinationFrom) {
        this.destinationFrom = destinationFrom;
    }

    public String getDestinationTo() {
        return destinationTo;
    }

    public void setDestinationTo(String destinationTo) {
        this.destinationTo = destinationTo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBus_name() {
        return bus_name;
    }

    public void setBus_name(String bus_name) {
        this.bus_name = bus_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }





}
