package com.inventorymanagement.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConfig {
	
	private static DbConfig instance;
	private String url;
	private String username;
	private String password;
	
	private DbConfig () {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try(InputStream input = DbConfig
					.class
					.getResourceAsStream("application.properties"))
			{
				Properties props = new Properties();
				props.load(input);
				this.url = props.getProperty("db.url");
				this.username = props.getProperty("db.username");
				this.password = props.getProperty("db.password");
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static DbConfig getInstance() {
		if (instance == null) {
			return instance = new DbConfig();
		}
		return instance;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}
