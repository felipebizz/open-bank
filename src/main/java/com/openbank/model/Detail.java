package com.openbank.model;

import lombok.Data;

@Data
public class Detail {

    private String description;
    private Value value;
    private String type;
    private String posted;
    private String completed;

    @Override
    public String toString() {
        return "Detail [description=" + description + ", value=" + value + ", type=" + type + ", posted=" + posted
                + ", completed=" + completed + "]";
    }
}
