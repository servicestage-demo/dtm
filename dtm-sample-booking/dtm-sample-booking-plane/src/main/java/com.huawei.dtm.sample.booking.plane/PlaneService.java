package com.huawei.dtm.sample.booking.plane;

import com.huawei.middleware.dtm.client.context.DTMContext;
import com.huawei.middleware.dtm.client.tcc.annotations.DTMTccBranch;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestSchema(schemaId = "plane")
@RequestMapping(value = "/plane")
public class PlaneService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlaneService.class);

    @GetMapping(value = "/bookTicket")
    @DTMTccBranch(identifier = "plane", confirmMethod = "confirm", cancelMethod = "cancel")
    public void bookTicket() throws InterruptedException {
        LOGGER.info("book plane ticket");
    }

    public void confirm() {
        LOGGER.info("{} - {} confirm plane ticket",
                DTMContext.getDTMContext().getGlobalTxId(),
                DTMContext.getDTMContext().getBranchTxId());
    }

    public void cancel() {
        LOGGER.info("{} - {} cancel plane ticket",
                DTMContext.getDTMContext().getGlobalTxId(),
                DTMContext.getDTMContext().getBranchTxId());
    }
}
