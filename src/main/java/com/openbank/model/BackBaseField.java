package com.openbank.model;

import lombok.Data;

/**
 * Class created to storage fields from {@link Transaction} after request..
 */
@Data
public class BackBaseField {

    private String id; //id
    private String accountId; //this_account.id
    private String counterpartyAccount; //other_account.number
    private String counterpartyName; //other_account.holder.name
    private String counterPartyLogoPath; //other_account.metadata.image_URL
    private double instructedAmount; // details.value.amount
    private String instructedCurrency; // details.value.currency
    private double transactionAmount;// details.value.amount
    private String transactionCurrency; // details.value.currency
    private String transactionType; //details.type
    private String description; // details.description

    /**
     * Mapper Transaction to BackField
     *
     * @param transaction Transaction
     * @return BackBaseField with transaction fields
     */
    public BackBaseField mapTransactionToBackBaseField(Transaction transaction) {
        BackBaseField backBaseField = new BackBaseField();
        backBaseField.setId(transaction.getId());
        backBaseField.setAccountId(transaction.getThis_account().getId());
        backBaseField.setCounterpartyAccount(transaction.getOther_account().getNumber());
        backBaseField.setCounterpartyName(transaction.getOther_account().getHolder().getName());
        backBaseField.setCounterPartyLogoPath(transaction.getOther_account().getMetadata().getImage_URL());
        backBaseField.setInstructedAmount(transaction.getDetails().getValue().getAmount());
        backBaseField.setInstructedCurrency(transaction.getDetails().getValue().getCurrency());
        backBaseField.setTransactionAmount(transaction.getDetails().getValue().getAmount());
        backBaseField.setTransactionCurrency(transaction.getDetails().getValue().getCurrency());
        backBaseField.setTransactionType(transaction.getDetails().getType());
        backBaseField.setDescription(transaction.getDetails().getDescription());
        return backBaseField;
    }

}

