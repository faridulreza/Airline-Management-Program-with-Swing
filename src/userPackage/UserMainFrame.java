package userPackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.basic.BasicBorders;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.SpringLayout;
import net.miginfocom.swing.MigLayout;
import utilities.FlightModel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import managerPackage.AllFlightsViewJpanel;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class UserMainFrame {

	private JFrame frame;
	private static PersonModel ps;
    private static ArrayList<FlightModel> flightList;
	/**
	 * Launch the application.
	 */
	public static void run(PersonModel pss) {
		ps = pss;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMainFrame window = new UserMainFrame();
					window.frame.pack();
					window.frame.setSize(950, 650);
					window.frame.setLocationRelativeTo(null);
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserMainFrame() {
		flightList=FlightModel.getFlightList();
		
		
		long tm=Calendar.getInstance().getTimeInMillis();
		
	    for(FlightModel flt:flightList) {
	    	if(ps.trophiesToGetFrom.contains(flt.ID)) {
	    		if(flt.status==flt.FLIGHT_CANCELLED) {
	    			ps.trophiesToGetFrom.remove(flt.ID);
	    		}
	    		else if(tm>flt.dateInMilli+flt.timeInMilli+flt.durationInMilli) {
	    	   		ps.trophies+=(int)(flt.durationInMilli/(1000*60*60))+1;
	    	   		ps.trophiesToGetFrom.remove(flt.ID);
	    	   	}
	    	}
	    }
	    
	    initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 950, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent evt) {
				ps.writeData();
				FlightModel.saveFlightList();

			}
		});
		
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		JPanel tabPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, tabPanel, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, tabPanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, tabPanel, 0, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, tabPanel, -750, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(tabPanel);

		JPanel ContentPanel = new JPanel();
		ContentPanel.setLayout(new BorderLayout());
		springLayout.putConstraint(SpringLayout.NORTH, ContentPanel, 55, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, ContentPanel, 6, SpringLayout.EAST, tabPanel);
		tabPanel.setLayout(new MigLayout("", "[200px]", "[200px][20px][45px][][45px][][45px][][][][]"));

		JLabel nameLb = new JLabel("Faridul Reza");
		nameLb.setForeground(new Color(75, 0, 130));
		nameLb.setFont(new Font("Sitka Small", Font.PLAIN, 18));
		tabPanel.add(nameLb, "cell 0 0,alignx center,aligny center");
		springLayout.putConstraint(SpringLayout.SOUTH, ContentPanel, -6, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ContentPanel, -6, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(ContentPanel);

		JPanel headingPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, headingPanel, 6, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, headingPanel, 6, SpringLayout.EAST, tabPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, headingPanel, -6, SpringLayout.NORTH, ContentPanel);
		springLayout.putConstraint(SpringLayout.EAST, headingPanel, -6, SpringLayout.EAST, frame.getContentPane());
		ContentPanel.setLayout(new CardLayout(0, 0));
		frame.getContentPane().add(headingPanel);
		headingPanel.setLayout(new MigLayout("", "[][][200px][grow]", "[29px]"));

		JLabel headingLb = new JLabel("Cheap flight tickets, Find yourself in travelling!");
		headingLb.setForeground(new Color(165, 42, 42));
		headingLb.setFont(new Font("Lucida Bright", Font.BOLD, 18));
		headingPanel.add(headingLb, "cell 1 0,alignx center,aligny center");

		JLabel trophyLb = new JLabel("10000");
		trophyLb.setForeground(new Color(165, 42, 42));
		trophyLb.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
		headingPanel.add(trophyLb, "cell 3 0,alignx right,aligny center");
		try {
			BufferedImage img = ImageIO.read(new File("images\\trophy.png"));
			trophyLb.setIcon(new ImageIcon(img.getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
			trophyLb.setHorizontalTextPosition(SwingConstants.LEFT);
			trophyLb.setIconTextGap(10);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		headingPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		tabPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2, true));

		JButton bookNowButton = new JButton("Book Now");
		bookNowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ContentPanel.removeAll();
				ContentPanel.add(new SearchPanelForUser(ps));
				headingLb.setText("Cheap flight ticket, Find Yourself in travelling!");
				ContentPanel.revalidate();
				ContentPanel.repaint();
			}
		});
		bookNowButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		tabPanel.add(bookNowButton, "cell 0 2,grow");

		JButton myBookingsButton = new JButton("My Bookings");
		myBookingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ContentPanel.removeAll();
				ArrayList<FlightModel> tmpList=new ArrayList<>();
				for(FlightModel flt:flightList) {
					if(ps.bookedFlightIDSet.contains(flt.ID))tmpList.add(flt);
				}
				
				JScrollPane js=new JScrollPane(new AllFlightsViewForUser(tmpList, 1, ps));
				js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				
				ContentPanel.add(js);
				headingLb.setText("Manage your bookings with ease");
				ContentPanel.revalidate();
				ContentPanel.repaint();
			}
		});
		myBookingsButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		tabPanel.add(myBookingsButton, "cell 0 4,grow");

		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f = new File("data\\userData\\session.txt");
				if (f.isFile())
					f.delete();
				ps.writeData();
			    FlightModel.saveFlightList();
				frame.setVisible(false);
				frame.dispose();
				UserLogin.reRun();
			}
		});
		logoutButton.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		tabPanel.add(logoutButton, "cell 0 6,grow");
		
		
		
		
		// Bind userData
		setUserImage(nameLb);
		trophyLb.setText(String.valueOf(ps.trophies));
		
		myBookingsButton.doClick();
	}

	
	
	
