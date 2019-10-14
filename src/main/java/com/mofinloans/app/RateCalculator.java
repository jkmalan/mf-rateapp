package com.mofinloans.app;

import java.sql.PreparedStatement;

public class RateCalculator {

    public static RateResult calculate(String state, String purpose, String property, String loan, int fico, int price, int rent, int months) {

        return RateResult.INELIGIBLE;
    }

    public static int getBaseRate(double ltv, double fico) {
        PreparedStatement ps = RateEngine.getEngine().getDatabase().getPreparedStatement("");
        return 0;
    }

}
