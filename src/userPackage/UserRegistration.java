package userPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;

public class UserRegistration {

	private JFrame frame;
	private JTextField nameTx;
	private JTextField usernameTx;
	private JPasswordField passwordField;
	private JPasswordField ConfirmPasswordField;
	private JLabel backgroundLabel;

	/**
	 * Launch the application.
	 */
	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegistration window = new UserRegistration();
					window.frame.pack();
					window.frame.setSize(450,360);
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
	public UserRegistration() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setBackground(new Color(0,0,0,0));
		
		backgroundLabel = new JLabel(new ImageIcon("images\\airline-wing.jpg"));
		frame.add(backgroundLabel);
		
		backgroundLabel.setLayout(new MigLayout("", "[40px][][][][200px][][][][]", "[80px][][][][25px][3px][24px][3px][25px][3px][24px][10px][]"));
		
		JButton closeButton = new JButton("X");
		closeButton.setBorderPainted(false);
		closeButton.setIcon(null);
		closeButton.setBackground(new Color(0,0,0,0));
		closeButton.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		closeButton.setForeground(Color.BLUE);
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
        		
		backgroundLabel.add(closeButton, "cell 8 0,alignx right");
		
		
		JLabel nameLb = new JLabel("Name");
		nameLb.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		backgroundLabel.add(nameLb, "cell 2 4");
		
		nameTx = new JTextField();
		nameTx.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
		backgroundLabel.add(nameTx, "cell 4 4,grow");
		nameTx.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		backgroundLabel.add(lblUsername, "cell 2 6");
		
		usernameTx = new JTextField();
		usernameTx.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1, true));
		usernameTx.setColumns(10);
		backgroundLabel.add(usernameTx, "cell 4 6,grow");
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		backgroundLabel.add(lblPassword, "cell 2 8");
		
		passwordField = new JPasswordField();
		passwordField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1, true));
		backgroundLabel.add(passwordField, "cell 4 8,grow");
		
		JLabel lblConfirmPass = new JLabel("Confirm pass");
		lblConfirmPass.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		backgroundLabel.add(lblConfirmPass, "cell 2 10");
		
		ConfirmPasswordField = new JPasswordField();
		ConfirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.GREEN, 1, true));
		backgroundLabel.add(ConfirmPasswordField, "cell 4 10,grow");
		
		JButton registerButton = new JButton("      REGISTER      ");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PersonModel ps=validate();
				if(ps!=null) {
					
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
					ps.writeData();
					//System.out.println(ps.passWordHash);
					UserMainFrame.run(ps);
				}
			}
		});
		registerButton.setForeground(Color.WHITE);
		registerButton.setBackground(Color.BLUE);
		registerButton.setIcon(null);
		registerButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 12));
		backgroundLabel.add(registerButton, "cell 4 12,alignx center,growy");
	}
	
	private PersonModel validate() {
     if(nameTx.getText().isEmpty() || usernameTx.getText().isEmpty() || passwordField.getText().toString().isEmpty() || ConfirmPasswordField.getText().toString().isEmpty()) {
    	 JOptionPane.showMessageDialog(backgroundLabel, "Please fill up all the field","Blank Field",JOptionPane.ERROR_MESSAGE);
    	 return null;
     }
		
	 File f=new File("data\\userData\\"+usernameTx.getText().trim()+".txt");
	 
	 if(f.isFile()){
		 JOptionPane.showMessageDialog(backgroundLabel, "Username \""+usernameTx.getText().trim()+"\" not available. Please Choose a different username.","",JOptionPane.ERROR_MESSAGE);
		 usernameTx.setText("");
		 return null;
	 }
	 
	 if(!passwordField.getText().trim().equals(ConfirmPasswordField.getText().trim())) {
		 JOptionPane.showMessageDialog(backgroundLabel, "Password did not match!","Password Missmatch",JOptionPane.ERROR_MESSAGE);
		 passwordField.setEchoChar((char)0);
		 ConfirmPasswordField.setEchoChar((char)0);
		 return null;
	 }
	 
	 
	 return new PersonModel(nameTx.getText().trim(),usernameTx.getText().trim(),passwordField.getText().trim());
	}
}
