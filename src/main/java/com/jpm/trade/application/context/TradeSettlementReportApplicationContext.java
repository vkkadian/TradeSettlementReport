package com.jpm.trade.application.context;

import com.jpm.trade.report.serialize.ConsoleReportWriter;
import com.jpm.trade.report.serialize.ReportWriter;

import java.time.DayOfWeek;
import java.util.*;

public final class TradeSettlementReportApplicationContext implements TradeReportApplicationContext {
    private static final TradeSettlementReportApplicationContext INSTANCE = new TradeSettlementReportApplicationContext();
    private final Map<Currency, List<DayOfWeek>> currencyWeekDayMap = new HashMap<>();
    private final Locale enUSLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
    private final List<Currency> sunThuWeekDayCurrencyList = new ArrayList<>();
    private final List<DayOfWeek> monFriNonWorkingDaysList = new ArrayList<>();
    private final List<DayOfWeek> sunThuNonWorkingDaysList = new ArrayList<>();
    private final List<ReportWriter> reportWriterlist = new ArrayList<>();

    private TradeSettlementReportApplicationContext() {
        initialize();
    }

    public static TradeSettlementReportApplicationContext getInstance() {
        return INSTANCE;
    }

    public Map<Currency, List<DayOfWeek>> getCurrencyWeekDayMap() {
        return Collections.unmodifiableMap(currencyWeekDayMap);
    }

    public Locale getLocale() {
        return enUSLocale;
    }

    List<Currency> getSunThuWeekDayCurrencyList() {
        return Collections.unmodifiableList(sunThuWeekDayCurrencyList);
    }

    List<DayOfWeek> getMonFriNonWorkingDaysList() {
        return Collections.unmodifiableList(monFriNonWorkingDaysList);
    }

    List<DayOfWeek> getSunThuNonWorkingDaysList() {
        return Collections.unmodifiableList(sunThuNonWorkingDaysList);
    }

    public List<ReportWriter> getReportWriterlist() {
        return reportWriterlist;
    }

    @Override
    public void initialize() {
        //report writers
        reportWriterlist.add(ConsoleReportWriter.getInstance());
        // currencies with sun-thu week days
        sunThuWeekDayCurrencyList.add(Currency.getInstance("AED"));
        sunThuWeekDayCurrencyList.add(Currency.getInstance("SAR"));

        //currency week day map
        monFriNonWorkingDaysList.add(DayOfWeek.SATURDAY);
        monFriNonWorkingDaysList.add(DayOfWeek.SUNDAY);

        sunThuNonWorkingDaysList.add(DayOfWeek.FRIDAY);
        sunThuNonWorkingDaysList.add(DayOfWeek.SATURDAY);

        for (Currency currency : Currency.getAvailableCurrencies()) {
            if (sunThuWeekDayCurrencyList.contains(currency)) {
                currencyWeekDayMap.put(currency, sunThuNonWorkingDaysList);
            } else {
                currencyWeekDayMap.put(currency, monFriNonWorkingDaysList);
            }
        }
    }
}
