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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerDateModel;

import com.toedter.calendar.JCalendar;
import javax.swing.JSpinner;
import java.awt.Color;

public class ABMCReservation extends JInternalFrame {
	private ArrayList<Bookable> books;
	private ControllerABMCBookable ctrlBook = new ControllerABMCBookable();
	private ControllerABMCTypeBookable ctrlType= new ControllerABMCTypeBookable();
	private JComboBox cboType;
	private JScrollPane scrollPane_1;
	private JSpinner spinner;
	private JCalendar cal;
	private Date dateSpinner;
	private Date dateBooking;

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
		setClosable(true);
		setBounds(100, 100, 527, 421);
		
		JLabel lblTypebookable = new JLabel("Bookable's Type");
		lblTypebookable.setBounds(10, 11, 77, 14);
		
		cboType = new JComboBox();
		cboType.setBounds(10, 36, 110, 20);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(148, 11, 225, 173);
		
		JComboBox cboBookables = new JComboBox();
		cboBookables.setBounds(10, 208, 363, 20);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setBounds(10, 64, 77, 14);
		spinner = new JSpinner( new SpinnerDateModel() );
		spinner.setBounds(10, 84, 74, 20);
		
		JButton btnSearchBookables = new JButton("Search bookables");
		btnSearchBookables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			 dateSpinner=(Date)spinner.getValue();
			 dateBooking=cal.getDate();
			dateBooking.setMinutes(dateSpinner.getMinutes());
			dateBooking.setHours(dateSpinner.getHours());
			loadListAvailableBookable();
			
			
 			}
		});
		btnSearchBookables.setBounds(10, 115, 117, 23);
		
		cal = new JCalendar();
		scrollPane_1.setViewportView(cal);
	    cal.getDayChooser().addPropertyChangeListener("day", new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent e) {
		    	
		    }});
		loadListTypeBookables();
		
		
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
		btnAddBooking.setBackground(Color.GREEN);
		btnAddBooking.setForeground(Color.WHITE);
		btnAddBooking.setBounds(10, 143, 117, 23);
		getContentPane().add(btnAddBooking);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				frame.dispose();
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(Color.RED);
		btnCancelar.setBounds(10, 172, 117, 23);
		getContentPane().add(btnCancelar);
		
		
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
			this.books=ctrlBook.getAllAvailable((TypeBookable)cboType.getSelectedItem(), dateBooking);
		} catch (Exception e){
			JOptionPane.showMessageDialog(this,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
	
		}
		
		
	
	}

	public Date convertStringToDate(String str) {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm"); //o el formato que prefieras
		Date startDate = null;
		try {
			  startDate = (Date) df.parse(str);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return startDate;
	}
	public String convertStringToDate2(Date date) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(date);
	}
}





