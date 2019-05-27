package com.openbank.processor;

import com.openbank.helper.ApplicationHelper;
import com.openbank.model.Account;
import com.openbank.util.CommonConstant;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class Responsible for processing total amount value by Transaction and
 * set result in the Body Exchange.
 */
public class SumTotalAmountProcessor implements Processor {

    private static final Logger log = LoggerFactory.getLogger(SumTotalAmountProcessor.class);

    /**
     * @param exchange Used to To support various message exchange patterns
     */
    @Override
    public void process(Exchange exchange) {

        log.info("get filter object from header");
        String type = (String) exchange.getIn().getHeader(CommonConstant.TRANSACTION_FILTER);

        log.info("get all transactions from body");
        Account account = exchange.getIn().getBody(Account.class);

        double totalAmount = 0.0;

        if (type != null)
            totalAmount = ApplicationHelper.sumTotalAmountByType(account, type);

        log.info("set filtered transactions into body");
        ApplicationHelper.setExchangeBody(exchange, account, totalAmount);
    }

}
