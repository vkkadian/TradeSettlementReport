package com.jpm.trade.data;

import com.jpm.trade.model.TradeCall;
import com.jpm.trade.model.TradeOrder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

public enum TradeOrderManager {
    INSTANCE;
    private List<TradeOrder> tradeOrders = new ArrayList<>();

    TradeOrderManager() {
        //client data
        tradeOrders.add(new TradeOrder("Foo", TradeCall.B, 0.50d, Currency.getInstance("SGD"), LocalDate.parse("19 Jul 2018", DateTimeFormatter.ofPattern("dd MMM yyyy")), LocalDate.now(), 200, 100.25d));
        tradeOrders.add(new TradeOrder("Foo", TradeCall.B, 0.50d, Currency.getInstance("SGD"), LocalDate.parse("19 Jul 2018", DateTimeFormatter.ofPattern("dd MMM yyyy")), LocalDate.now(), 100, 110.25d));
        tradeOrders.add(new TradeOrder("Foo", TradeCall.S, 0.50d, Currency.getInstance("SGD"), LocalDate.parse("20 Jul 2018", DateTimeFormatter.ofPattern("dd MMM yyyy")), LocalDate.now(), 300, 100.5d));
        tradeOrders.add(new TradeOrder("Foo", TradeCall.S, 0.50d, Currency.getInstance("SGD"), LocalDate.parse("20 Jul 2018", DateTimeFormatter.ofPattern("dd MMM yyyy")), LocalDate.now(), 200, 110.25d));

        tradeOrders.add(new TradeOrder("Bar", TradeCall.B, 0.22d, Currency.getInstance("AED"), LocalDate.parse("19 Jul 2018", DateTimeFormatter.ofPattern("dd MMM yyyy")), LocalDate.now(), 300, 160.5d));
        tradeOrders.add(new TradeOrder("Bar", TradeCall.S, 0.22d, Currency.getInstance("AED"), LocalDate.parse("20 Jul 2018", DateTimeFormatter.ofPattern("dd MMM yyyy")), LocalDate.now(), 450, 150.5d));
        tradeOrders.add(new TradeOrder("Bar", TradeCall.S, 0.22d, Currency.getInstance("AED"), LocalDate.parse("20 Jul 2018", DateTimeFormatter.ofPattern("dd MMM yyyy")), LocalDate.now(), 350, 160.5d));

    }

    public static TradeOrderManager getInstance() {
        return INSTANCE;
    }

    public void addTradeOrder(TradeOrder tradeOrder) {
        if (tradeOrder == null) {
            throw new IllegalArgumentException("TradeOrder cannot be null");
        }
        tradeOrders.add(tradeOrder);
    }

    public List<TradeOrder> getTradeOrders() {
        return Collections.unmodifiableList(tradeOrders);
    }

    public void clear() {
        tradeOrders.clear();
    }
}
