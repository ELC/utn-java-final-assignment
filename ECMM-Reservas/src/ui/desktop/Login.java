package ui.desktop;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import entities.Person;
import logic.ControllerABMCPerson;
import util.AppDataException;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPasswordField;

public class Login extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUserName;
	private ControllerABMCPerson ctrlPer = new ControllerABMCPerson();
	private JPasswordField passwordUser;

	public Login() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setBounds(100, 100, 270, 138);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(10, 14, 89, 14);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(109, 11, 135, 20);
		txtUserName.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 40, 89, 14);
		
		passwordUser = new JPasswordField();
		passwordUser.setBounds(109, 37, 135, 20);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(68, 68, 106, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLoginClick();
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLUE);
		
		txtUserName.addActionListener(btnNewButton.getActionListeners()[0]);
		passwordUser.addActionListener(btnNewButton.getActionListeners()[0]);
		
		getContentPane().setLayout(null);
		getContentPane().add(lblUsername);
		getContentPane().add(txtUserName);
		getContentPane().add(lblPassword);
		getContentPane().add(passwordUser);
		getContentPane().add(btnNewButton);

	}
	private void btnLoginClick(){
		try {
			ctrlPer.LoginPerson(this.mapearDeForm());
			MainAppWindow.window.unlockAll();
			this.dispose();
			
		} 
		catch (AppDataException apd) {
			JOptionPane.showMessageDialog(this, "Contraseña Incorrecta");
		}
			catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Ya hay un usuario logeado");
		}
	}
	
	
	@SuppressWarnings("deprecation")
	private Person mapearDeForm (){ 
		Person p= new Person();
		p.setUsername(this.txtUserName.getText());
		p.setPassword(this.passwordUser.getText());
		return p;
	}	
}
