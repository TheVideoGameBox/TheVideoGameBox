package Presentation.Person;

import Logic.Person.SAPersonImp;
import Logic.Person.TPerson;
import Presentation.VPrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VBajaPrueba extends JFrame{
	private TPerson p;
	public VBajaPrueba(TPerson p) {
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono_prueba.png")).getImage();
		this.setIconImage(iconFrame);
		setTitle("Baja Persona");
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
		JLabel titleLabel = new JLabel("�Desea eliminar la prueba con la siguiente informacion?");
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
		JLabel apellidoLabel = new JLabel("Apellidos: " + p.getApellidos());
		apellidoLabel.setFont(new Font("Leelawadee", Font.BOLD, 15));
		apellidoLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		
		// CONSTRUIR VISTAS
		mainPanel.add(titleLabel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
		mainPanel.add(contentContainer);
		contentContainer.add(nifLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(nombreLabel);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		contentContainer.add(apellidoLabel);
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
					SAPersonImp saPersonImp = new SAPersonImp();
					saPersonImp.delete(p.getId());
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
