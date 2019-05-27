package com.openbank.processor;

import com.openbank.helper.ApplicationHelper;
import com.openbank.model.Account;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Class responsible for convert the result from {@link Account} to pattern expected
 */
public class AccountMappingProcessor implements Processor {

    private static final Logger log = LoggerFactory.getLogger(AccountMappingProcessor.class);

    /**
     * @param exchange Used to To support various message exchange patterns
     */
    @Override
    public void process(Exchange exchange) {

        log.info("get all transactions from body");
        Account account = exchange.getIn().getBody(Account.class);

        log.info("set New transactions into body");
        ApplicationHelper.mapperTransactionsToBackBaseField(exchange, account);
    }
}
