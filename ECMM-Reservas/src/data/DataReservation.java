package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Person;
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
					"insert into reservation(id_persona, id_bookable, time, detail) values (?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setInt(1, re.getPerson().getId());
			stmt.setInt(2, re.getBookable().getId());
			stmt.setDate(3, re.getDate());
			stmt.setString(4, re.getDetail());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				re.setId(keyResultSet.getInt(1));
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
	
	public List<Reservation> getByIdPerson(Person p){
		List<Reservation> res = new ArrayList<Reservation>();
		Reservation r = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConection.getInstancia().getConn().prepareStatement(
					"select * from Reservation where id_persona=?");
			stmt.setInt(1, p.getId());
			rs=stmt.executeQuery();
			if(rs!=null){
				while(rs.next()){
					Reservation re = buildReservation(rs);
					res.add(re);
				}
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
		return res;
	}
	
	public void delete(Reservation re) {
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConection.getInstancia().getConn()
					.prepareStatement(
					"delete from reservation where id_reservation=?");
			stmt.setInt(1, re.getPerson().getId());
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

	public void update(Reservation r) {
		// TODO Auto-generated method stub
		
	}

	public Reservation getByIDRes(int id) {
		// TODO Auto-generated method stub
		return null;
	}	
}
