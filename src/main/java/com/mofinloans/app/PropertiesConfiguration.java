package com.mofinloans.app;

import java.util.Properties;

public class PropertiesConfiguration implements Configuration {

    private Properties properties = new Properties();
    private String settings = "settings.properties";

    public PropertiesConfiguration() {
        // TODO Properties loading from file
        properties.setProperty("db.host", "localhost");
        properties.setProperty("db.port", "3306");
        properties.setProperty("db.name", "mf_ratetool");
        properties.setProperty("db.user", "mofinloans");
        properties.setProperty("db.pass", "mofinloans");

        properties.setProperty("wb.path", "");
        properties.setProperty("wb.defaults.base-rate", "102.0");
        properties.setProperty("wb.defaults.base-price", "0.0595");
        properties.setProperty("wb.defaults.base-scalar", "0.0");
        properties.setProperty("wb.defaults.base-margin", "0.0");
        properties.setProperty("wb.adjustments", "0.0");
        properties.setProperty("wb.adj_ltv_vs_fico.sheet", "Investor Solutions(PI) ADJ");
        properties.setProperty("wb.adj_ltv_vs_fico.col-header", "C32:J32");
        properties.setProperty("wb.adj_ltv_vs_fico.row-header", "A33:A41");
        properties.setProperty("wb.adj_ltv_vs_fico.data", "C33:J41");
        properties.setProperty("wb.adj_ltv_vs_dscr.sheet", "Investor Solutions(PI) ADJ");
        properties.setProperty("wb.adj_ltv_vs_dscr.col-header", "C43:J43");
        properties.setProperty("wb.adj_ltv_vs_dscr.row-header", "A44:A46");
        properties.setProperty("wb.adj_ltv_vs_dscr.data", "C44:J46");
        properties.setProperty("wb.adj_ltv_vs_reserve.sheet", "Investor Solutions(PI) ADJ");
        properties.setProperty("wb.adj_ltv_vs_reserve.col-header", "C48:J48");
        properties.setProperty("wb.adj_ltv_vs_reserve.row-header", "A49:A49");
        properties.setProperty("wb.adj_ltv_vs_reserve.data", "C49:J49");
        properties.setProperty("wb.adj_ltv_vs_balance.sheet", "Investor Solutions(PI) ADJ");
        properties.setProperty("wb.adj_ltv_vs_balance.col-header", "C51:C51");
        properties.setProperty("wb.adj_ltv_vs_balance.row-header", "A52:A58");
        properties.setProperty("wb.adj_ltv_vs_balance.data", "C52:J58");
        properties.setProperty("wb.adj_ltv_vs_purpose.sheet", "Investor Solutions(PI) ADJ");
        properties.setProperty("wb.adj_ltv_vs_purpose.col-header", "C60:C60");
        properties.setProperty("wb.adj_ltv_vs_purpose.row-header", "A61:A61");
        properties.setProperty("wb.adj_ltv_vs_purpose.data", "C61:J61");
        properties.setProperty("wb.adj_ltv_vs_state.sheet", "Investor Solutions(PI) ADJ");
        properties.setProperty("wb.adj_ltv_vs_state.col-header", "C63:J63");
        properties.setProperty("wb.adj_ltv_vs_state.row-header", "A64:A114");
        properties.setProperty("wb.adj_ltv_vs_state.data", "C64:J114");
        properties.setProperty("wb.adj_ltv_vs_property.sheet", "Investor Solutions(PI) ADJ");
        properties.setProperty("wb.adj_ltv_vs_property.col-header", "C116:J116");
        properties.setProperty("wb.adj_ltv_vs_property.row-header", "A117:A119");
        properties.setProperty("wb.adj_ltv_vs_property.data", "C117:J119");
        properties.setProperty("wb.adj_ltv_vs_amortization.sheet", "Investor Solutions(PI) ADJ");
        properties.setProperty("wb.adj_ltv_vs_amortization.col-header", "C121:J121");
        properties.setProperty("wb.adj_ltv_vs_amortization.row-header", "A122:A122");
        properties.setProperty("wb.adj_ltv_vs_amortization.data", "C122:J122");
        properties.setProperty("wb.adj_ltv_vs_prepayment.sheet", "Investor Solutions(PI) ADJ");
        properties.setProperty("wb.adj_ltv_vs_prepayment.col-header", "C124:J124");
        properties.setProperty("wb.adj_ltv_vs_prepayment.row-header", "A125:A130");
        properties.setProperty("wb.adj_ltv_vs_prepayment.data", "C125:J130");
        properties.setProperty("wb.adj_term.sheet", "Investor Solutions(PI) ADJ");
        properties.setProperty("wb.adj_term.col-header", "L7:L7");
        properties.setProperty("wb.adj_term.row-header", "K8:K13");
        properties.setProperty("wb.adj_term.data", "L8:L13");
    }

    @Override
    public String getString(String key) {
        return properties.getProperty(key);
    }

    @Override
    public String getString(String key, String def) {
        return properties.getProperty(key, def);
    }

}
