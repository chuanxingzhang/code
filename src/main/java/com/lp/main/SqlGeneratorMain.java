package com.lp.main;

import com.lp.sql.SqlInfo;

public class SqlGeneratorMain {
    public static void main(String args[]) {
        String sql="SELECT\n" +
                "\tb.id,\n" +
                "\tb.NAME,\n" +
                "\tGROUP_CONCAT( DISTINCT cu.NAME ) collectName,\n" +
                "\tl.asset_character,\n" +
                "\tl.current_principal_balance - sum( p.principal ) current_principal_balance,\n" +
                "\tsum( p.amount ) total_amount,\n" +
                "\tmax( p.received_date ) received_date \n" +
                "FROM\n" +
                "\tms_borrower b\n" +
                "\tLEFT JOIN ms_loan l ON l.borrower_id = b.id\n" +
                "\tLEFT JOIN ms_received_payment p ON p.loan_id = l.id\n" +
                "\tLEFT JOIN ms_collect_staff s ON s.loan_id = l.id\n" +
                "\tLEFT JOIN st_user u ON u.id = s.user_id\n" +
                "\tLEFT JOIN st_customer_unit_user_relation cucr ON cucr.user_id = s.id\n" +
                "\tLEFT JOIN st_customer_unit cu ON cu.unit_code = cucr.unit_code \n" +
                "GROUP BY\n" +
                "\tb.id \n" +
                "ORDER BY\n" +
                "\tcurrent_principal_balance";
        SqlInfo sqlInfo=new SqlInfo();
        sqlInfo.printSQLInfo(sql);
    }
}
