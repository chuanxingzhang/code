package com.lp.sql;

import com.lp.db.JdbcUtil;
import com.lp.db.MysqlDataType;
import com.lp.util.UtilLp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class SqlInfo {
    public void printSQLInfo(String sql) {
        PreparedStatement pst = null;
        Connection conn = JdbcUtil.getOpenConnection();
        try {
            pst = conn.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            UtilLp utilLp = new UtilLp();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnType = rsmd.getColumnTypeName(i);
                String columnName = rsmd.getColumnName(i);
                MysqlDataType mysqlDataType = UtilLp.getFieldType(columnType);
                String javaType = mysqlDataType.getJavaDataType();
                String javaEntityName = UtilLp.columnNameToHumpStr(columnName);
                System.out.println("private " + javaType + " " + javaEntityName + ";");

            }
        } catch (Exception e) {

        }
    }
}
