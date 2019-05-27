package com.openbank.model;

import lombok.Data;

@Data
public class Value {

    private double amount;
    private String currency;

    @Override
    public String toString() {
        return "Value: [amount = " + amount + ", currency = " + currency + "]";
    }
}

