package com.lp.db;

/**
 * mysql数据类型，java数据类型，ibatis JDBCTYPE，之间的对应关系
 *
 */
public enum MysqlDataType {
	
	/**
	 * 
	 */
	BIGINT("bigint", "Long", "BIGINT"),
	/**
	 * 
	 */
	BINARY("binary", "byte[]", "BINARY"),
	/**
	 * 
	 */
	BIT("bit", "byte[]", "BIT"),
	/**
	 * 
	 */
	BLOB("blob", "byte[]", "BLOB"),
	/**
	 * 
	 */
	CHAR("char", "String", "CHAR"),
	/**
	 * 
	 */
	DATE("date", "Date", "DATE"),
	/**
	 * 
	 */
	DATETIME("datetime", "Date", "TIMESTAMP"),
	/**
	 * 
	 */
	DECIMAL("decimal", "BigDecimal", "DECIMAL"),
	/**
	 * 
	 */
	DOUBLE("double", "Double", "DOUBLE"),
	/**
	 * 
	 */
	FLOAT("float", "Float", "FLOAT"),
	/**
	 * 
	 */
	INT("int", "Integer", "INTEGER"),
	/**
	 * 
	 */
	SMALLINT("smallint", "Short", "SMALLINT"),
	/**
	 * 
	 */
	TEXT("text", "String", "LONGVARCHAR"),
	/**
	 * 
	 */
	TIME("time", "Date", "TIME"),
	/**
	 * 
	 */
	TIMESTAMP("timestamp", "Date", "TIMESTAMP"),
	/**
	 * 
	 */
	TINYINT("tinyint", "Byte", "TINYINT"),
	/**
	 * 
	 */
	VARCHAR("varchar", "String", "VARCHAR");
	
	private String mySqlDataType;
	
	private String javaDataType;
	
	private String ibatisJdbcType;
	
	MysqlDataType(String mySqlDataType, String javaDataType, String ibatisJdbcType) {
		this.mySqlDataType = mySqlDataType;
		this.javaDataType = javaDataType;
		this.ibatisJdbcType = ibatisJdbcType;
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

	
	
}
