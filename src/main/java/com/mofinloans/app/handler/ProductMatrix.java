package com.mofinloans.app.handler;

import com.mofinloans.app.database.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMatrix {

    private Database database;

    public ProductMatrix(Database database) {
        this.database = database;
    }

    public int getMaxLTV(int fico, int loan_amount, int dscr, String loan_type) {
        int max_ltv = -1;

        String query = "SELECT max_ltv FROM product_matrix AS pm " +
                "INNER JOIN ref_fico AS f ON f.id = pm.fico " +
                "INNER JOIN ref_loan_amount AS la ON la.id = pm.loan_amount " +
                "INNER JOIN ref_dscr AS d ON d.id = pm.dscr " +
                "INNER JOIN ref_loan_type AS lt ON lt.id = pm.loan_type " +
                "WHERE f.fico_min < ? AND f.fico_max >= ? " +
                "AND la.loan_amount_min < ? AND la.loan_amount_max >= ? " +
                "AND d.dscr_man < ? AND d.dscr_max >= ? " +
                "AND lt.loan_code = ?;";
        try {
            PreparedStatement statement = database.prepare(query);
            statement.setInt(1, fico);
            statement.setInt(2, fico);
            statement.setInt(3, loan_amount);
            statement.setInt(4, loan_amount);
            statement.setInt(5, dscr);
            statement.setInt(6, dscr);
            statement.setString(7, loan_type);
            ResultSet result = database.query(statement);

            if (result.next()) {
                max_ltv = result.getInt("max_ltv");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return max_ltv;
    }

}
