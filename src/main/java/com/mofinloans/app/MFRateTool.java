package com.mofinloans.app;

import com.mofinloans.app.database.Database;

import java.math.BigDecimal;
import java.math.MathContext;

public class MFRateTool {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        Database database = new Database(configuration);
        Calculator calculator = new Calculator(database);

        double ltv = 66.00;
        double fico = 690.00;
        double dscr = 0.90;
        double reserve = 1.00;
        double balance = 400000.00;
        String purpose = "PUR";
        String state = "CA";
        String property = "SFR";
        String amortization = "";
        int prepayment = 0;
        String term = "30 YR FIX";

        BigDecimal rate = new BigDecimal(0.05950);

        BigDecimal adjFico = calculator.calculateFicoAdjust(ltv, fico);
        System.out.println("LTV vs. FICO: " + adjFico);
        rate = rate.add(adjFico);

        BigDecimal adjDscr = calculator.calculateDscrAdjust(ltv, dscr);
        System.out.println("LTV vs. DSCR: " + adjDscr);
        rate = rate.add(adjDscr);

        BigDecimal adjReserve = calculator.calculateReserveAdjust(ltv, reserve);
        System.out.println("LTV vs. Reserves: " + adjReserve);
        rate = rate.add(adjReserve);

        BigDecimal adjBalance = calculator.calculateBalanceAdjust(ltv, balance);
        System.out.println("LTV vs. Balance: " + adjBalance);
        rate = rate.add(adjBalance);

        BigDecimal adjPurpose = calculator.calculatePurposeAdjust(ltv, purpose);
        System.out.println("LTV vs. Purpose: " + adjPurpose);
        rate = rate.add(adjPurpose);

        BigDecimal adjState = calculator.calculateStateAdjust(ltv, state);
        System.out.println("LTV vs. State: " + adjState);
        rate = rate.add(adjState);

        BigDecimal adjProperty = calculator.calculatePropertyAdjust(ltv, property);
        System.out.println("LTV vs. Property: " + adjProperty);
        rate = rate.add(adjProperty);

        BigDecimal adjAmortization = calculator.calculateAmortizationAdjust(ltv, amortization);
        System.out.println("LTV vs. Amortization: " + adjAmortization);
        rate = rate.add(adjAmortization);

        BigDecimal adjPrepayment = calculator.calculatePrepaymentAdjust(ltv, prepayment);
        System.out.println("LTV vs. Prepayment: " + adjPrepayment);
        rate = rate.add(adjPrepayment);

        BigDecimal adjTerm = calculator.calculateTermAdjust(ltv, term);
        System.out.println("Term: " + adjTerm);
        rate = rate.add(adjTerm);

        System.out.println("Estimated Rate: " + rate.round(new MathContext(4)));
    }

}
