package com.jpm.trade.report.produce;

import com.jpm.trade.model.TradeCall;
import com.jpm.trade.report.format.TradeSettlementReportFormatter;
import com.jpm.trade.report.serialize.ReportWriter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TradeSettlementReportProducer implements TradeReportProducer {
    private static final String REPORT_HEADER_LINE = "***********************************************************************";
    private static final String REPORT_HEADER = "DAILY TRADE SETTLEMENT REPORT - SETTLEMENT DATE:" + DateTimeFormatter.ofPattern("dd MMM yyyy").format(LocalDate.now());
    private static final String REPORT_SECTION_LINE = "----------------------------------------------------------------------";
    private static final EnumMap<TradeCall, String> reportSectionMap = new EnumMap<>(TradeCall.class);

    static {
        reportSectionMap.put(TradeCall.S, "TOTAL INCOMING AMOUNT:");
        reportSectionMap.put(TradeCall.B, "TOTAL OUTGONG AMOUNT:");
    }

    private List<ReportWriter> reportWriterList;

    public TradeSettlementReportProducer(List<ReportWriter> reportWriterList) {
        if (reportWriterList == null || reportWriterList.isEmpty()) {
            throw new IllegalArgumentException("ReportWriterList is null or empty");
        }
        this.reportWriterList = reportWriterList;
    }

    @Override
    public void produce(Map<TradeCall, Map<String, Double>> tradeReportMap) throws TradeReportProducerException {
        try {
            if (tradeReportMap == null || tradeReportMap.size() == 0) {
                sendToReportWriters("No trades to settle");
                return;
            }
            sendToReportWriters(REPORT_HEADER_LINE);
            sendToReportWriters(REPORT_HEADER);
            sendToReportWriters(REPORT_HEADER_LINE);
            for (Map.Entry<TradeCall, Map<String, Double>> entry : tradeReportMap.entrySet()) {
                double totalAmount = entry.getValue().values().stream().mapToDouble(value -> value).sum();
                sendToReportWriters(REPORT_SECTION_LINE);
                sendToReportWriters(reportSectionMap.get(entry.getKey()) + TradeSettlementReportFormatter.formatAmount(totalAmount));
                sendToReportWriters(REPORT_SECTION_LINE);
                produceReport(entry.getValue(), entry.getKey());
            }
        } catch (Throwable t) {
            new TradeReportProducerException(t.getMessage(), t.getCause());
        }
    }

    private void produceReport(Map<String, Double> entityPriceMap, TradeCall callType) {
        entityPriceMap.entrySet().stream().forEach(new Consumer<Map.Entry<String, Double>>() {
            int i = 1;
            double amount = 0;

            @Override
            public void accept(Map.Entry<String, Double> entry) {
                int rank = amount == entry.getValue() ? i : i++;
                String reportLine = String.format("Rank:%d | Trade Entity:%s | Trade Call Type:%s | Amount:%s", rank, entry.getKey(), callType.toString(), TradeSettlementReportFormatter.formatAmount(entry.getValue()));
                sendToReportWriters(reportLine);
            }
        });
    }

    private void sendToReportWriters(String reportLine) {
        for (ReportWriter reportWriter : reportWriterList) {
            reportWriter.write(reportLine);
        }
    }
}
