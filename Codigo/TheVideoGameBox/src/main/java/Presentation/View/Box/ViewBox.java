package Presentation.View.Box;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logic.Box.TBox;
import Presentation.Controller.Context;
import Presentation.View.IView;
import Presentation.View.Main.BorderTitle;
import Presentation.View.Main.JPanelConFondo;

public class ViewBox extends JFrame implements IView{

	private static final long serialVersionUID = 1L;
	TBox box;
	
	public ViewBox(TBox box) {
		super();
		this.box = box;
		initGUI(box);
	}
	 
	
    private void initGUI(TBox box) {
    	
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
		
		//ICONO DE LA APLICACION
		JLabel icon = new JLabel();
		icon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_small_blanco.png")));		
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
		midPanel.setBorder(new BorderTitle("Info about box", Color.black));
		
		// En esta HU (Añadir un juego a una box), solamente va a haber un botón para añadir juegos. El resto de la vista
		// de la box se realizará en Ver juegos de la box.
		
		//ADD GAME BUTTON
		JButton addGameButton = new JButton("Add game");
		addGameButton.setIcon(new ImageIcon((getClass().getClassLoader().getResource("add_button.png"))));
		addGameButton.setBackground(new Color(255, 255, 255));
		addGameButton.setForeground(Color.white);
		addGameButton.setBorder(BorderFactory.createBevelBorder(0));
		addGameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//try {
					// Llamamos al evento de search all by name, pues la vista va a ser la misma, pero cambiará que al hacer click
					// en un juego, en lugar de mostrar su info, se añadirá a la box.
					//Controller
				//} //catch(Exception e) {
					
				//}
			}
			
		});
		
		return null;
	}

	@Override
	public void update(Context context) {
		// TODO Auto-generated method stub
		
	}

}
