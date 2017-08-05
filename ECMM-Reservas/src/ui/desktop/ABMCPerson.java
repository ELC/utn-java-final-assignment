package ui.desktop;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import data.DataPerson;
import entities.Person;

import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;

public class ABMCPerson extends JInternalFrame {
	private DataPerson dataPer= new DataPerson();
	
	private JTextField txtName_Person;
	private JTextField txtLast_Name_Person;
	private JTextField txtDni;
	private JTextField txtId_Person;
	private JTextField txtUser_Person;
	private JTextField txtPassword;
	private JCheckBox chckbxEnable;
	private JTextField txtEmail;
	private JLabel lblEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ABMCPerson frame = new ABMCPerson();
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
	public ABMCPerson() {
		try {
			setClosed(true);
		} catch (PropertyVetoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setClosable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		getContentPane().setBackground(Color.GRAY);
		setBounds(100, 100, 450, 384);
		getContentPane().setLayout(null);
		
		JLabel lblNameperson = new JLabel("Name_Person :");
		lblNameperson.setBounds(28, 52, 85, 14);
		getContentPane().add(lblNameperson);
		
		txtName_Person = new JTextField();
		txtName_Person.setBounds(131, 49, 86, 20);
		getContentPane().add(txtName_Person);
		txtName_Person.setColumns(10);
		
		JLabel lblLastnameperson = new JLabel("Last_Name_Person :");
		lblLastnameperson.setBounds(10, 93, 101, 14);
		getContentPane().add(lblLastnameperson);
		
		txtLast_Name_Person = new JTextField();
		txtLast_Name_Person.setBounds(131, 90, 86, 20);
		getContentPane().add(txtLast_Name_Person);
		txtLast_Name_Person.setColumns(10);
		
		JLabel lblDni = new JLabel("DNI :");
		lblDni.setBounds(59, 130, 46, 14);
		getContentPane().add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setBounds(131, 127, 86, 20);
		getContentPane().add(txtDni);
		txtDni.setColumns(10);
		
		JLabel lblIdperson = new JLabel("Id_Person :");
		lblIdperson.setBounds(45, 11, 68, 14);
		getContentPane().add(lblIdperson);
		
		txtId_Person = new JTextField();
		txtId_Person.setBackground(Color.BLACK);
		txtId_Person.setEnabled(false);
		txtId_Person.setBounds(131, 8, 86, 20);
		getContentPane().add(txtId_Person);
		txtId_Person.setColumns(10);
		
		JLabel lblUserperson = new JLabel("User_Person :");
		lblUserperson.setBounds(45, 172, 68, 14);
		getContentPane().add(lblUserperson);
		
		txtUser_Person = new JTextField();
		txtUser_Person.setBounds(131, 169, 86, 20);
		getContentPane().add(txtUser_Person);
		txtUser_Person.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(45, 211, 73, 14);
		getContentPane().add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(131, 208, 86, 20);
		getContentPane().add(txtPassword);
		txtPassword.setColumns(10);
		
		
		
		JLabel lblEnableperson = new JLabel("Enable_Person :");
		lblEnableperson.setBounds(28, 282, 85, 14);
		getContentPane().add(lblEnableperson);
		
		chckbxEnable = new JCheckBox("Enable");
		chckbxEnable.setBounds(131, 278, 86, 23);
		getContentPane().add(chckbxEnable);
		
		JButton btnAddPerson = new JButton("Add Person");
		btnAddPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addClick();

			}
		});
		btnAddPerson.setBounds(262, 89, 109, 23);
		getContentPane().add(btnAddPerson);
		
		JButton btnFindPerson = new JButton("Find Person");
		btnFindPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			findClick();
			}
		});
		btnFindPerson.setBounds(262, 48, 109, 23);
		getContentPane().add(btnFindPerson);
		
		JButton btnUpdatePerson = new JButton("Update Person");
		btnUpdatePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			updateClick();
			}
		});
		btnUpdatePerson.setBounds(262, 138, 109, 23);
		getContentPane().add(btnUpdatePerson);
		
		JButton btnDeletePerson = new JButton("Delete Person");
		btnDeletePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			deleteClick();
			}
		});
		btnDeletePerson.setBounds(262, 187, 109, 23);
		getContentPane().add(btnDeletePerson);
		
		lblEmail = new JLabel("Email :");
		lblEmail.setBounds(59, 245, 46, 14);
		getContentPane().add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(131, 242, 86, 20);
		getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
	}
		
	public void addClick(){
		Person p=this.mapearDeForm();
		dataPer.add(p);
		}
	
	public void findClick(){
		Person p= this.mapearDeForm();
		this.mapearAForm(dataPer.getByDni(p));
		
		}
	
	public void deleteClick(){
		Person p= this.mapearDeForm();
		dataPer.delete(dataPer.getByDni(p));
	}

	public void updateClick(){
		Person p= this.mapearDeForm();
		dataPer.update(p);
	}
	
	private void mapearAForm(Person p) {   
		if(!String.valueOf(p.getId()).isEmpty()) {
			this.txtId_Person.setText(String.valueOf(p.getId()));
		}
		this.txtDni.setText(p.getDni());
		this.txtName_Person.setText(p.getName());
		this.txtLast_Name_Person.setText(p.getLastName());
		this.chckbxEnable.setSelected(p.isEnabled());
		this.txtUser_Person.setText(p.getUsername());
		this.txtPassword.setText(p.getPassword());
		this.txtEmail.setText(p.getEmail());
		
		
		}
	
	private Person mapearDeForm ()
	{ 
		Person p= new Person();
		if(!this.txtId_Person.getText().isEmpty()) {
			p.setId(Integer.parseInt(this.txtId_Person.getText()));
		}
		p.setDni(this.txtDni.getText());
		p.setName(this.txtName_Person.getText());
		p.setLastName(this.txtLast_Name_Person.getText());
		p.setUsername(this.txtUser_Person.getText());
		p.setEnabled(this.chckbxEnable.isSelected());
		p.setPassword(this.txtPassword.getText());
		p.setEmail(this.txtEmail.getText());
	
		return p;
		}	

	
	
	
}
