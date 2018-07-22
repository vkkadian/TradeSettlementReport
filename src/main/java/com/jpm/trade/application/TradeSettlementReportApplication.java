package com.jpm.trade.application;

import com.jpm.trade.data.TradeOrderManager;
import com.jpm.trade.model.TradeCall;
import com.jpm.trade.report.TradeReportApplicationException;
import com.jpm.trade.report.process.TradeReportProcessor;
import com.jpm.trade.report.produce.TradeReportProducer;

import java.util.Map;

public class TradeSettlementReportApplication implements TradeReportApplication {
    private TradeReportProcessor tradeReportProcessor;
    private TradeReportProducer tradeReportProducer;

    public TradeSettlementReportApplication(TradeReportProcessor tradeReportProcessor, TradeReportProducer tradeReportProducer) {
        if (tradeReportProcessor == null) {
            throw new IllegalArgumentException("TradeReportProcessor cannot be null");
        }
        if (tradeReportProducer == null) {
            throw new IllegalArgumentException("TradeReportProducer cannot be null");
        }
        this.tradeReportProcessor = tradeReportProcessor;
        this.tradeReportProducer = tradeReportProducer;
    }

    public void generateReport() {
        try {
            Map<TradeCall, Map<String, Double>> tradeReportMap = tradeReportProcessor.process(TradeOrderManager.getInstance().getTradeOrders());
            tradeReportProducer.produce(tradeReportMap);
        } catch (Throwable t) {
            throw new TradeReportApplicationException(t.getMessage(), t.getCause());
        }
    }
}
