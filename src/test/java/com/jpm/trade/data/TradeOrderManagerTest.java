package com.jpm.trade.data;

import com.jpm.trade.model.TradeCall;
import com.jpm.trade.model.TradeOrder;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

import static org.junit.Assert.*;

public class TradeOrderManagerTest {

    @Test
    public void getInstance() {
        TradeOrderManager tradeOrderManager1 = TradeOrderManager.getInstance();
        TradeOrderManager tradeOrderManager2 = TradeOrderManager.getInstance();
        assertSame(tradeOrderManager1, tradeOrderManager2);
    }

    @Test
    public void addTradeOrder() {
        TradeOrderManager tradeOrderManager = TradeOrderManager.getInstance();
        TradeOrder tradeOrder = new TradeOrder("Foo", TradeCall.B, 0.50d, Currency.getInstance("SGD"), LocalDate.parse("19 Jul 2018", DateTimeFormatter.ofPattern("dd MMM yyyy")), LocalDate.now(), 200, 100.25d);
        int size1 = tradeOrderManager.getTradeOrders().size();
        tradeOrderManager.addTradeOrder(tradeOrder);
        int size2 = tradeOrderManager.getTradeOrders().size();
        assertEquals(size2, size1 + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullTradeOrder() {
        TradeOrderManager tradeOrderManager = TradeOrderManager.getInstance();
        tradeOrderManager.addTradeOrder(null);
    }

    @Test
    public void getTradeOrders() {
        TradeOrderManager tradeOrderManager = TradeOrderManager.getInstance();
        assertTrue(tradeOrderManager.getTradeOrders().isEmpty());
    }

    @Test
    public void clear() {
        TradeOrderManager tradeOrderManager = TradeOrderManager.getInstance();
        TradeOrder tradeOrder = new TradeOrder("Foo", TradeCall.B, 0.50d, Currency.getInstance("SGD"), LocalDate.parse("19 Jul 2018", DateTimeFormatter.ofPattern("dd MMM yyyy")), LocalDate.now(), 200, 100.25d);
        tradeOrderManager.addTradeOrder(tradeOrder);
        int size1 = tradeOrderManager.getTradeOrders().size();
        tradeOrderManager.clear();
        int size2 = tradeOrderManager.getTradeOrders().size();
        assertNotEquals(size1, size2);
    }
}