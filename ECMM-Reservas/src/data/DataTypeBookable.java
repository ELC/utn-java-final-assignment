package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Person;
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
	
	public ArrayList<TypeBookable> getAll(){
		
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
	
	public TypeBookable getByName(String name){
		
		TypeBookable t=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"select * from type_bookable where name_type_bookable=?");
			stmt.setString(1, name);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
				t=buildTypeBookable(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			FactoryConection.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return t;
	}
	
	public void add(TypeBookable b) {
		ResultSet keyResultSet=null;
		PreparedStatement stmt=null;
		try {
			stmt= FactoryConection.getInstancia().getConn().prepareStatement(
					"insert into persona (name_type_bookable,restriction,hours_limit,days_limit) values(?,?,?,?) ",
					PreparedStatement.RETURN_GENERATED_KEYS);
	
			stmt.setString(1, b.getName()); // los numeros corresponden a los de ? de la query//
			stmt.setInt(2, b.getRestriction());
			stmt.setInt(3, b.getHourslimit());
			stmt.setInt(4, b.getDayslimit());
			stmt.executeUpdate();
			stmt.getGeneratedKeys();
			keyResultSet= stmt.getGeneratedKeys(); //Preguntar que hace?
			if(keyResultSet!=null && keyResultSet.next()) {
		
				b.setId(keyResultSet.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if(keyResultSet!=null) {keyResultSet.close();}
				if (stmt!=null){
								stmt.close();
				}
				FactoryConection.getInstancia().releaseConn();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void update(TypeBookable b){
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"update type_bookable set name_type_bookable=?, restriction=?, hours_limit= ?, days_limit where id_type_bookable=?");
			stmt.setString(1, b.getName());
			stmt.setInt(2, b.getRestriction());
			stmt.setInt(3, b.getHourslimit());
			stmt.setInt(4, b.getDayslimit());
			stmt.setInt(5, b.getId());
			stmt.executeUpdate();
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConection.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(TypeBookable b){
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"delete from type_bookable where id_bookable=?");
			stmt.setInt(1, b.getId());
			stmt.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConection.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
