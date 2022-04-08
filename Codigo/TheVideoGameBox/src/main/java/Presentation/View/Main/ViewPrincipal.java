package Presentation.View.Main;

import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Utils.Button;
import Presentation.View.Utils.TextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static Presentation.View.Utils.Images.backGround;
import static Presentation.View.Utils.Images.logo;

public class ViewPrincipal extends JFrame implements IView{

	private static int logged;
	
	public ViewPrincipal() {
		super();
		logged = -1;
		initGUI();
	}
	
	public void initGUI() {
		
		Image iconFrame = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(logo))).getImage();
		this.setIconImage(iconFrame);
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400,100);
		
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

		//BOTON DE LOGUEO
		Button logIn = new Button("Log In", "user_icon.png", new Color(64, 147, 255));
		//new Button("Register", "user_icon.png", new Color(64, 147, 255));
		logIn.buttonIcon();
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
		Button registry =new Button("Register", new Color(255, 170, 0));
		registry.button();
		registry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().action(new Context(Event.VIEW_CREATE_USER, null));
				dispose();
			}
		});
		
		//Icono de la aplicacion
		JLabel icon = new JLabel();
		icon.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("logo_small_blanco.png"))));

		//BOTON CREAR BOX
		Button createBox = new Button("Create Box", new Color(50, 170, 0));
		createBox.button();
		createBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().action(new Context(Event.VIEW_CREATE_BOX, null));
				dispose();
			}
		});
		
		topPanel.add(Box.createRigidArea(new Dimension(20,0)));
		topPanel.add(logIn);
		topPanel.add(Box.createRigidArea(new Dimension(10,0)));
		topPanel.add(registry);
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
		JPanelRound mainPanel = new JPanelRound(new Color(26, 59, 160), new Color(64, 147, 255));
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
		searchByName.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("lupa_icon.png"))));
		searchByName.setForeground(Color.white);
		searchByName.setFont(new Font("Leelawadee", Font.BOLD, 25));
		
		rightTopPanel.add(searchByName);
		
		// PANEL CON LA BUSQUEDA

		JPanel textPanelN = new JPanel();
		textPanelN.setOpaque(false);
		textPanelN.setLayout(new BoxLayout(textPanelN, BoxLayout.X_AXIS));
		textPanelN.add(Box.createRigidArea(new Dimension(50, 0)));
		textPanelN.setBorder(null);

		TextField textName = new TextField(new Dimension(200, 30), "Search Games");
		textName.textField();
		textName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String search = textName.getText();
				if(search.length() <= 50 && search.length() > 0) {
					ApplicationController.getInstance().action(new Context(Event.SEARCH_ALL_BY_NAME, search));
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "There is no games with that name");
				}
			}
		});
		Button searchName = new Button("SEARCH", null, Color.white, new Color(64, 147, 255), new Dimension(120, 30));
		searchName.button();
		searchName.setToolTipText("Search a Box by Name");
		searchName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String search = textName.getText();
				if(search.length() <= 50 && search.length() > 0) {
					ApplicationController.getInstance().action(new Context(Event.SEARCH_ALL_BY_NAME, search));
					dispose();
				}
				else if(search.length() > 50) {
					JOptionPane.showMessageDialog(null, "Too many characters");
				}
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
		JPanelRound mainPanel = new JPanelRound(new Color(26, 59, 160), new Color(64, 147, 255));
		mainPanel.setBackground(new Color(64, 147, 255));
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainPanel.setMaximumSize(new Dimension(1150, 275));
		mainPanel.setLayout(new GridLayout(2,2));
		mainPanel.setBorder(new BorderTitle("Boxes", Color.white));
		
		// BUSQUEDA BOXES POR NOMBRE

		panelBoxSearch();
		
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
		
		mainPanel.add(panelBoxSearch());
		mainPanel.add(leftTopPanel);
		mainPanel.add(rightBottonPanel);
		mainPanel.add(leftBottonPanel);
		
		return mainPanel;
	}

	private JPanel panelBoxSearch() {
		JPanel rightTopPanel = new JPanel();
		rightTopPanel.setBorder(new EmptyBorder(10, 50, 10, 50));
		rightTopPanel.setOpaque(false);
		rightTopPanel.setLayout(new GridLayout(2,2));

		JLabel searchByName = new JLabel("Search Box by Name");
		searchByName.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("lupa_icon.png"))));
		searchByName.setForeground(Color.white);
		searchByName.setFont(new Font("Leelawadee", Font.BOLD, 25));

		rightTopPanel.add(searchByName);

		// PANEL CON LA BUSQUEDA

		JPanel textPanelN = new JPanel();
		textPanelN.setOpaque(false);
		textPanelN.setLayout(new BoxLayout(textPanelN, BoxLayout.X_AXIS));
		textPanelN.add(Box.createRigidArea(new Dimension(50, 0)));
		textPanelN.setBorder(null);

		TextField textName = new TextField(new Dimension(200, 30), "Search Boxes");
		textName.textField();
		textName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String search = textName.getText();
				if(search.length() <= 50 && search.length() > 0) {
					ApplicationController.getInstance().action(new Context(Event.SEARCH_ALL_BOXES_BY_NAME, search));
					dispose();
				}
				else if(search.length() > 50) {
					JOptionPane.showMessageDialog(null, "Too many characters");
				}
			}
		});

		Button searchName = new Button("SEARCH", null, Color.white, new Color(64, 147, 255), new Dimension(120, 30));
		searchName.button();
		searchName.setToolTipText("Search a Box by Name");
		searchName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String search = textName.getText();
				if(search.length() <= 50 && search.length() > 0) {
					ApplicationController.getInstance().action(new Context(Event.SEARCH_ALL_BOXES_BY_NAME, search));		//CAMBIAR EL EVENTO CUANDO ESTE HECHA LA VISTA
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "There is no Boxes with that name");
				}
			}
		});

		textPanelN.add(textName);
		textPanelN.add(Box.createRigidArea(new Dimension(10, 0)));
		textPanelN.add(searchName);

		rightTopPanel.add(textPanelN);
		return rightTopPanel;
	}

	@Override
	public void update(Context context) {
		if(context.getEvent() == Event.RES_SEARCH_ALL_BY_NAME_KO) {
			JOptionPane.showMessageDialog(null, "There isn't any game with that name");
		}
		else if(context.getEvent() == Event.RES_SEARCH_ALL_BOXES_BY_NAME_KO) {
			JOptionPane.showMessageDialog(null, "There isn't any box with that name");
		}
		ApplicationController.getInstance().action(new Context(Event.VIEW, null));
		dispose();
	}
}