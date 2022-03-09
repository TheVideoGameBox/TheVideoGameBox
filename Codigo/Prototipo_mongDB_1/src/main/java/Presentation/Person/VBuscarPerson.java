package Presentation.Person;

import Logic.Person.TPerson;
import Presentation.JPanelConFondo;
import Presentation.VPrincipal;
import Presentation.Pet.VAddPet;
import Logic.Pet.TPet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VBuscarPerson extends JFrame{
	private TPerson p;
	private List<TPet> pets;
	public VBuscarPerson(TPerson p) {
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono_prueba.png")).getImage();
		this.setIconImage(iconFrame);
		setTitle("Buscar Persona");
		this.p = p;
//		pets = p.getPets();
		init_GUI();
		this.setLocationRelativeTo(null);
	}
	
	public void init_GUI() {
		this.getContentPane().removeAll();
		this.setPreferredSize(new Dimension(1150, 750));
		this.setLocation(400, 100);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanelConFondo mainPanel = new JPanelConFondo();
		mainPanel.setImagen(new ImageIcon(getClass().getClassLoader().getResource("fondo_pizarra.png")).getImage());
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		this.add(mainPanel);
		JPanel backButtonContainer = backButtonContainer();
		mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
		mainPanel.add(backButtonContainer);
		
		JPanel midPanel = new JPanel();
		midPanel.setOpaque(false);
		JPanel formContainer = new JPanel();
		formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
		formContainer.setPreferredSize(new Dimension(700, 500));
		formContainer.setAlignmentX(CENTER_ALIGNMENT);
		formContainer.setAlignmentY(CENTER_ALIGNMENT);
		formContainer.setOpaque(false);
		formContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		formContainer.setAutoscrolls(true);
		
		JLabel titleLabel = new JLabel("Datos de " + p.getNombre());
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setFont(new Font("Leelawadee", Font.BOLD, 40));
		titleLabel.setForeground(new Color(64, 147, 255));
		
		formContainer.add(titleLabel);
		formContainer.add(Box.createRigidArea(new Dimension(10, 30)));
		
		JLabel idLabel = new JLabel("ID: " + p.getId());
		idLabel.setFont(new Font("Leelawadee", Font.BOLD, 15));
		idLabel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel nifLabel = new JLabel("NIF: " + p.getNif());
		nifLabel.setFont(new Font("Leelawadee", Font.BOLD, 15));
		nifLabel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel nombreLabel = new JLabel("Nombre: " + p.getNombre());
		nombreLabel.setFont(new Font("Leelawadee", Font.BOLD, 15));
		nombreLabel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel apellidoLabel = new JLabel("Apellidos: " + p.getApellidos());
		apellidoLabel.setFont(new Font("Leelawadee", Font.BOLD, 15));
		apellidoLabel.setAlignmentX(CENTER_ALIGNMENT);
		JLabel activoLabel = new JLabel();
		activoLabel.setFont(new Font("Leelawadee", Font.BOLD, 20));
		activoLabel.setAlignmentX(CENTER_ALIGNMENT);
		if (p.isActivo()) {
			activoLabel.setText("Activo");
			activoLabel.setForeground(Color.green);
		} else {
			activoLabel.setText("Inactivo");
			activoLabel.setForeground(Color.red);
		}
		
		formContainer.add(idLabel);
		formContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		formContainer.add(nifLabel);
		formContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		formContainer.add(nombreLabel);
		formContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		formContainer.add(apellidoLabel);
		formContainer.add(Box.createRigidArea(new Dimension(0, 10)));
		formContainer.add(activoLabel);
		
		midPanel.add(formContainer);
		
		JLabel petTitle = new JLabel("Mascotas");
		petTitle.setFont(new Font("Leelawadee", Font.BOLD, 30));
		petTitle.setForeground(new Color(64, 147, 255));
		petTitle.setAlignmentX(CENTER_ALIGNMENT);
		
		formContainer.add(petTitle);
		
//		JScrollPane scrollFrame = new JScrollPane(formContainer);
//		scrollFrame.setOpaque(false);
//		scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//		mainPanel.add(scrollFrame);
		
//		for(int i=0; i < pets.size(); i++) {
//			//Mostraría el nombre de la mascota o mostraría toda la información si le clickas
//		}
		
		JButton addPet = new JButton("Añadir Mascota");
		addPet.setPreferredSize(new Dimension(180, 40));
		addPet.setMaximumSize(new Dimension(180, 40));
		addPet.setMinimumSize(new Dimension(180, 40));
		addPet.setBackground(new Color(64, 147, 255));
		addPet.setForeground(Color.white);
		addPet.setFont(new Font("Leelawadee", Font.BOLD, 20));
		addPet.setBorder(BorderFactory.createBevelBorder(0));
		addPet.setFocusPainted(false);
		addPet.setAlignmentX(CENTER_ALIGNMENT);
		addPet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				VAddPet alta = new VAddPet();
				dispose();
			}
			
		});
		
		formContainer.add(Box.createRigidArea(new Dimension(0, 20)));
		formContainer.add(addPet);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0,80)));
		mainPanel.add(midPanel);
		
		this.pack();
		this.setVisible(true);	
	}
	
	private JPanel backButtonContainer(){
		//BACK BUTTON
		JPanel backButtonContainer = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backButtonContainer.setMaximumSize(new Dimension(1000, 50));
		backButtonContainer.setOpaque(false);
		
		JButton backButton = new JButton("Volver al Principio");
		backButton.setFont(new Font("Leelawadee", Font.BOLD, 15));
		backButton.setBackground(new Color(64, 147, 255));
		backButton.setForeground(Color.white);
		backButton.setToolTipText("Volver a la pagina principal");
		backButton.setPreferredSize(new Dimension(180, 50));
		backButton.setFocusPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);
		backButton.setBorder(BorderFactory.createBevelBorder(0));
		backButtonContainer.add(backButton);
	
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				VPrincipal principal = new VPrincipal();
				dispose();
			}
			
		});
		
		return backButtonContainer;
	}
}
