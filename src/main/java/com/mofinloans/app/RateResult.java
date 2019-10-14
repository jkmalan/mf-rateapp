package com.mofinloans.app;

public class RateResult {

    public static final RateResult INELIGIBLE = new RateResult(-1, -1, -1, -1);

    private final int MAXIMUM_LTV;
    private final int MAXIMUM_LOAN;
    private final double RATE;
    private final double DSCR;

    public RateResult(int maxLTV, int maxLoan, double rate, double dscr) {
        MAXIMUM_LTV = maxLTV;
        MAXIMUM_LOAN = maxLoan;
        RATE = rate;
        DSCR = dscr;
    }

    public int getMaxLTV() {
        return MAXIMUM_LTV;
    }

    public int getMaxLoan() {
        return MAXIMUM_LOAN;
    }

    public double getRate() {
        return RATE;
    }

    public double getDSCR() {
        return DSCR;
    }

}
