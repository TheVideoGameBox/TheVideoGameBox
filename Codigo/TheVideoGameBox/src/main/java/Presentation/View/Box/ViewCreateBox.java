package Presentation.View.Box;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Logic.Box.SABoxImp;
import Logic.Box.TBox;
import Presentation.Controller.Context;
import Presentation.View.IView;
import Presentation.View.Main.JPanelConFondo;
import Presentation.View.Main.ViewPrincipal;

public class ViewCreateBox extends JFrame implements IView{
	//Falta que hacer con la privacidad; donde controlarla.
	private ArrayList<JTextField> textFields;
	private boolean privacy;
	
	public ViewCreateBox() {
		super();
		textFields = new ArrayList<JTextField>();
		initGUI();
	}
	
	public void initGUI() {
		this.getContentPane().removeAll();
		this.setPreferredSize(new Dimension(775, 550));
		//this.setLocation(400, 100);

		JPanelConFondo mainpanel = new JPanelConFondo();
		mainpanel.setLayout(new BoxLayout(mainpanel,BoxLayout.Y_AXIS));
		//mainpanel.setImagen(new ImageIcon(getClass().getClassLoader().getResource("fondo_triangular.png")).getImage());
		Image iconFrame = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("caja_definitiva.png"))).getImage();
		this.setIconImage(iconFrame);
		mainpanel.setBackground(new Color(60, 77, 96));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(mainpanel);
		
		JPanel backButtonContainer = backButtonContainer();
		mainpanel.add(Box.createRigidArea(new Dimension(0,20)));
		mainpanel.add(backButtonContainer);
		ArrayList<String> names = new ArrayList<String>();
		names.add("Nombre");
		names.add("Description");
		names.add("Category");
		
		//creando la forma
		JPanel midPanel = new JPanel();
		midPanel.setOpaque(false);
		JPanel formContainer = new JPanel();
		formContainer.setLayout(new BoxLayout(formContainer, BoxLayout.Y_AXIS));
		formContainer.setPreferredSize(new Dimension(400, 700));
		formContainer.setMinimumSize(new Dimension(400, 0));
		formContainer.setAlignmentX(CENTER_ALIGNMENT);
		formContainer.setAlignmentY(TOP_ALIGNMENT);
		formContainer.setOpaque(false);
		formContainer.setBorder(new EmptyBorder(0, 20, 20, 20));
		formContainer.setAutoscrolls(true);
		
		//TITULO
		JLabel titleLabel = new JLabel("Create Box");
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setFont(new Font("Leelawadee", Font.BOLD, 40));
		titleLabel.setForeground(new Color(170, 180, 180));
		
		//BOTON SUBMIT
		JButton submitButton = new JButton("Submit");
		submitButton.setMaximumSize(new Dimension(500, 30));
		submitButton.setAlignmentX(CENTER_ALIGNMENT);
		//submitButton.setBackground(new Color(64, 147, 255));
		//submitButton.setForeground(Color.white);
		submitButton.setContentAreaFilled(false);
		submitButton.setForeground(new Color(170, 170, 170));
		submitButton.setFont(new Font("Leelawadee", Font.BOLD, 15));
		submitButton.setFocusPainted(false);
		submitButton.setBorderPainted(false);
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textFields.get(0).getText().length() > 2 && textFields.get(1).getText().length() > 2 && textFields.get(2).getText().length() > 2) {
					//TBox b = new TBox(textFields.get(0).getText(), textFields.get(1).getText(), privacy ,textFields.get(2).getText());

					SABoxImp saBoxImp = new SABoxImp();
					//saBoxImp.createBox(b);
					
					ViewPrincipal principal = new ViewPrincipal();
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
		
		midPanel.add(formContainer);
		
		mainpanel.add(Box.createRigidArea(new Dimension(0,80)));
		mainpanel.add(midPanel);
		
		this.pack();
		this.setLocationRelativeTo(null);
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
		label.setForeground(new Color(170, 180, 180));

		JTextField input = new JTextField(6);
		input.setBackground(new Color(25, 29, 35));
		input.setForeground(Color.white);
		input.setFont(new Font("Leelawadee", Font.BOLD, 13));
		input.setMaximumSize(new Dimension(400, 30));
		input.setPreferredSize(new Dimension(400, 30));
		input.setBorder(null);
		input.setHorizontalAlignment(0);
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
		//backButton.setBackground(new Color(64, 147, 255));
		//backButton.setForeground(Color.white);
		backButton.setContentAreaFilled(false);
		backButton.setForeground(new Color(170, 170, 170));
		backButton.setToolTipText("Volver a la pagina principal");
		backButton.setPreferredSize(new Dimension(180, 50));
		backButton.setMinimumSize(new Dimension(180, 50));
		backButton.setFocusPainted(false);
		backButton.setAlignmentX(LEFT_ALIGNMENT);
		backButton.setBorder(null);
		//backButton.setBorder(BorderFactory.createBevelBorder(0));
		backButtonContainer.add(backButton);
	
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ViewPrincipal principal = new ViewPrincipal();
				dispose();
			}
			
		});
		
		return backButtonContainer;
	}

	@Override
	public void update(Context context) {
		// TODO Auto-generated method stub
		
	}

}
