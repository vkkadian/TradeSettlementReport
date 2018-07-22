package com.jpm.trade.application.context;

import com.jpm.trade.report.TradeReportApplicationContextException;

public interface TradeReportApplicationContext {
    void initialize() throws TradeReportApplicationContextException;
}
