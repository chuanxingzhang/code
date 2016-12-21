package com.lp.db;

public enum JavaDataTypePackage {
	
	
	DATETIME("datetime","java.util.Date"),
	
	TIMESTAMP("timestamp","java.util.Date"),
	
	DECIMAL("decimal","java.math.BigDecimal");
	
	
	private String mySqlDataType;
	
	private String javaPackage;
	
	JavaDataTypePackage(String mySqlDataType, String javaPackage) {
		this.mySqlDataType = mySqlDataType;
		this.javaPackage = javaPackage;
	}

	public String getMySqlDataType() {
		return mySqlDataType;
	}

	public void setMySqlDataType(String mySqlDataType) {
		this.mySqlDataType = mySqlDataType;
	}

	public String getJavaPackage() {
		return javaPackage;
	}

	public void setJavaPackage(String javaPackage) {
		this.javaPackage = javaPackage;
	}
	
}
