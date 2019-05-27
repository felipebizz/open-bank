package com.openbank.model;

import lombok.Data;

import java.util.List;

/**
 * Data mode for Account
 */
@Data
public class Account {

    private List<Transaction> transactions;
}
