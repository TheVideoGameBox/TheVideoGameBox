package Presentation.View.Box;

import static Presentation.View.Utils.Images.backGround;
import static Presentation.View.Utils.Images.logo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import Logic.Box.Genres;
import Logic.Box.Privacy;
import Logic.Box.TBox;
import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Main.JPanelConFondo;
import Presentation.View.Utils.Button;
import Presentation.View.Utils.TextField;

public class ViewModifyBox extends JFrame implements IView {
	
	private static final long serialVersionUID = 1L;

	private TBox box;
	
	JPanelConFondo mainPanel;
	
	private JPanel auxPanel;
	private JComboBox comboBoxPrivacy;
	
	private JPanel panelCheckbox;
	private JCheckBox checkBoxShooter;
	private JCheckBox checkBoxStrategy;
	private JCheckBox checkBoxIndie;
	private JCheckBox checkBoxRPG;
	private JCheckBox checkBoxRacing;
	private JCheckBox checkBoxSandbox;
	private JCheckBox checkBoxHorror;
	private JCheckBox checkBoxSports;
	private JCheckBox checkBoxSurvival;

	private TextField nameBox;
	private TextField descriptionBox;
	
	private JPanel backButtonContainer;
	
	public ViewModifyBox(TBox box) {
		super();
		setTitle("Modify box");
		this.box = box;
		initGUI();
	}
	
