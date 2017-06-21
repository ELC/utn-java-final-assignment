package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.UserRole;

public class DataUserRoles {
	
	private static UserRole buildUserRole(ResultSet rs) throws SQLException{
		UserRole ur=new UserRole();
		ur.setId(rs.getInt("id_user_roles"));
		ur.setName(rs.getString("name_user_roles"));
		ur.setPrivileges(rs.getInt("privileges"));
		return ur;
	}
	
	public static ArrayList<UserRole> getAll(){
		
		ArrayList<UserRole> userRoles= new ArrayList<UserRole>();
		try{
			Statement stmt = FactoryConection.getInstancia().getConn().createStatement();
			ResultSet rs=stmt.executeQuery("Select * from person");
			if(rs!=null){
				while(rs.next()){
					UserRole ur = buildUserRole(rs);
					userRoles.add(ur);
				}			
			}
		} catch(SQLException e) {
			e.printStackTrace();			
		}
		return userRoles;
	}
	
	public static UserRole getById(int id){
		return null;
	}
	
	public static List<UserRole> getAllByPermission(int permission){
		return null;
	}
	
	public static UserRole getByName(String name){
		return null;
	}

}
