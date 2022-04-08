package Presentation.View.User;

import Logic.User.TUser;
import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Main.BorderTitle;
import Presentation.View.Main.JPanelConFondo;
import Presentation.View.Main.JPanelRound;
import Presentation.View.Utils.Button;
import Presentation.View.Utils.TextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

import static Presentation.View.Utils.Images.backGround;
import static Presentation.View.Utils.Images.logo;

public class ViewRegister extends JFrame implements IView {

	private static final long serialVersionUID = 1L;
	
	public ViewRegister() {
		setTitle("Register user"); 
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	private void init_GUI() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		Image iconFrame = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(logo))).getImage();
		this.setIconImage(iconFrame);
		
		JPanelConFondo mainpanel = new JPanelConFondo();
		mainpanel.setLayout(new BorderLayout());
		mainpanel.setImagen(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(backGround))).getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(mainpanel);
		
		JPanel topPanel = createTopPanel();
		JPanel midPanel = createMidPanel();
		
		mainpanel.add(topPanel, BorderLayout.NORTH);
		mainpanel.add(midPanel, BorderLayout.SOUTH);
		
		this.pack();
		this.setResizable(true);
		setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
private JPanel createTopPanel() {
		
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(1150, 100));
		topPanel.setMaximumSize(new Dimension(1150, 100));
		topPanel.setMinimumSize(new Dimension(1150, 100));
		topPanel.setOpaque(false);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		
		//ICONO DE LA APLICACION
		JLabel icon = new JLabel();
		icon.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("logo_small_blanco.png"))));
		topPanel.add(Box.createRigidArea(new Dimension(110,0)));
		topPanel.add(icon);
		
		return topPanel;
    }

private JPanel createMidPanel() {
	JPanel midPanel = new JPanel();
	midPanel.setPreferredSize(new Dimension(1150, 600));
	midPanel.setMaximumSize(new Dimension(1150, 600));
	midPanel.setMinimumSize(new Dimension(1150, 600));
	midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
	midPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
	midPanel.setBackground(new Color(64, 147, 255));
	midPanel.setBorder(new BorderTitle("Sign Up - Join us today", Color.black));
	
	// Create account label
	JLabel createLabel = new JLabel("Create your account");
	createLabel.setHorizontalAlignment(SwingConstants.CENTER);
	createLabel.setFont(new Font("Leelawadee", Font.BOLD, 30));
	createLabel.setForeground(Color.WHITE);
	
	midPanel.add(Box.createRigidArea(new Dimension(0,10)));
	midPanel.add(createLabel);
	midPanel.add(Box.createRigidArea(new Dimension(0,15)));
	midPanel.add(registerPanel());
	
	return midPanel;
	}

	private Component registerPanel() {
		JPanel registerPanel = new JPanelRound(new Color(26, 59, 160), new Color(64, 147, 255));
		registerPanel.setMaximumSize(new Dimension(1150, 550));
		registerPanel.setMinimumSize(new Dimension(1150, 550));
		registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
		registerPanel.add(Box.createRigidArea(new Dimension(0,10)));
		
		// BACK BUTTON
		Button backButton = new Button(null, "back_icon.png");
		backButton.buttonIcon();
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setBounds(0, 11, 119, 50);
		backButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().action(new Context(Event.VIEW, null));
				dispose();
			}
		});
		
		registerPanel.add(backButton);
		
		// EMAIL LABEL
		JLabel emailLabel = new JLabel("  Email Adress");
		emailLabel.setFont(new Font("Comic Sans", Font.ITALIC, 20));
		emailLabel.setForeground(Color.WHITE);
		registerPanel.add(emailLabel);
		
		// Input para el email
		JPanel emailPanel = new JPanel();
		emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.X_AXIS));
		emailPanel.setOpaque(false);

		TextField emailUser = new TextField(new Dimension(600, 40));
		emailUser.textField();

		registerPanel.add(emailPanel);
		emailPanel.add(Box.createRigidArea(new Dimension(15, 10)));
		emailPanel.add(emailUser);
		
		// USERNAME LABEL
		JLabel usernameLabel = new JLabel("  Username");
		usernameLabel.setFont(new Font("Comic Sans", Font.ITALIC, 20));
		usernameLabel.setForeground(Color.WHITE);
		registerPanel.add(usernameLabel);
		
		// Input para el username
		JPanel usernamePanel = new JPanel();
		usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.X_AXIS));
		usernamePanel.setOpaque(false);

		TextField usernameUser = new TextField(new Dimension(600, 40));
		usernameUser.textField();

		registerPanel.add(usernamePanel);
		usernamePanel.add(Box.createRigidArea(new Dimension(15, 10)));
		usernamePanel.add(usernameUser);

		// PASSWORD LABEL
		JLabel passwordLabel = new JLabel("  Password");
		passwordLabel.setFont(new Font("Comic Sans", Font.ITALIC, 20));
		passwordLabel.setForeground(Color.WHITE);
		registerPanel.add(passwordLabel);
				
		// Input para la password
		JPanel passwordPanel = new JPanel();
		passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
		passwordPanel.setOpaque(false);

		TextField passwordUser = new TextField(new Dimension(600, 40));
		passwordUser.textField();

		registerPanel.add(passwordPanel);
		passwordPanel.add(Box.createRigidArea(new Dimension(15, 10)));
		passwordPanel.add(passwordUser);
		
		// CONFIRM PASSWORD LABEL
		JLabel confirmPasswordLabel = new JLabel("Confirm Password");
		confirmPasswordLabel.setFont(new Font("Comic Sans", Font.ITALIC, 20));
		confirmPasswordLabel.setForeground(Color.WHITE);
		registerPanel.add(confirmPasswordLabel);
						
		// Input para la confirm password
		JPanel confirmPasswordPanel = new JPanel();
		confirmPasswordPanel.setLayout(new BoxLayout(confirmPasswordPanel, BoxLayout.X_AXIS));
		confirmPasswordPanel.setOpaque(false);

		TextField confirmPasswordUser = new TextField(new Dimension(600, 40));
		confirmPasswordUser.textField();

		registerPanel.add(confirmPasswordPanel);
		confirmPasswordPanel.add(Box.createRigidArea(new Dimension(15, 10)));
		confirmPasswordPanel.add(confirmPasswordUser);
				
		// SIGN UP BUTTON
		Button signUpButton = new Button("Sing Up", new Color(50, 170, 0), new Dimension(80, 40));
		signUpButton.button();

		signUpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = emailUser.getText();
				String username = usernameUser.getText();
				String password = passwordUser.getText();
				String confirmPassword = confirmPasswordUser.getText();

				if (password.equals(confirmPassword)) {
					TUser user = new TUser(email, username, password);
					ApplicationController.getInstance().action(new Context(Event.CREATE_USER, user));;
				}
				else JOptionPane.showMessageDialog(null, "Incorrect password");
			}
		});
		
		registerPanel.add(Box.createRigidArea(new Dimension(100,5)));
		registerPanel.add(signUpButton);
		
		return registerPanel;
	}

	@Override
	public void update(Context context) {
		switch(context.getEvent()) {
		case Event.RES_CREATE_USER_OK:
			JOptionPane.showMessageDialog(this, "Signed Up!","Sign Up", JOptionPane.INFORMATION_MESSAGE);
			ApplicationController.getInstance().action(new Context(Event.VIEW, null));
			dispose();
			break;
		case Event.RES_CREATE_USER_KO:
			JOptionPane.showMessageDialog(null, "Failed to Sign Up","Sign Up", JOptionPane.ERROR_MESSAGE);
			break;
		}
	}

}
