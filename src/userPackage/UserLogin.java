package userPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import javax.swing.BorderFactory;
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
import java.awt.event.ActionEvent;
import java.awt.Color;

public class UserLogin {

	public JFrame frame;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JPasswordField textField_1;
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
	private JLabel background;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					File f = new File("data\\userData\\session.txt");
					if (f.isFile()) {
						ObjectInputStream in = new ObjectInputStream(
								new FileInputStream("data\\userData\\session.txt"));
						String s = (String) in.readObject();
						in.close();
						PersonModel ps = PersonModel.getPersonModel(s);
						if (ps != null) {
							UserMainFrame.run(ps);
						} else {
							UserLogin window = new UserLogin();
							window.frame.pack();
							window.frame.setSize(450, 360);
							window.frame.setLocationRelativeTo(null);
							window.frame.setVisible(true);
						}
					} else {
						UserLogin window = new UserLogin();
						window.frame.pack();
						window.frame.setSize(450, 360);
						window.frame.setLocationRelativeTo(null);
						window.frame.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void reRun() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					File f = new File("data\\userData\\session.txt");
					if (f.isFile()) {
						ObjectInputStream in = new ObjectInputStream(
								new FileInputStream("data\\userData\\session.txt"));
						String s = (String) in.readObject();
						in.close();
						PersonModel ps = PersonModel.getPersonModel(s);
						if (ps != null) {
							UserMainFrame.run(ps);
						} else {
							UserLogin window = new UserLogin();
							window.frame.pack();
							window.frame.setSize(450, 360);
							window.frame.setLocationRelativeTo(null);
							window.frame.setVisible(true);
						}
					} else {
						UserLogin window = new UserLogin();
						window.frame.pack();
						window.frame.setSize(450, 360);
						window.frame.setLocationRelativeTo(null);
						window.frame.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public UserLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBackground(new Color(0, 0, 0, 0));

		background = new JLabel(new ImageIcon("images\\airline-wing.jpg"));
		frame.add(background);

		// frame.setBounds(100, 100, 472, 362);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		background.setLayout(new MigLayout("", "[40px][][][][200px][][][][]", "[][][][25px][3px][24px][][][][][]"));
		verticalStrut = Box.createVerticalStrut(70);
		background.add(verticalStrut, "cell 4 0");

		verticalStrut_1 = Box.createVerticalStrut(20);
		background.add(verticalStrut_1, "cell 4 1");

		horizontalStrut_5 = Box.createHorizontalStrut(20);
		background.add(horizontalStrut_5, "cell 0 3");

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		background.add(horizontalStrut_1, "cell 1 3");

		lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		background.add(lblNewLabel, "cell 2 3");

		textField = new JTextField();
		background.add(textField, "cell 4 3,grow");
		textField.setColumns(10);
		textField.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
		

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		background.add(horizontalStrut_2, "cell 6 4");

		horizontalStrut_3 = Box.createHorizontalStrut(20);
		background.add(horizontalStrut_3, "cell 7 4");

		horizontalStrut_6 = Box.createHorizontalStrut(20);
		background.add(horizontalStrut_6, "cell 8 4");

		lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 14));
		background.add(lblNewLabel_1, "cell 2 5");

		textField_1 = new JPasswordField();
		textField_1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
		background.add(textField_1, "cell 4 5,grow");
		textField_1.setColumns(10);

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

		lblNewLabel_2 = new JButton("Don't have an account? Create One");
		lblNewLabel_2.setBorderPainted(false);
		lblNewLabel_2.setIcon(null);
		lblNewLabel_2.setBackground(new Color(0, 0, 0, 0));
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.ITALIC, 10));
		lblNewLabel_2.setForeground(Color.WHITE);
		background.add(lblNewLabel_2, "cell 4 10,alignx center");

		lblNewLabel_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// open registration window
				frame.setVisible(false);
				frame.dispose();
				UserRegistration.run();
			}

		});

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PersonModel ps = validate();
				if (ps != null) {

					File f = new File("data\\userData\\session.txt");
					try {
						f.createNewFile();
						FileOutputStream fout = new FileOutputStream("data\\userData\\session.txt");
						ObjectOutputStream out = new ObjectOutputStream(fout);
						out.writeObject(new String(ps.username));
						out.flush();
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					frame.setVisible(false);
					frame.dispose();
					UserMainFrame.run(ps);
				}

			}

		});

	}

	private PersonModel validate() {

		if (textField.getText().isEmpty() || textField_1.getText().isEmpty()) {
			JOptionPane.showMessageDialog(background, "Please provide both username & password", "Blank Field",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}

		PersonModel ps = PersonModel.getPersonModel(textField.getText().trim());

		if (ps == null) {
			JOptionPane.showMessageDialog(background, "User is not registered.\nPlease create a new account", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}

		if (!ps.passWordHash.equals(textField_1.getText().trim())) {
			JOptionPane.showMessageDialog(background, "Wrong Password!", "Error", JOptionPane.ERROR_MESSAGE);
			textField_1.setEchoChar((char) 0);
			// System.out.println(ps.passWordHash+" "+textField_1.getText().trim());
			return null;
		}

		return ps;
	}

}
