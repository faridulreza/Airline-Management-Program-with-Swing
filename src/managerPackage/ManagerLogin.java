package managerPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import userPackage.PersonModel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JCheckBox;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Component;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ManagerLogin {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField nameTx;
	private JPasswordField passTx;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private Component horizontalStrut;
	private Component horizontalStrut_1;
	private Component horizontalStrut_2;
	private Component horizontalStrut_3;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private Component horizontalStrut_4;
	private Component horizontalStrut_6;
	private Component horizontalStrut_5;
	private Component verticalStrut_2;
	private JButton lblNewLabel_2;
	private Component verticalStrut_3;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				File f = new File("data\\managerSession.txt");
				if (f.isFile()) {
                     ManagerMainFrame.run();
				} else {
					try {
						ManagerLogin window = new ManagerLogin();
						window.frame.pack();
						window.frame.setSize(450, 360);
						window.frame.setLocationRelativeTo(null);
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	public static void reRun() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerLogin window = new ManagerLogin();
					window.frame.pack();
					window.frame.setSize(450, 360);
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ManagerLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBackground(new Color(0, 0, 0, 0));

		JLabel background = new JLabel(new ImageIcon("images\\airline-wing-admin.jpg"));
		frame.add(background);

		// frame.setBounds(100, 100, 472, 362);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		background.setLayout(new MigLayout("", "[40px][][][][200px][][][][]", "[90px][][][25px][3px][24px][][][][][]"));
		verticalStrut = Box.createVerticalStrut(70);
		background.add(verticalStrut, "cell 4 0");

		verticalStrut_1 = Box.createVerticalStrut(20);
		background.add(verticalStrut_1, "cell 4 1");

		horizontalStrut_5 = Box.createHorizontalStrut(20);
		background.add(horizontalStrut_5, "cell 0 3");

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		background.add(horizontalStrut_1, "cell 1 3");

		lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		background.add(lblNewLabel, "cell 2 3");

		nameTx = new JTextField();
		background.add(nameTx, "cell 4 3,grow");
		nameTx.setColumns(10);

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		background.add(horizontalStrut_2, "cell 6 4");

		horizontalStrut_3 = Box.createHorizontalStrut(20);
		background.add(horizontalStrut_3, "cell 7 4");

		horizontalStrut_6 = Box.createHorizontalStrut(20);
		background.add(horizontalStrut_6, "cell 8 4");

		lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		background.add(lblNewLabel_1, "cell 2 5");

		passTx = new JPasswordField();
		background.add(passTx, "cell 4 5,grow");
		passTx.setColumns(10);

		verticalStrut_2 = Box.createVerticalStrut(20);
		background.add(verticalStrut_2, "cell 4 6");

		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 0));
		background.add(panel, "cell 4 8,growx,aligny top");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(Box.createVerticalStrut(20));
		btnNewButton = new JButton("Login");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setIcon(null);
		btnNewButton.setBackground(SystemColor.textHighlight);
		panel.add(btnNewButton);

		horizontalStrut_4 = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut_4);

		horizontalStrut = Box.createHorizontalStrut(20);
		panel.add(horizontalStrut);

		btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBackground(SystemColor.menu);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnNewButton_1.setIcon(null);
		panel.add(btnNewButton_1);

		verticalStrut_3 = Box.createVerticalStrut(20);
		background.add(verticalStrut_3, "cell 4 9");

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// check for user and open main window

				if (validateForManager()) {
					frame.setVisible(false);
					frame.dispose();
					ManagerMainFrame.run();

				}
			}

		});

	}

	ArrayList<String> managerList;

	private boolean validateForManager() {
		if (managerList == null) {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream("data\\managerList.txt"));
				managerList = (ArrayList<String>) in.readObject();
				in.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Could not fetch Manager Info.", "Read Error",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}

		}

		if (nameTx.getText().trim().isEmpty() || passTx.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Please fill up all the fields", "Blank Field",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		String s = nameTx.getText().trim() + "#" + passTx.getText().trim();

		if (managerList.contains(s)) {
			try {
				File f = new File("data\\managerSession.txt");
				if (f.exists())
					f.delete();

				FileOutputStream fout = new FileOutputStream("data\\managerSession.txt");
				ObjectOutputStream out = new ObjectOutputStream(fout);
				out.writeObject(s);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "Invalid ID or Password", "Error", JOptionPane.ERROR_MESSAGE);
			passTx.setEchoChar((char) 0);
		}

		return false;
	}

}
