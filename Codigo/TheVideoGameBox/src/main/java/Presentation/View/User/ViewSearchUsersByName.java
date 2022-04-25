package Presentation.View.User;

import static Presentation.View.Utils.Images.backGround;
import static Presentation.View.Utils.Images.logo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Logic.User.TUser;
import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Main.JPanelConFondo;
import Presentation.View.Main.JPanelRound;
import Presentation.View.Utils.Button;

public class ViewSearchUsersByName extends JFrame implements IView {
	
	private List<TUser> users = new ArrayList<TUser>();

	@Override
	public void update(Context context) {
		if (context.getEvent() == Event.RES_SEARCH_BY_NAME_OK) {
			this.users = (List<TUser>) context.getData();
			init_GUI();
		}
		
		refreshView();
	}
	
	private void init_GUI() {
		this.setTitle("Users List");
	    this.setPreferredSize(new Dimension(1150, 750));
	    this.setLocation(400, 100);

	    Image iconFrame = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(logo))).getImage();
	    this.setIconImage(iconFrame);

	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JPanelConFondo mainPanel = new JPanelConFondo();
	    mainPanel.setImagen(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(backGround))).getImage());
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    mainPanel.setPreferredSize(new Dimension(500, 500));
	    this.add(mainPanel);
	        
	    // CONTENT CONTAINER

	    JPanel contentContainer = new JPanel();
	    contentContainer.setLayout(new BoxLayout(contentContainer, BoxLayout.Y_AXIS));
	    contentContainer.setAlignmentX(CENTER_ALIGNMENT);
	    contentContainer.setOpaque(false);

        JScrollPane scrollFrame = new JScrollPane(contentContainer);
        contentContainer.setAutoscrolls(true);
        scrollFrame.getVerticalScrollBar().setUnitIncrement(25);
        scrollFrame.setOpaque(false);
        scrollFrame.getViewport().setOpaque(false);

        mainPanel.add(scrollFrame);

        // HEADER
        JPanel headerContainer = new JPanel();
        headerContainer.setMaximumSize(new Dimension(1200, 100));
        headerContainer.setLayout(new BoxLayout(headerContainer, BoxLayout.X_AXIS));
        headerContainer.setOpaque(false);
        
        // BACK BUTTON
        Button backButton = new Button(null, "back_icon.png", Color.white, Color.orange);
        backButton.buttonIcon();
        backButton.setToolTipText("Go back");
        backButton.setBounds(0, 11, 119, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().back();
                setVisible(false);
            }
        });
        
        headerContainer.add(backButton);
        
        // TITLE
        JLabel title = new JLabel("Users Results");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setForeground(Color.white);
        title.setFont(new Font("sans-serif", 1, 20));
        headerContainer.add(title);
        headerContainer.add(Box.createRigidArea(new Dimension(60, 0)));
       
		// ICONO DE MENU
		Button icon = new Button(null, "logo_small_blanco.png", new Dimension(500, 80));
		icon.buttonIcon();
		icon.setToolTipText("Back to main window");
		icon.setAlignmentX(CENTER_ALIGNMENT);
		headerContainer.add(icon);
		headerContainer.add(Box.createRigidArea(new Dimension(270, 0)));
		
		icon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().action(new Context(Event.VIEW, null));
				setVisible(false);
			}
		});

		// CONSTRUIR VISTA
		contentContainer.add(headerContainer);
		contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

        // HELP
        JPanel helpPanel = new JPanel();
        JLabel help = new JLabel("Push the TVGB logo to go back the main window");
        help.setForeground(Color.white);
        help.setAlignmentX(CENTER_ALIGNMENT);
        help.setFont(new Font("Leelawadee", Font.BOLD, 13));
        helpPanel.setOpaque(false);
        helpPanel.setMaximumSize(new Dimension(1000, 40));
        helpPanel.add(help);

        contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        contentContainer.add(helpPanel);
        contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));

        for(TUser user : users) {
        	try {
        		contentContainer.add(userPanel(user));
        	}catch (IOException e1) {
        		e1.printStackTrace();
        	}
        	contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        this.pack();
	}
	
	private JPanelRound userPanel(TUser user) throws IOException{
		
		JPanelRound panel = new JPanelRound(new Color(26, 59, 160), new Color(64, 147, 255), new Color(26, 59, 160));
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(10, 10, 10, 20));
		panel.setMaximumSize(new Dimension(1000, 135));
		panel.setPreferredSize(new Dimension(1000, 135));
		panel.setMinimumSize(new Dimension(1000, 135));
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
		namePanel.setOpaque(false);
		namePanel.setMaximumSize(new Dimension(700, 135));
		namePanel.setPreferredSize(new Dimension(700, 135));
		namePanel.setMinimumSize(new Dimension(700, 135));

		// NAME
		JLabel name = new JLabel(user.getUsername());
		name.setForeground(Color.white);
		name.setFont(new Font("Leelawadee", Font.BOLD, 20));
		
		//CONSTRUIR NAMEPANEL
		namePanel.add(Box.createRigidArea(new Dimension(55, 0)));
		namePanel.add(name);
		
		//BUTTON PANEL
		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.setOpaque(false);
		Button viewInfo = new Button("View Users", "info_icon.png", Color.white, new Color(50, 170, 0), new Dimension(200, 45), Color.orange);
		viewInfo.buttonIcon();
		viewInfo.setBorderPainted(false);
		viewInfo.setContentAreaFilled(false);
		viewInfo.setToolTipText("Search a User by Name");
		viewInfo.addActionListener(new ActionListener() {
		//Falta evento de ListUsers
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.getInstance().action(new Context(Event.SEARCH_BY_NAME, user));
				setVisible(false);
			}
		});
		buttonPanel.add(viewInfo, BorderLayout.WEST);

		Button viewAttributes = new Button("View attributes", "info_icon.png", Color.white, new Color(50, 170, 0), new Dimension(200, 45), Color.orange);
		viewAttributes.buttonIcon();
		viewAttributes.setBorderPainted(false);
		viewAttributes.setContentAreaFilled(false);
		viewAttributes.setToolTipText("View attributes of the user");
//		viewAttributes.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				//FaltaEvento ShowUser
//				ApplicationController.getInstance().action(new Context(Event.SHOW, user));
//				setVisible(false);
//			}
//		});
		buttonPanel.add(viewAttributes, BorderLayout.EAST);
		
		//CONSTRUIR PANEL
		panel.add(namePanel, BorderLayout.WEST);
		panel.add(buttonPanel, BorderLayout.EAST);
		
		return panel;
	}
	
	
    private void refreshView(){
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
