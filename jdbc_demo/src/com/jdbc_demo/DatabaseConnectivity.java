package com.jdbc_demo;


public class DatabaseConnectivity{
	public static void main(String[] args) throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("complete");
	}
				}


