package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Bookable;
import entities.TypeBookable;

public class DataBookable {
	
	private static Bookable buildBookable(ResultSet rs) throws SQLException{
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
			ResultSet rs=stmt.executeQuery("Select * from bookable");
			if(rs!=null){
				while(rs.next()){
					Bookable b = buildBookable(rs);
					bookables.add(b);
				}			
			}
		} catch(SQLException e) {
			e.printStackTrace();			
		}
		return bookables;
	}
	
	public  Bookable getById(int id){
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
		e.printStackTrace();
	}
	
	try {
		if(rs!=null) rs.close();
		if(stmt!=null) stmt.close();
		FactoryConection.getInstancia().releaseConn();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return b;
	}
	
	public static Bookable getByName(int restriction){
		return null;
	}
	
	public static List<Bookable> getAllByType(TypeBookable bookable_type){
		List<Bookable> bookables= new ArrayList<Bookable>();
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
		stmt= FactoryConection.getInstancia().getConn().prepareStatement(		
				"select * from bookable where id_type_bookable=?");
		stmt.setInt(1, bookable_type.getId()); 
		rs = stmt.executeQuery();
		if(rs!=null && rs.next()){
			Bookable b=buildBookable(rs);
			bookables.add(b);
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	try {
		if(rs!=null) rs.close();
		if(stmt!=null) stmt.close();
		FactoryConection.getInstancia().releaseConn();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	return bookables;
		
		
	}

	public void add(Bookable b) {
		ResultSet keyResultSet=null;
		PreparedStatement stmt=null;
try {
	stmt= FactoryConection.getInstancia().getConn().prepareStatement(
	"insert into bookable (name_bookable,id_type_bookable) values(?,?)",
	PreparedStatement.RETURN_GENERATED_KEYS);
	
	stmt.setString(1, b.getName()); 
	stmt.setInt(2, b.getType().getId());
	stmt.executeUpdate();
	stmt.getGeneratedKeys();
	keyResultSet= stmt.getGeneratedKeys(); 
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
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}	
	}
	
	public void update(Bookable b){
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"update bookable set name_bookable=?, id_type_bookable=? where id_bookable=?");
			stmt.setString(1, b.getName());
			stmt.setInt(2, b.getType().getId());
			stmt.setInt(3, b.getId());
			
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

	public void delete(Bookable b){
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"delete from bookable where name_bookable=?");
			stmt.setString(1, b.getName());
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