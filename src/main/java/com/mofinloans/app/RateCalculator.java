package com.mofinloans.app;

import com.mofinloans.app.database.Database;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RateCalculator {

    private final Database database;

    private Map<Integer, BigDecimal> ltvMap = new HashMap<>();
    private Map<Integer, BigDecimal> ficoMap = new HashMap<>();

    public RateCalculator(Database database) {
        this.database = database;

        getLtvMap();
        ltvMap.forEach((key, value) -> System.out.println("LtvMap: " + key + ", " + value));
        getFicoMap();
        ficoMap.forEach((key, value) -> System.out.println("FicoMap: " + key + ", " + value));
    }

    private void getLtvMap() {
        String query = "SELECT * FROM bkt_ltv;";
        ResultSet result = database.execute(query);
        try {
            while (result.next()) {
                Integer id = result.getInt("id");
                BigDecimal ltvMin = result.getBigDecimal("ltv_min");
                ltvMap.put(id, ltvMin);
            }
            result.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void getFicoMap() {
        String query = "SELECT * FROM bkt_fico";
        ResultSet result = database.execute(query);
        try {
            while (result.next()) {
                Integer id = result.getInt("id");
                BigDecimal ficoMin = result.getBigDecimal("fico_min");
                ficoMap.put(id, ficoMin);
            }
            result.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public RateResult calculate(String state, String purpose, String property, String loan, int fico, int price, int rent, int months) {

        return RateResult.INELIGIBLE;
    }

    public BigDecimal getBaseRate(double ltv, double fico) {
        BigDecimal inLtv = new BigDecimal(ltv);
        BigDecimal inFico = new BigDecimal(fico);

        Integer findLtv = 0;
        for (Map.Entry<Integer, BigDecimal> e : ltvMap.entrySet()) {
            BigDecimal d = e.getValue();
            int compare = d.compareTo(inLtv);
            System.out.println(compare);
            if (compare < 0) {
                System.out.println("Entry: " + e.getKey() + ", " + e.getValue());
                findLtv = e.getKey();
                break;
            }
        }

        Integer findFico = 0;
        for (Map.Entry<Integer, BigDecimal> e : ficoMap.entrySet()) {
            BigDecimal d = e.getValue();
            int compare = d.compareTo(inFico);
            System.out.println(compare);
            if (compare < 0) {
                System.out.println("Entry: " + e.getKey() + ", " + e.getValue());
                findFico = e.getKey();
                break;
            }
        }

        BigDecimal rate = new BigDecimal(-1);

        String query = "SELECT rate_adj FROM adj_ltv_vs_fico WHERE bkt_ltv=? AND bkt_fico=?;";
        PreparedStatement ps = database.getPreparedStatement(query);
        try {
            ps.setInt(1, findLtv);
            ps.setInt(2, findFico);
            ResultSet result = ps.executeQuery();
            if (result.next()) {

                System.out.println("findLTV: " + findLtv + ", findFico: " + findFico + "rate_adj: " + result.getBigDecimal(1));
                rate = result.getBigDecimal("rate_adj");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rate;
    }

}
