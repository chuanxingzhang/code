package com.lp.main;

import com.lp.sql.SqlInfo;

public class SqlGeneratorMain {
    public static void main(String args[]) {
        String sql="select id ,uuid,case_status from kv_case_entrust";
        SqlInfo sqlInfo=new SqlInfo();
        sqlInfo.printSQLInfo(sql);
    }
}
