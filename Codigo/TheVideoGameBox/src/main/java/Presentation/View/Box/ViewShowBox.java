package Presentation.View.Box;

import static Presentation.View.Utils.Images.backGround;
import static Presentation.View.Utils.Images.logo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import Logic.Box.TBox;
import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Main.JPanelConFondo;
import Presentation.View.Main.JPanelRound;
import Presentation.View.Utils.Button;

public class ViewShowBox extends JFrame implements IView {

	private TBox box;
	
	private static final long serialVersionUID = 1L;

	@Override
	public void update(Context context) {
        if (context.getEvent() == Event.RES_SHOW_BOX_OK) {
            this.box = (TBox) context.getData();
            initGUI();
        }

        refreshView();
		
	}
	
    private void initGUI() {
        this.setPreferredSize(new Dimension(600, 600));
        this.setLocation(400, 100);
        Image iconFrame = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(logo))).getImage();
        this.setIconImage(iconFrame);

        JPanelConFondo mainpanel = new JPanelConFondo();
        mainpanel.setLayout(new BorderLayout());
        mainpanel.setImagen(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(backGround))).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(mainpanel);

        JPanel topPanel = createTopPanel();
        JPanel midPanel = createMidPanel();

        mainpanel.add(topPanel, BorderLayout.NORTH);
        mainpanel.add(midPanel, BorderLayout.CENTER);

        this.pack();
    }
    
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(600, 100));
        topPanel.setMaximumSize(new Dimension(600, 100));
        topPanel.setMinimumSize(new Dimension(600, 100));
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        //BOTON DE BACK
        Button backButton = new Button(null, "back_icon.png", Color.white, Color.orange);
        backButton.buttonIcon();
        backButton.setBounds(0, 11, 119, 50);
        backButton.setToolTipText("Go back");
        backButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().back();
                setVisible(false);
            }
        });

        //ICONO DE LA APLICACION
        Button icon = new Button(null, "logo_small_blanco.png", new Dimension(400, 80), false);
        icon.buttonIcon();
        icon.setToolTipText("Back to main window");
        icon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().action(new Context(Event.VIEW, null));
                setVisible(false);
            }
        });

        topPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        topPanel.add(backButton);
        topPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        topPanel.add(icon);


        return topPanel;
    }
    
    private JPanel createMidPanel() {
        JPanel midPanel = new JPanel();
        midPanel.setPreferredSize(new Dimension(500, 400));
        midPanel.setMaximumSize(new Dimension(500, 400));
        midPanel.setMinimumSize(new Dimension(500, 400));
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
        midPanel.setAlignmentX(CENTER_ALIGNMENT);
        midPanel.setBorder(new EmptyBorder(0, 20, 10, 20));
        midPanel.setOpaque(false);


        // Nombre de la box.
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.setOpaque(false);
        namePanel.setBorder(new EmptyBorder(0, 10, 7, 10));
        namePanel.setAlignmentX(CENTER_ALIGNMENT);

        JTextArea nameTitle = new JTextArea(box.getName());
        nameTitle.setForeground(Color.white);
        nameTitle.setOpaque(false);
        nameTitle.setFont(new Font("sans-serif", 1, 28));
        nameTitle.setLineWrap(true);
        nameTitle.setWrapStyleWord(true);
        nameTitle.setAlignmentX(CENTER_ALIGNMENT);

        namePanel.add(nameTitle);
        
        midPanel.add(namePanel);
        
        // Privacidad de la box.
        JPanel privacyPanel = new JPanel();
        privacyPanel.setLayout(new BoxLayout(privacyPanel, BoxLayout.Y_AXIS));
        privacyPanel.setOpaque(false);
        privacyPanel.setBorder(new EmptyBorder(0, 10, 7, 10));
        privacyPanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel privacyTitle = new JLabel("Privacy");
        privacyTitle.setAlignmentX(LEFT_ALIGNMENT);
        privacyTitle.setForeground(Color.white);
        privacyTitle.setFont(new Font("sans-serif", 1, 25));
        JTextArea privacyText = new JTextArea("There isn't any privacy info.");
        privacyText.setAlignmentX(LEFT_ALIGNMENT);
        privacyText.setForeground(Color.white);
        privacyText.setOpaque(false);
        privacyText.setFont(new Font("sans-serif", 1, 14));
        privacyText.setLineWrap(true);
        privacyText.setWrapStyleWord(true);
        if (box.getPrivacy() != null) {
        	privacyText.setText(box.getPrivacy().toString());
        }

        privacyPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        privacyPanel.add(privacyTitle);
        privacyPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        privacyPanel.add(privacyText);

        midPanel.add(privacyPanel);


        // Generos de la box.
        JPanelRound genrePanel = new JPanelRound(new Color(12, 17, 21), new Color(74, 90, 151), new Color(12, 17, 21));
        genrePanel.setLayout(new BoxLayout(genrePanel, BoxLayout.Y_AXIS));
        genrePanel.setPreferredSize(new Dimension(250, 150));
        genrePanel.setMaximumSize(new Dimension(250, 150));
        genrePanel.setMinimumSize(new Dimension(250, 150));
        genrePanel.setAlignmentX(CENTER_ALIGNMENT);
        JLabel genreTitle = new JLabel("Genres");
        genreTitle.setAlignmentX(CENTER_ALIGNMENT);
        genreTitle.setForeground(Color.white);
        genreTitle.setFont(new Font("sans-serif", 1, 25));
        genrePanel.add(Box.createRigidArea(new Dimension(0, 15)));
        genrePanel.add(genreTitle);
        if (box.getGenres() == null) {
        	genrePanel.add(Box.createRigidArea(new Dimension(0, 5)));
        	genrePanel.add(genreLabel("There isn't any genre."));
        } else {
            for (int i = 0; box.getGenres() != null && !box.getGenres().isEmpty() && i < box.getGenres().size(); i++) {
            	genrePanel.add(Box.createRigidArea(new Dimension(0, 5)));
            	genrePanel.add(genreLabel(box.getGenres().get(i).toString()));
            }
        }

        midPanel.add(genrePanel);

        midPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Descripción de la box.
        JPanelRound descPanel = new JPanelRound(new Color(12, 17, 21), new Color(74, 90, 151), new Color(12, 17, 21));
        descPanel.setBorder(new EmptyBorder(0, 10, 7, 10));
        descPanel.setPreferredSize(new Dimension(600, 150));
        descPanel.setMaximumSize(new Dimension(500, 150));
        descPanel.setMinimumSize(new Dimension(500, 150));
        descPanel.setLayout(new BoxLayout(descPanel, BoxLayout.Y_AXIS));
        descPanel.setAlignmentX(CENTER_ALIGNMENT);
        JLabel descTitle = new JLabel("Description");
        descTitle.setAlignmentX(CENTER_ALIGNMENT);
        descTitle.setForeground(Color.white);
        descTitle.setFont(new Font("sans-serif", 1, 25));
        JTextArea descText = new JTextArea("There isn't any description.");
        descText.setAlignmentX(CENTER_ALIGNMENT);
        descText.setForeground(Color.white);
        descText.setOpaque(false);
        descText.setFont(new Font("sans-serif", 1, 14));
        descText.setLineWrap(true);
        descText.setWrapStyleWord(true);
        if (box.getDescription() != null) {
            descText.setText(box.getDescription());
        }

        JScrollPane scroll = new JScrollPane(descText);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 12));
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                scroll.getVerticalScrollBar().setValue(0);
            }
        });

        descPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        descPanel.add(descTitle);
        descPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        descPanel.add(scroll);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                scroll.getViewport().setViewPosition(new Point(0, 0));
            }
        });



        midPanel.add(descPanel);

        this.pack();
        return midPanel;
    }
    
    private JLabel genreLabel(String genre) {
        JLabel comp = new JLabel(genre);
        comp.setAlignmentX(CENTER_ALIGNMENT);
        comp.setForeground(Color.white);
        comp.setFont(new Font("sans-serif", 1, 15));
        return comp;
    }

    
    private void refreshView() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
