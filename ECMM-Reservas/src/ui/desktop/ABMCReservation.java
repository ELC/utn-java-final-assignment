package ui.desktop;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import data.DataReservation;
import entities.Reservation;
import entities.TypeBookable;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.GroupLayout;
import javax.swing.JButton;

public class ABMCReservation extends JFrame {

	private DataReservation dataR = new DataReservation();
	private JPanel contentPane;
	private JTextField IDReservation;
	private JTextField DateRes;
	private JTextField Detail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMCReservation frame = new ABMCReservation();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ABMCReservation() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdReservation = new JLabel("ID Reservation");
		lblIdReservation.setBounds(20, 24, 92, 14);
		contentPane.add(lblIdReservation);
		
		IDReservation = new JTextField();
		IDReservation.setBounds(109, 21, 106, 20);
		contentPane.add(IDReservation);
		IDReservation.setColumns(10);
		
		
		
		JLabel lblPerson = new JLabel("Person");
		lblPerson.setBounds(20, 68, 46, 14);
		contentPane.add(lblPerson);
		
		JComboBox comboPersona = new JComboBox();
		comboPersona.setBounds(109, 65, 106, 20);
		contentPane.add(comboPersona);
		// /////////////
		
		// /////////////
		JLabel lblBookable = new JLabel("Bookable");
		lblBookable.setBounds(20, 116, 63, 14);
		contentPane.add(lblBookable);
		
		JComboBox comboBookable = new JComboBox();
		comboBookable.setBounds(109, 113, 106, 20);
		contentPane.add(comboBookable);
		
		
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(20, 165, 46, 14);
		contentPane.add(lblDate);
		
		DateRes = new JTextField();
		DateRes.setBounds(109, 162, 106, 20);
		contentPane.add(DateRes);
		DateRes.setColumns(10);
		
		JLabel lblDetail = new JLabel("Detail");
		lblDetail.setBounds(20, 213, 46, 14);
		contentPane.add(lblDetail);
		
		Detail = new JTextField();
		Detail.setBounds(109, 210, 106, 20);
		contentPane.add(Detail);
		Detail.setColumns(10);
		
		JButton btnAddReservation = new JButton("Add Reservation");
		btnAddReservation.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {}
		});
		btnAddReservation.setBounds(245, 20, 161, 23);
		contentPane.add(btnAddReservation);
		
		JButton btnSearchReservation = new JButton("Search Reservation");
		btnSearchReservation.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {}
		});
		btnSearchReservation.setBounds(245, 64, 161, 23);
		contentPane.add(btnSearchReservation);
		
		JButton btnUpdateReservation = new JButton("Update Reservation");
		btnUpdateReservation.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {}
		});
		btnUpdateReservation.setBounds(245, 112, 161, 23);
		contentPane.add(btnUpdateReservation);
		
		JButton btnDeleteReservation = new JButton("Delete Reservation");
		btnDeleteReservation.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {}
		});
		btnDeleteReservation.setBounds(245, 161, 161, 23);
		contentPane.add(btnDeleteReservation);
	}
	
	
	public void addClick()
	{
		Reservation r=this.mapearDeForm();
		dataR.add(r);
	}
	
	public void findClick()
	{
		Reservation r= this.mapearDeForm();
		this.mapearAForm(dataR.getByIDRes(r.getId()));
	}
	
	public void deleteClick()
	{
		Reservation r= this.mapearDeForm();
		dataR.delete(dataR.getByIDRes(r.getId()));
	}

	public void updateClick()
	{
		Reservation r= this.mapearDeForm();
		dataR.update(r);
	}
	
	private void mapearAForm(Reservation r) 
	{   
		if(!String.valueOf(r.getId()).isEmpty()) 
		{
			this.IDReservation.setText(String.valueOf(r.getId()));
		}
		this.DateRes.setText(String.valueOf(r.getDate()));
		this.Detail.setText(r.getDetail());
	}
	
	private Reservation mapearDeForm ()
	{ 
		Reservation r= new Reservation();
		if(!this.IDReservation.getText().isEmpty()) 
		{
			r.setId(Integer.parseInt(this.IDReservation.getText()));
		}
		//r.setDate(this.DateRes.getText());
		r.setDetail(this.Detail.getText());
	
		return r;
	}
}
