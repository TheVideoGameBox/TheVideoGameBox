package Presentation.View.Box;

import Logic.Box.Category;
import Logic.Box.Privacy;
import Logic.Box.TBox;
import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Main.JPanelConFondo;
import Presentation.View.Main.ViewPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ViewCreateBox extends JFrame implements IView{

	private JPanelConFondo mainPanel = new JPanelConFondo();
	private JPanel backButtonContainer;
	private JTextField textFieldName;
	private JTextField textFieldDescription;
	private JCheckBox checkBoxShooter;
	private JCheckBox checkBoxStrategy;
	private JCheckBox checkBoxIndie;
	private JCheckBox checkBoxRPG;
	private JCheckBox checkBoxRacing;
	private JCheckBox checkBoxSandbox;
	private JCheckBox checkBoxHorror;
	private JCheckBox checkBoxSports;
	private JCheckBox checkBoxSurvival;
	private JPanel panelCategories;
	private JPanel midPanel;
	private JComboBox comboBoxPrivacy;

	/**
	 * Create the frame.
	 */
	public ViewCreateBox() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().removeAll();
		setPreferredSize(new Dimension(900, 550));
		setLocation(400, 100);
		setBounds(100, 100, 887, 536);
		mainPanel = new JPanelConFondo();
		mainPanel.setBorder(null);
		mainPanel.setImagen(new ImageIcon(getClass().getClassLoader().getResource("fondo_triangular.png")).getImage());
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		midPanel = new JPanel();
		midPanel.setBounds(203, 23, 556, 446);
		midPanel.setOpaque(false);
		mainPanel.add(midPanel);
		midPanel.setLayout(null);
		
	
		
		JLabel titleLabel = new JLabel("Create Box");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setAlignmentX(CENTER_ALIGNMENT);
		titleLabel.setFont(new Font("Leelawadee", Font.BOLD, 40));
		titleLabel.setBounds(158, 22, 225, 59);
		titleLabel.setForeground(SystemColor.controlHighlight);
		midPanel.add(titleLabel);
		
		textFieldName = new JTextField();
		textFieldName.setToolTipText("Type new Box name");
		textFieldName.setFont(new Font("Leelawadee", Font.PLAIN, 13));
		textFieldName.setBounds(81, 114, 401, 30);
		midPanel.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel labelName = new JLabel("Name:");
		labelName.setToolTipText("");
		labelName.setForeground(Color.WHITE);
		labelName.setFont(new Font("Leelawadee", Font.BOLD, 15));
		labelName.setAlignmentX(CENTER_ALIGNMENT);
		labelName.setBounds(257, 97, 66, 14);
		midPanel.add(labelName);
		
		JLabel labelDescription = new JLabel("Description:");
		labelDescription.setToolTipText("");
		labelDescription.setForeground(Color.WHITE);
		labelDescription.setFont(new Font("Leelawadee", Font.BOLD, 15));
		labelDescription.setAlignmentX(0.5f);
		labelDescription.setBounds(238, 162, 97, 14);
		midPanel.add(labelDescription);
		
		textFieldDescription = new JTextField();
		textFieldDescription.setToolTipText("Type new Box description");
		textFieldDescription.setFont(new Font("Leelawadee", Font.PLAIN, 13));
		textFieldDescription.setColumns(10);
		textFieldDescription.setBounds(81, 180, 401, 30);
		midPanel.add(textFieldDescription);
		
		JLabel labelCategories = new JLabel("Categories:");
		labelCategories.setToolTipText("Select categories");
		labelCategories.setForeground(Color.WHITE);
		labelCategories.setFont(new Font("Leelawadee", Font.BOLD, 15));
		labelCategories.setAlignmentX(0.5f);
		labelCategories.setBounds(36, 277, 88, 19);
		midPanel.add(labelCategories);
		
		
		
		comboBoxPrivacy = new JComboBox(Privacy.values());
		comboBoxPrivacy.setBackground(UIManager.getColor("Button.light"));
		comboBoxPrivacy.setFont(new Font("Leelawadee", Font.PLAIN, 14));
		comboBoxPrivacy.setBounds(395, 276, 87, 22);
		midPanel.add(comboBoxPrivacy);
		
		JButton btnCreate = new JButton("CREATE");
		btnCreate.setForeground(Color.WHITE);
		btnCreate.setFont(new Font("Leelawadee", Font.BOLD, 15));
		btnCreate.setBounds(232, 378, 127, 39);
		btnCreate.setBackground(new Color(64, 147, 255));
		btnCreate.setFocusable(false);
		btnCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				createBox();
			}
		});
		midPanel.add(btnCreate);
		
		addPanelCheckboxes();
		
		
		backButtonContainer = new JPanel();
		backButtonContainer.setLayout(null);
		backButtonContainer.setOpaque(false);
		backButtonContainer.setBounds(10, 11, 196, 67);
		addBackButton();
		mainPanel.add(backButtonContainer);
		
		
		
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	protected void createBox() {
		//Capturar datos
		String name = textFieldName.getText();
		String description = textFieldDescription.getText();
		List<Category> categories = getCategories();
		Privacy privacy = getPrivacy();
		
		TBox box = new TBox(name, description, privacy, categories);
		
		ApplicationController.getInstance().action(new Context(Event.CREATE_BOX, box));
		
		ViewPrincipal principal = new ViewPrincipal();
		dispose();
		
	}

	private Privacy getPrivacy() {
		String privacy=comboBoxPrivacy.getSelectedItem().toString();
		
		if(privacy.equals(Privacy.PRIVATE.toString()))
			return Privacy.PRIVATE;
		else
			return Privacy.PUBLIC;
	}

	private List<Category> getCategories() {
		List<Category> categories = new ArrayList<>();
		
		if(checkBoxShooter.isSelected()) {
			categories.add(Category.SHOOTER);
		}
		if(checkBoxStrategy.isSelected()) {
			categories.add(Category.STRATEGY);
		}
		if(checkBoxIndie.isSelected()) {
			categories.add(Category.INDIE);
		}
		if(checkBoxRPG.isSelected()) {
			categories.add(Category.RPG);
		}
		if(checkBoxRacing.isSelected()) {
			categories.add(Category.RACING);
		}
		if(checkBoxSandbox.isSelected()) {
			categories.add(Category.SANDBOX);
		}
		if(checkBoxHorror.isSelected()) {
			categories.add(Category.HORROR);
		}
		if(checkBoxSports.isSelected()) {
			categories.add(Category.SPORTS);
		}
		if(checkBoxSurvival.isSelected()) {
			categories.add(Category.SURVIVAL);
		}
		return categories;
	}

	private void addPanelCheckboxes() {
		panelCategories = new JPanel();
		panelCategories.setBounds(120, 221, 215, 143);
		panelCategories.setOpaque(false);
		panelCategories.setLayout(null);
		midPanel.add(panelCategories);
		
		checkBoxShooter = new JCheckBox("Shooter");
		checkBoxShooter.setOpaque(false);
		checkBoxShooter.setForeground(Color.WHITE);
		checkBoxShooter.setFont(new Font("Leelawadee", Font.BOLD, 13));
		checkBoxShooter.setFocusable(false);
		checkBoxShooter.setBounds(6, 7, 83, 23);
		panelCategories.add(checkBoxShooter);
		
		checkBoxStrategy = new JCheckBox("Estrategia");
		checkBoxStrategy.setOpaque(false);
		checkBoxStrategy.setForeground(Color.WHITE);
		checkBoxStrategy.setFont(new Font("Leelawadee", Font.BOLD, 13));
		checkBoxStrategy.setFocusable(false);
		checkBoxStrategy.setBounds(6, 33, 91, 23);
		panelCategories.add(checkBoxStrategy);
		
		checkBoxIndie = new JCheckBox("Indie");
		checkBoxIndie.setOpaque(false);
		checkBoxIndie.setForeground(Color.WHITE);
		checkBoxIndie.setFont(new Font("Leelawadee", Font.BOLD, 13));
		checkBoxIndie.setFocusable(false);
		checkBoxIndie.setBounds(6, 59, 83, 23);
		panelCategories.add(checkBoxIndie);
		
		checkBoxRPG = new JCheckBox("RPG");
		checkBoxRPG.setOpaque(false);
		checkBoxRPG.setForeground(Color.WHITE);
		checkBoxRPG.setFont(new Font("Leelawadee", Font.BOLD, 13));
		checkBoxRPG.setFocusable(false);
		checkBoxRPG.setBounds(6, 85, 83, 23);
		panelCategories.add(checkBoxRPG);
		
		checkBoxRacing = new JCheckBox("Racing");
		checkBoxRacing.setOpaque(false);
		checkBoxRacing.setForeground(Color.WHITE);
		checkBoxRacing.setFont(new Font("Leelawadee", Font.BOLD, 13));
		checkBoxRacing.setFocusable(false);
		checkBoxRacing.setBounds(6, 111, 83, 23);
		panelCategories.add(checkBoxRacing);
		
		checkBoxSandbox = new JCheckBox("Sandbox");
		checkBoxSandbox.setHorizontalAlignment(SwingConstants.CENTER);
		checkBoxSandbox.setOpaque(false);
		checkBoxSandbox.setForeground(Color.WHITE);
		checkBoxSandbox.setFont(new Font("Leelawadee", Font.BOLD, 13));
		checkBoxSandbox.setFocusable(false);
		checkBoxSandbox.setBounds(99, 20, 83, 23);
		panelCategories.add(checkBoxSandbox);
		
		checkBoxHorror = new JCheckBox("Terror");
		checkBoxHorror.setOpaque(false);
		checkBoxHorror.setForeground(Color.WHITE);
		checkBoxHorror.setFont(new Font("Leelawadee", Font.BOLD, 13));
		checkBoxHorror.setFocusable(false);
		checkBoxHorror.setBounds(101, 46, 81, 23);
		panelCategories.add(checkBoxHorror);
		
		checkBoxSports = new JCheckBox("Deporte");
		checkBoxSports.setOpaque(false);
		checkBoxSports.setForeground(Color.WHITE);
		checkBoxSports.setFont(new Font("Leelawadee", Font.BOLD, 13));
		checkBoxSports.setFocusable(false);
		checkBoxSports.setBounds(101, 72, 81, 23);
		panelCategories.add(checkBoxSports);
		
		checkBoxSurvival = new JCheckBox("Supervivencia");
		checkBoxSurvival.setOpaque(false);
		checkBoxSurvival.setForeground(Color.WHITE);
		checkBoxSurvival.setFont(new Font("Leelawadee", Font.BOLD, 13));
		checkBoxSurvival.setFocusable(false);
		checkBoxSurvival.setBounds(101, 96, 114, 23);
		panelCategories.add(checkBoxSurvival);
		
		JLabel labelPrivacy = new JLabel("Privacy:");
		labelPrivacy.setToolTipText("Select privacy");
		labelPrivacy.setForeground(Color.WHITE);
		labelPrivacy.setFont(new Font("Leelawadee", Font.BOLD, 15));
		labelPrivacy.setAlignmentX(0.5f);
		labelPrivacy.setBounds(334, 277, 66, 19);
		midPanel.add(labelPrivacy);
	}

	private void addBackButton() {
		JButton backButton = new JButton("Go back");
		backButton.setForeground(Color.WHITE);
		backButton.setFont(new Font("Leelawadee", Font.BOLD, 15));
		backButton.setBackground(new Color(64, 147, 255));
		backButton.setBounds(36, 11, 119, 45);
		backButton.setFocusable(false);
		backButtonContainer.add(backButton);
		
		backButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ViewPrincipal principal = new ViewPrincipal();
				dispose();
			}
			
		});
		
	}

	@Override
	public void update(Context context) {
		
		switch(context.getEvent()) {
		case Event.RES_CREATE_BOX_OK:
			JOptionPane.showMessageDialog(null, "Box creada con id: " + (String)context.getData());
			break;
		case Event.RES_CREATE_BOX_KO:
			JOptionPane.showMessageDialog(null, "No se pudo crear la Box");
		}
		
		ApplicationController.getInstance().action(new Context(Event.VIEW, null));
		dispose();
	}
}
