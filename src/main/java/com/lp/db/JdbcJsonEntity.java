package com.lp.db;

public class JdbcJsonEntity {

	private String username;
	
	private String password;
	
	private String url;
	
	private String dbname;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDbname(String dbname) {
		this.dbname = dbname;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getUrl() {
		return url;
	}

	public String getDbname() {
		return dbname;
	}
	
}
