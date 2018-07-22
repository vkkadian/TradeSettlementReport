package com.jpm.trade.report.filter;

import com.jpm.trade.application.context.TradeSettlementReportApplicationContext;
import com.jpm.trade.model.TradeOrder;

import java.time.LocalDate;

public final class TradeSettlementReportFilter implements TradeOrderFilter {
    private static final TradeSettlementReportFilter INSTANCE = new TradeSettlementReportFilter();

    private TradeSettlementReportFilter() {
    }

    public static TradeSettlementReportFilter getInstance() {
        return INSTANCE;
    }

    public final boolean filter(TradeOrder tradeOrder) {
        return !TradeSettlementReportApplicationContext.getInstance().getCurrencyWeekDayMap().get(tradeOrder.getCurrency()).contains(tradeOrder.getSettlementDate().getDayOfWeek()) && tradeOrder.getSettlementDate().isEqual(LocalDate.now());
    }
}
