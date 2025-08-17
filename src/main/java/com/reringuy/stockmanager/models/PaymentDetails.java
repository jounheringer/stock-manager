package com.reringuy.stockmanager.models;

public class PaymentDetails {
    private final Payment payment;
    private final Users user;
    private final Products product;

    public PaymentDetails(Payment payment, Users user, Products product) {
        this.payment = payment;
        this.user = user;
        this.product = product;
    }

    public Payment getPayment() {
        return payment;
    }

    public Users getUser() {
        return user;
    }

    public Products getProduct() {
        return product;
    }
}
