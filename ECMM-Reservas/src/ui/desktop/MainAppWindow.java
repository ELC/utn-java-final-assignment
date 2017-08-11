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

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
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

public class MainAppWindow extends JFrame {

	JFrame frmFinalAssigment;
	private JMenuItem mntmAbmcPerson;
	private JDesktopPane desktopPane1;
	private JMenu mnTypeBookable;
	private JMenuItem mntmAbmcTypeBookable;
	private JMenu mnLogIn;
	private JMenu mnLogOut;
	private JMenuItem mntmAbmcBookable;
	private JMenuItem mntmCreateBooking;
	private JMenuItem mntmShowAllBooking;
	private JMenuItem mntmDeleteBooking;
	private JMenuItem mntmShowAllPerson;
	private JMenuItem mntmModifyBoking;
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAppWindow window = new MainAppWindow();
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
		
		JMenu mnPerson = new JMenu("Person");
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
		
		JMenu mnBookable = new JMenu("Bookable");
		menuBar.add(mnBookable);
		
		mntmAbmcBookable = new JMenuItem("CRUD");
		mntmAbmcBookable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showABMCBookable();
			
			}
		});
		mnBookable.add(mntmAbmcBookable);
		
		JMenu mnBooking = new JMenu("Booking");
		menuBar.add(mnBooking);
		
		mntmCreateBooking = new JMenuItem("Create");
		mnBooking.add(mntmCreateBooking);
		
		mntmModifyBoking = new JMenuItem("Modify");
		mnBooking.add(mntmModifyBoking);
		
		mntmShowAllBooking = new JMenuItem("Show All");
		mnBooking.add(mntmShowAllBooking);
		
		Component verticalStrut = Box.createVerticalStrut(10);
		mnBooking.add(verticalStrut);
		
		mntmDeleteBooking = new JMenuItem("Delete");
		mnBooking.add(mntmDeleteBooking);
		
		mnLogIn = new JMenu("Log In");
		mnLogIn.setHorizontalAlignment(SwingConstants.RIGHT);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(mnLogIn);
		
		mnLogOut = new JMenu("Log Out");
		menuBar.add(mnLogOut);
		
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
	}

		protected void showABMCBookable() {
			ABMCBookable frmBookable= new ABMCBookable();
			desktopPane.add(frmBookable);
			frmBookable.setVisible(true);
			
			
			
		}
	
	
		protected void showABMCTypeBookable() {
		// TODO Auto-generated method stub

			ABMCType_Bookable frmTypeBookable=new ABMCType_Bookable();
			desktopPane.add(frmTypeBookable);
			frmTypeBookable.setVisible(true);
	}

		protected void showABMCPerson() {
			
			ABMCPerson frmPer=new ABMCPerson();
			desktopPane.add(frmPer);
			frmPer.setVisible(true);
			
			
			
		}
}

