package com.jpm.trade.report.process;

import com.jpm.trade.model.TradeCall;
import com.jpm.trade.model.TradeOrder;

import java.util.List;
import java.util.Map;

public interface TradeReportProcessor {
    Map<TradeCall, Map<String, Double>> process(List<TradeOrder> tradeOrderList) throws TradeReportProcessorException;
}
