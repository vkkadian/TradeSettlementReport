package com.jpm.trade.report.serialize;

public class ConsoleReportWriter implements ReportWriter {
    private static final ConsoleReportWriter INSTANCE = new ConsoleReportWriter();

    private ConsoleReportWriter() {
    }

    public static ConsoleReportWriter getInstance() {
        return INSTANCE;
    }

    @Override
    public void write(String reportLine) {
        System.out.println(reportLine);
    }
}