	private void initGUI() {
        Image iconFrame = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(logo))).getImage();
        this.setIconImage(iconFrame);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().removeAll();
        this.setPreferredSize(new Dimension(900, 700));
        setLocation(400, 100);
        setBounds(100, 100, 887, 536);
        
        mainPanel = new JPanelConFondo();
        mainPanel.setBorder(null);
        mainPanel.setImagen(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(backGround))).getImage());
        getContentPane().add(mainPanel);
        mainPanel.setLayout(null);

        auxPanel = new JPanel();
        auxPanel.setBounds(203, 23, 556, 446);
	    auxPanel.setOpaque(false);
        mainPanel.add(auxPanel);
        auxPanel.setLayout(null);
        
        // TITLE LABEL
	    JLabel titleLabel = new JLabel("Modify Box");
	    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    titleLabel.setAlignmentX(CENTER_ALIGNMENT);
	    titleLabel.setFont(new Font("Leelawadee", Font.BOLD, 40));
	    titleLabel.setBounds(158, 22, 225, 59);
	    titleLabel.setForeground(SystemColor.controlHighlight);
	    auxPanel.add(titleLabel);
        
	    // Input para el name
	    TextField nameBox = textField();
	    nameBox.setToolTipText("Modify box name");
	    nameBox.setFont(new Font("Leelawadee", Font.PLAIN, 13));
	    nameBox.setBounds(81, 114, 401, 30);
	    auxPanel.add(nameBox);
	    nameBox.setColumns(10);

	    // BOX NAME LABEL
	    JLabel labelName = new JLabel("Name:");
	    labelName.setToolTipText("");
	    labelName.setForeground(Color.WHITE);
	    labelName.setFont(new Font("Leelawadee", Font.BOLD, 15));
	    labelName.setAlignmentX(CENTER_ALIGNMENT);
	    labelName.setBounds(257, 97, 66, 14);
	    auxPanel.add(labelName);
        
	    // DESCRIPTION LABEL
	    JLabel labelDescription = new JLabel("Description:");
	    labelDescription.setToolTipText("");
	    labelDescription.setForeground(Color.WHITE);
	    labelDescription.setFont(new Font("Leelawadee", Font.BOLD, 15));
	    labelDescription.setAlignmentX(0.5f);
	    labelDescription.setBounds(238, 162, 97, 14);
	    auxPanel.add(labelDescription);
	    
	 	// Input para el description
	    TextField descriptionBox = textField();
	    descriptionBox.setToolTipText("Type new description");
	    descriptionBox.setFont(new Font("Leelawadee", Font.PLAIN, 13));
	    descriptionBox.setColumns(10);
	    descriptionBox.setBounds(81, 180, 401, 30);
	    auxPanel.add(descriptionBox);
	    
	    // GENRES LABEL
	    JLabel labelGenres = new JLabel("Genres:");
	    labelGenres.setToolTipText("Select Genres");
	    labelGenres.setForeground(Color.WHITE);
	    labelGenres.setFont(new Font("Leelawadee", Font.BOLD, 15));
	    labelGenres.setAlignmentX(0.5f);
	    labelGenres.setBounds(62, 277, 60, 19);
	    auxPanel.add(labelGenres);
	    
	    // ComboBox para Privacy
	    JComboBox comboBoxPrivacy = new JComboBox(Privacy.values());
	    comboBoxPrivacy.setBackground(UIManager.getColor("Button.light"));
	    comboBoxPrivacy.setFont(new Font("Leelawadee", Font.PLAIN, 14));
	    comboBoxPrivacy.setBounds(395, 276, 87, 22);
	    auxPanel.add(comboBoxPrivacy);
	    
	    // Botón para confirmar los cambios
	  	Button btnModify = new Button("MODIFY", new Color(64, 147, 255), new Dimension(120, 30));
	  	btnModify.button();
	  	btnModify.setBounds(232, 378, 127, 39);
	  	btnModify.addActionListener(new ActionListener() {
	  		@Override
	  		public void actionPerformed(ActionEvent e) {
	  			modifyBox();
	  		}
	  	});   
	  	auxPanel.add(btnModify);
	    
	  	addPanelCheckboxes();
	  	
	    backButtonContainer = new JPanel();
        backButtonContainer.setLayout(null);
        backButtonContainer.setOpaque(false);
        backButtonContainer.setBounds(10, 11, 196, 67);
        addBackButton();
        mainPanel.add(backButtonContainer);
	    
        this.pack();
        refreshView();
	}
	 
	// TextField para obtener nombre y descripcion
	private TextField textField() {
		TextField aux = new TextField(new Dimension(500, 40));
	    aux.textField();
	    aux.setAlignmentX(CENTER_ALIGNMENT);
	    
	    return aux;
	}
	    

	// Método update
	@Override
	public void update(Context context) {
		switch (context.getEvent()) {
        	case Event.RES_MODIFY_BOX_OK:
        		JOptionPane.showMessageDialog(this, "Box data modified!", "Modify Box", JOptionPane.INFORMATION_MESSAGE);
        		ApplicationController.getInstance().action(new Context(Event.VIEW, null));
        		setVisible(false);
        		break;
        	case Event.RES_MODIFY_BOX_KO:
        		JOptionPane.showMessageDialog(null, "Failed to modify the Box", "Modify Box", JOptionPane.ERROR_MESSAGE);
        		break;
		}
	}
	
	// Método para confirmar
	protected void modifyBox() {
		//Capturar datos
		String name = nameBox.getText();
		String description = descriptionBox.getText();
		List<Genres> categories = getCategories();
		Privacy privacy = getPrivacy();

		TBox box = new TBox(name, description, privacy, categories);

		ApplicationController.getInstance().action(new Context(Event.MODIFY_BOX, box));	
	}
	
	// GetPrivacy del ComboBox
	private Privacy getPrivacy() {
		String privacy = comboBoxPrivacy.getSelectedItem().toString();
        
        if (privacy.equals(Privacy.PRIVATE.toString())) {
        	return Privacy.PRIVATE;
        }
        else {
        	return Privacy.PUBLIC;
        }
    }
	
	// Check Genres
	private List<Genres> getCategories() {
		List<Genres>genres = new ArrayList<>();
		
		if (checkBoxShooter.isSelected()) {
			genres.add(Genres.SHOOTER);
		}
		if (checkBoxStrategy.isSelected()) {
			genres.add(Genres.STRATEGY);
		}
		if (checkBoxIndie.isSelected()) {
			genres.add(Genres.INDIE);
		}
		if (checkBoxRPG.isSelected()) {
			genres.add(Genres.RPG);
		}
		if (checkBoxRacing.isSelected()) {
			genres.add(Genres.RACING);
		}
		if (checkBoxSandbox.isSelected()) {
			genres.add(Genres.SANDBOX);
		}
		if (checkBoxHorror.isSelected()) {
			genres.add(Genres.HORROR);
		}
		if (checkBoxSports.isSelected()) {
			genres.add(Genres.SPORTS);
		}
		if (checkBoxSurvival.isSelected()) {
			genres.add(Genres.SURVIVAL);
		}
		
		return genres;
	}
	
	private void addPanelCheckboxes() {
		// Checkbox Panel
	  	panelCheckbox = new JPanel();
	  	panelCheckbox.setBounds(120, 221, 215, 143);
	  	panelCheckbox.setOpaque(false);
	  	panelCheckbox.setLayout(null);
	    auxPanel.add(panelCheckbox);
	  	
	    // Checkbox Shooter
	    checkBoxShooter = new JCheckBox("Shooter");
	    checkBoxShooter.setOpaque(false);
	    checkBoxShooter.setForeground(Color.WHITE);
	    checkBoxShooter.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxShooter.setFocusable(false);
	    checkBoxShooter.setBounds(6, 7, 83, 23);
	    panelCheckbox.add(checkBoxShooter);
	        
	    // Checkbox Strategy
	    checkBoxStrategy = new JCheckBox("Strategy");
	    checkBoxStrategy.setOpaque(false);
	    checkBoxStrategy.setForeground(Color.WHITE);
	    checkBoxStrategy.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxStrategy.setFocusable(false);
	    checkBoxStrategy.setBounds(6, 33, 91, 23);
	    panelCheckbox.add(checkBoxStrategy);

	    // Checkbox Indie
	    checkBoxIndie = new JCheckBox("Indie");
	    checkBoxIndie.setOpaque(false);
	    checkBoxIndie.setForeground(Color.WHITE);
	    checkBoxIndie.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxIndie.setFocusable(false);
	    checkBoxIndie.setBounds(6, 59, 83, 23);
	    panelCheckbox.add(checkBoxIndie);

	    // Checkbox RPG
	    checkBoxRPG = new JCheckBox("RPG");
	    checkBoxRPG.setOpaque(false);
	    checkBoxRPG.setForeground(Color.WHITE);
	    checkBoxRPG.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxRPG.setFocusable(false);
	    checkBoxRPG.setBounds(6, 85, 83, 23);
	    panelCheckbox.add(checkBoxRPG);

	    // Checkbox Racing
	    checkBoxRacing = new JCheckBox("Racing");
	    checkBoxRacing.setOpaque(false);
	    checkBoxRacing.setForeground(Color.WHITE);
	    checkBoxRacing.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxRacing.setFocusable(false);
	    checkBoxRacing.setBounds(6, 111, 83, 23);
	    panelCheckbox.add(checkBoxRacing);

	    // Checkbox Sandbox
	    checkBoxSandbox = new JCheckBox("Sandbox");
	    checkBoxSandbox.setHorizontalAlignment(SwingConstants.CENTER);
	    checkBoxSandbox.setOpaque(false);
	    checkBoxSandbox.setForeground(Color.WHITE);
	    checkBoxSandbox.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxSandbox.setFocusable(false);
	    checkBoxSandbox.setBounds(99, 20, 83, 23);
	    panelCheckbox.add(checkBoxSandbox);

	    // Checkbox Horror
	    checkBoxHorror = new JCheckBox("Horror");
	    checkBoxHorror.setOpaque(false);
	    checkBoxHorror.setForeground(Color.WHITE);
	    checkBoxHorror.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxHorror.setFocusable(false);
	    checkBoxHorror.setBounds(101, 46, 81, 23);
	    panelCheckbox.add(checkBoxHorror);

	    // Checkbox Sports
	    checkBoxSports = new JCheckBox("Sports");
	    checkBoxSports.setOpaque(false);
	    checkBoxSports.setForeground(Color.WHITE);
	    checkBoxSports.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxSports.setFocusable(false);
	    checkBoxSports.setBounds(101, 72, 81, 23);
	    panelCheckbox.add(checkBoxSports);

	    // Checkbox Survival
	    checkBoxSurvival = new JCheckBox("Survival");
	    checkBoxSurvival.setOpaque(false);
	    checkBoxSurvival.setForeground(Color.WHITE);
	    checkBoxSurvival.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxSurvival.setFocusable(false);
	    checkBoxSurvival.setBounds(101, 96, 114, 23);
	    panelCheckbox.add(checkBoxSurvival);
	    
	    // PRIVACY LABEL
	    JLabel privacyLabel = new JLabel("Privacy:");
	    privacyLabel.setToolTipText("Select Privacy");
	    privacyLabel.setForeground(Color.WHITE);
	    privacyLabel.setFont(new Font("Leelawadee", Font.BOLD, 15));
	    privacyLabel.setAlignmentX(0.5f);
	    privacyLabel.setBounds(238, 162, 97, 14);
	    panelCheckbox.add(privacyLabel);
	}
	
	private void addBackButton() {
        Button backButton = new Button(null, "back_icon.png", Color.white, Color.orange);
        backButton.buttonIcon();
        backButton.setBounds(0, 11, 119, 50);
        backButton.setToolTipText("Back to the main window");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().back();
                setVisible(false);
            }
        });
        backButtonContainer.add(backButton);
    }
	
	// Refrescar vista
	private void refreshView() {
	    setLocationRelativeTo(null);
	    setVisible(true);
	}
	
}
