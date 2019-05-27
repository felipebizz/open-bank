package com.openbank.util;

public class CommonConstant {

    public static final String TRANSACTION_FILTER = "transactiontype";
    public static final String TRANSACTION_TYPE = "sandbox-payment";

    public static final String ALL_TRANSACTIONS_DIRECT = "direct://allTransactions";
    public static final String TRANSACTIONS_DIRECT = "direct://filteredTransactions";
    public static final String TRANSACTIONS_TOTAL_AMOUNT_DIRECT = "direct://filteredTransactionsTotalAmt";

    public static final String ALL_TRANSACTIONS = "/rest/transactions";
    public static final String TRANSACTIONS_BY_TYPE = "/rest/transactions/{transactiontype}";
    public static final String TRANSACTIONS_TOTAL_AMOUNT_BY_TYPE = "/rest/transaction-total-amount/{transactiontype}";

    private CommonConstant() {
    }

}
