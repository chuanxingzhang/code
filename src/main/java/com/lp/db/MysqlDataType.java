package com.lp.db;

/**
 * mysql数据类型，java数据类型，ibatis JDBCTYPE，之间的对应关系
 */
public enum MysqlDataType {
    /**
     *
     */
    MEDIUMINT("mediumint", "Integer", "INTEGER", "java.lang.Integer"),

    /**
     *
     */
    BIGINT("bigint", "Long", "BIGINT", "java.lang.Long"),
    /**
     *
     */
    BINARY("binary", "byte[]", "BINARY", "java.lang.Byte"),
    /**
     *
     */
    BIT("bit", "byte[]", "BIT", "java.lang.Byte"),
    /**
     *
     */
    BLOB("blob", "byte[]", "BLOB", "java.lang.Byte"),
    /**
     *
     */
    CHAR("char", "String", "CHAR", "java.lang.String"),
    /**
     *
     */
    DATE("date", "Date", "DATE", "java.util.Date"),
    /**
     *
     */
    DATETIME("datetime", "Date", "TIMESTAMP", "java.util.Date"),
    /**
     *
     */
    DECIMAL("decimal", "BigDecimal", "DECIMAL", "java.math.BigDecimal"),
    /**
     *
     */
    DOUBLE("double", "Double", "DOUBLE", "java.lang.Double"),
    /**
     *
     */
    FLOAT("float", "Float", "FLOAT", "java.lang.Float"),
    /**
     *
     */
    INT("int", "Integer", "INTEGER", "java.lang.Integer"),
    /**
     *
     */
    SMALLINT("smallint", "Short", "SMALLINT", "java.lang.Short"),
    /**
     *
     */
    TEXT("text", "String", "LONGVARCHAR", "java.lang.String"),
    /**
     *
     */
    TIME("time", "Date", "TIME", "java.util.Date"),
    /**
     *
     */
    TIMESTAMP("timestamp", "Date", "TIMESTAMP", "java.util.Date"),
    /**
     *
     */
    TINYINT("tinyint", "Boolean", "TINYINT", "java.lang.Boolean"),
    /**
     *
     */
    VARCHAR("varchar", "String", "VARCHAR", "java.lang.String");

    private String mySqlDataType;

    private String javaDataType;

    private String ibatisJdbcType;

    private String primaryKeyIbatisJavaType;

    MysqlDataType(String mySqlDataType, String javaDataType, String ibatisJdbcType, String primaryKeyIbatisJavaType) {
        this.mySqlDataType = mySqlDataType;
        this.javaDataType = javaDataType;
        this.ibatisJdbcType = ibatisJdbcType;
        this.primaryKeyIbatisJavaType = primaryKeyIbatisJavaType;
    }

    public String getMySqlDataType() {
        return mySqlDataType;
    }

    public String getJavaDataType() {
        return javaDataType;
    }

    public String getIbatisJdbcType() {
        return ibatisJdbcType;
    }

    public void setMySqlDataType(String mySqlDataType) {
        this.mySqlDataType = mySqlDataType;
    }

    public void setJavaDataType(String javaDataType) {
        this.javaDataType = javaDataType;
    }

    public void setIbatisJdbcType(String ibatisJdbcType) {
        this.ibatisJdbcType = ibatisJdbcType;
    }

    public String getPrimaryKeyIbatisJavaType() {
        return primaryKeyIbatisJavaType;
    }

    public void setPrimaryKeyIbatisJavaType(String primaryKeyIbatisJavaType) {
        this.primaryKeyIbatisJavaType = primaryKeyIbatisJavaType;
    }
}
