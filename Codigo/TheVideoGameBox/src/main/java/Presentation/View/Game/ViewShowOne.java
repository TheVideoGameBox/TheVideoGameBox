package Presentation.View.Game;

import Logic.Game.TGame;
import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Main.JPanelConFondo;
import Presentation.View.Main.JPanelRound;
import Presentation.View.Utils.Button;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

import static Presentation.View.Utils.Images.backGround;
import static Presentation.View.Utils.Images.logo;

public class ViewShowOne extends JFrame implements IView {

    private TGame game;

    @Override
    public void update(Context context) {
        if (context.getEvent() == Event.RES_SEARCH_ONE_OK) {
            this.game = (TGame) context.getData();
            initGUI();
        }

        refreshView();
    }

    private void initGUI() {
        this.setPreferredSize(new Dimension(1150, 750));
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
        mainpanel.add(midPanel, BorderLayout.SOUTH);

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

        topPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        topPanel.add(backButton);
        topPanel.add(Box.createRigidArea(new Dimension(180, 0)));
        topPanel.add(icon);


        return topPanel;
    }

    // Panel de la vista de mostrar un juego, donde se detallan sus atributos.
    private JPanel createMidPanel() {
        JPanel midPanel = new JPanel();
        midPanel.setPreferredSize(new Dimension(1150, 600));
        midPanel.setMaximumSize(new Dimension(1150, 600));
        midPanel.setMinimumSize(new Dimension(1150, 600));
        midPanel.setLayout(new GridBagLayout());
        midPanel.setBorder(new EmptyBorder(0, 20, 10, 20));
        midPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        // INFO DEL JUEGO

        JPanel infoJuego = new JPanel();
        infoJuego.setLayout(new BoxLayout(infoJuego, BoxLayout.Y_AXIS));
        infoJuego.setOpaque(false);
        infoJuego.setBorder(new EmptyBorder(0, 10, 7, 10));
        infoJuego.setPreferredSize(new Dimension(555, 400));
        infoJuego.setMaximumSize(new Dimension(555, 400));
        infoJuego.setMinimumSize(new Dimension(555, 400));
        infoJuego.setAlignmentX(CENTER_ALIGNMENT);

        // Carátula del juego.
        JLabel coverLabel = new JLabel();
        coverLabel.setPreferredSize(new Dimension(264, 350));
        coverLabel.setAlignmentX(CENTER_ALIGNMENT);
        if (game.getCover() != null) {
            Image image = null;
            URL url;
            try {
                url = new URL("https:" + game.getImage());
                URLConnection connection = url.openConnection();
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
                connection.connect();
                image = ImageIO.read(connection.getInputStream());
                connection.getInputStream().close();
                coverLabel = new JLabel(new ImageIcon(image));
                coverLabel.setAlignmentX(CENTER_ALIGNMENT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            coverLabel.setIcon(new ImageIcon((getClass().getClassLoader().getResource("No_Image_big.png"))));
        }

        infoJuego.add(Box.createRigidArea(new Dimension(0, 10)));
        infoJuego.add(coverLabel);

        // Nombre del juego.
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
        namePanel.setOpaque(false);
        namePanel.setBorder(new EmptyBorder(0, 10, 7, 10));
        namePanel.setMaximumSize(new Dimension(555, 50));
        namePanel.setMinimumSize(new Dimension(555, 50));
        namePanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);

        JTextPane nameTitle = new JTextPane();
        nameTitle.setForeground(Color.white);
        nameTitle.setOpaque(false);
        nameTitle.setFont(new Font("sans-serif", 1, 28));
        nameTitle.setText(game.getName());
        nameTitle.setEditable(false);
        StyledDocument doc = nameTitle.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        namePanel.add(nameTitle);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;

        infoJuego.add(namePanel);
        midPanel.add(infoJuego, gbc);

        // Empresas responsables del juego.
        JPanelRound companyPanel = new JPanelRound(new Color(12, 17, 21), new Color(74, 90, 151), new Color(12, 17, 21));
        companyPanel.setLayout(new BoxLayout(companyPanel, BoxLayout.Y_AXIS));
        companyPanel.setAlignmentX(CENTER_ALIGNMENT);
        JLabel companyTitle = new JLabel("Companies");
        companyTitle.setAlignmentX(CENTER_ALIGNMENT);
        companyTitle.setForeground(Color.white);
        companyTitle.setFont(new Font("sans-serif", 1, 25));
        companyPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        companyPanel.add(companyTitle);
        JTextPane companyText = new JTextPane();
        companyText.setForeground(Color.white);
        companyText.setOpaque(false);
        companyText.setFont(new Font("sans-serif", 1, 15));
        companyText.setText("There isn't any company.");
        companyText.setEditable(false);
        StyledDocument doc2 = companyText.getStyledDocument();
        SimpleAttributeSet center2 = new SimpleAttributeSet();
        StyleConstants.setAlignment(center2, StyleConstants.ALIGN_CENTER);
        doc2.setParagraphAttributes(0, doc2.getLength(), center2, false);
        if (game.getInvolved_companies() != null) {
            Boolean first = true;
            for (int i = 0; game.getInvolved_companies() != null && !game.getInvolved_companies().isEmpty() && i < game.getInvolved_companies().size(); i++) {
                if (first) {
                    companyText.setText(game.getInvolved_companies().get(i));
                    first = false;
                } else {
                    String s = "\n" + game.getInvolved_companies().get(i);
                    try {
                        companyText.getStyledDocument().insertString(companyText.getStyledDocument().getLength(), s, null);
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        JScrollPane companyScroll = new JScrollPane(companyText);
        companyScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        companyScroll.setOpaque(false);
        companyScroll.getViewport().setOpaque(false);
        companyScroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 12));
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                companyScroll.getVerticalScrollBar().setValue(0);
            }
        });
        companyPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        companyPanel.add(companyScroll);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                companyScroll.getViewport().setViewPosition(new Point(0, 0));
            }
        });


        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;

        midPanel.add(companyPanel, gbc);

        // Categorías del juego.
        JPanelRound categoryPanel = new JPanelRound(new Color(12, 17, 21), new Color(74, 90, 151), new Color(12, 17, 21));
        categoryPanel.setLayout(new BoxLayout(categoryPanel, BoxLayout.Y_AXIS));
        categoryPanel.setAlignmentX(CENTER_ALIGNMENT);
        JLabel categoryTitle = new JLabel("Genres");
        categoryTitle.setAlignmentX(CENTER_ALIGNMENT);
        categoryTitle.setForeground(Color.white);
        categoryTitle.setFont(new Font("sans-serif", 1, 25));
        categoryPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        categoryPanel.add(categoryTitle);
        if (game.getGenres() == null) {
            categoryPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            categoryPanel.add(categoryLabel("There isn't any genre."));
        } else {
            for (int i = 0; game.getGenres() != null && !game.getGenres().isEmpty() && i < game.getGenres().size(); i++) {
                categoryPanel.add(Box.createRigidArea(new Dimension(0, 5)));
                categoryPanel.add(categoryLabel(game.getGenres().get(i)));
            }
        }

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;

        midPanel.add(categoryPanel, gbc);

        // Plataformas para las que está disponible el juego.
        JPanelRound platformPanel = new JPanelRound(new Color(12, 17, 21), new Color(74, 90, 151), new Color(12, 17, 21));
        platformPanel.setLayout(new BoxLayout(platformPanel, BoxLayout.Y_AXIS));
        platformPanel.setAlignmentX(CENTER_ALIGNMENT);
        JLabel platformTitle = new JLabel("Platforms");
        platformTitle.setAlignmentX(CENTER_ALIGNMENT);
        platformTitle.setForeground(Color.white);
        platformTitle.setFont(new Font("sans-serif", 1, 25));
        platformPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        platformPanel.add(platformTitle);
        if (game.getPlatforms() == null) {
            platformPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            platformPanel.add(platformLabel("There isn't any platform."));
        } else {
            for (int i = 0; game.getPlatforms() != null && !game.getPlatforms().isEmpty() && i < game.getPlatforms().size(); i++) {
                platformPanel.add(Box.createRigidArea(new Dimension(0, 5)));
                platformPanel.add(platformLabel(game.getPlatforms().get(i)));
            }
        }

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;

        midPanel.add(platformPanel, gbc);

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
        JTextArea descText = new JTextArea("There isn't any description.");
        descText.setAlignmentX(CENTER_ALIGNMENT);
        descText.setForeground(Color.white);
        descText.setEditable(false);
        descText.setOpaque(false);
        descText.setFont(new Font("sans-serif", 1, 14));
        descText.setLineWrap(true);
        descText.setWrapStyleWord(true);
        if (game.getSummary() != null) {
            descText.setText(game.getSummary());
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
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;

        midPanel.add(descPanel, gbc);

        this.pack();
        return midPanel;
    }

    private JLabel categoryLabel(String category) {
        JLabel comp = new JLabel(category);
        comp.setAlignmentX(CENTER_ALIGNMENT);
        comp.setForeground(Color.white);
        comp.setFont(new Font("sans-serif", 1, 15));
        return comp;
    }

    private JLabel platformLabel(String platform) {
        JLabel comp = new JLabel(platform);
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
