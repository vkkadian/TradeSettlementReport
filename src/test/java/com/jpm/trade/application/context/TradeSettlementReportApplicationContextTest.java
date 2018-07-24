package com.jpm.trade.application.context;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;

public class TradeSettlementReportApplicationContextTest {
    private TradeSettlementReportApplicationContext tradeSettlementReportApplicationContext = TradeSettlementReportApplicationContext.getInstance();

    @Test
    public void getInstance() {
        assertNotNull(tradeSettlementReportApplicationContext);
    }

    @Test
    public void getCurrencyWeekDayMap() {
        assertTrue(!tradeSettlementReportApplicationContext.getCurrencyWeekDayMap().isEmpty());
    }

    @Test
    public void getLocale() {
        assertEquals(tradeSettlementReportApplicationContext.getLocale(), Locale.US);
    }

    @Test
    public void getSunThuWeekDayCurrencyList() {
        assertTrue(!tradeSettlementReportApplicationContext.getSunThuWeekDayCurrencyList().isEmpty());
    }

    @Test
    public void getMonFriNonWorkingDaysList() {
        assertTrue(!tradeSettlementReportApplicationContext.getMonFriNonWorkingDaysList().isEmpty());
    }

    @Test
    public void getSunThuNonWorkingDaysList() {
        assertTrue(!tradeSettlementReportApplicationContext.getSunThuNonWorkingDaysList().isEmpty());
    }

    @Test
    public void getReportWriterlist() {
        assertTrue(!tradeSettlementReportApplicationContext.getReportWriterlist().isEmpty());
    }

    @Test
    public void initialize() {
        tradeSettlementReportApplicationContext.initialize();
        assertTrue(!tradeSettlementReportApplicationContext.getCurrencyWeekDayMap().isEmpty());
    }
}