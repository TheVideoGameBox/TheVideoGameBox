package Presentation.View.Game;

import Logic.Game.TGame;
import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Main.JPanelConFondo;
import org.bson.types.ObjectId;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class ViewShowOne extends JFrame implements IView {

	private static final long serialVersionUID = 1L;

	TGame game;
	private ObjectId _id;
	
	public ViewShowOne(TGame game) {
		super();
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
		JButton backButton = new JButton();
		backButton.setPreferredSize(new Dimension(50, 50));
		backButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("back_icon.png")));
		backButton.setMaximumSize(new Dimension(50, 50));
		backButton.setMinimumSize(new Dimension(50, 50));
		backButton.setBorderPainted(false);
		backButton.setOpaque(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.setToolTipText("Back to the main window");
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().action(new Context(Event.VIEW, null));
				dispose();
			}
		});
		
		//ICONO DE LA APLICACION
		JLabel icon = new JLabel();
		icon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_small_blanco.png")));
				
		topPanel.add(Box.createRigidArea(new Dimension(20,0)));
		topPanel.add(backButton);
		topPanel.add(Box.createRigidArea(new Dimension(270,0)));
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
		midPanel.setOpaque(false);
		
		JLabel title = new JLabel("About the game");
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setForeground(Color.white);
		title.setFont(new Font("sans-serif", 1, 35));
		
		// INFO DEL JUEGO
		
		// Carátula del juego.
		JLabel coverLabel = new JLabel();	
		coverLabel.setAlignmentX(CENTER_ALIGNMENT);
		if (game.getCover() != null) {
			Image image = null;
			URL url;
			try {
				url = new URL("https:" + game.getCover());
				image = ImageIO.read(url);
				coverLabel = new JLabel(new ImageIcon(image));
				coverLabel.setAlignmentX(CENTER_ALIGNMENT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			coverLabel.setIcon(new ImageIcon((getClass().getClassLoader().getResource("no_image.png"))));
		}
		
		// Nombre del juego.
		JLabel nameTitle = new JLabel("Nombre");
		nameTitle.setAlignmentX(CENTER_ALIGNMENT);
		nameTitle.setForeground(Color.white);
		nameTitle.setFont(new Font("sans-serif", 1, 25));
		
		JLabel nameLabel = new JLabel(game.getName());
		nameLabel.setAlignmentX(CENTER_ALIGNMENT);
		nameLabel.setForeground(Color.white);
		nameLabel.setFont(new Font("sans-serif", 1, 15));
		
		
		// Empresas responsables del juego.
		JPanel companyPanel = new JPanel();
		companyPanel.setLayout(new BoxLayout(companyPanel, BoxLayout.Y_AXIS));
		companyPanel.setOpaque(false);
		companyPanel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel companyTitle = new JLabel("Companies");
		companyTitle.setAlignmentX(CENTER_ALIGNMENT);
		companyTitle.setForeground(Color.white);
		companyTitle.setFont(new Font("sans-serif", 1, 25));
		companyPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		companyPanel.add(companyTitle);
		if(game.getInvolvedCompanies() == null) {
			companyPanel.add(Box.createRigidArea(new Dimension(0, 5)));
			companyPanel.add(companyLabel("There isn't any company."));
		}
		else {
			for(int i = 0; game.getInvolvedCompanies() != null && !game.getInvolvedCompanies().isEmpty() && i < game.getInvolvedCompanies().size(); i++) {
				companyPanel.add(Box.createRigidArea(new Dimension(0, 5)));
				companyPanel.add(companyLabel(game.getInvolvedCompanies().get(i)));
			}
		}
		
		// Categorías del juego.
		JPanel categoryPanel = new JPanel();
		categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
		categoryPanel.setOpaque(false);
		categoryPanel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel categoryTitle = new JLabel("Genres");
		categoryTitle.setAlignmentX(CENTER_ALIGNMENT);
		categoryTitle.setForeground(Color.white);
		categoryTitle.setFont(new Font("sans-serif", 1, 25));
		categoryPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		categoryPanel.add(categoryTitle);
		if(game.getGenres() == null) {
			categoryPanel.add(Box.createRigidArea(new Dimension(0, 5)));
			categoryPanel.add(categoryLabel("There isn't any genre."));
		}
		else {
			for(int i = 0; game.getGenres() != null && !game.getGenres().isEmpty() && i < game.getGenres().size(); i++) {
				categoryPanel.add(Box.createRigidArea(new Dimension(0, 5)));
				categoryPanel.add(categoryLabel(game.getGenres().get(i)));
			}
		}
		
		// Plataformas para las que está disponible el juego.
		JPanel platformPanel = new JPanel();
		platformPanel.setLayout(new BoxLayout(platformPanel, BoxLayout.Y_AXIS));
		platformPanel.setOpaque(false);
		platformPanel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel platformTitle = new JLabel("Platforms");
		platformTitle.setAlignmentX(CENTER_ALIGNMENT);
		platformTitle.setForeground(Color.white);
		platformTitle.setFont(new Font("sans-serif", 1, 25));
		platformPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		platformPanel.add(platformTitle);
		if(game.getPlatforms() == null) {
			platformPanel.add(Box.createRigidArea(new Dimension(0, 5)));
			platformPanel.add(platformLabel("There isn't any platform."));
		}
		else{
			for(int i = 0; game.getPlatforms() != null && !game.getPlatforms().isEmpty() && i < game.getPlatforms().size(); i++) {
				platformPanel.add(Box.createRigidArea(new Dimension(0, 5)));
				platformPanel.add(platformLabel(game.getPlatforms().get(i)));
			}
		}
		// Descripción del juego.
		JLabel descTitle = new JLabel("Description");
		descTitle.setAlignmentX(CENTER_ALIGNMENT);
		descTitle.setForeground(Color.white);
		descTitle.setFont(new Font("sans-serif", 1, 25));
		JLabel descLabel = new JLabel("There isn't any description.");
		descLabel.setAlignmentX(CENTER_ALIGNMENT);
		descLabel.setForeground(Color.white);
		descLabel.setFont(new Font("sans-serif", 1, 14));
		if(game.getSummary() != null) {
			descLabel = new JLabel(game.getSummary());
			descLabel.setAlignmentX(CENTER_ALIGNMENT);
			descLabel.setForeground(Color.white);
			descLabel.setFont(new Font("sans-serif", 1, 14));
		}
		
		midPanel.add(title);
		midPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		midPanel.add(coverLabel);
		midPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		midPanel.add(nameTitle);
		midPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		midPanel.add(nameLabel);
		midPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		midPanel.add(companyPanel);
		midPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		midPanel.add(categoryPanel);
		midPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		midPanel.add(platformPanel);
		midPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		midPanel.add(descTitle);
		midPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		midPanel.add(descLabel);
		this.pack();
		return midPanel;
	}
	
	private JLabel companyLabel(String company) {
		JLabel comp = new JLabel(company);
		comp.setAlignmentX(CENTER_ALIGNMENT);
		comp.setForeground(Color.white);
		comp.setFont(new Font("sans-serif", 1, 15));
		return comp;
	}
	
	private JLabel categoryLabel(String category) {
		JLabel comp = new JLabel(category);
		comp.setAlignmentX(CENTER_ALIGNMENT);
		comp.setForeground(Color.white);
		comp.setFont(new Font("sans-serif", 1, 15));
		return comp;
	}
	
	private JLabel platformLabel(String platform) {
		JLabel comp = new JLabel(platform);
		comp.setAlignmentX(CENTER_ALIGNMENT);
		comp.setForeground(Color.white);
		comp.setFont(new Font("sans-serif", 1, 15));
		return comp;
	}
	

	@Override
	public void update(Context context) {}
}
