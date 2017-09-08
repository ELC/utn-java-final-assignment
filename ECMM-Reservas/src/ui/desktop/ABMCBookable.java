package ui.desktop;

import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import entities.Bookable;
import entities.TypeBookable;
import logic.ControllerABMCBookable;
import logic.ControllerABMCTypeBookable;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JFrame;

public class ABMCBookable extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtIdBookable;
	private JTextField txtName_Bookable;
	private JComboBox cboType;
	private ControllerABMCTypeBookable ctrlTypeBook= new ControllerABMCTypeBookable();
	private ControllerABMCBookable ctrlBook= new ControllerABMCBookable();
	
	public ABMCBookable() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		JLabel lblIdbookable = new JLabel("Id_Bookable");
		
		txtIdBookable = new JTextField();
		txtIdBookable.setEnabled(false);
		txtIdBookable.setEditable(false);
		txtIdBookable.setBackground(Color.BLACK);
		txtIdBookable.setColumns(10);
		
		JLabel lblNamebookable = new JLabel("Name_Bookable");
		
		txtName_Bookable = new JTextField();
		txtName_Bookable.setColumns(10);
		
		JLabel lblTypebookable = new JLabel("Type_Bookable");
		
		
		
		JButton btnSearch = new JButton("Find Bookable");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			searchClick();}
		});
		
		JButton btnAdd = new JButton("Add Bookable");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			addClick();}
		});
		
		JButton btnUpdate = new JButton("Update Bookable");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			updateClick();}
		});
		
		JButton btnDelete = new JButton("Delete Bookable");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			deleteClick();}
		});
		
		cboType = new JComboBox();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblTypebookable)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cboType, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNamebookable)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtName_Bookable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblIdbookable)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(txtIdBookable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(61)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnDelete, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnUpdate, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAdd, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnSearch, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE))
					.addContainerGap(87, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIdbookable)
						.addComponent(txtIdBookable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNamebookable)
						.addComponent(txtName_Bookable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd))
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTypebookable)
						.addComponent(cboType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUpdate))
					.addGap(31)
					.addComponent(btnDelete)
					.addContainerGap(53, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		loadListTypeBookables();
	}

	private void loadListTypeBookables(){
		try {
			this.cboType.setModel(new DefaultComboBoxModel(ctrlTypeBook.getAll().toArray()));
			this.cboType.setSelectedIndex(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	private void searchClick() {
		try {
			this.mapearAForm(ctrlBook.getByName(this.mapearDeForm()));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,"No existe un elemento con ese nombre");
		}
	}
	
	private void addClick(){
		try {
			ctrlBook.RegisterBookable(this.mapearDeForm());
			showBookable(ctrlBook.getByName(this.mapearDeForm()));
			JOptionPane.showMessageDialog(null,"Elemento agregado con exito");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,"Ya existe un elemento con ese nombre");
		}
	}
	
	private void updateClick(){
		try {
			ctrlBook.ModifyBookable(this.mapearDeForm());
			showBookable(ctrlBook.getByName(this.mapearDeForm()));
			JOptionPane.showMessageDialog(null,"elemento modificado con exito");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,"No existe un elemento con ese nombre");
		}
	}
		
	private void deleteClick(){
		try {
			ctrlBook.DeleteBookable(this.mapearDeForm());
			JOptionPane.showMessageDialog(this,"Elemento borrado con exito");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,"No existe un elemento con ese nombre");
		}
	}
	
	private void mapearAForm(Bookable b) {   
		if(!String.valueOf(b.getId()).isEmpty()) {
			this.txtIdBookable.setText(String.valueOf(b.getId()));
		}
		this.txtName_Bookable.setText(b.getName());
		if(b.getType()!=null) {
			this.cboType.setSelectedItem(b.getType());
		}
	}
	
	private Bookable mapearDeForm (){ 
		Bookable b= new Bookable();
		if(!this.txtIdBookable.getText().isEmpty()) {
			b.setId(Integer.parseInt(this.txtIdBookable.getText()));
		}
		b.setName(this.txtName_Bookable.getText());
		if (cboType.getSelectedIndex() != -1){
			b.setType((TypeBookable)this.cboType.getSelectedItem());
		}
		return b;
	}

	private void showBookable(Bookable b) {
		this.mapearAForm(b);
	}
}
