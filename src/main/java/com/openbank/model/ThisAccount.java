package com.openbank.model;

import lombok.Data;

@Data
public class ThisAccount {

    private String id;

    @Override
    public String toString() {
        return "ThisAccount [id=" + id + "]";
    }
}
