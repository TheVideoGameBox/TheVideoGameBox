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
	
	private TBox box;
	private List<Genres> genres;
	
	public ViewModifyBox(TBox box) {
		super();
		setTitle("Modify box");
		this.box = box;
		initGUI();
	}
	
	@Override
	public void update(Context context) {
		 switch (context.getEvent()) {
         case Event.RES_MODIFY_BOX_OK:
             JOptionPane.showMessageDialog(this, "Box data updated!", "Modify Box", JOptionPane.INFORMATION_MESSAGE);
             ApplicationController.getInstance().action(new Context(Event.VIEW, null));
             setVisible(false);
             break;
         case Event.RES_MODIFY_BOX_KO:
             JOptionPane.showMessageDialog(null, "Failed to update the Box", "Modify Box", JOptionPane.ERROR_MESSAGE);
             break;
     }
	}
	
	private void initGUI() {
		this.setPreferredSize(new Dimension(900, 700));
        Image iconFrame = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(logo))).getImage();
        this.setIconImage(iconFrame);
        
        JPanelConFondo mainPanel = new JPanelConFondo();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setImagen(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(backGround))).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(mainPanel);
        
        JPanel topPanel = createTopPanel();
        JPanel midPanel = createMidPanel();
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(midPanel, BorderLayout.SOUTH);

        this.pack();
	}
	
	private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(1150, 100));
        topPanel.setMaximumSize(new Dimension(1150, 100));
        topPanel.setMinimumSize(new Dimension(1150, 100));
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        // BACK BUTTON
        Button backButton = new Button(null, "back_icon.png", Color.white, Color.orange);
        backButton.buttonIcon();
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setBounds(0, 11, 119, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().back();
                setVisible(false);
            }
        });

        // ICONO DE MENU
        Button icon = new Button(null, "logo_small_blanco.png", new Dimension(500, 80));
        icon.buttonIcon();
        icon.setToolTipText("Back to main window");

        icon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().action(new Context(Event.VIEW, null));
                setVisible(false);
            }
        });
		topPanel.add(backButton);
        topPanel.add(Box.createRigidArea(new Dimension(50, 0)));
        topPanel.add(icon);

        return topPanel;
    }

	private JPanel createMidPanel() {
        JPanel midPanel = new JPanel();
        midPanel.setPreferredSize(new Dimension(1150, 550));
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
        midPanel.setOpaque(false);

        // Create account label
        JLabel createLabel = new JLabel("Modify box ");
        createLabel.setHorizontalAlignment((SwingConstants.LEFT));
        createLabel.setFont(new Font("Leelawadee", Font.BOLD, 30));
        createLabel.setForeground(Color.WHITE);
        createLabel.setAlignmentX(CENTER_ALIGNMENT);

        midPanel.add(createLabel);
        midPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        midPanel.add(modifyBoxPanel());

        return midPanel;
    }
	
	private JPanel modifyBoxPanel() {
	    JPanel auxPanel = new JPanel();
	    auxPanel.setLayout(new BoxLayout(auxPanel, BoxLayout.Y_AXIS));
	    auxPanel.setOpaque(false);
	        
	    JLabel titleLabel = new JLabel("Modify Box");
	    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    titleLabel.setAlignmentX(CENTER_ALIGNMENT);
	    titleLabel.setFont(new Font("Leelawadee", Font.BOLD, 40));
	    titleLabel.setBounds(158, 22, 225, 59);
	    titleLabel.setForeground(SystemColor.controlHighlight);
	    auxPanel.add(titleLabel);

	    // BOX NAME LABEL
	    JLabel labelName = new JLabel("Name:");
	    labelName.setToolTipText("");
	    labelName.setForeground(Color.WHITE);
	    labelName.setFont(new Font("Leelawadee", Font.BOLD, 15));
	    labelName.setAlignmentX(CENTER_ALIGNMENT);
	    labelName.setBounds(257, 97, 66, 14);
	    auxPanel.add(labelName);

	    // Input para el name
	    TextField nameBox = textField();
	    nameBox.setToolTipText("Type new name");
	    nameBox.setFont(new Font("Leelawadee", Font.PLAIN, 13));
	    nameBox.setColumns(10);
	    nameBox.setBounds(81, 180, 401, 30);
	    auxPanel.add(nameBox);
	    auxPanel.add(Box.createRigidArea(new Dimension(50, 15)));

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
	    auxPanel.add(Box.createRigidArea(new Dimension(50, 15)));

	    // GENRES LABEL
	    JLabel labelGenres = new JLabel("Genres:");
	    labelDescription.setToolTipText("Select Genres");
	    labelDescription.setForeground(Color.WHITE);
	    labelDescription.setFont(new Font("Leelawadee", Font.BOLD, 15));
	    labelDescription.setAlignmentX(0.5f);
	    labelDescription.setBounds(238, 162, 97, 14);
	    auxPanel.add(labelGenres);
	        
	    // ComboBox para Privacy
	    JComboBox comboBoxPrivacy = new JComboBox(Privacy.values());
	    comboBoxPrivacy.setBackground(UIManager.getColor("Button.light"));
	    comboBoxPrivacy.setFont(new Font("Leelawadee", Font.PLAIN, 14));
	    comboBoxPrivacy.setBounds(395, 276, 87, 22);
	    auxPanel.add(comboBoxPrivacy);
	       
	    // Panel para categorías de la box
	    JPanel panelCategories = new JPanel();
	    panelCategories.setBounds(120, 221, 215, 143);
	    panelCategories.setOpaque(false);
	    panelCategories.setLayout(null);
	    auxPanel.add(panelCategories);
	        
	    // Checkbox Shooter
	    JCheckBox checkBoxShooter = new JCheckBox("Shooter");
	    checkBoxShooter.setOpaque(false);
	    checkBoxShooter.setForeground(Color.WHITE);
	    checkBoxShooter.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxShooter.setFocusable(false);

	    checkBoxShooter.setBounds(6, 7, 83, 23);
	    panelCategories.add(checkBoxShooter);
	        
	    // Checkbox Strategy
	    JCheckBox checkBoxStrategy = new JCheckBox("Strategy");
	    checkBoxStrategy.setOpaque(false);
	    checkBoxStrategy.setForeground(Color.WHITE);
	    checkBoxStrategy.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxStrategy.setFocusable(false);
	    checkBoxStrategy.setBounds(6, 33, 91, 23);
	    panelCategories.add(checkBoxStrategy);

	    // Checkbox Indie
	    JCheckBox checkBoxIndie = new JCheckBox("Indie");
	    checkBoxIndie.setOpaque(false);
	    checkBoxIndie.setForeground(Color.WHITE);
	    checkBoxIndie.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxIndie.setFocusable(false);
	    checkBoxIndie.setBounds(6, 59, 83, 23);
	    panelCategories.add(checkBoxIndie);

	    // Checkbox RPG
	    JCheckBox checkBoxRPG = new JCheckBox("RPG");
	    checkBoxRPG.setOpaque(false);
	    checkBoxRPG.setForeground(Color.WHITE);
	    checkBoxRPG.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxRPG.setFocusable(false);
	    checkBoxRPG.setBounds(6, 85, 83, 23);
	    panelCategories.add(checkBoxRPG);

	    // Checkbox Racing
	    JCheckBox checkBoxRacing = new JCheckBox("Racing");
	    checkBoxRacing.setOpaque(false);
	    checkBoxRacing.setForeground(Color.WHITE);
	    checkBoxRacing.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxRacing.setFocusable(false);
	    checkBoxRacing.setBounds(6, 111, 83, 23);
	    panelCategories.add(checkBoxRacing);

	    // Checkbox Sandbox
	    JCheckBox checkBoxSandbox = new JCheckBox("Sandbox");
	    checkBoxSandbox.setHorizontalAlignment(SwingConstants.CENTER);
	    checkBoxSandbox.setOpaque(false);
	    checkBoxSandbox.setForeground(Color.WHITE);
	    checkBoxSandbox.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxSandbox.setFocusable(false);
	    checkBoxSandbox.setBounds(99, 20, 83, 23);
	    panelCategories.add(checkBoxSandbox);

	    // Checkbox Horror
	    JCheckBox checkBoxHorror = new JCheckBox("Horror");
	    checkBoxHorror.setOpaque(false);
	    checkBoxHorror.setForeground(Color.WHITE);
	    checkBoxHorror.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxHorror.setFocusable(false);
	    checkBoxHorror.setBounds(101, 46, 81, 23);
	    panelCategories.add(checkBoxHorror);

	    // Checkbox Sports
	    JCheckBox checkBoxSports = new JCheckBox("Sports");
	    checkBoxSports.setOpaque(false);
	    checkBoxSports.setForeground(Color.WHITE);
	    checkBoxSports.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxSports.setFocusable(false);
	    checkBoxSports.setBounds(101, 72, 81, 23);
	    panelCategories.add(checkBoxSports);

	    // Checkbox Survival
	    JCheckBox checkBoxSurvival = new JCheckBox("Survival");
	    checkBoxSurvival.setOpaque(false);
	    checkBoxSurvival.setForeground(Color.WHITE);
	    checkBoxSurvival.setFont(new Font("Leelawadee", Font.BOLD, 13));
	    checkBoxSurvival.setFocusable(false);
	    checkBoxSurvival.setBounds(101, 96, 114, 23);
	    panelCategories.add(checkBoxSurvival);

	    // PRIVACY LABEL
	    JLabel labelPrivacy = new JLabel("Privacy:");
	    labelPrivacy.setToolTipText("Select privacy");
	    labelPrivacy.setForeground(Color.WHITE);
	    labelPrivacy.setFont(new Font("Leelawadee", Font.BOLD, 15));
	    labelPrivacy.setAlignmentX(0.5f);
	    labelPrivacy.setBounds(334, 277, 66, 19);
	    auxPanel.add(labelPrivacy);	 
	        
	    // BACK BUTTON
	    JPanel backButtonContainer = new JPanel();
	    backButtonContainer.setLayout(null);
	    backButtonContainer.setOpaque(false);
	    backButtonContainer.setBounds(10, 11, 196, 67);
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
	    auxPanel.add(backButtonContainer);
	    backButtonContainer.add(backButton);
	         
	    // Check Genres
	    genres = new ArrayList<>();
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
	         
	    // Botón para confirmar
		Button btnModify = new Button("MODIFY", new Color(64, 147, 255), new Dimension(120, 30));
		btnModify.button();
		btnModify.setBounds(232, 378, 127, 39);
		btnModify.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
				//Capturar datos
		    	String name = nameBox.getText();
		    	String description = descriptionBox.getText();
		    	List<Genres> categories = genres;
		    	Privacy privacy = getPrivacy(comboBoxPrivacy);

		    	TBox box = new TBox(name, description, privacy, categories);

		    	ApplicationController.getInstance().action(new Context(Event.MODIFY_BOX, box));	            }
		});   
		auxPanel.add(btnModify);

	    this.pack();
	    refreshView();
	    
	    return auxPanel;
	}
	 
	private TextField textField() {
		TextField aux = new TextField(new Dimension(500, 40));
	    aux.textField();
	    aux.setAlignmentX(CENTER_ALIGNMENT);
	    
	    return aux;
	}
	    
	private void refreshView(){
	    setLocationRelativeTo(null);
	    setVisible(true);
	}
	
	private Privacy getPrivacy(JComboBox comboBoxPrivacy) {
        String privacy = comboBoxPrivacy.getSelectedItem().toString();
        if (privacy.equals(Privacy.PRIVATE.toString())) {
        	return Privacy.PRIVATE;
        }
        else {
        	return Privacy.PUBLIC;
        }
    }
}
