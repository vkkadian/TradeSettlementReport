package com.jpm.trade.report.produce;

import com.jpm.trade.model.TradeCall;

import java.util.Map;

public interface TradeReportProducer {
    void produce(Map<TradeCall, Map<String, Double>> tradeReportMap) throws TradeReportProducerException;
}
