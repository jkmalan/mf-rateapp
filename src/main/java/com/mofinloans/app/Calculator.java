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
                                 int prepayment,
                                 String term) {

        return calculateFicoAdjust(ltv, fico)
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

    public BigDecimal calculateFicoAdjust(double ltv, double fico) {
        BigDecimal adjust = new BigDecimal("0.00000");
        String query =
                "SELECT ltv_min, fico_min, rate_adj FROM adj_ltv_vs_fico AS adj " +
                "INNER JOIN bkt_ltv AS bl ON adj.bkt_ltv = bl.id " +
                "INNER JOIN bkt_fico AS bf ON adj.bkt_fico = bf.id " +
                "WHERE ltv_min = (SELECT MIN(ltv_min) FROM bkt_ltv WHERE ltv_max >= ?) " +
                "AND fico_min = (SELECT MIN(fico_min) FROM bkt_fico WHERE fico_max >= ?)";
        PreparedStatement statement = database.prepare(query);
        if (statement == null) {
            return adjust;
        }

        try {
            statement.setDouble(1, ltv);
            statement.setDouble(2, fico);
            ResultSet result = database.query(statement);
            if (result != null && result.next()) {
                System.out.println();
                adjust = result.getBigDecimal("rate_adj");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }
        return adjust;
    }

    public BigDecimal calculateDscrAdjust(double ltv, double dscr) {
        BigDecimal adjust = new BigDecimal("0.00000");
        String query =
                "SELECT ltv_min, dscr_min, rate_adj FROM adj_ltv_vs_dscr AS adj " +
                        "INNER JOIN bkt_ltv AS bl ON adj.bkt_ltv = bl.id " +
                        "INNER JOIN bkt_dscr AS bd ON adj.bkt_dscr = bd.id " +
                        "WHERE ltv_min = (SELECT MIN(ltv_min) FROM bkt_ltv WHERE ltv_max >= ?) " +
                        "AND dscr_min = (SELECT MIN(dscr_min) FROM bkt_dscr WHERE dscr_max >= ?);";
        PreparedStatement statement = database.prepare(query);
        if (statement == null) {
            return adjust;
        }

        try {
            statement.setDouble(1, ltv);
            statement.setDouble(2, dscr);
            ResultSet result = database.query(statement);
            if (result != null && result.next()) {
                adjust = result.getBigDecimal("rate_adj");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }
        return adjust;
    }

    public BigDecimal calculateReserveAdjust(double ltv, double reserve) {
        BigDecimal adjust = new BigDecimal("0.00000");
        String query =
                "SELECT ltv_min, reserve_min, rate_adj FROM adj_ltv_vs_reserve AS adj " +
                        "INNER JOIN bkt_ltv AS bl ON adj.bkt_ltv = bl.id " +
                        "INNER JOIN bkt_reserve AS br ON adj.bkt_reserve = br.id " +
                        "WHERE ltv_min = (SELECT MIN(ltv_min) FROM bkt_ltv WHERE ltv_max >= ?) " +
                        "AND reserve_min = (SELECT MIN(reserve_min) FROM bkt_reserve WHERE reserve_max > ?);";
        PreparedStatement statement = database.prepare(query);
        if (statement == null) {
            return adjust;
        }

        try {
            statement.setDouble(1, ltv);
            statement.setDouble(2, reserve);
            ResultSet result = database.query(statement);
            if (result != null && result.next()) {
                adjust = result.getBigDecimal("rate_adj");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }
        return adjust;
    }

    public BigDecimal calculateBalanceAdjust(double ltv, double balance) {
        BigDecimal adjust = new BigDecimal("0.00000");
        String query =
                "SELECT ltv_min, balance_min, rate_adj FROM adj_ltv_vs_balance AS adj " +
                        "INNER JOIN bkt_ltv AS bl ON adj.bkt_ltv = bl.id " +
                        "INNER JOIN bkt_balance AS bb ON adj.bkt_balance = bb.id " +
                        "WHERE ltv_min = (SELECT MIN(ltv_min) FROM bkt_ltv WHERE ltv_max >= ?) " +
                        "AND balance_min = (SELECT MIN(balance_min) FROM bkt_balance WHERE balance_max > ?);";
        PreparedStatement statement = database.prepare(query);
        if (statement == null) {
            return adjust;
        }

        try {
            statement.setDouble(1, ltv);
            statement.setDouble(2, balance);
            ResultSet result = database.query(statement);
            if (result != null && result.next()) {
                adjust = result.getBigDecimal("rate_adj");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }
        return adjust;
    }

    public BigDecimal calculatePurposeAdjust(double ltv, String purpose) {
        BigDecimal adjust = new BigDecimal("0.00000");
        String query =
                "SELECT ltv_min, purpose, rate_adj FROM adj_ltv_vs_purpose AS adj " +
                        "INNER JOIN bkt_ltv AS bl ON adj.bkt_ltv = bl.id " +
                        "INNER JOIN bkt_purpose AS bp ON adj.bkt_purpose = bp.id " +
                        "WHERE ltv_min = (SELECT MIN(ltv_min) FROM bkt_ltv WHERE ltv_max >= ?) " +
                        "AND purpose = ?;";
        PreparedStatement statement = database.prepare(query);
        if (statement == null) {
            return adjust;
        }

        try {
            statement.setDouble(1, ltv);
            statement.setString(2, purpose);
            ResultSet result = database.query(statement);
            if (result != null && result.next()) {
                adjust = result.getBigDecimal("rate_adj");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }
        return adjust;
    }

    public BigDecimal calculateStateAdjust(double ltv, String state) {
        BigDecimal adjust = new BigDecimal("0.00000");
        String query =
                "SELECT ltv_min, state, rate_adj FROM adj_ltv_vs_state AS adj " +
                        "INNER JOIN bkt_ltv AS bl ON adj.bkt_ltv = bl.id " +
                        "INNER JOIN bkt_state AS bs ON adj.bkt_state = bs.tier " +
                        "WHERE ltv_min = (SELECT MIN(ltv_min) FROM bkt_ltv WHERE ltv_max >= ?) " +
                        "AND state = (SELECT state FROM bkt_state WHERE include = TRUE AND state = ?);";
        PreparedStatement statement = database.prepare(query);
        if (statement == null) {
            return adjust;
        }

        try {
            statement.setDouble(1, ltv);
            statement.setString(2, state);
            ResultSet result = database.query(statement);
            if (result != null && result.next()) {
                adjust = result.getBigDecimal("rate_adj");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }
        return adjust;
    }

    public BigDecimal calculatePropertyAdjust(double ltv, String property) {
        BigDecimal adjust = new BigDecimal("0.00000");
        String query =
                "SELECT ltv_min, property, rate_adj FROM adj_ltv_vs_property AS adj " +
                        "INNER JOIN bkt_ltv AS bl ON adj.bkt_ltv = bl.id " +
                        "INNER JOIN bkt_property AS bp ON adj.bkt_property = bp.id " +
                        "WHERE ltv_min = (SELECT MIN(ltv_min) FROM bkt_ltv WHERE ltv_max >= ?) " +
                        "AND property = ?;";
        PreparedStatement statement = database.prepare(query);
        if (statement == null) {
            return adjust;
        }

        try {
            statement.setDouble(1, ltv);
            statement.setString(2, property);
            ResultSet result = database.query(statement);
            if (result != null && result.next()) {
                adjust = result.getBigDecimal("rate_adj");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }
        return adjust;
    }

    public BigDecimal calculateAmortizationAdjust(double ltv, String amortization) {
        BigDecimal adjust = new BigDecimal("0.00000");
        String query =
                "SELECT ltv_min, amortization, rate_adj FROM adj_ltv_vs_amortization AS adj " +
                        "INNER JOIN bkt_ltv AS bl ON adj.bkt_ltv = bl.id " +
                        "INNER JOIN bkt_amortization AS ba ON adj.bkt_amortization = ba.id " +
                        "WHERE ltv_min = (SELECT MIN(ltv_min) FROM bkt_ltv WHERE ltv_max >= ?) " +
                        "AND amortization = ?;";
        PreparedStatement statement = database.prepare(query);
        if (statement == null) {
            return adjust;
        }

        try {
            statement.setDouble(1, ltv);
            statement.setString(2, amortization);
            ResultSet result = database.query(statement);
            if (result != null && result.next()) {
                adjust = result.getBigDecimal("rate_adj");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }
        return adjust;
    }

    public BigDecimal calculatePrepaymentAdjust(double ltv, int prepayment) {
        BigDecimal adjust = new BigDecimal("0.00000");
        String query =
                "SELECT ltv_min, prepayment, rate_adj FROM adj_ltv_vs_prepayment AS adj " +
                        "INNER JOIN bkt_ltv AS bl ON adj.bkt_ltv = bl.id " +
                        "INNER JOIN bkt_prepayment AS bp ON adj.bkt_prepayment = bp.id " +
                        "WHERE ltv_min = (SELECT MIN(ltv_min) FROM bkt_ltv WHERE ltv_max >= ?) " +
                        "AND prepayment = ?;";
        PreparedStatement statement = database.prepare(query);
        if (statement == null) {
            return adjust;
        }

        try {
            statement.setDouble(1, ltv);
            statement.setInt(2, prepayment);
            ResultSet result = database.query(statement);
            if (result != null && result.next()) {
                adjust = result.getBigDecimal("rate_adj");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }
        return adjust;
    }

    public BigDecimal calculateTermAdjust(double ltv, String term) {
        BigDecimal adjust = new BigDecimal("0.00000");
        String query =
                "SELECT term, rate_adj FROM adj_term AS adj " +
                        "INNER JOIN bkt_term AS bt ON adj.bkt_term = bt.id " +
                        "WHERE term = ?;";
        PreparedStatement statement = database.prepare(query);
        if (statement == null) {
            return adjust;
        }

        try {
            statement.setString(1, term);
            ResultSet result = database.query(statement);
            if (result != null && result.next()) {
                adjust = result.getBigDecimal("rate_adj");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // TODO Log handler
        }
        return adjust;
    }

}
