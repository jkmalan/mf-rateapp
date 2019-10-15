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

    public Calculation calculate(double ltv, double fico) {
        BigDecimal baseRate = calculateBaseRate(ltv, fico);

        return null;
    }

    public BigDecimal calculateBaseRate(double ltv, double fico) {
        BigDecimal rate = new BigDecimal(0);

        String query =
                "SELECT ltv_min, fico_min, rate_adj FROM adj_ltv_vs_fico AS adj " +
                "    INNER JOIN bkt_ltv AS bl ON adj.bkt_ltv = bl.id " +
                "    INNER JOIN bkt_fico AS bf ON adj.bkt_fico = bf.id " +
                "    WHERE ltv_min < ? AND fico_min < ? LIMIT 1;";
        PreparedStatement statement = database.prepare(query);
        if (statement == null) {
            return rate;
        }

        try {
            statement.setDouble(1, ltv);
            statement.setDouble(2, fico);
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

}
