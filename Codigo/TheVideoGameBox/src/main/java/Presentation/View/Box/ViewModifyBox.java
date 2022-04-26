package Presentation.View.Box;

import static Presentation.View.Utils.Images.backGround;
import static Presentation.View.Utils.Images.logo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.*;

import org.bson.types.ObjectId;

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
	
	private TBox tbox;
	
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
	private JTextArea descriptionBox;
	
	private JPanel backButtonContainer;
	
	public ViewModifyBox(TBox tbox) {
		super();
		this.tbox = tbox;
		setTitle("Modify box");
		initGUI();
	}
	
	private void initGUI() {
        Image iconFrame = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(logo))).getImage();
        this.setIconImage(iconFrame);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().removeAll();
        this.setPreferredSize(new Dimension(900, 575));
        
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
	    nameBox = textField();
		nameBox.setText(tbox.getName());
	    nameBox.setToolTipText("Modify box name");
	    nameBox.setBounds(81, 114, 401, 30);
	    auxPanel.add(nameBox);
	    nameBox.setColumns(10);

	    // BOX NAME LABEL
	    JLabel labelName = label("Name:");
	    labelName.setBounds(257, 97, 66, 14);
	    auxPanel.add(labelName);
        
	    // DESCRIPTION LABEL
	    JLabel labelDescription = label("Description:");
	    labelDescription.setBounds(238, 162, 97, 14);
	    auxPanel.add(labelDescription);
	    
	 	// Input para el description
	    descriptionBox = new JTextArea();
		descriptionBox.setText(tbox.getDescription());
		descriptionBox.setLineWrap(true);
		descriptionBox.setColumns(100);
	    descriptionBox.setToolTipText("Type new description");
	    descriptionBox.setBounds(82, 180, 401, 40);
	    auxPanel.add(descriptionBox);
	    
	    // GENRES LABEL
	    JLabel labelGenres = label("Genres:");
	    labelGenres.setToolTipText("Select Genres");
	    labelGenres.setBounds(62, 245, 60, 19);
	    auxPanel.add(labelGenres);

		JLabel privacyLabel = new JLabel("Privacy:");
		privacyLabel.setToolTipText("Select Privacy");
		privacyLabel.setForeground(Color.WHITE);
		privacyLabel.setFont(new Font("Leelawadee", Font.BOLD, 15));
		privacyLabel.setAlignmentX(0.5f);
		privacyLabel.setBounds(330, 315, 87, 22);
		auxPanel.add(privacyLabel);
	    
	    // ComboBox para Privacy
	    comboBoxPrivacy = new JComboBox(Privacy.values());
		comboBoxPrivacy.setSelectedItem(tbox.getPrivacy());
	    comboBoxPrivacy.setBackground(UIManager.getColor("Button.light"));
	    comboBoxPrivacy.setFont(new Font("Leelawadee", Font.PLAIN, 14));
	    comboBoxPrivacy.setBounds(395, 315, 87, 22);
	    auxPanel.add(comboBoxPrivacy);
	    
	    // Botón para confirmar los cambios
	  	Button btnModify = new Button("MODIFY", new Color(64, 147, 255), new Dimension(120, 30));
	  	btnModify.button();
	  	btnModify.setBounds(232, 405, 127, 39);
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
		this.setLocationRelativeTo(null);
        refreshView();
	}
	 
	// TextField para obtener nombre y descripcion
	private TextField textField() {
		TextField aux = new TextField(new Dimension(500, 40));
	    aux.textField();
		aux.setFont(new Font("Leelawadee", Font.PLAIN, 13));
	    aux.setAlignmentX(CENTER_ALIGNMENT);
	    return aux;
	}

	private JLabel label(String name) {
		JLabel aux = new JLabel(name);
		aux.setForeground(Color.WHITE);
		aux.setFont(new Font("Leelawadee", Font.BOLD, 15));
		aux.setAlignmentX(0.5f);
		return aux;
	}

	// Método update
	@Override
	public void update(Context context) {
		switch (context.getEvent()) {
        	case Event.RES_MODIFY_BOX_OK:
        		JOptionPane.showMessageDialog(this, "Box modified!", "Modify Box", JOptionPane.INFORMATION_MESSAGE);
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
		ObjectId id = tbox.getId();
		String name = nameBox.getText();
		String description = descriptionBox.getText();
		List<Genres> categories = getCategories();
		Privacy privacy = getPrivacy();

		TBox box = new TBox(id, name, description, privacy, categories);

		ApplicationController.getInstance().action(new Context(Event.MODIFY_BOX, box));	
	}
	
	// GetPrivacy del ComboBox
	private Privacy getPrivacy() {
		String privacy = Objects.requireNonNull(comboBoxPrivacy.getSelectedItem()).toString();
        
        if (privacy.equals(Privacy.PRIVATE.toString())) {
        	return Privacy.PRIVATE;
        }
        else if(privacy.equals(Privacy.PUBLIC.toString())) {
        	return Privacy.PUBLIC;
        }
		else
			return Privacy.PRIVATE;
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
	  	panelCheckbox.setBounds(120, 260, 215, 143);
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
		checkBoxShooter.setSelected(tbox.getGenres().contains(Genres.SHOOTER));
	    panelCheckbox.add(checkBoxShooter);

	        
	    // Checkbox Strategy
	    checkBoxStrategy = new JCheckBox("Strategy");
	    checkBoxStrategy.setOpaque(false);
	    checkBoxStrategy.setForeground(Color.WHITE);
	    checkBoxStrategy.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxStrategy.setFocusable(false);
	    checkBoxStrategy.setBounds(6, 33, 91, 23);
		checkBoxStrategy.setSelected(tbox.getGenres().contains(Genres.STRATEGY));
	    panelCheckbox.add(checkBoxStrategy);

	    // Checkbox Indie
	    checkBoxIndie = new JCheckBox("Indie");
	    checkBoxIndie.setOpaque(false);
	    checkBoxIndie.setForeground(Color.WHITE);
	    checkBoxIndie.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxIndie.setFocusable(false);
	    checkBoxIndie.setBounds(6, 59, 83, 23);
		checkBoxIndie.setSelected(tbox.getGenres().contains(Genres.INDIE));
		panelCheckbox.add(checkBoxIndie);

	    // Checkbox RPG
	    checkBoxRPG = new JCheckBox("RPG");
	    checkBoxRPG.setOpaque(false);
	    checkBoxRPG.setForeground(Color.WHITE);
	    checkBoxRPG.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxRPG.setFocusable(false);
	    checkBoxRPG.setBounds(6, 85, 83, 23);
		checkBoxRPG.setSelected(tbox.getGenres().contains(Genres.RPG));
		panelCheckbox.add(checkBoxRPG);

	    // Checkbox Racing
	    checkBoxRacing = new JCheckBox("Racing");
	    checkBoxRacing.setOpaque(false);
	    checkBoxRacing.setForeground(Color.WHITE);
	    checkBoxRacing.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxRacing.setFocusable(false);
	    checkBoxRacing.setBounds(6, 111, 83, 23);
		checkBoxRacing.setSelected(tbox.getGenres().contains(Genres.RACING));
		panelCheckbox.add(checkBoxRacing);

	    // Checkbox Sandbox
	    checkBoxSandbox = new JCheckBox("Sandbox");
	    checkBoxSandbox.setHorizontalAlignment(SwingConstants.CENTER);
	    checkBoxSandbox.setOpaque(false);
	    checkBoxSandbox.setForeground(Color.WHITE);
	    checkBoxSandbox.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxSandbox.setFocusable(false);
	    checkBoxSandbox.setBounds(99, 20, 83, 23);
		checkBoxSandbox.setSelected(tbox.getGenres().contains(Genres.SANDBOX));
		panelCheckbox.add(checkBoxSandbox);

	    // Checkbox Horror
	    checkBoxHorror = new JCheckBox("Horror");
	    checkBoxHorror.setOpaque(false);
	    checkBoxHorror.setForeground(Color.WHITE);
	    checkBoxHorror.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxHorror.setFocusable(false);
	    checkBoxHorror.setBounds(101, 46, 81, 23);
		checkBoxHorror.setSelected(tbox.getGenres().contains(Genres.HORROR));

		panelCheckbox.add(checkBoxHorror);

	    // Checkbox Sports
	    checkBoxSports = new JCheckBox("Sports");
	    checkBoxSports.setOpaque(false);
	    checkBoxSports.setForeground(Color.WHITE);
	    checkBoxSports.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxSports.setFocusable(false);
	    checkBoxSports.setBounds(101, 72, 81, 23);
		checkBoxSports.setSelected(tbox.getGenres().contains(Genres.SPORTS));
		panelCheckbox.add(checkBoxSports);

	    // Checkbox Survival
	    checkBoxSurvival = new JCheckBox("Survival");
	    checkBoxSurvival.setOpaque(false);
	    checkBoxSurvival.setForeground(Color.WHITE);
	    checkBoxSurvival.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxSurvival.setFocusable(false);
	    checkBoxSurvival.setBounds(101, 96, 114, 23);
		checkBoxSurvival.setSelected(tbox.getGenres().contains(Genres.SURVIVAL));
		panelCheckbox.add(checkBoxSurvival);

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
