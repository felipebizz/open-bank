package com.openbank.model;

import lombok.Data;

@Data
public class Transaction {

    private String id;
    private Detail details;
    private OtherAccount other_account;
    private ThisAccount this_account;

    @Override
    public String toString() {
        return "Transaction [id=" + id + ", details=" + details + ", other_account=" + other_account + "]";
    }
}
