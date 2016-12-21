package com.lp.db;

public class FileConfigJsonEntity {

	private String ibatisDaoPackage;
	
	private String ibatisEntityPackage;
	
	private String fileSavePath;
	
	private String uploadFilePath;
	
	private String ibatisFileCharset;
	
	private String servicePackage;
	
	private String serviceImplPackage;
	
	private String controllerPackage;
	
	private String deleteStr;
	
	private boolean isCreateMoveSql;
	

	public void setIbatisDaoPackage(String ibatisDaoPackage) {
		this.ibatisDaoPackage = ibatisDaoPackage;
	}

	public void setIbatisEntityPackage(String ibatisEntityPackage) {
		this.ibatisEntityPackage = ibatisEntityPackage;
	}

	public void setFileSavePath(String fileSavePath) {
		this.fileSavePath = fileSavePath;
	}

	public String getIbatisDaoPackage() {
		return ibatisDaoPackage;
	}

	public String getIbatisEntityPackage() {
		return ibatisEntityPackage;
	}

	public String getFileSavePath() {
		return fileSavePath;
	}

	public String getIbatisFileCharset() {
		return ibatisFileCharset;
	}

	public void setIbatisFileCharset(String ibatisFileCharset) {
		this.ibatisFileCharset = ibatisFileCharset;
	}

	public String getServicePackage() {
		return servicePackage;
	}

	public void setServicePackage(String servicePackage) {
		this.servicePackage = servicePackage;
	}

	public String getServiceImplPackage() {
		return serviceImplPackage;
	}

	public void setServiceImplPackage(String serviceImplPackage) {
		this.serviceImplPackage = serviceImplPackage;
	}

	public String getDeleteStr() {
		return deleteStr;
	}

	public void setDeleteStr(String deleteStr) {
		this.deleteStr = deleteStr;
	}

	public boolean getIsCreateMoveSql() {
		return isCreateMoveSql;
	}

	public void setIsCreateMoveSql(boolean isCreateMoveSql) {
		this.isCreateMoveSql = isCreateMoveSql;
	}

	public String getControllerPackage() {
		return controllerPackage;
	}

	public void setControllerPackage(String controllerPackage) {
		this.controllerPackage = controllerPackage;
	}

	public String getUploadFilePath() {
		return uploadFilePath;
	}

	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}
	
	
}
