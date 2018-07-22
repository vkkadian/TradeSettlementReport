package com.jpm.trade.model;

public enum TradeCall {
    B("Buy"),
    S("Sell");
    private final String value;

    TradeCall(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
