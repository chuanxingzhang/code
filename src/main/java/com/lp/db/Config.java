package com.lp.db;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by zhangchuanxing on 2016/12/15.
 */
public class Config {
    public static String url;
    public static String userName;
    public static String password;
    public static String databaseName;
    public static String daoPackage;
    public static String mapperPackage;
    public static String servicePackage;
    public static String serviceImplPackage;
    public static String entityPackage;
    public static String entityName;
    public static String entityExplain;
    public static String fileSavePath;



    static {
        Properties props = new Properties();
        try {
            props.load(new InputStreamReader(Config.class.getClassLoader().getResourceAsStream("jdbc.properties"), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = props.getProperty("url");
        userName = props.getProperty("userName");
        password = props.getProperty("password");
        databaseName = props.getProperty("databaseName");
        daoPackage =props.getProperty("daoPackage");
        mapperPackage =props.getProperty("mapperPackage");
        servicePackage =props.getProperty("servicePackage");
        serviceImplPackage =props.getProperty("serviceImplPackage");
        entityPackage =props.getProperty("entityPackage");
        entityName =props.getProperty("entityName");
        fileSavePath =props.getProperty("fileSavePath");
        entityExplain=props.getProperty("entityExplain");
    }
}
