package com.example.bus;

public class TransactionHistory {
    int id, busPrice, amount, payment, numberOfTicket;

    public TransactionHistory(int id, int busPrice, int amount, int payment, int numberOfTicket) {
        this.id = id;
        this.busPrice = busPrice;
        this.amount = amount;
        this.payment = payment;
        this.numberOfTicket = numberOfTicket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBusPrice() {
        return busPrice;
    }

    public void setBusPrice(int busPrice) {
        this.busPrice = busPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getNumberOfTicket() {
        return numberOfTicket;
    }

    public void setNumberOfTicket(int numberOfTicket) {
        this.numberOfTicket = numberOfTicket;
    }




}
