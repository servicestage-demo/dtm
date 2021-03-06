package com.huawei.dtm.sample.booking.booking;

import com.huawei.dtm.sample.booking.api.HotelService;
import com.huawei.dtm.sample.booking.api.PlaneService;
import com.huawei.middleware.dtm.client.annotations.DTMTxBegin;
import com.huawei.middleware.dtm.client.context.DTMContext;
import org.apache.servicecomb.provider.pojo.RpcReference;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Path;

@RestSchema(schemaId = "booking")
@Path(value = "booking")
public class BookingConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookingConsumer.class);

    @RpcReference(microserviceName = "plane", schemaId = "plane")
    private PlaneService planeService;

    @RpcReference(microserviceName = "hotel", schemaId = "hotel")
    private HotelService hotelService;

    @DTMTxBegin(appName = "booking")
    @Path(value = "/book")
    public void book() {
        LOGGER.info("{} start booking...", DTMContext.getDTMContext().getGlobalTxId());
        planeService.bookTicket();
        hotelService.bookRoom();
        LOGGER.info("{} booking succeeded", DTMContext.getDTMContext().getGlobalTxId());
    }
}
