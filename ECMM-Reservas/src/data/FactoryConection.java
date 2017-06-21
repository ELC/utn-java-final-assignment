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
		
		if (FactoryConection.instancia==null){
			FactoryConection.instancia=new FactoryConection();
			
			}
		
		return FactoryConection.instancia;
		
	}
	
	public Connection getConn(){
		try {
			
			conn=  DriverManager.getConnection("jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10181254?user=sql10181254&password=eZkGdZyJe8");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return conn;		
	}

}
