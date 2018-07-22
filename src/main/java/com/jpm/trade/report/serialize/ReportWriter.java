package com.jpm.trade.report.serialize;

public interface ReportWriter {
    void write(String reportLine) throws ReportWriterException;
}
