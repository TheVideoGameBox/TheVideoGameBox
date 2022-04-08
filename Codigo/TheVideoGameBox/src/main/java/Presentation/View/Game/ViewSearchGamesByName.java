package Presentation.View.Game;

import Logic.Game.TGame;
import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Main.JPanelConFondo;
import Presentation.View.Main.JPanelRound;
import Presentation.View.Utils.Button;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Objects;

import static Presentation.View.Utils.Images.backGround;
import static Presentation.View.Utils.Images.logo;

public class ViewSearchGamesByName extends JFrame implements IView{
	private List<TGame> games;

	public ViewSearchGamesByName(List<TGame> games) {
		setTitle("Games List");
		this.games = games;
		init_GUI();
		this.setLocationRelativeTo(null);
	}

	public void init_GUI() {
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);
		
		Image iconFrame = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(logo))).getImage();
		this.setIconImage(iconFrame);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanelConFondo mainPanel = new JPanelConFondo();
		mainPanel.setImagen(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(backGround))).getImage());
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
		scrollFrame.getVerticalScrollBar().setUnitIncrement(25);
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
		JLabel title = new JLabel("Games Results");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
		title.setAlignmentX(CENTER_ALIGNMENT);
		title.setForeground(Color.white);
		title.setFont(new Font("sans-serif", 1, 20));
		headerContainer.add(title);
		headerContainer.add(Box.createRigidArea(new Dimension(130, 0)));

		// ICONO DE MENU
		JButton icon = new JButton();
		icon.setIcon(new ImageIcon((Objects.requireNonNull(getClass().getClassLoader().getResource("logo_small_blanco.png")))));
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

		JPanelRound panel = new JPanelRound(new Color(26, 59, 160), new Color(64, 147, 255));
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
		JLabel name = new JLabel(game.getName());
		name.setForeground(Color.white);
		name.setFont(new Font("Leelawadee", Font.BOLD, 20));
		JLabel cover = new JLabel();
		if(game.getCover() != null) {
			Image image = null;
			URL url = new URL( "https:"+ game.getCover());
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			connection.connect();
			image = ImageIO.read(connection.getInputStream());
			connection.getInputStream().close();
			cover = new JLabel(new ImageIcon(image));
		}
		else {
			cover.setIcon(new ImageIcon((Objects.requireNonNull(getClass().getClassLoader().getResource("no_image.png")))));
		}
				
		// CONSTRUIR NAMEPANEL
		namePanel.add(Box.createRigidArea(new Dimension(10, 0)));
		namePanel.add(cover);
		namePanel.add(Box.createRigidArea(new Dimension(55, 0)));
		namePanel.add(name);
		
		//BUTTON PANEL
		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.setOpaque(false);
		Button buttonCreate = new Button("View Games", "info_icon.png", new Color(50, 170, 0));
		JButton viewInfo = buttonCreate.buttonIcon();
		viewInfo.setBorderPainted(false);
		viewInfo.setContentAreaFilled(false);
		viewInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().action(new Context(Event.SEARCH_ONE, game.getId()));
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
		
	}

}