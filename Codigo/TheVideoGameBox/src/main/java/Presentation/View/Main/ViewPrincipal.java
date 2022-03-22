package Presentation.View.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.View.IView;
import Presentation.View.ViewAbstractFactory;
import Presentation.View.Box.ViewCreateBox;
import Presentation.Controller.Event;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPrincipal extends JFrame implements IView{

	private static int logged;
	
	public ViewPrincipal() {
		super();
		logged = -1;
		initGUI();
	}
	
	public void initGUI() {
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("caja_definitiva.png")).getImage();
		this.setIconImage(iconFrame);
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400,100);
		
		JPanelConFondo mainpanel = new JPanelConFondo();
		mainpanel.setLayout(new BorderLayout());
		mainpanel.setImagen(new ImageIcon(getClass().getClassLoader().getResource("fondo_triangular.png")).getImage());
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

		//BOTON DE LOGUEO
		
		JButton logIn = new JButton("Log In");
		logIn.setPreferredSize(new Dimension(120, 50));
		logIn.setIcon(new ImageIcon(getClass().getClassLoader().getResource("user_icon.png")));
		logIn.setMaximumSize(new Dimension(120, 50));
		logIn.setMinimumSize(new Dimension(120, 50));
		logIn.setBackground(new Color(64, 147, 255));
		logIn.setForeground(Color.white);
		logIn.setFont(new Font("Leelawadee", Font.BOLD, 15));
		logIn.setBorder(BorderFactory.createBevelBorder(0));
		logIn.setFocusPainted(false);
		logIn.setToolTipText("Log In");
		logIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					logged = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your user name:", "Access to the user aplication", JOptionPane.YES_NO_CANCEL_OPTION));
					//Se metería a la vista de una vez estás conectado
					
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Invalid data entered","Connection error", JOptionPane.NO_OPTION);;
				}
			}
			
		});
		
		//BOTON DE REGISTRARSE
		
		JButton registro = new JButton("Register");
		registro.setPreferredSize(new Dimension(120, 50));
		registro.setMaximumSize(new Dimension(120, 50));
		registro.setMinimumSize(new Dimension(120, 50));
		registro.setBackground(new Color(255, 170, 0));
		registro.setForeground(Color.white);
		registro.setFont(new Font("Leelawadee", Font.BOLD, 15));
		registro.setBorder(BorderFactory.createBevelBorder(0));
		registro.setFocusPainted(false);
		registro.setToolTipText("Register");
		
		//Icono de la aplicacion
		
		JLabel icon = new JLabel();
		icon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_small_blanco.png")));
		

		//BOTON CREAR BOX
		
		JButton createBox = new JButton("Create Box");
		createBox.setPreferredSize(new Dimension(120, 50));
		createBox.setMaximumSize(new Dimension(120, 50));
		createBox.setMinimumSize(new Dimension(120, 50));
		createBox.setBackground(new Color(50, 170, 0));
		createBox.setForeground(Color.white);
		createBox.setFont(new Font("Leelawadee", Font.BOLD, 15));
		createBox.setBorder(BorderFactory.createBevelBorder(0));
		createBox.setFocusPainted(false);
		createBox.setToolTipText("Create Box");
		createBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ViewCreateBox createView = new ViewCreateBox();
				dispose();
			}
			
		});
		
		topPanel.add(Box.createRigidArea(new Dimension(20,0)));
		topPanel.add(logIn);
		topPanel.add(Box.createRigidArea(new Dimension(10,0)));
		topPanel.add(registro);
		topPanel.add(Box.createRigidArea(new Dimension(110,0)));
		topPanel.add(icon);
		topPanel.add(Box.createRigidArea(new Dimension(200,0)));
		topPanel.add(createBox);

		return topPanel;
	}
	
	private JPanel createMidPanel() {
		JPanel midPanel = new JPanel();
		midPanel.setOpaque(false);
		midPanel.setPreferredSize(new Dimension(1150, 600));
		midPanel.setMaximumSize(new Dimension(1150, 600));
		midPanel.setMinimumSize(new Dimension(1150, 600));
		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
		midPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		
		//PANEL DEL JUEGO
		
		midPanel.add(gamesPanel());
		midPanel.add(Box.createRigidArea(new Dimension(0,10)));
		midPanel.add(boxPanel());
		
		return midPanel;
	}
	
	private JPanelRound gamesPanel() {
		JPanelRound mainPanel = new JPanelRound();
		mainPanel.setBackground(new Color(64, 147, 255));
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setMaximumSize(new Dimension(1150, 275));
		mainPanel.setLayout(new GridLayout(2,1));
		mainPanel.setBorder(new BorderTitle("Games", Color.white));
		
		// BUSCAR UN JUEGO POR NOMBRE
		
		JPanel rightTopPanel = new JPanel();
		rightTopPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
		rightTopPanel.setOpaque(false);
		rightTopPanel.setLayout(new GridLayout(2,2));
		
		// TEXTO E ICONO
		
		JLabel searchByName = new JLabel("Search by Name");
		searchByName.setIcon(new ImageIcon(getClass().getClassLoader().getResource("lupa_icon.png")));
		searchByName.setForeground(Color.white);
		searchByName.setFont(new Font("Leelawadee", Font.BOLD, 25));
		
		rightTopPanel.add(searchByName);
		
		// PANEL CON LA BUSQUEDA
		
		JPanel textPanelN = new JPanel();
		textPanelN.setOpaque(false);
		textPanelN.setLayout(new BoxLayout(textPanelN, BoxLayout.X_AXIS));
		textPanelN.add(Box.createRigidArea(new Dimension(50, 0)));
		
		JTextField textName = new JTextField();
		textName.setPreferredSize(new Dimension(200, 30));
		textName.setMinimumSize(new Dimension(200, 30));
		textName.setMaximumSize(new Dimension(200, 30));

		JButton searchName = new JButton("SEARCH");
		searchName.setPreferredSize(new Dimension(120, 30));
		searchName.setMaximumSize(new Dimension(120, 30));
		searchName.setMinimumSize(new Dimension(120, 30));
		searchName.setBackground(new Color(64, 147, 255));
		searchName.setForeground(Color.white);
		searchName.setFont(new Font("Leelawadee", Font.BOLD, 15));
		searchName.setBorder(BorderFactory.createBevelBorder(0));
		searchName.setFocusPainted(false);
		searchName.setToolTipText("Search a Game by Name");
		searchName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String search = textName.getText();
				ApplicationController.getInstance().action(new Context(Event.SEARCH_ALL_BY_NAME, search));
				dispose();
				
			}
			
		});
		
		textPanelN.add(textName);
		textPanelN.add(Box.createRigidArea(new Dimension(10, 0)));
		textPanelN.add(searchName);
		
		rightTopPanel.add(textPanelN);
		
		// BUSQUEDA XXXXXXXX
		
		JPanel leftTopPanel = new JPanel(new BorderLayout());
		leftTopPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
		leftTopPanel.setOpaque(false);
		leftTopPanel.setLayout(new GridLayout(2,2));
		
		// BUSQUEDA XXXXXXXX
		
		JPanel rightBottonPanel = new JPanel(new BorderLayout());
		rightBottonPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
		rightBottonPanel.setOpaque(false);
		rightBottonPanel.setLayout(new GridLayout(2,2));
		
		// BUSQUEDA XXXXXXXX
		
		JPanel leftBottonPanel = new JPanel(new BorderLayout());
		leftBottonPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
		leftBottonPanel.setOpaque(false);
		leftBottonPanel.setLayout(new GridLayout(2,2));
		
		mainPanel.add(rightTopPanel);
		mainPanel.add(leftTopPanel);
		mainPanel.add(rightBottonPanel);
		mainPanel.add(leftBottonPanel);
		
		return mainPanel;
	}
	
	private JPanelRound boxPanel() {
		JPanelRound mainPanel = new JPanelRound();
		mainPanel.setBackground(new Color(64, 147, 255));
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setMaximumSize(new Dimension(1150, 275));
		mainPanel.setLayout(new GridLayout(2,2));
		mainPanel.setBorder(new BorderTitle("Boxes", Color.white));
		
		// BUSQUEDA XXXXXXXX
		
		JPanel rightTopPanel = new JPanel();
		rightTopPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
		rightTopPanel.setOpaque(false);
		rightTopPanel.setLayout(new GridLayout(2,2));
		
		// BUSQUEDA XXXXXXXX
		
		JPanel leftTopPanel = new JPanel(new BorderLayout());
		leftTopPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
		leftTopPanel.setOpaque(false);
		leftTopPanel.setLayout(new GridLayout(2,2));
		
		// BUSQUEDA XXXXXXXX
		
		JPanel rightBottonPanel = new JPanel(new BorderLayout());
		rightBottonPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
		rightBottonPanel.setOpaque(false);
		rightBottonPanel.setLayout(new GridLayout(2,2));
		
		// BUSQUEDA XXXXXXXX
		
		JPanel leftBottonPanel = new JPanel(new BorderLayout());
		leftBottonPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
		leftBottonPanel.setOpaque(false);
		leftBottonPanel.setLayout(new GridLayout(2,2));
		
		mainPanel.add(rightTopPanel);
		mainPanel.add(leftTopPanel);
		mainPanel.add(rightBottonPanel);
		mainPanel.add(leftBottonPanel);
		
		return mainPanel;
	}

	@Override
	public void update(Context context) {
		if(context.getEvent() == Event.RES_SEARCH_ALL_BY_NAME_KO) {
			JOptionPane.showMessageDialog(null, "No ha habido resultados para la busqueda");
		}
	
	}
}