package Presentation.View.Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Logic.Game.TGame;
import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Main.BorderTitle;
import Presentation.View.Main.JPanelConFondo;
import org.bson.types.ObjectId;

public class ViewShowOne extends JFrame implements IView {

	private static final long serialVersionUID = 1L;

	TGame game;
	private ObjectId _id;
	private static int logged;
	
	public ViewShowOne(TGame game) {
		super();
		logged = -1;
		this.game = game;
		initGUI(game);
	}

	private void initGUI(TGame game) {
		
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400,100);
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("caja_definitiva.png")).getImage();
		this.setIconImage(iconFrame);
		
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
					// En esta vista habría que diferenciar de si el usuario esta logueado o no, pero de momento damos por hecho que no se ha logueado.
					logged = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter your user name:", "Access to the user aplication", JOptionPane.YES_NO_CANCEL_OPTION));
					//Se metería a la vista de una vez estás conectado
					
				} catch(Exception e1) {
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
		
		//ICONO DE LA APLICACION
		JLabel icon = new JLabel();
		icon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_small_blanco.png")));
				
		topPanel.add(Box.createRigidArea(new Dimension(20,0)));
		topPanel.add(logIn);
		topPanel.add(Box.createRigidArea(new Dimension(10,0)));
		topPanel.add(registro);
		topPanel.add(Box.createRigidArea(new Dimension(110,0)));
		topPanel.add(icon);

		
		return topPanel;
	}
	
	// Panel de la vista de mostrar un juego, donde se detallan sus atributos.
	private JPanel createMidPanel() {
		JPanel midPanel = new JPanel();
		midPanel.setPreferredSize(new Dimension(1150, 600));
		midPanel.setMaximumSize(new Dimension(1150, 600));
		midPanel.setMinimumSize(new Dimension(1150, 600));
		midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
		midPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
		midPanel.setBackground(new Color(64, 147, 255));
		midPanel.setBorder(new BorderTitle("About the game", Color.white));
		
		// INFO DEL JUEGO
		JLabel coverLabel = new JLabel();			// Carátula del juego.
		if (game.getCover() != "nan") {
			Image image = null;
			URL url;
			try {
				url = new URL("https:" + game.getCover());
				image = ImageIO.read(url);
				coverLabel = new JLabel(new ImageIcon(image));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		JLabel nameLabel = null;					// Nombre del juego.
		if(game.getName() != null) {
			nameLabel = new JLabel(game.getName());
		}
		
		List<JLabel> companyLabel = new ArrayList<>();		// Empresas responsables del juego.
		for(int i = 0; game.getInvolvedCompanies() != null && !game.getInvolvedCompanies().isEmpty() && i < game.getGenres().size(); i++) {
			companyLabel.add(new JLabel(game.getInvolvedCompanies().get(i)));
		}
		
		List<JLabel> categoryLabel = new ArrayList<>();		// Categorías del juego.
		for(int i = 0; game.getGenres() != null && !game.getGenres().isEmpty() && i < game.getGenres().size(); i++) {
			categoryLabel.add(new JLabel(game.getGenres().get(i)));
		}
		
		List<JLabel> platformLabel = new ArrayList<>();		// Plataformas para las que está disponible el juego.
		for(int i = 0; game.getPlatforms() != null && !game.getPlatforms().isEmpty() && i < game.getGenres().size(); i++) {
			platformLabel.add(new JLabel(game.getPlatforms().get(i)));
		}
		
		JLabel descLabel = null;			// Descripción del juego.
		if(game.getSummary() != null) descLabel = new JLabel(game.getSummary());
		
		// BACK BUTTON (para volver a la vista principal).
		JPanel backButtonPanel = goBackButtonPanel();
		
		midPanel.add(backButtonPanel);
		midPanel.add(coverLabel);
		midPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		if(nameLabel != null) midPanel.add(nameLabel);
		midPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		for(int i = 0; companyLabel != null && i < companyLabel.size(); i++) {
			midPanel.add(companyLabel.get(i));
		}
		midPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		for(int i = 0; categoryLabel != null && i < categoryLabel.size(); i++) {
			midPanel.add(categoryLabel.get(i));
		}
		midPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		for(int i = 0; platformLabel != null && i < platformLabel.size(); i++) {
			midPanel.add(platformLabel.get(i));
		}
		midPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		if(descLabel != null) midPanel.add(descLabel);
		midPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		this.pack();
		return midPanel;
	}
	
	private JPanel goBackButtonPanel() {
		JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonPanel.setMaximumSize(new Dimension(1150, 75));
		
		JButton goBackButton = new JButton();
		goBackButton.setBackground(new Color(237, 237, 237));
		goBackButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("back_icon.png")));
		goBackButton.setToolTipText("Volver a inicio");
		goBackButton.setPreferredSize(new Dimension(60, 60));
		goBackButton.setBorderPainted(false);
		goBackButton.setAlignmentX(LEFT_ALIGNMENT);
		
		goBackButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().action(new Context(Event.VIEW, _id));
				dispose();
			}
		});
		backButtonPanel.add(goBackButton);
		
		return backButtonPanel;
	}

	@Override
	public void update(Context context) {}
}