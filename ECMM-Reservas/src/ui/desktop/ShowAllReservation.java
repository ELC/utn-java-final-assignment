package ui.desktop;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import entities.Reservation;
import logic.ControllerABMCReservation;

import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import java.sql.Timestamp;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ShowAllReservation extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private ArrayList<Reservation> bookings;
	ControllerABMCReservation ctrlRes= new ControllerABMCReservation();

	public ShowAllReservation() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		setClosable(true);
		setBounds(100, 100, 528, 430);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnDelte = new JButton("Delete Reservation");
		btnDelte.setForeground(Color.WHITE);
		btnDelte.setBackground(Color.RED);
		btnDelte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			btnDeleteClick();	
			
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(307, Short.MAX_VALUE)
					.addComponent(btnDelte)
					.addGap(80))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addComponent(btnDelte)
					.addGap(38))
		);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		listado();
		initDataBindings();
	}

	private void btnDeleteClick(){
		int indexBooking;
		
		indexBooking = table.convertRowIndexToModel(table.getSelectedRow());
		try {
			ctrlRes.DeleteReservation(bookings.get(indexBooking));
			JOptionPane.showMessageDialog(null, "Booking deleted");
			listado();
			initDataBindings();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Please, select a column");
		}
	}
	
	private void listado() {
		try{
			this.bookings=(ArrayList<Reservation>) ctrlRes.getAllByUser();
		} catch (Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	protected void initDataBindings() {
		JTableBinding<Reservation, List<Reservation>, JTable> jTableBinding = SwingBindings.createJTableBinding(UpdateStrategy.READ_WRITE, bookings, table);
		//
		BeanProperty<Reservation, String> reservationBeanProperty = BeanProperty.create("bookable.name");
		jTableBinding.addColumnBinding(reservationBeanProperty).setColumnName("Bookable");
		//
		BeanProperty<Reservation, String> reservationBeanProperty_1 = BeanProperty.create("person.name");
		jTableBinding.addColumnBinding(reservationBeanProperty_1).setColumnName("Person");
		//
		BeanProperty<Reservation, Timestamp> reservationBeanProperty_2 = BeanProperty.create("date");
		jTableBinding.addColumnBinding(reservationBeanProperty_2).setColumnName("Date Booking");
		//
		BeanProperty<Reservation, String> reservationBeanProperty_3 = BeanProperty.create("detail");
		jTableBinding.addColumnBinding(reservationBeanProperty_3).setColumnName("Detail");
		//
		jTableBinding.bind();
	}
}