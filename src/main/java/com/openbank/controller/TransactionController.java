package com.openbank.controller;

import com.openbank.route.AllTransactionRouteProxy;
import com.openbank.route.FilteredTransactionsRouteProxy;
import com.openbank.route.FilteredTransactionsTotalAmtRouteProxy;
import com.openbank.util.CommonConstant;
import org.apache.camel.Produce;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TransactionController {

    @Produce(uri = CommonConstant.ALL_TRANSACTIONS_DIRECT)
    private AllTransactionRouteProxy allTransactionRoute;

    @Produce(uri = CommonConstant.TRANSACTIONS_DIRECT)
    private FilteredTransactionsRouteProxy filteredTransactionsRoute;

    @Produce(uri = CommonConstant.TRANSACTIONS_TOTAL_AMOUNT_DIRECT)
    private FilteredTransactionsTotalAmtRouteProxy filteredTransactionsTotalAmtRoute;

    public TransactionController() {
    }

    @RequestMapping(value = CommonConstant.ALL_TRANSACTIONS, method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String getAllTransactions() {
        return allTransactionRoute.getAllTransactions();
    }


    @RequestMapping(value = CommonConstant.TRANSACTIONS_BY_TYPE, method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody()
    String getTransactionsByTransactionType(@PathVariable(CommonConstant.TRANSACTION_FILTER) String transactiontype) {
        return filteredTransactionsRoute.getTransactionsByTransactionType(transactiontype, null);
    }


    @RequestMapping(value = CommonConstant.TRANSACTIONS_TOTAL_AMOUNT_BY_TYPE, method = RequestMethod.GET,
            produces = "application/json; charset=UTF-8")
    public @ResponseBody
    String getTotalAmountByTransactionType(@PathVariable(CommonConstant.TRANSACTION_FILTER) String transactiontype) {
        return filteredTransactionsTotalAmtRoute.getTotalAmountByTransationType(transactiontype, null);
    }

}
