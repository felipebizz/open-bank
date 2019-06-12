package com.openbank.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openbank.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertNotNull;

/**
 * Test suite for the {@link TransactionController} class.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:applicationContext.xml")
public class ControllerTest {

    private static final String TRANSACTION_TYPE = "SANDBOX_TAN";

    @Autowired
    private TransactionController transactionController;

    private ObjectMapper objectMapper;

    private File JSONFile;


    @Before
    public void setup() throws Exception {

        objectMapper = new ObjectMapper();
        JSONFile = new ClassPathResource("transactions-mock.json").getFile();
    }

    //@Test
    public void mustReturn_OnlyTransactionsByTransactionType() throws IOException {

        String response = transactionController.getTransactionsByTransactionType(TRANSACTION_TYPE);

        assertNotNull(response);

        ArrayList backBaseFieldList = objectMapper.readValue(response, ArrayList.class);
        backBaseFieldList.forEach(o ->
                Assert.assertEquals(TRANSACTION_TYPE, ((LinkedHashMap) o).get("transactionType")));
    }

    //@Test
    public void mustReturn_AllTransactions() throws IOException {

        ResponseEntity<?> response = transactionController.getAllTransactions();

        assertNotNull(response);

        ArrayList backBaseFieldList = objectMapper.readValue(response.toString(), ArrayList.class);

        Assert.assertEquals(50, backBaseFieldList.size());
    }

    //@Test
    public void mustReturn_TotalAmountByTransactionType() {

        String response = transactionController.getTotalAmountByTransactionType(TRANSACTION_TYPE);

        assertNotNull(response);

        Assert.assertEquals("{ \"totalAmount\" : 10.0}", response);
    }

    /**
     * @return Account
     * @throws IOException IOException
     */
    private Account stubAccount() {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(Files.readAllBytes(JSONFile.toPath()), Account.class);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
