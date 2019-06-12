package com.openbank.route;

import org.apache.camel.Route;
import org.apache.camel.model.FromDefinition;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;

public class CamelRoutesTest extends CamelSpringTestSupport {

    private static final int CONFIGURED_ROUTES = 6;

    private List<Route> routes;

    @Override
    protected AbstractApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        routes = context.getRoutes();
    }

    @Test
    public void checkRoutesAmount() {
        Assert.assertEquals(CONFIGURED_ROUTES, routes.size());
    }

    @Test
    public void checkRoutesDefinitions() {
        List<RouteDefinition> routeDefinitions = context.getRouteDefinitions();
        HashMap<String, String> inputRoutes = new HashMap<>();

        for (RouteDefinition routeDefinition : routeDefinitions) {
            for (FromDefinition input : routeDefinition.getInputs()) {
                inputRoutes.put(input.getUri(), "");
            }
        }

        Assert.assertTrue(inputRoutes.containsKey("direct:allTransactions"));
        Assert.assertTrue(inputRoutes.containsKey("direct:filteredTransactions"));
        Assert.assertTrue(inputRoutes.containsKey("direct:filteredTransactionsTotalAmt"));
        Assert.assertTrue(inputRoutes.containsKey("direct:filteredTransactions"));
        Assert.assertTrue(inputRoutes.containsKey("direct:unmarshal"));
        Assert.assertTrue(inputRoutes.containsKey("direct:marshal"));

    }
}

