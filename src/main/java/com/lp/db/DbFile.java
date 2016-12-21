package com.lp.db;

public class DbFile {
	

	public static DbFile singleton;
	
	private JdbcJsonEntity jdbc;
	
	private FileConfigJsonEntity fileConfig;

	public void setJdbc(JdbcJsonEntity jdbc) {
		this.jdbc = jdbc;
	}

	public void setFileConfig(FileConfigJsonEntity fileConfig) {
		this.fileConfig = fileConfig;
	}

	public JdbcJsonEntity getJdbc() {
		return jdbc;
	}

	public FileConfigJsonEntity getFileConfig() {
		return fileConfig;
	}
	
}
