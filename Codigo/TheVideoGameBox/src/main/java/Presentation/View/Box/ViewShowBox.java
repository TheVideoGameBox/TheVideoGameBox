package Presentation.View.Box;

import Logic.Box.TBox;
import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Main.JPanelConFondo;
import Presentation.View.Main.JPanelRound;
import Presentation.View.Utils.Button;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static Presentation.View.Main.ViewMain.viewOptions;
import static Presentation.View.Utils.Images.backGround;
import static Presentation.View.Utils.Images.logo;

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
        this.setPreferredSize(new Dimension(1150, 750));
        this.setLocation(400, 100);
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
        Button icon = new Button(null, "logo_small_blanco.png", new Dimension(400, 80));
        icon.buttonIcon();
        icon.setToolTipText("Back to main window");
        icon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().action(new Context(Event.VIEW, null));
                setVisible(false);
            }
        });

        Button modifyInfo = new Button("Modify", "modify_icon.png", Color.white, new Color(50, 170, 9), new Dimension(150, 45), Color.orange);
        modifyInfo.buttonIcon();
        modifyInfo.setVisible(viewOptions);
        modifyInfo.setBorderPainted(false);
        modifyInfo.setContentAreaFilled(false);
        modifyInfo.setFont(new Font("Leelawadee", Font.BOLD, 22));
        modifyInfo.setToolTipText("Modify box");
        modifyInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().action(new Context(Event.VIEW_MODIFY_BOX, box));
                setVisible(false);
            }
        });

        topPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        topPanel.add(backButton);
        topPanel.add(Box.createRigidArea(new Dimension(200, 0)));
        topPanel.add(icon);
        topPanel.add(Box.createRigidArea(new Dimension(200, 0)));
        topPanel.add(modifyInfo);


        return topPanel;
    }

    private JPanel createMidPanel() {
        JPanel midPanel = new JPanel();
        midPanel.setPreferredSize(new Dimension(1150, 600));
        midPanel.setMaximumSize(new Dimension(1150, 600));
        midPanel.setMinimumSize(new Dimension(1150, 600));
        midPanel.setLayout(new GridBagLayout());
        midPanel.setBorder(new EmptyBorder(0, 20, 10, 20));
        midPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        // Nombre de la box.
        JPanel namePanel = new JPanel();
        namePanel.setPreferredSize(new Dimension(500, 40));
        namePanel.setMaximumSize(new Dimension(500, 40));
        namePanel.setMinimumSize(new Dimension(500, 40));
        namePanel.setOpaque(false);
        namePanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        namePanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);


        JLabel nameTitle = new JLabel(box.getName());
        nameTitle.setForeground(Color.white);
        nameTitle.setFont(new Font("sans-serif", 1, 35));
        nameTitle.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        nameTitle.setAlignmentY(JPanel.CENTER_ALIGNMENT);

        namePanel.add(nameTitle);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        midPanel.add(namePanel, gbc);

        // Panel de privacidad y número de juegos contenidos

        JPanel privNumPanel = new JPanel();
        privNumPanel.setLayout(new GridLayout(2, 1));
        privNumPanel.setPreferredSize(new Dimension(500, 80));
        privNumPanel.setMaximumSize(new Dimension(500, 80));
        privNumPanel.setMinimumSize(new Dimension(500, 80));
        privNumPanel.setOpaque(false);
        privNumPanel.setBorder(new EmptyBorder(0, 10, 7, 10));
        privNumPanel.setAlignmentX(CENTER_ALIGNMENT);

        // Privacidad de la box.
        JPanel privacyPanel = new JPanel();
        privacyPanel.setLayout(new BoxLayout(privacyPanel, BoxLayout.Y_AXIS));
        privacyPanel.setPreferredSize(new Dimension(500, 80));
        privacyPanel.setMaximumSize(new Dimension(500, 80));
        privacyPanel.setMinimumSize(new Dimension(500, 80));
        privacyPanel.setOpaque(false);
        privacyPanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel privacyTitle = new JLabel("Privacy");
        privacyTitle.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        privacyTitle.setForeground(Color.white);
        privacyTitle.setFont(new Font("sans-serif", 1, 27));

        JLabel privacyText = new JLabel("There isn't any privacy info.");
        privacyText.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        privacyText.setForeground(Color.white);
        privacyText.setOpaque(false);
        privacyText.setFont(new Font("sans-serif", 1, 23));
        if (box.getPrivacy() != null) {
            privacyText.setText(box.getPrivacy().toString());
            if (box.getPrivacy().toString() == "Public") privacyText.setForeground(Color.green);
            else privacyText.setForeground(Color.red);
        }

        privacyPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        privacyPanel.add(privacyTitle);
        privacyPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        privacyPanel.add(privacyText);

        privNumPanel.add(privacyPanel);

        // Número de juegos de la box

        JPanel numGamePanel = new JPanel();
        numGamePanel.setPreferredSize(new Dimension(500, 80));
        numGamePanel.setMaximumSize(new Dimension(500, 80));
        numGamePanel.setMinimumSize(new Dimension(500, 80));
        numGamePanel.setOpaque(false);
        numGamePanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel numGames = new JLabel();
        numGames.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        numGames.setForeground(Color.white);
        numGames.setFont(new Font("sans-serif", 1, 27));
        if (box.getGameList() != null) numGames.setText("Number of Games: " + box.getGameList().size());
        else numGames.setText("Number of Games: 0");

        numGamePanel.add(numGames);

        privNumPanel.add(numGamePanel);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        midPanel.add(privNumPanel, gbc);


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

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        midPanel.add(genrePanel, gbc);


        // Descripción del juego.
        JPanelRound descPanel = new JPanelRound(new Color(12, 17, 21), new Color(74, 90, 151), new Color(12, 17, 21));
        descPanel.setBorder(new EmptyBorder(0, 10, 7, 10));
        descPanel.setPreferredSize(new Dimension(555, 150));
        descPanel.setMaximumSize(new Dimension(555, 150));
        descPanel.setMinimumSize(new Dimension(555, 150));
        descPanel.setLayout(new BoxLayout(descPanel, BoxLayout.Y_AXIS));
        descPanel.setAlignmentX(CENTER_ALIGNMENT);
        JLabel descTitle = new JLabel("Description");
        descTitle.setAlignmentX(CENTER_ALIGNMENT);
        descTitle.setForeground(Color.white);
        descTitle.setFont(new Font("sans-serif", 1, 25));
        JTextPane descText = new JTextPane();
        descText.setText("There isn't any description.");
        descText.setAlignmentX(CENTER_ALIGNMENT);
        descText.setForeground(Color.white);
        descText.setEditable(false);
        descText.setOpaque(false);
        descText.setFont(new Font("sans-serif", 1, 17));
        StyledDocument doc2 = descText.getStyledDocument();
        SimpleAttributeSet center2 = new SimpleAttributeSet();
        StyleConstants.setAlignment(center2, StyleConstants.ALIGN_CENTER);
        doc2.setParagraphAttributes(0, doc2.getLength(), center2, false);
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

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        midPanel.add(descPanel, gbc);

        this.pack();
        return midPanel;
    }

    private JLabel genreLabel(String genre) {
        JLabel comp = new JLabel(genre);
        comp.setAlignmentX(CENTER_ALIGNMENT);
        comp.setForeground(Color.white);
        comp.setFont(new Font("sans-serif", 1, 18));
        return comp;
    }


    private void refreshView() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
