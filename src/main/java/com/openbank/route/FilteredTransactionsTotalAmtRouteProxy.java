package com.openbank.route;

import com.openbank.util.CommonConstant;
import org.apache.camel.Body;
import org.apache.camel.Header;

public interface FilteredTransactionsTotalAmtRouteProxy {

    String getTotalAmountByTransationType(@Header(CommonConstant.TRANSACTION_FILTER) String type, @Body String body);
}
