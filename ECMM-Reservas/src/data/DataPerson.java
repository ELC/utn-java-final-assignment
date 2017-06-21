package data;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Person;
import entities.UserRole;
import data.DataUserRoles;

public class DataPerson {
	
	private static Person buildPerson(ResultSet rs) throws SQLException{
		Person p=new Person();
		p.setId(rs.getInt("id_person"));
		p.setName(rs.getString("name_person"));
		p.setLastName(rs.getString("last_name_person"));
		p.setDni(rs.getString("dni"));
		p.setEnabled(rs.getBoolean("enable_person"));
		p.setUsername(rs.getString("user_person"));
		p.setEmail(rs.getString("email_person"));
		UserRole user_role = DataUserRoles.getById(rs.getInt("privileges"));
		p.setPrivileges(user_role.getPrivileges());
		return p;
	}
	
	public static ArrayList<Person> getAll(){
		
		ArrayList<Person> pers= new ArrayList<Person>();
		try{
			Statement stmt = FactoryConection.getInstancia().getConn().createStatement();
			ResultSet rs=stmt.executeQuery("Select * from person");
			if(rs!=null){
				while(rs.next()){
					Person p = buildPerson(rs);
					pers.add(p);
				}			
			}
		} catch(SQLException e) {
			e.printStackTrace();			
		}
		return pers;
	}
	
	public static Person getById(int id){
		return null;
	}
	
	public static ArrayList<Person> getByPermission(int permission){
		return null;
	}
	
	public static Person getByEmail(String email){
		return null;
	}
	
	public static ArrayList<Person> getAllEnable(){
		return null;
	}
	
	public static ArrayList<Person> getAllwithUserRole(UserRole userRole){
		return null;
	}
	
	public static Person getByUsername(String username){
		return null;
	}
	
	public static Person getByDni(int dni){
		return null;
	}

}
