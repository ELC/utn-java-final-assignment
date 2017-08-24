package ui.desktop;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import data.DataTypeBookable;
import entities.Person;
import entities.TypeBookable;
import logic.ControllerABMCTypeBookable;
import util.AppDataException;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ABMCType_Bookable extends JInternalFrame {
	private ControllerABMCTypeBookable ctrlType= new ControllerABMCTypeBookable();
	private JTextField IDTypeBookable;
	private JTextField NameTypeBookable;
	private JTextField HoursLimit;
	private JTextField DaysLimit;
	private JTextField Restriction;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMCType_Bookable frame = new ABMCType_Bookable();
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
	public ABMCType_Bookable() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblIdTypeBookable = new JLabel("ID Type Bookable");
		lblIdTypeBookable.setBounds(10, 11, 94, 14);
		getContentPane().add(lblIdTypeBookable);
		
		IDTypeBookable = new JTextField();
		IDTypeBookable.setBackground(Color.BLACK);
		IDTypeBookable.setEnabled(false);
		IDTypeBookable.setEditable(false);
		IDTypeBookable.setBounds(118, 8, 86, 20);
		getContentPane().add(IDTypeBookable);
		IDTypeBookable.setColumns(10);
		
		JLabel lblNameTypeBookable = new JLabel("Name Type Bookable");
		lblNameTypeBookable.setBounds(10, 53, 110, 14);
		getContentPane().add(lblNameTypeBookable);
		
		NameTypeBookable = new JTextField();
		NameTypeBookable.setBounds(118, 50, 86, 20);
		getContentPane().add(NameTypeBookable);
		NameTypeBookable.setColumns(10);
		
		JLabel lblHoursLimit = new JLabel("Hours Limit");
		lblHoursLimit.setBounds(10, 100, 74, 14);
		getContentPane().add(lblHoursLimit);
		
		HoursLimit = new JTextField();
		HoursLimit.setBounds(118, 97, 86, 20);
		getContentPane().add(HoursLimit);
		HoursLimit.setColumns(10);
		
		JLabel lblDaysLimit = new JLabel("Days Limit");
		lblDaysLimit.setBounds(10, 141, 74, 14);
		getContentPane().add(lblDaysLimit);
		
		DaysLimit = new JTextField();
		DaysLimit.setBounds(118, 138, 86, 20);
		getContentPane().add(DaysLimit);
		DaysLimit.setColumns(10);
		
		JLabel lblRestrictions = new JLabel("Restrictions");
		lblRestrictions.setBounds(10, 182, 74, 14);
		getContentPane().add(lblRestrictions);
		
		Restriction = new JTextField();
		Restriction.setEnabled(false);
		Restriction.setEditable(false);
		Restriction.setBounds(118, 179, 86, 20);
		getContentPane().add(Restriction);
		Restriction.setColumns(10);
		
		JButton btnAddTypeBookable = new JButton("Add Type Bookable");
		btnAddTypeBookable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			addClick();
			}
		});
		btnAddTypeBookable.setBounds(260, 49, 144, 23);
		getContentPane().add(btnAddTypeBookable);
		
		JButton btnFindTypeBookable = new JButton("Find Type Bookable");
		btnFindTypeBookable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			findClick();
			}
		});
		btnFindTypeBookable.setBounds(260, 7, 144, 23);
		getContentPane().add(btnFindTypeBookable);
		
		JButton btnUpdateTypeBookable = new JButton("Update Type Bookable");
		btnUpdateTypeBookable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			updateClick();
			}
		});
		btnUpdateTypeBookable.setBounds(260, 96, 144, 23);
		getContentPane().add(btnUpdateTypeBookable);
		
		JButton btnDeleteTypeBookable = new JButton("Delete Type Bookable");
		btnDeleteTypeBookable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			deleteClick();
			}
		});
		btnDeleteTypeBookable.setBounds(260, 137, 144, 23);
		getContentPane().add(btnDeleteTypeBookable);
		
		JLabel lblversinPromocin = new JLabel("(Versi\u00F3n Promoci\u00F3n)");
		lblversinPromocin.setBounds(235, 182, 129, 14);
		getContentPane().add(lblversinPromocin);

	}
	
	public void addClick(){
		try {
			ctrlType.RegisterTypeBookable(this.mapearDeForm());
			showTypeBookable(ctrlType.getByName(this.mapearDeForm()));
			JOptionPane.showMessageDialog(null,"Tipo de Elemento Agregado con exito");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this,"Error al agregar un tipo de elemento");
		}
		}
	
	public void findClick(){
		try {
			this.mapearAForm(ctrlType.getByName(this.mapearDeForm()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,"Error, Tipo de elemento Inexistente, intentelo de nuevo");
			
		}
		
		}
	
	public void deleteClick(){
		try {
			ctrlType.DeleteTypeBookable(this.mapearDeForm());
			JOptionPane.showMessageDialog(null, "Tipo de elemento borrado exitosamente");

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error al borrar el tipo de elemento");
		}
	}

	public void updateClick(){
		try {
			ctrlType.ModifyTypeBookable(this.mapearDeForm());
			JOptionPane.showMessageDialog(null, "Modificación exitosa");
			showTypeBookable(ctrlType.getByName(this.mapearDeForm()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "No existe un tipo de elemento con ese nombre");

		}
	}
	
	private void mapearAForm(TypeBookable t) {   
		if(!String.valueOf(t.getId()).isEmpty()) {
			this.IDTypeBookable.setText(String.valueOf(t.getId()));
		}
		this.NameTypeBookable.setText(t.getName());
		if(!String.valueOf(t.getDayslimit()).isEmpty()) {
			this.DaysLimit.setText(String.valueOf(t.getDayslimit()));
		}
		
		if(!String.valueOf(t.getHourslimit()).isEmpty()) {
			this.HoursLimit.setText(String.valueOf(t.getHourslimit()));
		}
		//this.Restriction.setText(String.valueOf(t.getRestriction()));
		
		
		}
	
	private TypeBookable mapearDeForm ()
	{ 
		TypeBookable t= new TypeBookable();
		if(!this.IDTypeBookable.getText().isEmpty()) {
			t.setId(Integer.parseInt(this.IDTypeBookable.getText()));
		}
		t.setName(this.NameTypeBookable.getText());
		if(!this.HoursLimit.getText().isEmpty()) {
			t.setHourslimit(Integer.parseInt(this.HoursLimit.getText()));
		}
		if(!this.DaysLimit.getText().isEmpty()) {
				t.setDayslimit(Integer.parseInt(this.DaysLimit.getText()));
		}
		

		//t.setRestriction(Integer.parseInt(this.Restriction.getText()));
	
		return t;
		}

	public void showTypeBookable(TypeBookable t){
		this.mapearAForm(t);
	}
}
