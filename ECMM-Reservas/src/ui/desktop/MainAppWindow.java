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

public class MainAppWindow extends JFrame {

	JFrame frmAbmPerson;
	private JMenuItem mntmAbmc;
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAppWindow window = new MainAppWindow();
					window.frmAbmPerson.setVisible(true);
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
		frmAbmPerson = new JFrame();
		frmAbmPerson.setTitle("Final Assignment Java2017");
		frmAbmPerson.setBounds(100, 100, 641, 428);
		frmAbmPerson.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAbmPerson.getContentPane().setLayout(new BorderLayout(0, 0));
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		frmAbmPerson.getContentPane().add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 97, 21);
		desktopPane.add(menuBar);
		
		JMenu mnas = new JMenu("Person");
		menuBar.add(mnas);
		
		mntmAbmc = new JMenuItem("ABMC");
		mntmAbmc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			showABMCPerson();
			
			}
		});
		mnas.add(mntmAbmc);
		
		JDesktopPane desktopPane_1 = new JDesktopPane();
		desktopPane_1.setBounds(287, 157, 1, 1);
		desktopPane.add(desktopPane_1);
	}


		protected void showABMCPerson() {
			
			ABMCPerson frmPer=new ABMCPerson();
			desktopPane.add(frmPer);
			frmPer.setVisible(true);
			
			
			
		}







}

