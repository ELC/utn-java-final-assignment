package ui.desktop;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import entities.Bookable;
import entities.TypeBookable;
import logic.ControllerABMCBookable;
import logic.ControllerABMCTypeBookable;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ABMCReservation extends JInternalFrame {
	private ArrayList<Bookable> books;
	private ControllerABMCBookable ctrlBook = new ControllerABMCBookable();
	private ControllerABMCTypeBookable ctrlType= new ControllerABMCTypeBookable();
	private JTable table;
	private JComboBox cboType;
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private JLabel lblDate;
	private JTextField txtDate;
	private Date startDate;

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
	public ABMCReservation() {
		setBounds(100, 100, 502, 474);
		
		JLabel lblTypebookable = new JLabel("Type_Bookable");
		
		cboType = new JComboBox();
		
		scrollPane = new JScrollPane();
		
		JButton btnMostrar = new JButton("View");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//loadListAvailableBookables();
				loadListAvailableBookable();
				initDataBindings();
			}
		});
		
		btnNewButton = new JButton("Edit");
		
		lblDate = new JLabel("Date");
		
		txtDate = new JTextField();
		txtDate.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 472, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(23)
							.addComponent(lblTypebookable)
							.addGap(18)
							.addComponent(cboType, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
							.addGap(55)
							.addComponent(lblDate)
							.addGap(34)
							.addComponent(txtDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(92)
							.addComponent(btnMostrar, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addGap(85)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTypebookable)
						.addComponent(cboType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDate)
						.addComponent(txtDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnMostrar)
						.addComponent(btnNewButton))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		loadListTypeBookables();
		String dateString = this.txtDate.getText();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); //o el formato que prefieras
		try {
			 startDate = (Date) df.parse(dateString);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}

	private void loadListTypeBookables(){
		try {
			this.cboType.setModel(new DefaultComboBoxModel(ctrlType.getAll().toArray()));
			this.cboType.setSelectedIndex(-1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	private void loadListAvailableBookable() {
		
		
		
		try{
			this.books=ctrlBook.getAllAvailable((TypeBookable)cboType.getSelectedItem(), startDate);
		} catch (Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
	
		}
		
		
		
	}



	protected void initDataBindings() {
		JTableBinding<Bookable, List<Bookable>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ, books, table);
		//
		BeanProperty<Bookable, Integer> bookableBeanProperty = BeanProperty.create("id");
		jTableBinding.addColumnBinding(bookableBeanProperty).setColumnName("Id_Bookable");
		//
		BeanProperty<Bookable, String> bookableBeanProperty_1 = BeanProperty.create("name");
		jTableBinding.addColumnBinding(bookableBeanProperty_1).setColumnName("Name_Bookable");
		//
		jTableBinding.bind();
	}
}





