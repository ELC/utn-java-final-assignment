package ui.desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.ControllerABMCPerson;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainAppWindow extends JFrame {

	JFrame frmFinalAssigment;
	private JDesktopPane desktopPane1;
	private JMenu mnTypeBookable;
	private JMenuItem mntmAbmcTypeBookable;
	private JMenu mnLogIn;
	private JMenu mnLogOut;
	private JMenu mnBooking;
	private JMenu mnBookable;
	private JMenuItem mntmAbmcBookable;
	private JMenuItem mntmCreateBooking;
	private JMenuItem mntmShowAllBooking;
	private JMenuItem mntmShowAllPerson;
	private JDesktopPane desktopPane;
	private JMenu mnPerson;
	private JMenuItem mntmAbmcPerson;
	public static MainAppWindow window;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainAppWindow();
					window.setMinimumSize(new Dimension(550,550));
					window.frmFinalAssigment.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainAppWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFinalAssigment = new JFrame();
		frmFinalAssigment.setTitle("Final Assignment Java2017");
		frmFinalAssigment.setBounds(100, 100, 550, 428);
		frmFinalAssigment.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFinalAssigment.getContentPane().setLayout(new BoxLayout(frmFinalAssigment.getContentPane(), BoxLayout.X_AXIS));
		
		desktopPane1 = new JDesktopPane();
		desktopPane1.setBackground(Color.WHITE);
		frmFinalAssigment.getContentPane().add(desktopPane1);
		desktopPane1.setLayout(new BorderLayout(0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		desktopPane1.add(menuBar, BorderLayout.NORTH);
		
		mnPerson = new JMenu("Person");
		menuBar.add(mnPerson);
		
		mntmAbmcPerson = new JMenuItem("CRUD");
		mntmAbmcPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			showABMCPerson();
			
			}
		});
		mnPerson.add(mntmAbmcPerson);
		
		mntmShowAllPerson = new JMenuItem("Show All");
		mnPerson.add(mntmShowAllPerson);
		
		mnTypeBookable = new JMenu("Type Bookable");
		menuBar.add(mnTypeBookable);
		
		mntmAbmcTypeBookable = new JMenuItem("CRUD");
		mntmAbmcTypeBookable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			showABMCTypeBookable();
			}
		});
		mnTypeBookable.add(mntmAbmcTypeBookable);
		
		mnBookable = new JMenu("Bookable");
		menuBar.add(mnBookable);
		
		mntmAbmcBookable = new JMenuItem("CRUD");
		mntmAbmcBookable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showABMCBookable();
			
			}
		});
		mnBookable.add(mntmAbmcBookable);
		
		mnBooking = new JMenu("Booking");
		menuBar.add(mnBooking);
		
		mntmCreateBooking = new JMenuItem("CRUD");
		mntmCreateBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showABMCReservation();
			}
		});
		mnBooking.add(mntmCreateBooking);
		
		Component verticalStrut = Box.createVerticalStrut(10);
		mnBooking.add(verticalStrut);
		
		mntmShowAllBooking = new JMenuItem("Show All");
		mntmShowAllBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			showAllReservation();
			}
		});
		mnBooking.add(mntmShowAllBooking);
		
		mnLogIn = new JMenu("Log In");
		mnLogIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				openLogIn();
			}
		});

		mnLogIn.setHorizontalAlignment(SwingConstants.RIGHT);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(mnLogIn);
		
		mnLogOut = new JMenu("Log Out");
		menuBar.add(mnLogOut);
		mnLogOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lockAll();
			}
		});
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		desktopPane1.add(desktopPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(20);
		panel.setBackground(Color.BLACK);
		desktopPane1.add(panel, BorderLayout.SOUTH);
		
		JLabel lblTpJava = new JLabel("TP Java 2017");
		lblTpJava.setHorizontalAlignment(SwingConstants.LEFT);
		lblTpJava.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTpJava.setForeground(Color.WHITE);
		panel.add(lblTpJava);
		
		JLabel lblCastaoEzequiel = new JLabel("Casta\u00F1o Ezequiel - Mulassi Mat\u00EDas - Sacchi Priscila");
		lblCastaoEzequiel.setHorizontalAlignment(SwingConstants.LEFT);
		lblCastaoEzequiel.setForeground(Color.WHITE);
		lblCastaoEzequiel.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblCastaoEzequiel);
		
		JLabel lblDasdf = new JLabel("Comisi\u00F3n 3E02");
		lblDasdf.setHorizontalAlignment(SwingConstants.LEFT);
		lblDasdf.setForeground(Color.WHITE);
		lblDasdf.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel.add(lblDasdf);
		lockAll();
	}

		protected void openLogIn() {
		Login login = new Login();
		desktopPane.add(login);
		login.setVisible(true);
	}
		
		public void unlockAll(){
			mnPerson.setEnabled(true);
			mnTypeBookable.setEnabled(true);
			mnBookable.setEnabled(true);
			mnBooking.setEnabled(true);
			mnLogIn.setVisible(false);
			mnLogOut.setVisible(true);
		}
		
		public void lockAll(){
			ControllerABMCPerson ctrlPer= new ControllerABMCPerson();
			ctrlPer.LogOutPerson();
			mnPerson.setEnabled(false);
			mnTypeBookable.setEnabled(false);
			mnBookable.setEnabled(false);
			mnBooking.setEnabled(false);
			mnLogIn.setVisible(true);
			mnLogOut.setVisible(false);
		}		
		
		protected void showABMCBookable() {
			ABMCBookable frmBookable= new ABMCBookable();
			desktopPane.add(frmBookable);
			frmBookable.setVisible(true);
		}
		
		protected void showABMCTypeBookable() {
			ABMCType_Bookable frmTypeBookable=new ABMCType_Bookable();
			desktopPane.add(frmTypeBookable);
			frmTypeBookable.setVisible(true);
	}

		protected void showABMCPerson() {
			ABMCPerson frmPer=new ABMCPerson();
			desktopPane.add(frmPer);
			frmPer.setVisible(true);
			
			
			
		}

protected void showABMCReservation() {
			
			ABMCReservation frmRes=new ABMCReservation();
			desktopPane.add(frmRes);
			frmRes.setVisible(true);
			
			
			
		}

protected void showAllReservation() {
	
	ShowAllReservation frmAllRes=new ShowAllReservation();
	desktopPane.add(frmAllRes);
	frmAllRes.setVisible(true);
	
	
	
}

}

