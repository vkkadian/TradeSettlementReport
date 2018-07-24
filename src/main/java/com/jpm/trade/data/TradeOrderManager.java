package com.jpm.trade.data;

import com.jpm.trade.model.TradeOrder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum TradeOrderManager {
    INSTANCE;
    private List<TradeOrder> tradeOrders = new ArrayList<>();

    TradeOrderManager() {
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
