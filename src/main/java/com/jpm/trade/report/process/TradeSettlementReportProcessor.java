package com.jpm.trade.report.process;

import com.jpm.trade.model.TradeCall;
import com.jpm.trade.model.TradeOrder;
import com.jpm.trade.report.filter.TradeSettlementReportFilter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.summingDouble;
import static java.util.stream.Collectors.toMap;

public class TradeSettlementReportProcessor implements TradeReportProcessor {
    @Override
    public Map<TradeCall, Map<String, Double>> process(List<TradeOrder> tradeOrders) throws TradeReportProcessorException {
        Map<TradeCall, Map<String, Double>> reportMap = new HashMap<>();
        Stream<TradeOrder> tradeOrderEligibleToSettleStream = tradeOrders.stream().filter(TradeSettlementReportFilter.getInstance()::filter);
        List<TradeOrder> tradeOrderBuyList = tradeOrderEligibleToSettleStream.filter(tradeOrder -> tradeOrder.getCall().equals(TradeCall.B)).collect(Collectors.toList());
        tradeOrderEligibleToSettleStream = tradeOrders.stream().filter(TradeSettlementReportFilter.getInstance()::filter);
        List<TradeOrder> tradeOrderSellList = tradeOrderEligibleToSettleStream.filter(tradeOrder -> tradeOrder.getCall().equals(TradeCall.S)).collect(Collectors.toList());
        Map<String, Double> entityBuyPriceMap = tradeOrderBuyList.stream().collect(Collectors.groupingBy(TradeOrder::getEntity, summingDouble((tradeOrder -> tradeOrder.getPrice() * tradeOrder.getQty() * tradeOrder.getAgreedFx()))));
        Map<String, Double> entitySellPriceMap = tradeOrderSellList.stream().collect(Collectors.groupingBy(TradeOrder::getEntity, summingDouble((tradeOrder -> tradeOrder.getPrice() * tradeOrder.getQty() * tradeOrder.getAgreedFx()))));
        LinkedHashMap<String, Double> sortedEntityBuyPriceMap = entityBuyPriceMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        LinkedHashMap<String, Double> sortedEntitySellPriceMap = entitySellPriceMap.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        if (!sortedEntitySellPriceMap.isEmpty()) {
            reportMap.put(TradeCall.S, sortedEntitySellPriceMap);
        }
        if (!sortedEntityBuyPriceMap.isEmpty()) {
            reportMap.put(TradeCall.B, sortedEntityBuyPriceMap);
        }
        return reportMap;
    }
}
