package com.openbank.model;

import lombok.Data;

@Data
public class OtherAccount {

    private String id;
    private Holder holder;
    private String number;
    private Metadata metadata;

    public OtherAccount() {
    }

    @Override
    public String toString() {
        return "OtherAccount [id=" + id + ", number=" + number + "]";
    }
}
