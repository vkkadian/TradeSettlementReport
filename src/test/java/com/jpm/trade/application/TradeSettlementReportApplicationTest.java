package com.jpm.trade.application;

import com.jpm.trade.model.TradeCall;
import com.jpm.trade.model.TradeOrder;
import com.jpm.trade.report.process.TradeReportProcessor;
import com.jpm.trade.report.produce.TradeReportProducer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.mockito.Mockito.mock;

public class TradeSettlementReportApplicationTest {
    private TradeReportProcessor tradeReportProcessor = mock(TradeReportProcessor.class);
    private TradeReportProducer tradeReportProducer = mock(TradeReportProducer.class);
    private List<TradeOrder> tradeOrders;

    @Before
    public void setUp() {
        tradeOrders = new ArrayList<>();
        //client data
        tradeOrders.add(new TradeOrder("Foo", TradeCall.B, 0.50d, Currency.getInstance("SGD"), LocalDate.parse("19 Jul 2018", DateTimeFormatter.ofPattern("dd MMM yyyy")), LocalDate.now(), 200, 100.25d));
        tradeOrders.add(new TradeOrder("Foo", TradeCall.B, 0.50d, Currency.getInstance("SGD"), LocalDate.parse("19 Jul 2018", DateTimeFormatter.ofPattern("dd MMM yyyy")), LocalDate.now(), 100, 110.25d));
        tradeOrders.add(new TradeOrder("Foo", TradeCall.S, 0.50d, Currency.getInstance("SGD"), LocalDate.parse("20 Jul 2018", DateTimeFormatter.ofPattern("dd MMM yyyy")), LocalDate.now(), 300, 100.5d));
        tradeOrders.add(new TradeOrder("Foo", TradeCall.S, 0.50d, Currency.getInstance("SGD"), LocalDate.parse("20 Jul 2018", DateTimeFormatter.ofPattern("dd MMM yyyy")), LocalDate.now(), 200, 110.25d));

        tradeOrders.add(new TradeOrder("Bar", TradeCall.B, 0.22d, Currency.getInstance("AED"), LocalDate.parse("19 Jul 2018", DateTimeFormatter.ofPattern("dd MMM yyyy")), LocalDate.now(), 300, 160.5d));
        tradeOrders.add(new TradeOrder("Bar", TradeCall.S, 0.22d, Currency.getInstance("AED"), LocalDate.parse("20 Jul 2018", DateTimeFormatter.ofPattern("dd MMM yyyy")), LocalDate.now(), 450, 150.5d));
        tradeOrders.add(new TradeOrder("Bar", TradeCall.S, 0.22d, Currency.getInstance("AED"), LocalDate.parse("20 Jul 2018", DateTimeFormatter.ofPattern("dd MMM yyyy")), LocalDate.now(), 350, 160.5d));

    }

    @Test
    public void generateReportTest() {
        TradeSettlementReportApplication tradeSettlementReportApplication = new TradeSettlementReportApplication(tradeReportProcessor, tradeReportProducer);
        tradeSettlementReportApplication.generateReport();
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTest_ProcessorNull() {
        new TradeSettlementReportApplication(null, tradeReportProducer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTest_ProducerNull() {
        new TradeSettlementReportApplication(tradeReportProcessor, null);

    }

    @After
    public void tearDown() {
        tradeOrders.clear();
    }
}