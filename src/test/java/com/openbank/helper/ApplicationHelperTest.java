package com.openbank.helper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openbank.model.Account;
import com.openbank.util.CommonConstant;
import org.apache.camel.Exchange;
import org.apache.camel.Route;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Test suite for the {@link ApplicationHelper}
 */
public class ApplicationHelperTest extends CamelSpringTestSupport {

    private File JSONFile;
    private Account account;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        JSONFile = new ClassPathResource("transactions-mock.json").getFile();
        account = stubAccount();

    }


    @Test
    public void test_sumTotalAmountByType() {
        ApplicationHelper.sumTotalAmountByType(account, CommonConstant.TRANSACTION_TYPE);
        Assert.assertEquals(account.getTransactions().size(), 19);
    }

    @Test
    public void test_mapperTransactionsToBackBaseField() {

        Route route = context.getRoute("all-transaction-route");
        Exchange exchange = route.getEndpoint().createExchange();

        ApplicationHelper.mapperTransactionsToBackBaseField(exchange, account);
        Assert.assertTrue(exchange.getOut().getBody().toString().contains("accountId"));
        Assert.assertTrue(exchange.getOut().getBody().toString().contains("transactionType"));
        Assert.assertTrue(exchange.getOut().getBody().toString().contains("description"));
    }


    @Test
    public void test_setExchangeBody() {

        Route route = context.getRoute("all-transaction-route");

        Exchange exchange = route.getEndpoint().createExchange();
        ApplicationHelper.setExchangeBody(exchange, account, 100d);
        Assert.assertEquals("{ \"totalAmount\" : 100.0}", exchange.getOut().getBody());
    }

    /**
     * @return Account
     * @throws IOException IOException
     */
    private Account stubAccount() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(Files.readAllBytes(JSONFile.toPath()), Account.class);
    }


    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("applicationContext.xml");
    }
}