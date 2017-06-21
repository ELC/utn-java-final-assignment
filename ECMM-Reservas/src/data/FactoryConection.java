package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryConection {
	
	private static FactoryConection instancia;
	
	private Connection conn;
	
	private FactoryConection(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			}
	}
	
	public static FactoryConection getInstancia(){
		if (FactoryConection.instancia == null){
			FactoryConection.instancia = new FactoryConection();
		}
		return FactoryConection.instancia;
	}
	
	public Connection getConn(){
		String dbhost = ProjectConfiguration.getProperty("dbhost");
		String dbuser = ProjectConfiguration.getProperty("dbuser");
		String dbpassword = ProjectConfiguration.getProperty("dbpassword");
		String dbport = ProjectConfiguration.getProperty("dbport");
		String dbname = ProjectConfiguration.getProperty("dbname");
		
		try {
			conn=  DriverManager.getConnection("jdbc:mysql://"+dbhost+":"+dbport+"/"+dbname+"?user="+dbuser+"&password="+dbpassword);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return conn;		
	}

}
