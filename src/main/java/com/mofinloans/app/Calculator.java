package com.mofinloans.app;

import com.mofinloans.app.database.Database;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Calculator {

    private Database database;

    public Calculator(Database database) {
        this.database = database;
    }

    public BigDecimal calculate(double ltv,
                                 double fico,
                                 double dscr,
                                 double reserve,
                                 double balance,
                                 String purpose,
                                 String state,
                                 String property,
                                 String amortization,
                                 String prepayment,
                                 String term) {

        return calculateBaseRate(ltv, fico)
                        .add(calculateDscrAdjust(ltv, dscr))
                        .add(calculateReserveAdjust(ltv, reserve))
                        .add(calculateBalanceAdjust(ltv, balance))
                        .add(calculatePurposeAdjust(ltv, purpose))
                        .add(calculateStateAdjust(ltv, state))
                        .add(calculatePropertyAdjust(ltv, property))
                        .add(calculateAmortizationAdjust(ltv, amortization))
                        .add(calculatePrepaymentAdjust(ltv, prepayment)
                        .add(calculateTermAdjust(ltv, term)));
    }

    public BigDecimal calculateBaseRate(double ltv, double fico) {
        BigDecimal rate = new BigDecimal(0);
        String query =
                "SELECT ltv_min, fico_min, rate_adj FROM adj_ltv_vs_fico AS adj " +
                "    INNER JOIN bkt_ltv AS bl ON adj.bkt_ltv = bl.id " +
                "    INNER JOIN bkt_fico AS bf ON adj.bkt_fico = bf.id " +
                "    WHERE ltv_min < ? " +
                        "AND ltv_max >= ?" +
                        "AND fico_min < ? " +
                        "AND fico_max >= ?;";
        PreparedStatement statement = database.prepare(query);
        if (statement == null) {
            return rate;
        }

        try {
            statement.setDouble(1, ltv);
            statement.setDouble(2, ltv);
            statement.setDouble(3, fico);
            statement.setDouble(4, fico);
            ResultSet result = database.query(statement);
            if (result != null && result.next()) {
                rate = result.getBigDecimal("rate_adj");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            // TODO Log handler
        }
        return rate;
    }

    public BigDecimal calculateDscrAdjust(double ltv, double dscr) {
        BigDecimal adjust = new BigDecimal(0);

        return adjust;
    }

    public BigDecimal calculateReserveAdjust(double ltv, double reserve) {
        BigDecimal adjust = new BigDecimal(0);

        return adjust;
    }

    public BigDecimal calculateBalanceAdjust(double ltv, double balance) {
        BigDecimal adjust = new BigDecimal(0);

        return adjust;
    }

    public BigDecimal calculatePurposeAdjust(double ltv, String purpose) {
        BigDecimal adjust = new BigDecimal(0);

        return adjust;
    }

    public BigDecimal calculateStateAdjust(double ltv, String state) {
        BigDecimal adjust = new BigDecimal(0);

        return adjust;
    }

    public BigDecimal calculatePropertyAdjust(double ltv, String property) {
        BigDecimal adjust = new BigDecimal(0);

        return adjust;
    }

    public BigDecimal calculateAmortizationAdjust(double ltv, String amortization) {
        BigDecimal adjust = new BigDecimal(0);

        return adjust;
    }

    public BigDecimal calculatePrepaymentAdjust(double ltv, String prepayment) {
        BigDecimal adjust = new BigDecimal(0);

        return adjust;
    }

    public BigDecimal calculateTermAdjust(double ltv, String term) {
        BigDecimal adjust = new BigDecimal(0);

        return adjust;
    }

}
