package com.jpm.trade.report.filter;

import com.jpm.trade.model.TradeOrder;

public interface TradeOrderFilter {
    boolean filter(TradeOrder tradeOrder);
}
