package com.lp.main;

import com.lp.ibatis.*;

import java.util.List;

/**
 * Created by zhangchuanxing on 2016/12/13.
 */
public class MainClass {
    public static void  main(String args[]){
        String tableName="kv_business_opportunity";
        List<TableColumnInfo> tableInfo=CreateIbatisFile.getTableInfo(tableName);
        CreateIbatisXmlFileUtil.createIbatisXmlFile(tableInfo,tableName);
        CreateIbatisDaoFileUtil.createIbatisDaoFile(tableInfo,tableName);
        CreateIbatisEntityFileUtil.createIbatisEntityFile(tableInfo,tableName);
        CreateAppServiceFileUtil.createAppServiceFile(tableInfo,tableName);
        CreateAppServiceImplFileUtil.createAppServiceImplFile(tableInfo,tableName);
    }
}
