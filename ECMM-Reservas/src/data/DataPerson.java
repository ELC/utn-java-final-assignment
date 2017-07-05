package data;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entities.Person;
import entities.UserRole;
import data.DataUserRoles;

public class DataPerson {
	
	private Person buildPerson(ResultSet rs) throws SQLException{
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
	
	public ArrayList<Person> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Person> pers= new ArrayList<Person>();
		try{
			stmt= FactoryConection.getInstancia().getConn().createStatement();
			rs=stmt.executeQuery("Select * from person");
			if(rs!=null){
				while(rs.next()){
					Person p = buildPerson(rs);
					pers.add(p);
				}			
			}
		} catch(SQLException e) {
			e.printStackTrace();		
		}
		
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			FactoryConection.getInstancia().releaseConn();
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		
		return pers;
	}
	
	public  Person getById(int id){
		return null;
	}
	
	public ArrayList<Person> getByPermission(int permission){
		return null;
	}
	
	public Person getByEmail(String email){
		return null;
	}
	
	public ArrayList<Person> getAllEnable(){
		return null;
	}
	
	public ArrayList<Person> getAllwithUserRole(UserRole userRole){
		return null;
	}
	
	public Person getByUsername(String username){
		return null;
	}
	
	public Person getByDni(Person per){
		Person p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"select * from person where person_dni=?");
			stmt.setString(1, per.getDni());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
				p=buildPerson(rs);
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
		
		return p;
	}
	public void add(Person p){
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"insert into persona(dni, nombre, apellido, habilitado) values (?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, p.getDni());
			stmt.setString(2, p.getName());
			stmt.setString(3, p.getLastName());
			stmt.setBoolean(4, p.isEnabled());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				p.setId(keyResultSet.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			FactoryConection.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void delete(Person p) {
		
	}
	public void update(Person p){}
	
}
