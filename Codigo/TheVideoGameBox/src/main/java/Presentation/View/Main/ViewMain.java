package Presentation.View.Main;

import static Presentation.View.Utils.Images.logo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import Logic.Game.TGame;
import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Utils.Button;
import Presentation.View.Utils.TextField;

public class ViewMain extends JFrame implements IView{

	private static int logged;
	private boolean desplegado;

	public ViewMain() {
		super();
		logged = 4;
		desplegado = true;
		initGUI();
	}
	
	@Override
	public void update(Context context) {
		ApplicationController.getInstance().clearViewStack();

		if(context.getEvent() == Event.RES_SEARCH_ALL_BY_NAME_KO) {
			JOptionPane.showMessageDialog(null, "There isn't any game with that name");
		}
		else if(context.getEvent() == Event.RES_SEARCH_ALL_BOXES_BY_NAME_KO) {
			JOptionPane.showMessageDialog(null, "There isn't any box with that name");
		}

		refreshView();
	}

	public void initGUI() {
		
		Image iconFrame = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(logo))).getImage();
		this.setIconImage(iconFrame);
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400,100);
		
		JPanelConFondo mainpanel = new JPanelConFondo();
		mainpanel.setLayout(new BorderLayout());
		mainpanel.setImagen(new ImageIcon(getClass().getClassLoader().getResource("fondo_triangular.png")).getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(mainpanel);
		
		JPanel topPanel = creaTopPanel();
		JPanel midPanel = creaMidPanel();

		this.setPreferredSize(new Dimension(1150, 750));
		
		mainpanel.add(topPanel, BorderLayout.WEST);
		mainpanel.add(midPanel, BorderLayout.CENTER);

		this.pack();
		this.setResizable(true);
		this.setVisible(true);
	}


	public JPanel creaTopPanel() {
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.setBackground(new Color(62, 80, 90));
		topPanel.setPreferredSize(new Dimension(250, 750));
		topPanel.setMinimumSize(new Dimension(250, 750));
		topPanel.setMaximumSize(new Dimension(250, 750));
		
		//TOP PANEL
		
		JPanel top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
		top.setOpaque(false);
		top.setPreferredSize(new Dimension(250, 50));
		top.setMinimumSize(new Dimension(250, 50));
		top.setMaximumSize(new Dimension(250, 50));
		
		JPanel game = new JPanel();
		JPanel box = new JPanel();
		JPanel user = new JPanel();
		
		//PARA CENTRAR EL TEXTO DE LOS JCOMBOBOX
		DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		
		//BOTON DESPEGABLE
		
		Button despleg = new Button(null, "desplegable_icon.png", Color.white, new Color(62, 80, 90), new Dimension(50, 50), false);
		despleg.buttonIcon();
		despleg.setBorder(BorderFactory.createBevelBorder(0));
		despleg.setToolTipText("Desplegar el menu");
		top.add(despleg);
		despleg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(desplegado) {
					desplegado = !desplegado;
					topPanel.setOpaque(false);
					topPanel.setPreferredSize(new Dimension(50, 750));
					topPanel.setMinimumSize(new Dimension(50, 750));
					topPanel.setMaximumSize(new Dimension(50, 750));
					top.setOpaque(false);
					game.setVisible(false);
					box.setVisible(false);
					user.setVisible(false);
				}
				else {
					desplegado = !desplegado;
					topPanel.setOpaque(true);
					topPanel.setBackground(new Color(62, 80, 90));
					topPanel.setPreferredSize(new Dimension(250, 750));
					topPanel.setMinimumSize(new Dimension(250, 750));
					topPanel.setMaximumSize(new Dimension(250, 750));
					top.setBackground(new Color(62, 80, 90));
					game.setVisible(true);
					box.setVisible(true);
					user.setVisible(true);
				}
			}
			
		});
		
		//LOGUEO DEL USUARIO
		
		Button logIn = new Button(null, "user_icon.png", Color.white, new Color(62, 80, 90), new Dimension(50, 50), false);
		logIn.buttonIcon();
		despleg.setBorder(BorderFactory.createBevelBorder(0));
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
		logIn.setBorder(BorderFactory.createBevelBorder(0));
		logIn.setToolTipText("Log In");
		
		top.add(logIn);
		
		//REGISTRO DEL USUARIO
		
		Button registry = new Button(null, "register_icon.png", Color.white, new Color(62, 80, 90), new Dimension(50, 50), false);
		registry.buttonIcon();
		registry.setBorder(BorderFactory.createBevelBorder(0));
		registry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().action(new Context(Event.VIEW_CREATE_USER, null));
				setVisible(false);
			}
		});
		registry.setBorder(BorderFactory.createBevelBorder(0));
		registry.setToolTipText("Register");
		
		top.add(registry);
		
		//CIERRE DE SESION DEL USUARIO
		
		Button logout = new Button(null, "logout_icon.png", Color.white, new Color(252, 147, 3), new Dimension(50, 50), false);
		logout.buttonIcon();
		logout.setBorder(BorderFactory.createBevelBorder(0));
		logout.setVisible(false);
		logout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logIn.setVisible(true);
				registry.setVisible(true);
				logout.setVisible(false);
				game.setVisible(false);
				box.setVisible(false);
				user.setVisible(false);
				logged = 0;
			}

		});
		logout.setBorder(BorderFactory.createBevelBorder(0));
		logout.setToolTipText("Log out");
		//En función de si esta logueado se ve visible o no
		/*if (logged==4) {
			logIn.setVisible(false);
			registry.setVisible(false);
			logout.setVisible(true);
			game.setVisible(true);
			box.setVisible(true);
			user.setVisible(true);
		}
		else {
			logIn.setVisible(true);
			registry.setVisible(true);
			logout.setVisible(false);
			game.setVisible(false);
			box.setVisible(false);
			user.setVisible(false);
		}*/
		
		top.add(logout);
		
		//PANEL DE JUEGOS
		
		game.setLayout(new GridLayout(2,0));
		game.setOpaque(false);
		game.setPreferredSize(new Dimension(250, 217));
		game.setMinimumSize(new Dimension(250, 217));
		game.setMaximumSize(new Dimension(250, 217));
		game.setBorder(new BorderTitle("Games", Color.white));
		
		//BUSCAR JUEGOS
		
		JPanel searchGameType = new JPanel();
		searchGameType.setLayout(new GridLayout(0,2));
		searchGameType.setOpaque(false);
		searchGameType.setPreferredSize(new Dimension(250, 70));
		searchGameType.setMinimumSize(new Dimension(250, 70));
		searchGameType.setMaximumSize(new Dimension(250, 70));
		
		JLabel searchGameText = new JLabel("Type of Search:");
		searchGameText.setForeground(Color.white);
		searchGameText.setFont(new Font("Leelawadee", Font.BOLD, 16));
			
		JPanel comboBoxGamePanel = new JPanel();
		comboBoxGamePanel.setLayout(new BoxLayout(comboBoxGamePanel, BoxLayout.Y_AXIS));
		comboBoxGamePanel.setOpaque(false);
		comboBoxGamePanel.setPreferredSize(new Dimension(100, 30));
		comboBoxGamePanel.setMinimumSize(new Dimension(100, 30));
		comboBoxGamePanel.setMaximumSize(new Dimension(100, 30));
		
		JComboBox<String> comboBoxGame = new JComboBox<String>();
		comboBoxGame.addItem("By Name");
		comboBoxGame.addItem("By Platform");
		comboBoxGame.setRenderer(listRenderer);
		
		//Falta hacer la comprobación de en que combobox está en función de eso cambia el TextField y la búsqueda
		
		comboBoxGamePanel.add(Box.createRigidArea(new Dimension(0,30)));
		comboBoxGamePanel.add(comboBoxGame);
		comboBoxGamePanel.add(Box.createRigidArea(new Dimension(0,30)));
		
		searchGameType.add(searchGameText);
		searchGameType.add(comboBoxGamePanel);
		
		game.add(searchGameType);
		
		JPanel textGPanel = new JPanel();
		textGPanel.setLayout(new GridLayout(2,0));
		textGPanel.setOpaque(false);
		textGPanel.setPreferredSize(new Dimension(250, 100));
		textGPanel.setMinimumSize(new Dimension(250, 100));
		textGPanel.setMaximumSize(new Dimension(250, 100));
		
		JPanel textGamePanel = new JPanel();
		textGamePanel.setOpaque(false);
		textGamePanel.setPreferredSize(new Dimension(250, 100));
		textGamePanel.setMinimumSize(new Dimension(250, 100));
		textGamePanel.setMaximumSize(new Dimension(250, 100));
		
		TextField textGame = new TextField(new Dimension(225, 30),"Name of the Game");
		textGame.textField();
		textGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String search = textGame.getText();
				if(search.length() <= 50 && search.length() > 0) {
					textGame.setText(null);
					ApplicationController.getInstance().action(new Context(Event.SEARCH_ALL_BY_NAME, search));
					setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "Too many characters");
				}
			}
		});
		
		JPanel gameButtonPanel = new JPanel();
		gameButtonPanel.setOpaque(false);
		gameButtonPanel.setPreferredSize(new Dimension(250, 100));
		gameButtonPanel.setMinimumSize(new Dimension(250, 100));
		gameButtonPanel.setMaximumSize(new Dimension(250, 100));

		Button gameButton = new Button("SEARCH", "tinylupa_icon.png", new Dimension(120, 35), Color.orange);
		gameButton.buttonIcon();
		gameButton.setToolTipText("Search a Game by Name");
		gameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String search = textGame.getText();
				if(search.length() <= 50 && search.length() > 0) {
					textGame.setText(null);
					ApplicationController.getInstance().action(new Context(Event.SEARCH_ALL_BY_NAME, search));
					setVisible(false);
				}
				else if(search.length() > 50) {
					JOptionPane.showMessageDialog(null, "Too many characters");
				}
			}
		});
		
		textGamePanel.add(textGame);
		gameButtonPanel.add(gameButton);
		
		textGPanel.add(textGamePanel);
		textGPanel.add(gameButtonPanel);
		
		game.add(textGPanel);
		
		
		//PANEL DE BOX
		
		box.setLayout(new GridLayout(2,0));
		box.setOpaque(false);
		box.setPreferredSize(new Dimension(250, 217));
		box.setMinimumSize(new Dimension(250, 217));
		box.setMaximumSize(new Dimension(250, 217));
		box.setBorder(new BorderTitle("Boxes", Color.white));
		
		//BUSCAR BOXES

		JPanel searchBoxType = new JPanel();
		searchBoxType.setLayout(new GridLayout(0,2));
		searchBoxType.setOpaque(false);
		searchBoxType.setPreferredSize(new Dimension(250, 70));
		searchBoxType.setMinimumSize(new Dimension(250, 70));
		searchBoxType.setMaximumSize(new Dimension(250, 70));
		
		JLabel searchBoxText = new JLabel("Type of Search:");
		searchBoxText.setForeground(Color.white);
		searchBoxText.setFont(new Font("Leelawadee", Font.BOLD, 16));
		
		JPanel comboBoxBoxPanel = new JPanel();
		comboBoxBoxPanel.setLayout(new BoxLayout(comboBoxBoxPanel, BoxLayout.Y_AXIS));
		comboBoxBoxPanel.setOpaque(false);
		comboBoxBoxPanel.setPreferredSize(new Dimension(100, 30));
		comboBoxBoxPanel.setMinimumSize(new Dimension(100, 30));
		comboBoxBoxPanel.setMaximumSize(new Dimension(100, 30));
		
		JComboBox<String> comboBoxBox = new JComboBox<String>();
		comboBoxBox.addItem("By Name");
		comboBoxBox.setRenderer(listRenderer);
		
		comboBoxBoxPanel.add(Box.createRigidArea(new Dimension(0,30)));
		comboBoxBoxPanel.add(comboBoxBox);
		comboBoxBoxPanel.add(Box.createRigidArea(new Dimension(0,30)));
		
		searchBoxType.add(searchBoxText);
		searchBoxType.add(comboBoxBoxPanel);
		
		box.add(searchBoxType);
		
		JPanel textBPanel = new JPanel();
		textBPanel.setLayout(new GridLayout(2,0));
		textBPanel.setOpaque(false);
		textBPanel.setPreferredSize(new Dimension(250, 100));
		textBPanel.setMinimumSize(new Dimension(250, 100));
		textBPanel.setMaximumSize(new Dimension(250, 100));
		
		JPanel textBoxPanel = new JPanel();
		textBoxPanel.setOpaque(false);
		textBoxPanel.setPreferredSize(new Dimension(250, 100));
		textBoxPanel.setMinimumSize(new Dimension(250, 100));
		textBoxPanel.setMaximumSize(new Dimension(250, 100));
		
		TextField textBox = new TextField(new Dimension(225, 30),"Name of the Box");
		textBox.textField();
		textBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String search = textBox.getText();
				if(search.length() <= 50 && search.length() > 0) {
					textBox.setText(null);
					ApplicationController.getInstance().action(new Context(Event.SEARCH_ALL_BOXES_BY_NAME, search));
					setVisible(false);
				}
				else if(search.length() > 50) {
					JOptionPane.showMessageDialog(null, "Too many characters");
				}
			}
		});

		
		JPanel boxButtonPanel = new JPanel();
		boxButtonPanel.setOpaque(false);
		boxButtonPanel.setPreferredSize(new Dimension(250, 100));
		boxButtonPanel.setMinimumSize(new Dimension(250, 100));
		boxButtonPanel.setMaximumSize(new Dimension(250, 100));
		
		Button boxButton = new Button("SEARCH", "tinylupa_icon.png", new Dimension(120, 35), Color.orange);
		boxButton.buttonIcon();
		boxButton.setToolTipText("Search a Box by Name");
		boxButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String search = textBox.getText();
				if(search.length() <= 50 && search.length() > 0) {
					textBox.setText(null);
					ApplicationController.getInstance().action(new Context(Event.SEARCH_ALL_BOXES_BY_NAME, search));		//CAMBIAR EL EVENTO CUANDO ESTE HECHA LA VISTA
					setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "There is no Boxes with that name");
				}
			}
		});
		
		textBoxPanel.add(textBox);
		boxButtonPanel.add(boxButton);
		
		textBPanel.add(textBoxPanel);
		textBPanel.add(boxButtonPanel);
		
		box.add(textBPanel);
		
		//PANEL DE USERS

		user.setLayout(new GridLayout(2,0));
		user.setOpaque(false);
		user.setPreferredSize(new Dimension(250, 217));
		user.setMinimumSize(new Dimension(250, 217));
		user.setMaximumSize(new Dimension(250, 217));
		user.setBorder(new BorderTitle("Users", Color.white));
		
		//BUSCAR USERS

		JPanel searchUserType = new JPanel();
		searchUserType.setLayout(new GridLayout(0,2));
		searchUserType.setOpaque(false);
		searchUserType.setPreferredSize(new Dimension(250, 70));
		searchUserType.setMinimumSize(new Dimension(250, 70));
		searchUserType.setMaximumSize(new Dimension(250, 70));
		
		JLabel searchUserText = new JLabel("Type of Search:");
		searchUserText.setForeground(Color.white);
		searchUserText.setFont(new Font("Leelawadee", Font.BOLD, 16));
		
		JPanel comboBoxUserPanel = new JPanel();
		comboBoxUserPanel.setLayout(new BoxLayout(comboBoxUserPanel, BoxLayout.Y_AXIS));
		comboBoxUserPanel.setOpaque(false);
		comboBoxUserPanel.setPreferredSize(new Dimension(100, 30));
		comboBoxUserPanel.setMinimumSize(new Dimension(100, 30));
		comboBoxUserPanel.setMaximumSize(new Dimension(100, 30));
		
		JComboBox<String> comboBoxUser = new JComboBox<String>();
		comboBoxUser.addItem("By Name");
		comboBoxUser.setRenderer(listRenderer);
		
		comboBoxUserPanel.add(Box.createRigidArea(new Dimension(0,30)));
		comboBoxUserPanel.add(comboBoxUser);
		comboBoxUserPanel.add(Box.createRigidArea(new Dimension(0,30)));
		
		searchUserType.add(searchUserText);
		searchUserType.add(comboBoxUserPanel);
		
		user.add(searchUserType);
		
		JPanel textUPanel = new JPanel();
		textUPanel.setLayout(new GridLayout(2,0));
		textUPanel.setOpaque(false);
		textUPanel.setPreferredSize(new Dimension(250, 100));
		textUPanel.setMinimumSize(new Dimension(250, 100));
		textUPanel.setMaximumSize(new Dimension(250, 100));
		
		JPanel textUserPanel = new JPanel();
		textUserPanel.setOpaque(false);
		textUserPanel.setPreferredSize(new Dimension(250, 100));
		textUserPanel.setMinimumSize(new Dimension(250, 100));
		textUserPanel.setMaximumSize(new Dimension(250, 100));
		
		TextField textUser = new TextField(new Dimension(225, 30),"Name of the User");
		textUser.textField();
		textUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String search = textUser.getText();
				if(search.length() <= 50 && search.length() > 0) {
					textUser.setText(null);
					//LLAMADA AL EVENTO
					setVisible(false);
				}
				else if(search.length() > 50) {
					JOptionPane.showMessageDialog(null, "Too many characters");
				}
			}
		});
		
		JPanel userButtonPanel = new JPanel();
		userButtonPanel.setOpaque(false);
		userButtonPanel.setPreferredSize(new Dimension(250, 100));
		userButtonPanel.setMinimumSize(new Dimension(250, 100));
		userButtonPanel.setMaximumSize(new Dimension(250, 100));
		
		Button userButton = new Button("SEARCH", "tinylupa_icon.png", new Dimension(120, 35), Color.orange);
		userButton.buttonIcon();
		userButton.setToolTipText("Search a Box by Name");
		userButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String search = textUser.getText();
				if(search.length() <= 50 && search.length() > 0) {
					textUser.setText(null);
					//LLAMADA AL EVENTO
					setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "There is no Boxes with that name");
				}
			}
		});
		
		textUserPanel.add(textUser);
		userButtonPanel.add(userButton);
		
		textUPanel.add(textUserPanel);
		textUPanel.add(userButtonPanel);
		
		user.add(textUPanel);

		topPanel.add(top);
		topPanel.add(game);
		topPanel.add(box);
		topPanel.add(user);

		return topPanel;
	}


	public JPanel creaMidPanel() {
		JPanel midpanel = new JPanel();
		midpanel.setLayout(new BoxLayout(midpanel, BoxLayout.Y_AXIS));
		midpanel.setBorder(new EmptyBorder(0, 10, 0, 10));
		midpanel.setOpaque(false);
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topPanel.setOpaque(false);
		topPanel.setMaximumSize(new Dimension(1150, 172));
		topPanel.setMinimumSize(new Dimension(950, 172));
		
		JPanel iconPanel = new JPanel();
		iconPanel.setOpaque(false);
		
		JLabel icono = new JLabel();
		icono.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_medium_blanco.png")));
		icono.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		icono.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		
		iconPanel.add(icono);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		
		Button myBox = new Button("Create Box", null, Color.white, null, new Dimension(120, 50), Color.orange);
		myBox.button();
		myBox.setBorderPainted(false);
		myBox.setContentAreaFilled(false);
		myBox.setAlignmentX(JPanel.RIGHT_ALIGNMENT);
		myBox.setAlignmentY(JPanel.TOP_ALIGNMENT);
		myBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//CAMBIAR CUANDO TENGAMOS EL ACCESO A MIS BOXES
				ApplicationController.getInstance().action(new Context(Event.VIEW_CREATE_BOX, null));
				setVisible(false);
			}
		});
		
		buttonPanel.add(myBox);
		
		topPanel.add(iconPanel, BorderLayout.CENTER);
		topPanel.add(buttonPanel, BorderLayout.EAST);
		
		//JPanelRound randomPanel = new JPanelRound(new Color(0, 0, 128), new Color(47, 79, 79), new Color(0, 0, 128)); //Azul-gris
		//JPanelRound randomPanel = new JPanelRound(new Color(46, 56, 90), new Color(107, 113, 144), new Color(46, 56, 90)); //Grises
		//JPanelRound randomPanel = new JPanelRound(new Color(0, 0, 0), new Color(107, 113, 144), new Color(0, 0, 0)); //Negro-Gris
		//JPanelRound randomPanel = new JPanelRound(new Color(62, 65, 68), new Color(101, 114, 124), new Color(62, 65, 68)); //Grises más oscuros
		//JPanelRound randomPanel = new JPanelRound(new Color(122, 122, 122), new Color(200, 200, 200), new Color(122, 122, 122)); //Grises claros
		JPanelRound randomPanel = new JPanelRound(new Color(52, 69, 77), new Color(107, 138, 156), new Color(52, 69, 77)); //Relacionados con el fondo
		randomPanel.setMaximumSize(new Dimension(950, 420));
		randomPanel.setMinimumSize(new Dimension(900, 420));
		randomPanel.setLayout(new BoxLayout(randomPanel, BoxLayout.Y_AXIS));
		randomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		
		JPanel panel1 = new JPanel();
		panel1.setOpaque(false);
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
				
		randomPanel.add(panel1);
		
		//Falta funcion para coger 3 juegos randoms
		
		/*List<TGame> games = new ArrayList<TGame>();
		List<String> dev = new ArrayList<String>();
		dev.add("deve1"); dev.add("deve2");
		List<String> cat = new ArrayList<String>();
		cat.add("cate1"); cat.add("cate2");
		List<String> plat = new ArrayList<String>();
		plat.add("plat1"); plat.add("plat2");
		TGame game1 = new TGame("Game1", "//images.igdb.com/igdb/image/upload/t_cover_big/co2rld.jpg", dev, cat, plat, "Descripcion del juego");
		TGame game2 = new TGame("Game2", "//images.igdb.com/igdb/image/upload/t_cover_big/co2rld.jpg", dev, cat, plat, "Descripcion del juego");
		TGame game3 = new TGame("Game3", "//images.igdb.com/igdb/image/upload/t_cover_big/co2rld.jpg", dev, cat, plat, "Descripcion del juego");
		games.add(game1);
		games.add(game2);
		games.add(game3);
		
		for(TGame g : games) panel1.add(gamePanel(g));*/
		
		midpanel.add(topPanel);
		midpanel.add(Box.createRigidArea(new Dimension(0,20)));
		midpanel.add(randomPanel);
		
		return midpanel;
	}
	
	public JPanel gamePanel(TGame g) {
		JPanel main = new JPanel(new BorderLayout());
		main.setMaximumSize(new Dimension(300, 400));
		main.setMinimumSize(new Dimension(200, 400));
		main.setOpaque(false);
		
		JLabel coverLabel = new JLabel();
		coverLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
		coverLabel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
		if (g.getCover() != null) {
			Image image = null;
			URL url;
			try {
				url = new URL( "https:"+ g.getCover());
				URLConnection connection = url.openConnection();
				connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
				connection.connect();
				image = ImageIO.read(connection.getInputStream());
				connection.getInputStream().close();
				coverLabel = new JLabel(new ImageIcon(image));
				coverLabel.setAlignmentX(CENTER_ALIGNMENT);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			coverLabel.setIcon(new ImageIcon((getClass().getClassLoader().getResource("No_Image_big.png"))));
		}
		
		JPanel namePanel = new JPanel();
		namePanel.setOpaque(false);
		
		JLabel name = new JLabel(g.getName());
		name.setForeground(Color.white);
        name.setFont(new Font("Leelawadee", Font.BOLD, 17));
        name.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        name.setAlignmentY(JPanel.TOP_ALIGNMENT);
		
        namePanel.add(name);
        
		main.add(coverLabel, BorderLayout.NORTH);
		main.add(namePanel, BorderLayout.SOUTH);
		
		return main;
	}
	
	private void refreshView(){
		setLocationRelativeTo(null);
		setVisible(true);
	}

}