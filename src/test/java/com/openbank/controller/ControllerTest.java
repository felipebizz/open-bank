package com.openbank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertNotNull;

/**
 * Test suite for the {@link TransactionController} class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ControllerTest {

    private static final String TRANSACTION_TYPE = "SANDBOX_TAN";

    @Autowired
    private TransactionController transactionController;

    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void mustReturn_OnlyTransactionsByTransactionType() throws IOException {

        String response = transactionController.getTransactionsByTransactionType(TRANSACTION_TYPE);

        assertNotNull(response);

        ArrayList backBaseFieldList = objectMapper.readValue(response, ArrayList.class);
        for (Object o : backBaseFieldList) {
            Assert.assertEquals(TRANSACTION_TYPE, ((LinkedHashMap) o).get("transactionType"));
        }
    }

    @Test
    public void mustReturn_AllTransactions() throws IOException {

        String response = transactionController.getAllTransactions();

        assertNotNull(response);

        ArrayList backBaseFieldList = objectMapper.readValue(response, ArrayList.class);

        Assert.assertEquals(50, backBaseFieldList.size());
    }

    @Test
    public void mustReturn_TotalAmountByTransactionType() {

        String response = transactionController.getTotalAmountByTransactionType(TRANSACTION_TYPE);

        assertNotNull(response);

        Assert.assertEquals("{ \"totalAmount\" : 10.0}", response);
    }
}
