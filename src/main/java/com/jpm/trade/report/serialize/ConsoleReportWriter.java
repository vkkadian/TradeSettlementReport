package com.jpm.trade.report.serialize;

public class ConsoleReportWriter implements ReportWriter {
    private static final ConsoleReportWriter INSTANCE = new ConsoleReportWriter();

    private ConsoleReportWriter() {
    }

    public static ConsoleReportWriter getInstance() {
        return INSTANCE;
    }

    @Override
    public void write(String reportLine) throws ReportWriterException {
        try {
            System.out.println(reportLine);
        } catch (Throwable t) {
            throw new ReportWriterException(t.getMessage(), t.getCause());
        }
    }
}