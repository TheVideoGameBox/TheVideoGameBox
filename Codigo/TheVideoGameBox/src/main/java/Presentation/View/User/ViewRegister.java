package Presentation.View.User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Main.BorderTitle;
import Presentation.View.Main.JPanelConFondo;

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

	return midPanel;
}

	@Override
	public void update(Context context) {
		// TODO Auto-generated method stub
	}

}
