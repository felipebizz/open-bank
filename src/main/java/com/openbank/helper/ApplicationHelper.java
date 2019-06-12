package com.openbank.helper;

import com.openbank.model.Account;
import com.openbank.model.BackBaseField;
import com.openbank.model.Transaction;
import com.openbank.util.CommonConstant;
import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible to execute  some business Rules
 */
public class ApplicationHelper {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationHelper.class);

    public ApplicationHelper() {
    }

    /**
     * @param account Account with transactions
     * @param type    transaction Type
     * @return totalAmount
     */
    public static double sumTotalAmountByType(Account account, String type) {

        double totalAmmount = 0.0;

        ArrayList<Transaction> filterTransactions = new ArrayList<>();

        for (Transaction transaction : account.getTransactions()) {

            if (transaction != null && transaction.getDetails() != null
                    && transaction.getDetails().getType() != null
                    && transaction.getDetails().getType().equals(type)) {

                if (transaction.getDetails().getValue() != null) {
                    totalAmmount += transaction.getDetails().getValue().getAmount();
                    filterTransactions.add(transaction);
                }
            }
        }

        account.setTransactions(filterTransactions);

        logger.info("set total sum by Transaction Type");

        return totalAmmount;
    }

    /**
     * @param exchange    Used to To support various message exchange patterns
     * @param account     Account with transactions
     * @param totalAmount Total  Amount value by type
     */
    public static void setExchangeBody(Exchange exchange, Account account, double totalAmount) {

        if (exchange.getFromEndpoint().getEndpointUri().equals(CommonConstant.TRANSACTIONS_DIRECT)) {
            exchange.getOut().setBody(account);

            logger.info("set filter transactions into body");

        } else {

            exchange.getOut().setBody("{ \"totalAmount\" : " + totalAmount + "}");
            logger.info("set total amount of filtered transactions into body");
        }
    }

    /**
     * Method responsible to Map {@link Transaction} to {@link BackBaseField}
     *
     * @param exchange Used to To support various message exchange patterns
     * @param account  Account with transactions
     */
    public static void mapperTransactionsToBackBaseField(Exchange exchange, Account account) {

        logger.info("converting transactions to BackbaseField into body");

        List<Transaction> transactions = account.getTransactions();
        ArrayList<BackBaseField> backBaseFieldsList = new ArrayList<>();
        BackBaseField backBaseField = new BackBaseField();

        transactions.forEach(transaction ->
                backBaseFieldsList.add(backBaseField.mapTransactionToBackBaseField(transaction)));

        exchange.getOut().setBody(backBaseFieldsList);
    }
}
