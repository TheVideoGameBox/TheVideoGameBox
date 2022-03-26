package Presentation.View.Box;

import Logic.Game.TGame;
import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Main.JPanelConFondo;
import Presentation.View.Main.JPanelRound;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.List;

// Clase vista para añadir juegos a una box. Es igual que ViewSearchByName pero cambiando el Event.
public class ViewAddGameToBox extends JFrame implements IView {

	private static final long serialVersionUID = 1L;
	
	private List<TGame> games;
	
	public ViewAddGameToBox(List<TGame> games) {
		setTitle("Add a game to box"); 
		this.games = games;
		init_GUI();
		this.setLocationRelativeTo(null);
	}
	
	private void init_GUI() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("caja_definitiva.png")).getImage();
		this.setIconImage(iconFrame);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanelConFondo mainPanel = new JPanelConFondo();
		mainPanel.setImagen(new ImageIcon(getClass().getClassLoader().getResource("fondo_triangular.png")).getImage());
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500, 500));
		this.add(mainPanel);
		
		// CONTENT CONTAINER

		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setOpaque(false);

		JScrollPane scrollFrame = new JScrollPane(contentContainer);
		contentContainer.setAutoscrolls(true);
		scrollFrame.setOpaque(false);
		scrollFrame.getViewport().setOpaque(false);

		mainPanel.add(scrollFrame);
				
		// HEADER
		JPanel headerContainer = new JPanel();
		headerContainer.setMaximumSize(new Dimension(1200, 100));
		headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.X_AXIS));
		headerContainer.setOpaque(false);
		headerContainer.add(Box.createRigidArea(new Dimension(60, 0)));
				
		// TITLE
		JLabel title = new JLabel("Select a game to add");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setForeground(Color.white);
		title.setFont(new Font("sans-serif", 1, 20));
		headerContainer.add(title);
		headerContainer.add(Box.createRigidArea(new Dimension(190, 0)));
		
		// ICONO DE MENU
		JButton icon = new JButton();
		icon.setIcon(new ImageIcon((getClass().getClassLoader().getResource("logo_small_blanco.png"))));
		icon.setToolTipText("Back to main window");
		icon.setBorderPainted(false);
		icon.setOpaque(false);
		icon.setContentAreaFilled(false);
		icon.setFocusPainted(false);
		icon.setAlignmentX(CENTER_ALIGNMENT);
		headerContainer.add(icon);
		headerContainer.add(Box.createRigidArea(new Dimension(270, 0)));

		icon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().action(new Context(Event.VIEW, null));
				dispose();
	 		}
		});
				
		// CONSTRUIR VISTA
		contentContainer.add(headerContainer);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
			
		// HELP
		JPanel helpPanel = new JPanel();
		JLabel help = new JLabel("Push the TVGB logo to go back the main window");
		help.setForeground(Color.white);
		help.setAlignmentX(CENTER_ALIGNMENT);
		help.setFont(new Font("Leelawadee", Font.BOLD, 13));
		helpPanel.setOpaque(false);
		helpPanel.setMaximumSize(new Dimension(1000, 40));
		helpPanel.add(help);
				
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(helpPanel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
				
		for (TGame game : games) {
			try {
				contentContainer.add(gamePanel(game));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		}
				
		this.pack();
		this.setVisible(true);
	}
	
	private JPanelRound gamePanel(TGame game) throws IOException {

		JPanelRound panel = new JPanelRound();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(10, 10, 10, 20));
		panel.setMaximumSize(new Dimension(1000, 135));
		panel.setPreferredSize(new Dimension(1000, 135));
		panel.setMinimumSize(new Dimension(1000, 135));
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
		namePanel.setOpaque(false);
		namePanel.setMaximumSize(new Dimension(700, 135));
		namePanel.setPreferredSize(new Dimension(700, 135));
		namePanel.setMinimumSize(new Dimension(700, 135));
		
		// NAME
		JLabel name = new JLabel("Nombre: " + game.getName());
		name.setForeground(Color.white);
		name.setFont(new Font("Leelawadee", Font.BOLD, 20));
		name.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ApplicationController.getInstance().action(new Context(Event.ADD_GAME_TO_BOX, game.getId()));
				dispose();
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

		});
		
		//COVER
		JLabel cover = new JLabel();
		if (game.getCover() != "nan") {
			Image image = null;
			URL url = new URL( "https:"+ game.getCover());
			image = ImageIO.read(url);
			cover = new JLabel(new ImageIcon(image));
		}
		
		// CONSTRUIR NAMEPANEL
		namePanel.add(Box.createRigidArea(new Dimension(10, 0)));
		namePanel.add(cover);
		namePanel.add(Box.createRigidArea(new Dimension(70, 0)));
		namePanel.add(name);
		
		//BUTTON PANEL
		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.setOpaque(false);
		buttonPanel.setMaximumSize(new Dimension(300, 135));
		buttonPanel.setPreferredSize(new Dimension(300, 135));
		buttonPanel.setMinimumSize(new Dimension(300, 135));
		JButton viewInfo = new JButton("Add game");
		viewInfo.setPreferredSize(new Dimension(170, 45));
		viewInfo.setMaximumSize(new Dimension(170, 45));
		viewInfo.setMinimumSize(new Dimension(170, 45));
		viewInfo.setBackground(new Color(64, 147, 255));
		viewInfo.setForeground(Color.white);
		viewInfo.setFont(new Font("Leelawadee", Font.BOLD, 15));
		viewInfo.setBorder(BorderFactory.createBevelBorder(0));
		viewInfo.setFocusPainted(false);
		viewInfo.setAlignmentX(CENTER_ALIGNMENT);
		viewInfo.setAlignmentY(CENTER_ALIGNMENT);
		viewInfo.setToolTipText("Search a Game by Name");
		viewInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().action(new Context(Event.ADD_GAME_TO_BOX, game.getId()));
				dispose();
			}
		});
		buttonPanel.add(viewInfo, BorderLayout.CENTER);
		
		//CONSTRUIR PANEL
		panel.add(namePanel, BorderLayout.WEST);
		panel.add(buttonPanel, BorderLayout.EAST);
		
		return panel;
	}

	@Override
	public void update(Context context) {
		// TODO Auto-generated method stub
		
	}

}
