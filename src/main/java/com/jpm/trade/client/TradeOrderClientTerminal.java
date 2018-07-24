package com.jpm.trade.client;

import com.jpm.trade.application.TradeReportApplication;
import com.jpm.trade.application.TradeSettlementReportApplication;
import com.jpm.trade.application.context.TradeSettlementReportApplicationContext;
import com.jpm.trade.data.TradeOrderManager;
import com.jpm.trade.model.TradeCall;
import com.jpm.trade.model.TradeOrder;
import com.jpm.trade.report.process.TradeSettlementReportProcessor;
import com.jpm.trade.report.produce.TradeSettlementReportProducer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class TradeOrderClientTerminal implements TradeOrderClient {

    private TradeReportApplication tradeReportApplication;

    public static void main(String[] args) {

        TradeReportApplication tradeReportApplication =
                new TradeSettlementReportApplication(
                        new TradeSettlementReportProcessor(),
                        new TradeSettlementReportProducer(TradeSettlementReportApplicationContext.getInstance().getReportWriterlist()));
        TradeOrderClientTerminal tradeOrderClient = new TradeOrderClientTerminal();
        tradeOrderClient.setTradeReportApplication(tradeReportApplication);
        while (true) {
            if (!tradeOrderClient.start()) break;
        }
    }

    @Override
    public boolean start() throws TradeOrderClientException {

        try (Scanner input = new Scanner(System.in)) {
            do {
                int menuChoice;
                displayTradeOrderMenu();
                menuChoice = input.nextInt();
                if (menuChoice == 0) {
                    return false;
                } else if (menuChoice == 1) {
                    addTradeOrderAction(input);
                } else if (menuChoice == 2) {
                    viewTradeOrdersInStoreAction();
                } else if (menuChoice == 3) {
                    TradeOrderManager.getInstance().clear();
                } else if (menuChoice == 4) {
                    if (TradeOrderManager.getInstance().getTradeOrders().isEmpty()) {
                        System.out.println("No trade orders to process, please retry");
                    } else {
                        tradeReportApplication.generateReport();
                    }
                } else {
                    throw new IllegalArgumentException("un-recognized menu choice");
                }
            } while (true);
        } catch (RuntimeException t) {
            System.out.println("Input error:" + t.getMessage() + ", please retry.");
        } catch (Exception e) {
            throw new TradeOrderClientException(e.getMessage(), e.getCause());
        }
        return true;
    }

    private void displayTradeOrderMenu() {
        System.out.println("******************TRADE ORDER ENTRY MENU******************");
        System.out.println("\t1. Add Trade Order\n\t2. View Trade Orders\n\t3. Clear Trade Orders\n\t4. Generate report\n\t0. Exit");
        System.out.println("**********************************************************");
        System.out.println("Enter a choice: ");
    }

    private void addTradeOrderAction(Scanner input) {
        System.out.println("Entity name [Example: Foo]:");
        String entity = input.next();
        System.out.println("Trade call type [Example: B or S]:");
        TradeCall tradeCall = TradeCall.valueOf(input.next().toUpperCase());
        System.out.println("Agreed fx rate [Example: 0.50]:");
        double agreedFx = input.nextDouble();
        System.out.println("Currency [Example: SGD]:");
        Currency currency = Currency.getInstance(input.next());
        System.out.println("Instruction date (ddMMyyyy) [Example: 23072018]:");
        LocalDate instructionDate = LocalDate.parse(input.next(), DateTimeFormatter.ofPattern("ddMMyyyy", Locale.ENGLISH));
        System.out.println("Settlement date (ddMMyyyy) [Example: 23072018]:");
        LocalDate settlementDate = LocalDate.parse(input.next(), DateTimeFormatter.ofPattern("ddMMyyyy", Locale.ENGLISH));
        if (settlementDate.isBefore(instructionDate)) {
            throw new IllegalArgumentException("settlement date can't be before instruction date");
        }
        System.out.println("Units [Example: 200]:");
        int qty = input.nextInt();
        System.out.println("Price [Example: 100.25]:");
        double price = input.nextDouble();
        TradeOrder tradeOrder = new TradeOrder(entity, tradeCall, agreedFx, currency, instructionDate, settlementDate, qty, price);
        TradeOrderManager.getInstance().addTradeOrder(tradeOrder);
        System.out.println("Trade order received:" + tradeOrder);
    }

    private void viewTradeOrdersInStoreAction() {
        System.out.println("//Trade orders in store//");
        List<TradeOrder> tradeOrderList = TradeOrderManager.getInstance().getTradeOrders();
        if (tradeOrderList.isEmpty()) {
            System.out.println("No trade orders in store");
        }
        for (TradeOrder tradeOrder : tradeOrderList) {
            System.out.println(tradeOrder);
        }
        System.out.println("//End//");
    }

    private void setTradeReportApplication(TradeReportApplication tradeReportApplication) {
        this.tradeReportApplication = tradeReportApplication;
    }

}
