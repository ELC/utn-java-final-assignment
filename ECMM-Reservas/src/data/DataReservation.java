package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.Reservation;

public class DataReservation {
	
	private Reservation buildReservation(ResultSet rs) throws SQLException{
		DataPerson dataPer= new DataPerson();
		DataBookable databook=new DataBookable();
		Reservation re= new Reservation();
		//re.setId(rs.getInt("id_type_bookable"));
		re.setPerson(dataPer.getById(rs.getInt("id_persona")));
		re.setBookable(databook.getById(rs.getInt("id_bookable")));
		re.setDate(rs.getDate("time"));
		return re;
	}
	
	
	public ArrayList<Reservation> getAll(){
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Reservation> res= new ArrayList<Reservation>();
		try{
			stmt= FactoryConection.getInstancia().getConn().createStatement();
			rs=stmt.executeQuery("Select * from reservation");
			if(rs!=null){
				while(rs.next()){
					Reservation re = buildReservation(rs);
					res.add(re);
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
		
		return res;
	}
	
	
	
	public void add(Reservation re){
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"insert into reservation(id_persona,id_bookable,time) values (?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setInt(1, re.getPerson().getId());
			stmt.setInt(2, re.getBookable().getId());
			stmt.setDate(3, re.getDate());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
			//	p.setId(keyResultSet.getInt(1));
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
	
	
	
	public Reservation getByIdPerson(Reservation re){
		Reservation r=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"select * from Reservation where id_persona=?");
			stmt.setInt(1, re.getPerson().getId());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
				r=buildReservation(rs);
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
		
		return r;
	}
	
}
