package ui.desktop;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import entities.Bookable;
import entities.Reservation;
import entities.TypeBookable;
import logic.ControllerABMCBookable;
import logic.ControllerABMCPerson;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CRUDReservation extends JInternalFrame {
	private ControllerABMCBookable ctrlBook= new ControllerABMCBookable();
	private ControllerABMCPerson ctrlPer= new ControllerABMCPerson();
	private JTextField txtIdReservation;
	private JTextField txtTime;
	private JTextField txtBookable;
	private JTextField txtDni;
	private JTextField txtDetail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CRUDReservation frame = new CRUDReservation();
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
	public CRUDReservation() {
		setBounds(100, 100, 450, 300);
		
		JLabel lblIdreservation = new JLabel("Id_Reservation");
		
		txtIdReservation = new JTextField();
		txtIdReservation.setEnabled(false);
		txtIdReservation.setEditable(false);
		txtIdReservation.setColumns(10);
		
		JLabel lblDniperson = new JLabel("Dni_Person");
		
		JLabel lblTime = new JLabel("Time");
		
		txtTime = new JTextField();
		txtTime.setColumns(10);
		
		JLabel lblBookable = new JLabel("Bookable");
		
		txtBookable = new JTextField();
		txtBookable.setEditable(false);
		txtBookable.setEnabled(false);
		txtBookable.setColumns(10);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		
		JButton btnAddReservation = new JButton("Add Reservation");
		btnAddReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnDeleteReservation = new JButton("Delete Reservation");
		btnDeleteReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblDetail = new JLabel("Detail");
		
		txtDetail = new JTextField();
		txtDetail.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblIdreservation)
						.addComponent(lblDniperson)
						.addComponent(lblTime)
						.addComponent(lblBookable)
						.addComponent(lblDetail))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(txtDetail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(221))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtTime, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
								.addComponent(txtDni, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
								.addComponent(txtIdReservation, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
								.addComponent(txtBookable, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnAddReservation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnDeleteReservation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(48))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIdreservation)
						.addComponent(txtIdReservation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddReservation))
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDniperson)
						.addComponent(txtDni, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addComponent(btnDeleteReservation))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(26)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTime)
								.addComponent(txtTime, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtBookable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBookable))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDetail)
						.addComponent(txtDetail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

	}

	private void mapearAForm(Reservation r) {   
		if(!String.valueOf(r.getId()).isEmpty()) {
			this.txtIdReservation.setText(String.valueOf(r.getId()));
		}
		this.txtDni.setText(r.getPerson().getDni());
		this.txtTime.setText(r.getDate().toString());
		this.txtBookable.setText(r.getBookable().getName());
		this.txtDetail.setText(r.getDetail());
		
	
		}
	
	private Reservation mapearDeForm()
	{ 
		Reservation r= new Reservation();
		if(!this.txtIdReservation.getText().isEmpty()) {
			r.setId(Integer.parseInt(this.txtIdReservation.getText()));
		}
		try {
			r.setPerson(ctrlPer.getByDni(this.txtDni.getText()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	r.setDate(this.txtTime)
		r.setDetail(this.txtDetail.getText());
		try {
			r.setBookable(ctrlBook.getByName(this.txtBookable.getText()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return r;
		}	

	private void showReservation(Reservation r) {
		this.mapearAForm(r);
	}
	
	
}
