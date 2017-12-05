package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import entities.Bookable;
import entities.TypeBookable;
import logic.ControllerABMCTypeBookable;
import util.AppDataException;

public class DataBookable {
	
	public static Bookable buildBookable(ResultSet rs) throws SQLException{
		ControllerABMCTypeBookable ctrlType= new ControllerABMCTypeBookable();
		Bookable b= new Bookable();
		b.setId(rs.getInt("id_bookable"));
		b.setName(rs.getString("name_bookable"));
		TypeBookable type_bookable=null;
	    try {
			type_bookable = ctrlType.getById(rs.getInt("id_type_bookable"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    b.setType(type_bookable); 
		return b;
	}
	
	public ArrayList<Bookable> getAll() throws Exception{
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Bookable> bookables= new ArrayList<Bookable>();
		try{
			 stmt = FactoryConection.getInstancia().getConn().createStatement();
			 rs=stmt.executeQuery("Select * from bookable");
			if(rs!=null){
				while(rs.next()){
					Bookable b = buildBookable(rs);
					bookables.add(b);
				}			
			}
		} catch(SQLException e) {
			throw e;			
		} finally {	
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return bookables;
	}
	
	public  Bookable getById(int id) throws Exception{
		Bookable b= new Bookable();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt= FactoryConection.getInstancia().getConn().prepareStatement(		
					"select * from bookable where id_bookable=?");
			stmt.setInt(1, id); 
			rs = stmt.executeQuery();
			if(rs!=null && rs.next()){
				b=buildBookable(rs);
			}
		} catch (SQLException e) {
			throw e;
		} finally {	
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return b;
	}
	
	public  Bookable getByName(Bookable b) throws Exception{
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt= FactoryConection.getInstancia().getConn().prepareStatement(		
					"select * from bookable bk inner join type_bookable type on bk.id_type_bookable=type.id_type_bookable where name_bookable=?"); 
			if(!b.getName().isEmpty()){
			stmt.setString(1, b.getName());
			} else { throw new AppDataException(null,"Ha ocurrido un error en la base de datos");
			  }
			rs = stmt.executeQuery();
			if(rs!=null && rs.next()){
				b.setType(new TypeBookable());
				b.getType().setId(rs.getInt("id_type_bookable"));
				b.getType().setName(rs.getString("name_type_bookable"));
				b.setId(rs.getInt("id_bookable"));
				b.setName(rs.getString("name_bookable"));
			} else {throw new SQLException();}
		} catch (SQLException e) {
			throw new AppDataException(null,"Ha ocurrido un error en la base de datos");
		} finally {	
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return b;
	}
	
public  Bookable getByName(String name) throws Exception{
		Bookable b= new Bookable();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt= FactoryConection.getInstancia().getConn().prepareStatement(		
					"select * from bookable where name_bookable=?");
			stmt.setString(1, name); 
			rs = stmt.executeQuery();
			if(rs!=null && rs.next()){
				b=buildBookable(rs);
			}
		} catch (Exception e) {
			throw e;
		} finally {	
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return b;
	}
	
	public  ArrayList<Bookable> getAllByType(TypeBookable bookable_type) throws Exception{
		ArrayList<Bookable> bookables= new ArrayList<Bookable>();
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt= FactoryConection.getInstancia().getConn().prepareStatement(		
					"select * from bookable where id_type_bookable=?");
			stmt.setInt(1, bookable_type.getId()); 
			rs = stmt.executeQuery();
			if(rs!=null){
				while(rs.next()){
					Bookable b=buildBookable(rs);
					bookables.add(b);
				}			
			}
		} catch (SQLException e) {
			throw e;
		} finally {	
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		return bookables;
	}

	public void add(Bookable b) throws Exception{
		ResultSet keyResultSet=null;
		PreparedStatement stmt=null;
		try {
			stmt= FactoryConection.getInstancia().getConn().prepareStatement(
			"insert into bookable (name_bookable, id_type_bookable) values(?,?)",
			PreparedStatement.RETURN_GENERATED_KEYS);
			if(!b.getName().isEmpty()) {
			stmt.setString(1, b.getName());
			} 
			else {throw new AppDataException(null,"Ha ocurrido un error en la base de datos");			
		    } 
			stmt.setInt(2, b.getType().getId());
			stmt.executeUpdate();
			stmt.getGeneratedKeys();
			keyResultSet= stmt.getGeneratedKeys(); 
			if(keyResultSet!=null && keyResultSet.next()) {
				
				b.setId(keyResultSet.getInt(1));
			}
			
		} catch (SQLException e) {
			throw new AppDataException(null,"Ha ocurrido un error en la base de datos");	
		} finally {
			try {
				if(keyResultSet!=null) {keyResultSet.close();}
				if (stmt!=null){
					stmt.close();
				}
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public void update(Bookable b)throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"update bookable set name_bookable=?, id_type_bookable=? where id_bookable=?");
			stmt.setString(1, b.getName());
			stmt.setInt(2, b.getType().getId());
			stmt.setInt(3, b.getId());
			
			int rowsAfected = stmt.executeUpdate();
			if (rowsAfected==0){
				throw new AppDataException(null, "Elemento Inexistente");
			}	
			
		
		} catch (AppDataException apd) {
			throw apd;
		} finally {
			try {
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void delete(Bookable b)throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"delete from bookable where name_bookable=?");
			stmt.setString(1, b.getName());
			int rowsAfected = stmt.executeUpdate();
			if (rowsAfected==0){
				throw new AppDataException(null, "Elemento inexistente");
			}	
			
		
		} catch (AppDataException apd) {
			throw apd;
		} finally {
			try {
				if(stmt!=null)stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
	}
	
	public ArrayList<Bookable> getAvailableBookable(TypeBookable type,Timestamp date)throws Exception{
		
		ArrayList<Bookable> bookables= new ArrayList<Bookable>();
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt= FactoryConection.getInstancia().getConn().prepareStatement(
					"select bk.id_bookable, bk.name_bookable " +  
					"from bookable bk  " +
					"where bk.id_type_bookable=? and bk.id_bookable not in ( " +
					"	select res.id_bookable " +
					"	from reservation res " +
					"	where ((? >= res.dateTimeReservation) and (? < addTime(res.dateTimeReservation, ?))) or " + 
					"    ((? <= res.dateTimeReservation) and (addTime(?, ?) > res.dateTimeReservation)) " +
					");");
			String fecha = date.toString();
			String limit = type.getHourslimit();
			stmt.setInt(1, type.getId());
			stmt.setString(2, fecha);
			stmt.setString(3, fecha);
			stmt.setString(4, limit);
			stmt.setString(5, fecha);
			stmt.setString(6, fecha);
			stmt.setString(7, limit);
			rs = stmt.executeQuery();
			if(rs!=null){
				while(rs.next()){
					Bookable b = new Bookable();
					b.setId(rs.getInt("id_bookable"));
					b.setName(rs.getString("name_bookable"));
					bookables.add(b);
				}	
			}
		} catch (SQLException e) {
			throw e;
		} finally {	
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConection.getInstancia().releaseConn();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}	
		return bookables;
	}	
}