//Setting user Image
	private void setUserImage(JLabel lb) {
		File f = new File("data\\userData\\" + ps.username+".png");
		if (f.exists()) {
			setProfileImage(lb);
		} else {
			try {
				BufferedImage img = ImageIO.read(new File("images\\default-user.png"));
				lb.setIcon(new ImageIcon(img.getScaledInstance(180, 180, Image.SCALE_SMOOTH)));
				lb.setVerticalTextPosition(SwingConstants.BOTTOM);
				lb.setHorizontalTextPosition(SwingConstants.CENTER);
				lb.setText(ps.name);
				lb.setIconTextGap(10);
				lb.setMaximumSize(new Dimension(170, 210));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		lb.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				fileChooser.addChoosableFileFilter(new ImageFilter());
				fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setDialogTitle("Choose profile picture");
                
				int option = fileChooser.showOpenDialog(new JFrame());
				
				
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					File dest = new File("data\\userData\\" + ps.username+".png");
					if (dest.exists())
						dest.delete();
					
					dest = new File("data\\userData\\" + ps.username + ".png");

					try {
						BufferedImage img=createResizedCopy(file,180,180,true);
						ImageIO.write(img, "png", dest);
						EventQueue.invokeLater(new Runnable() {
							@Override
							public void run() {
								lb.setIcon(new ImageIcon(img));
								lb.revalidate();
								lb.repaint();
							}
							
						});
						
						
					   
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}

				}

			}
		});

	}

	private void setProfileImage(JLabel lb) {
        
		lb.setIcon(new ImageIcon("Data\\userData\\" + ps.username + ".png"));
		lb.setVerticalTextPosition(SwingConstants.BOTTOM);
		lb.setHorizontalTextPosition(SwingConstants.CENTER);
		lb.setText(ps.name);
		lb.setIconTextGap(10);
		lb.setMaximumSize(new Dimension(170, 210));

	}

	private BufferedImage createResizedCopy(File f, int scaledWidth, int scaledHeight, boolean preserveAlpha)
			throws IOException {
		Image originalImage = ImageIO.read(f);
		int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
		BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
		Graphics2D g = scaledBI.createGraphics();
		if (preserveAlpha) {
			g.setComposite(AlphaComposite.Src);
		}
		g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
		g.dispose();
		return scaledBI;
	}

}

//filter for profile image chooser
class ImageFilter extends FileFilter {
	public final static String JPEG = "jpeg";
	public final static String JPG = "jpg";
	public final static String GIF = "gif";
	public final static String TIFF = "tiff";
	public final static String TIF = "tif";
	public final static String PNG = "png";

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String extension = getExtension(f);
		if (extension != null) {
			if (extension.equals(TIFF) || extension.equals(TIF) || extension.equals(GIF) || extension.equals(JPEG)
					|| extension.equals(JPG) || extension.equals(PNG)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		return "Image Only";
	}

	String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}
}
