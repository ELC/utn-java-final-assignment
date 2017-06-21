package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Bookable;
import entities.TypeBookable;

public class DataBookable {
	
	private static Bookable buildTypeBookable(ResultSet rs) throws SQLException{
		Bookable b= new Bookable();
		b.setId(rs.getInt("id_bookable"));
		b.setName(rs.getString("name_bookable"));
		TypeBookable type_bookable = DataTypeBookable.getById(rs.getInt("id_type_bookable "));
		b.setType(type_bookable);
		return b;
	}
	
	public static ArrayList<Bookable> getAll(){
		
		ArrayList<Bookable> bookables= new ArrayList<Bookable>();
		try{
			Statement stmt = FactoryConection.getInstancia().getConn().createStatement();
			ResultSet rs=stmt.executeQuery("Select * from person");
			if(rs!=null){
				while(rs.next()){
					Bookable b = buildTypeBookable(rs);
					bookables.add(b);
				}			
			}
		} catch(SQLException e) {
			e.printStackTrace();			
		}
		return bookables;
	}
	
	public static Bookable getById(int id){
		return null;
	}
	
	public static Bookable getByName(int restriction){
		return null;
	}
	
	public static List<Bookable> getAllByType(TypeBookable bookable_type){
		return null;
	}

}
