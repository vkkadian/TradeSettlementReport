package com.jpm.trade.model;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Objects;

public final class TradeOrder {
    private final String entity;
    private final TradeCall call;
    private final double agreedFx;
    private final Currency currency;
    private final LocalDate instructionDate;
    private final LocalDate settlementDate;
    private final int qty;
    private final double price;

    public TradeOrder(String entity, TradeCall call, double agreedFx, Currency currency, LocalDate instructionDate, LocalDate settlementDate, int qty, double price) {
        this.entity = entity;
        this.call = call;
        this.agreedFx = agreedFx;
        this.currency = currency;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.qty = qty;
        this.price = price;
    }

    public String getEntity() {
        return entity;
    }

    public TradeCall getCall() {
        return call;
    }

    public double getAgreedFx() {
        return agreedFx;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradeOrder that = (TradeOrder) o;
        return Objects.equals(getEntity(), that.getEntity()) &&
                getCall() == that.getCall() &&
                Objects.equals(getCurrency(), that.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntity(), getCall(), getCurrency());
    }

    @Override
    public String toString() {
        return "TradeOrder{" +
                "entity='" + getEntity() + '\'' +
                ", call=" + getCall() +
                ", agreedFx=" + getAgreedFx() +
                ", currency=" + getCurrency() +
                ", instructionDate=" + getInstructionDate() +
                ", settlementDate=" + getSettlementDate() +
                ", qty=" + getQty() +
                ", price=" + getPrice() +
                '}';
    }
}
