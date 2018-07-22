package com.jpm.trade.report.process;

public class TradeReportProcessorException extends RuntimeException {
    public TradeReportProcessorException() {
        super();
    }

    public TradeReportProcessorException(String message) {
        super(message);
    }

    public TradeReportProcessorException(String message, Throwable cause) {
        super(message, cause);
    }

    public TradeReportProcessorException(Throwable cause) {
        super(cause);
    }

    protected TradeReportProcessorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
