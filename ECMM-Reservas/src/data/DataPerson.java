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
		p.setEmail(rs.getString("email"));
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
		
		
		Person p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"select * from person where id_person=?");
			stmt.setInt(1, id);
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
	
	public ArrayList<Person> getByPermission(int permission){
		return null;
	}
	
	public Person getByEmail(String email){
		Person p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"select * from person where email=?");
			stmt.setString(1, email);
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
	
	public ArrayList<Person> getAllEnable(){
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Person> pers= new ArrayList<Person>();
		try{
			stmt= FactoryConection.getInstancia().getConn().createStatement();
			rs=stmt.executeQuery("Select * from person where enable_person=1");
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
	
	public ArrayList<Person> getAllwithUserRole(UserRole userRole){
		return null;
	}
	
	public Person getByUsername(String username){
		Person p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"select * from person where user_person=?");
			stmt.setString(1,username);
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
	
	public Person getByDni(Person per){
		Person p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"select * from person where dni=?");
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
					"insert into person(dni, nombre, apellido, habilitado) values (?,?,?,?)",
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
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"delete from person where dni=?");
			stmt.setString(1, p.getDni());
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
	public void update(Person p){
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"update persona set name_person=?, last_name_person=?, user_person= ?, email=?, password_person=?, enable person=? where dni=?");
			stmt.setString(1, p.getName());
			stmt.setString(2, p.getLastName());
			stmt.setString(3, p.getUsername());
			stmt.setString(4, p.getDni());
			stmt.setString(5, p.getEmail());
			stmt.setString(6, p.getPassword());
			stmt.setBoolean(7, p.isEnabled());
			stmt.setString(8, p.getDni());
			
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
