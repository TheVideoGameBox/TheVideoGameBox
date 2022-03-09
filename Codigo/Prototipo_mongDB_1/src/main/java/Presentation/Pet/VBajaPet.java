package Presentation.Pet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

import Logic.Pet.PetFunctions;
import Logic.Pet.TPet;
import Presentation.VPrincipal;

public class VBajaPet extends JFrame{
	private TPet p;
	public VBajaPet(TPet p) {
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono_prueba.png")).getImage();
		this.setIconImage(iconFrame);
		setTitle("Baja Mascota");
		this.p = p;
		init_GUI();
	}
	
	public void init_GUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		mainPanel.setPreferredSize(new Dimension(500,300));
		mainPanel.setBorder(new EmptyBorder(25, 10, 10, 10));
		mainPanel.setBackground(Color.white);
				
		// TITULO
		JLabel titleLabel = new JLabel("�Desea eliminar la mascota con la siguiente informaci�n?");
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setFont(new Font("Leelawadee", Font.BOLD, 17));
		titleLabel.setForeground(new Color(64, 147, 255));
		
		// CONTENT CONTAINER
		JPanel contentContainer = new JPanel();
		contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
		contentContainer.setAlignmentX(CENTER_ALIGNMENT);
		contentContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentContainer.setOpaque(false);
		
		// INFO
		JLabel nifLabel = new JLabel("NIF: " + p.getNif());
		nifLabel.setFont(new Font("Leelawadee", Font.BOLD, 15));
		nifLabel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel nombreLabel = new JLabel("Nombre: " + p.getNombre());
		nombreLabel.setFont(new Font("Leelawadee", Font.BOLD, 15));
		nombreLabel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel tipoLabel = new JLabel("Tipo: " + p.getTipo());
		tipoLabel.setFont(new Font("Leelawadee", Font.BOLD, 15));
		tipoLabel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel infoLabel = new JLabel("Informaci�n extra: " + p.getInfoExtra());
		infoLabel.setFont(new Font("Leelawadee", Font.BOLD, 15));
		infoLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		// CONSTRUIR VISTAS
		mainPanel.add(titleLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(contentContainer);
		contentContainer.add(nifLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(nombreLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(tipoLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(infoLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		
		// BOTONES
		JButton yesButton = new JButton("CONFIRMO");
		JButton noButton = new JButton("NO");
		yesButton.setBackground(new Color(39, 174, 95));
		yesButton.setForeground(Color.white);
		yesButton.setPreferredSize(new Dimension(80,50));
		yesButton.setMaximumSize(new Dimension(80,50));
		yesButton.setMinimumSize(new Dimension(80,50));
		yesButton.setBorder(BorderFactory.createBevelBorder(0));
		yesButton.setFocusPainted(false);
			
		noButton.setBackground(new Color(236, 115, 115));
		noButton.setForeground(Color.white);
		noButton.setPreferredSize(new Dimension(80,50));
		noButton.setMaximumSize(new Dimension(80,50));
		noButton.setMinimumSize(new Dimension(80,50));
		noButton.setBorder(BorderFactory.createBevelBorder(0));
		noButton.setFocusPainted(false);
			
		noButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					VPrincipal principal = new VPrincipal();
					dispose();
				}
				
			});
			
			
		yesButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					PetFunctions petFunctions = new PetFunctions();
					petFunctions.delete(p.getId());
					VPrincipal principal = new VPrincipal();
					dispose();
				}
				
			});
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel,BoxLayout.X_AXIS));
		buttonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		buttonsPanel.add(yesButton);
		buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonsPanel.add(noButton);
		
		this.add(mainPanel);
		mainPanel.add(buttonsPanel);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
	}
}
