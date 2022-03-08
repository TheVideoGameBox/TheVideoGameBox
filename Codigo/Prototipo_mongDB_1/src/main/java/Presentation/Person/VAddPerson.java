package Presentation.Person;

import Logic.Person.SAPersonImp;
import Logic.Person.TPerson;
import Presentation.JPanelConFondo;
import Presentation.VPrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VAddPerson extends JFrame{

	private ArrayList<JTextField> textFields;
	
	public VAddPerson() {
		super("Nueva persona");
		Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("icono_prueba.png")).getImage();
		this.setIconImage(iconFrame);
		setTitle("Alta Persona");
		textFields = new ArrayList<JTextField>();
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
		ArrayList<String> names = new ArrayList<String>();
		names.add("NIF");
		names.add("Nombre");
		names.add("Apellidos");

		JPanel midPanel = new JPanel();
		midPanel.setOpaque(false);
		JPanel formContainer = new JPanel();
		formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
		formContainer.setPreferredSize(new Dimension(400, 500));
		formContainer.setAlignmentX(CENTER_ALIGNMENT);
		formContainer.setAlignmentY(CENTER_ALIGNMENT);
		formContainer.setOpaque(false);
		formContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
		formContainer.setAutoscrolls(true);
		
		JLabel titleLabel = new JLabel("Alta Persona");
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setFont(new Font("Leelawadee", Font.BOLD, 40));
		titleLabel.setForeground(new Color(64, 147, 255));
		
		JButton submitButton = new JButton("Submit");
		submitButton.setMaximumSize(new Dimension(1000, 30));
		submitButton.setAlignmentX(CENTER_ALIGNMENT);
		submitButton.setBackground(new Color(64, 147, 255));
		submitButton.setForeground(Color.white);
		submitButton.setFont(new Font("Leelawadee", Font.BOLD, 15));
		submitButton.setFocusPainted(false);
		submitButton.setBorderPainted(false);
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(textFields.get(0).getText().length() > 2 && textFields.get(1).getText().length() > 2 && textFields.get(2).getText().length() > 2) {
					TPerson p = new TPerson(textFields.get(0).getText(), textFields.get(1).getText(), textFields.get(2).getText());

					SAPersonImp saPersonImp = new SAPersonImp();
					saPersonImp.add(p);
					
					VPrincipal principal = new VPrincipal();
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Rellene todos los campos, por favor");
				}
			}
			
		});

		// CONSTRUIR COMPONENTE
		formContainer.add(titleLabel);
		formContainer.add(Box.createRigidArea(new Dimension(10, 20)));
		for (String name : names) {
			formContainer.add(createFormField(name));
			formContainer.add(Box.createRigidArea(new Dimension(10, 20)));
		}
		formContainer.add(submitButton);

		/*JScrollPane scrollFrame = new JScrollPane(formContainer);
		scrollFrame.setPreferredSize(new Dimension(400, 500));*/

		midPanel.add(formContainer);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0,80)));
		mainPanel.add(midPanel);
		
		this.pack();
		this.setVisible(true);		
	}
	
	private JPanel createFormField(String title) {
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		container.setOpaque(false);
		container.setAlignmentX(CENTER_ALIGNMENT);

		JLabel label = new JLabel(title);
		label.setFont(new Font("Leelawadee", Font.BOLD, 15));
		label.setAlignmentX(CENTER_ALIGNMENT);

		JTextField input = new JTextField(6);
		input.setMaximumSize(new Dimension(400, 30));
		input.setPreferredSize(new Dimension(400, 30));
		textFields.add(input);

		container.add(label);
		container.add(input);

		return container;
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
