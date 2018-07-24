package com.jpm.trade.report.format;

import com.jpm.trade.application.context.TradeSettlementReportApplicationContext;

import java.text.NumberFormat;

public final class TradeSettlementReportFormatter {
    private static final TradeSettlementReportFormatter INSTANCE = new TradeSettlementReportFormatter();

    private TradeSettlementReportFormatter() {
    }

    public static TradeSettlementReportFormatter getInstance() {
        return INSTANCE;
    }

    public final String formatAmount(double amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(TradeSettlementReportApplicationContext.getInstance().getLocale());
        return currencyFormatter.format(amount);
    }
}
