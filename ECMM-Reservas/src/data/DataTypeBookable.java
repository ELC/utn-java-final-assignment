package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Bookable;
import entities.TypeBookable;

public class DataTypeBookable {
	
	private static TypeBookable buildTypeBookable(ResultSet rs) throws SQLException{
		TypeBookable tb= new TypeBookable();
		tb.setId(rs.getInt("id_type_bookable"));
		tb.setName(rs.getString("name_type_bookable"));
		tb.setHourslimit(rs.getInt("hours_limit"));
		tb.setDayslimit(rs.getInt("days_limit"));
		tb.setRestriction(rs.getInt("restriction"));
		return tb;
	}
	
	public static ArrayList<TypeBookable> getAll(){
		
		ArrayList<TypeBookable> typeBookables= new ArrayList<TypeBookable>();
		try{
			Statement stmt = FactoryConection.getInstancia().getConn().createStatement();
			ResultSet rs=stmt.executeQuery("Select * from type_bookable");
			if(rs!=null){
				while(rs.next()){
					TypeBookable tb = buildTypeBookable(rs);
					typeBookables.add(tb);
				}			
			}
		} catch(SQLException e) {
			e.printStackTrace();			
		}
		return typeBookables;
	}
	
	public static TypeBookable getById(int id){
		return null;
	}
	
	public static List<TypeBookable> getAllByPermission(int restriction){
		return null;
	}
	
	public static List<TypeBookable> getByHourLimit(int HoursLimit){
		return null;
	}
	
	public static List<TypeBookable> getByDayLimit(int DaysLimit){
		return null;
	}
public void add(TypeBookable b) {
		
	}
	
	public void update(TypeBookable b){
		
	}

	public void delete(TypeBookable b){
		
	}
}
