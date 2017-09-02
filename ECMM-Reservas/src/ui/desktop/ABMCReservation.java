package ui.desktop;

import java.sql.Timestamp;
import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import entities.Bookable;
import entities.Reservation;
import entities.TypeBookable;
import logic.Application;
import logic.ControllerABMCBookable;
import logic.ControllerABMCReservation;
import logic.ControllerABMCTypeBookable;
import util.Util;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import com.toedter.calendar.JCalendar;
import javax.swing.JSpinner;
import java.awt.Color;

public class ABMCReservation extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ControllerABMCReservation ctrlRes= new ControllerABMCReservation();
	private ControllerABMCBookable ctrlBook = new ControllerABMCBookable();
	private ControllerABMCTypeBookable ctrlType= new ControllerABMCTypeBookable();
	private JComboBox cboType;
	private JScrollPane scrollPane_1;
	private JSpinner spinner;
	private JCalendar cal;
	private JComboBox cboBookables;
	private JTextField txtIdReservation;
	private JTextField txtDetail;
	private Timestamp dateBooking;

	public ABMCReservation() {
		setClosable(true);
		setBounds(100, 100, 403, 301);
		
		JLabel lblTypebookable = new JLabel("Bookable's Type");
		lblTypebookable.setBounds(10, 11, 110, 14);
		
		cboType = new JComboBox();
		cboType.setBounds(10, 36, 110, 20);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(148, 11, 225, 173);
		
		cboBookables = new JComboBox();
		cboBookables.setBounds(10, 233, 363, 20);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(10, 64, 77, 14);
		spinner = new JSpinner( new SpinnerDateModel() );
		spinner.setBounds(10, 84, 74, 20);
		
		JButton btnSearchBookables = new JButton("Search bookables");
		btnSearchBookables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			readDateAndTime();			
 			}
		});
		btnSearchBookables.setBounds(10, 115, 117, 23);
		
		cal = new JCalendar();
		scrollPane_1.setViewportView(cal);
	    cal.getDayChooser().addPropertyChangeListener("day", new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent e) {
		    	loadListTypeBookables();
		    }});
		
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spinner, "HH:mm");
		spinner.setEditor(timeEditor);
		spinner.setValue(new Date()); // will only show the current time
		getContentPane().setLayout(null);
		getContentPane().add(btnSearchBookables);
		getContentPane().add(lblTypebookable);
		getContentPane().add(cboType);
		getContentPane().add(lblTime);
		getContentPane().add(spinner);
		getContentPane().add(scrollPane_1);
		getContentPane().add(cboBookables);
		
		JButton btnAddBooking = new JButton("Add Booking");
		btnAddBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			addClick();
			}
		});
		btnAddBooking.setBackground(Color.GREEN);
		btnAddBooking.setForeground(Color.WHITE);
		btnAddBooking.setBounds(10, 143, 117, 23);
		getContentPane().add(btnAddBooking);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CloseWindow();
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.RED);
		btnCancelar.setBounds(10, 172, 117, 23);
		getContentPane().add(btnCancelar);
		
		JLabel lblIdreservation = new JLabel("Booking Code");
		lblIdreservation.setBounds(192, 205, 85, 14);
		getContentPane().add(lblIdreservation);
		
		txtIdReservation = new JTextField();
		txtIdReservation.setBackground(Color.BLACK);
		txtIdReservation.setEnabled(false);
		txtIdReservation.setEditable(false);
		txtIdReservation.setBounds(287, 202, 86, 20);
		getContentPane().add(txtIdReservation);
		txtIdReservation.setColumns(10);
		
		txtDetail = new JTextField();
		txtDetail.setBounds(52, 202, 86, 20);
		getContentPane().add(txtDetail);
		txtDetail.setColumns(10);
		
		JLabel lblDetail = new JLabel("Detail");
		lblDetail.setBounds(10, 206, 46, 14);
		getContentPane().add(lblDetail);
		loadListTypeBookables();
	}

	protected void CloseWindow() {
		this.dispose();
	}

	private void addClick() {
		try {
			ctrlRes.RegisterReservation(this.mapearDeForm());
			JOptionPane.showMessageDialog(this, "Your booking was successfully created!");
			this.dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al intentar agregar una reserva");
		}
	}	
	
	private void readDateAndTime(){
		String fecha = convertDateToString(cal.getDate()) + " " + 
				Util.convertTimeToString((java.util.Date)spinner.getValue());
		dateBooking=Timestamp.valueOf(fecha);
		try {
			loadListAvailableBookable((TypeBookable)cboType.getSelectedItem(), dateBooking);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "No hay elementos disponibles para esa fecha y hora");
		}
	}
	
	private void loadListTypeBookables(){
		try {
			this.cboType.setModel(new DefaultComboBoxModel(ctrlType.getAllByDate(cal.getDate()).toArray()));
			this.cboType.setSelectedIndex(-1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadListAvailableBookable(TypeBookable type, Timestamp dateBooking) throws Exception {
		ArrayList<Bookable> bookables = ctrlBook.getAllAvailable(type,dateBooking);
		if (bookables.isEmpty()){
			throw new Exception();
		}
		this.cboBookables.setModel(new DefaultComboBoxModel(bookables.toArray()));
		this.cboBookables.setSelectedIndex(-1);
	}

	public String convertDateToString(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

	private void mapearAForm(Reservation re) {   
		if(!String.valueOf(re.getId()).isEmpty()) {
			this.txtIdReservation.setText(String.valueOf(re.getId()));
		}
	}
	private Reservation mapearDeForm() { 
		Reservation r= new Reservation();
		if(!this.txtIdReservation.getText().isEmpty()) {
			r.setId(Integer.parseInt(this.txtIdReservation.getText()));
		}
		r.setDetail(this.txtDetail.getText());
		r.setBookable((Bookable)this.cboBookables.getSelectedItem());
		r.setDate(dateBooking);
		r.setPerson(Application.getInstancia().getActivePerson());
		return r;
	}
}