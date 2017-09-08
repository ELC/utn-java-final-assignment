package ui.desktop;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import entities.TypeBookable;
import logic.ControllerABMCTypeBookable;
import util.Util;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JSpinner;

public class ABMCType_Bookable extends JInternalFrame {
	private ControllerABMCTypeBookable ctrlType= new ControllerABMCTypeBookable();
	private JTextField IDTypeBookable;
	private JTextField NameTypeBookable;
	private JTextField DaysLimit;
	private JTextField Restriction;
	private JSpinner spinner;

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
		
		spinner = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinner, "HH:mm");
		spinner.setEditor(timeEditor);
		spinner.setValue(new Date());
		spinner.setBounds(118, 97, 86, 20);
		getContentPane().add(spinner);

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
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error al borrar el tipo de elemento");
		}
	}

	public void updateClick(){
		try {
			ctrlType.ModifyTypeBookable(this.mapearDeForm());
			JOptionPane.showMessageDialog(null, "Modificación exitosa");
			showTypeBookable(ctrlType.getByName(this.mapearDeForm()));
		} catch (Exception e) {
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
			this.spinner.setValue(this.convertStringToDate(t.getHourslimit()));
		}
		//this.Restriction.setText(String.valueOf(t.getRestriction()));
	}
	
	private TypeBookable mapearDeForm (){ 
		TypeBookable t= new TypeBookable();
		if(!this.IDTypeBookable.getText().isEmpty()) {
			t.setId(Integer.parseInt(this.IDTypeBookable.getText()));
		}
		t.setName(this.NameTypeBookable.getText());
		
		t.setHourslimit(Util.convertTimeToString((java.util.Date)spinner.getValue()));
			
		if(!this.DaysLimit.getText().isEmpty()) {
				t.setDayslimit(Integer.parseInt(this.DaysLimit.getText()));
		}
		//t.setRestriction(Integer.parseInt(this.Restriction.getText()));
		return t;
	}

	public Date convertStringToDate(String str) {
		DateFormat df = new SimpleDateFormat("HH:mm"); //o el formato que prefieras
		Date startDate = null;
		try {
			  startDate = (Date) df.parse(str);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return startDate;
	}
	
	public void showTypeBookable(TypeBookable t){
		this.mapearAForm(t);
	}
}
