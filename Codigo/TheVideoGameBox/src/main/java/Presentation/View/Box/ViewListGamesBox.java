package Presentation.View.Box;

import Logic.Box.TBox;
import Logic.Game.TGame;
import Logic.SAAbstractFactory;
import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Main.JPanelConFondo;
import Presentation.View.Main.JPanelRound;
import Presentation.View.Utils.Button;
import Presentation.View.Utils.Platform;
import Presentation.View.Utils.TextField;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.bson.types.ObjectId;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static Presentation.View.Main.ViewMain.viewOptions;
import static Presentation.View.Utils.Images.backGround;
import static Presentation.View.Utils.Images.logo;

public class ViewListGamesBox extends JFrame implements IView {
    private List<TGame> games;
    private TBox tBox;
    private JPanel contentContainer;

    public ViewListGamesBox(Pair<TBox, List<TGame>> pair) {
        setTitle("List of Games");
        this.tBox = pair.getLeft();
        this.games = pair.getRight();
        init_GUI();
    }

    @Override
    public void update(Context context) {
        switch (context.getEvent()) {
            case Event.BACK:
                //ApplicationController.getInstance().action(new Context(Event.UPDATE_GAME_LIST, tBox));
                break;
            case Event.BACK_AUX:
                ApplicationController.getInstance().action(new Context(Event.UPDATE_GAME_LIST, tBox));
                break;
            case Event.RES_UPDATE_GAME_LIST_OK:
                setVisible(false);
                games = (List<TGame>) context.getData();
                break;
            case Event.RES_ADD_GAME_TO_BOX_KO:
                JOptionPane.showMessageDialog(null, "This game is already in this box!", "Add Game", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                break;
        }
        refreshView();
    }

    public void init_GUI() {
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

        contentContainer = new JPanel();
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
        headerContainer.add(Box.createRigidArea(new Dimension(15, 0)));

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

        headerContainer.add(backButton);

        // TITLE
        JLabel title = new JLabel(tBox.getName());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setForeground(Color.white);
        title.setFont(new Font("sans-serif", Font.BOLD, 20));
        headerContainer.add(title);
        headerContainer.add(Box.createRigidArea(new Dimension(30, 0)));

        // ICONO DE MENU
        Button icon = new Button(null, "logo_small_blanco.png", new Dimension(500, 80));
        icon.buttonIcon();
        icon.setToolTipText("Back to main window");
        icon.setAlignmentX(CENTER_ALIGNMENT);
        headerContainer.add(icon);
        headerContainer.add(Box.createRigidArea(new Dimension(70, 0)));

        icon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().action(new Context(Event.VIEW, null));
                setVisible(false);
            }
        });

        TextField textName = new TextField(new Dimension(210, 30), "Add Games");
        textName.textField();
        textName.setVisible(viewOptions);
        headerContainer.add(textName);
        headerContainer.add(Box.createRigidArea(new Dimension(0, 0)));
        textName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = textName.getText();
                if (search.length() <= 50 && search.length() > 0) {

                    Pair<List<TGame>, TBox> aux = new Pair<List<TGame>, TBox>() {
                        @Override
                        public List<TGame> getLeft() {
                            return SAAbstractFactory.getInstance().createSAGame().searchAllByName(search);
                        }

                        @Override
                        public TBox getRight() {
                            return tBox;
                        }

                        @Override
                        public TBox setValue(TBox value) {
                            return null;
                        }
                    };
                    textName.setText(null);
                    ApplicationController.getInstance().action(new Context(Event.ADD_GAME_TO_BOX, aux));
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "There is no games with that name");
                }
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

        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(CENTER_ALIGNMENT);
        panel.setOpaque(false);

        if (games != null && !games.isEmpty()) {
            for (TGame game : games) {
                try {
                    panel.add(gamePanel(game));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                panel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        contentContainer.add(panel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JPanelRound gamePanel(TGame game) throws IOException {

        JPanelRound panel = new JPanelRound(new Color(26, 59, 160), new Color(64, 147, 255), new Color(26, 59, 160));
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 20));
        panel.setMaximumSize(new Dimension(1000, 135));
        panel.setPreferredSize(new Dimension(1000, 135));
        panel.setMinimumSize(new Dimension(1000, 135));

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.setOpaque(false);
        namePanel.setMaximumSize(new Dimension(600, 135));
        namePanel.setPreferredSize(new Dimension(600, 135));
        namePanel.setMinimumSize(new Dimension(600, 135));

        // NAME
        JLabel name = new JLabel(game.getName());
        name.setForeground(Color.white);
        name.setFont(new Font("Leelawadee", Font.BOLD, 20));
        JLabel cover = new JLabel();
        if (game.getCover() != null) {
            Image image = null;
            URL url = new URL("https:" + game.getCover());
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();
            image = ImageIO.read(connection.getInputStream());
            connection.getInputStream().close();
            cover = new JLabel(new ImageIcon(image));
        } else {
            cover.setIcon(new ImageIcon((Objects.requireNonNull(getClass().getClassLoader().getResource("no_image.png")))));
        }

        // CONSTRUIR NAMEPANEL
        namePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        namePanel.add(cover);
        namePanel.add(Box.createRigidArea(new Dimension(55, 0)));
        namePanel.add(name);

        JPanel platformPanel = new JPanel();
        platformPanel.setOpaque(false);
        platformPanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        if (game.getPlatforms() != null) {
            int num = game.getPlatforms().size();
            if (num != 0) {
                if (num == 1) {
                    platformPanel.setLayout(new GridLayout(1, 1));
                    for (String p : game.getPlatforms()) {
                        platformPanel.add(platform(p, num));
                    }
                } else if (num == 2) {
                    platformPanel.setLayout(new GridLayout(1, 2));
                    for (String p : game.getPlatforms()) {
                        platformPanel.add(platform(p, num));
                    }
                } else if (num == 3) {
                    platformPanel.setLayout(new GridLayout(0, 1));
                    for (int i = 0; i < 3; i++) {
                        platformPanel.add(platform(game.getPlatforms().get(i), num));
                    }
                } else if (num > 3) {
                    platformPanel.setLayout(new GridLayout(2, 2));
                    for (int i = 0; i < 3; i++) {
                        platformPanel.add(platform(game.getPlatforms().get(i), num));
                    }
                    if (num > 3) {
                        platformPanel.add(platform("+" + num, num));
                    }
                }
            }
        }

        //BUTTON PANEL
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setOpaque(false);
        Button viewInfo = new Button("Details", "info_icon.png", Color.white, new Color(50, 170, 0), new Dimension(200, 45), Color.orange);
        viewInfo.buttonIcon();
        viewInfo.setBorderPainted(false);
        viewInfo.setContentAreaFilled(false);
        viewInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().action(new Context(Event.SEARCH_ONE, game.getId()));
                setVisible(false);
            }
        });
        buttonPanel.add(viewInfo);

        JPanel deletePanel = new JPanel(new BorderLayout());
        deletePanel.setOpaque(false);
        Button deleteButton = new Button(null, "delete_icon.png", new Dimension(60, 45), Color.orange);
        deleteButton.buttonIcon();
        deleteButton.setVisible(viewOptions);
        deleteButton.setBorderPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pair<ObjectId, ObjectId> aux = new MutablePair<>(tBox.getId(), game.getId());
                ApplicationController.getInstance().action(new Context(Event.DELETE_GAME_FROM_BOX, aux));
                ApplicationController.getInstance().action(new Context(Event.UPDATE_GAME_LIST, tBox));
                //setVisible(false);

            }
        });
        buttonPanel.add(deleteButton);

        //CONSTRUIR PANEL

        panel.add(namePanel, BorderLayout.WEST);
        panel.add(platformPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.EAST);
        //panel.add(deletePanel, BorderLayout.EAST);

        return panel;
    }

    private JPanel platform(String p, int n) {
        JPanel aux = new JPanel();
        aux.setLayout(new BoxLayout(aux, BoxLayout.Y_AXIS));
        Dimension d = new Dimension(0, 0);
        Random rand = new Random();
        if (n < 3) d = new Dimension(0, 40);
        aux.add(Box.createRigidArea(d));
        aux.setOpaque(false);
        aux.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        aux.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        aux.setBorder(new EmptyBorder(2, 2, 2, 2));
        if (p.contains("Commodore")) aux.add(new Platform(p, Color.gray, Color.white));
        else if (p.contains("Sega")) aux.add(new Platform(p, new Color(51, 60, 135), Color.white));
        else if (p.contains("Nintendo")) aux.add(new Platform(p, new Color(230, 0, 18), Color.white));
        else if (p.contains("PlayStation")) aux.add(new Platform(p, new Color(46, 109, 180), Color.white));
        else if (p.contains("iOS")) aux.add(new Platform(p, new Color(252, 49, 88), Color.white));
        else if (p.contains("PC")) aux.add(new Platform(p, new Color(60, 149, 61), Color.white));
        else if (p.contains("Philips")) aux.add(new Platform(p, new Color(22, 190, 190), Color.white));
        else if (p.contains("Xbox")) aux.add(new Platform(p, new Color(16, 124, 16), Color.white));
        else if (p.contains("Game Boy")) aux.add(new Platform(p, new Color(111, 38, 195), Color.white));
        else if (p.contains("Neo")) aux.add(new Platform(p, new Color(145, 237, 40), Color.white));
        else if (p.contains("Wii")) aux.add(new Platform(p, Color.white, Color.black));
        else if (p.contains("Oculus")) aux.add(new Platform(p, Color.black, Color.white));
        else if (p.contains("VR")) aux.add(new Platform(p, new Color(255, 140, 0), Color.white));
        else if (p.contains("Google")) aux.add(new Platform(p, new Color(244, 180, 0), Color.black));
        else if (p.contains("Android")) aux.add(new Platform(p, new Color(61, 220, 132), Color.white));
        else if (p.contains("Mac")) aux.add(new Platform(p, new Color(160, 82, 45), Color.white));
        else if (p.contains("Linux")) aux.add(new Platform(p, new Color(222, 76, 138), Color.white));
        else if (p.equals("+" + n)) aux.add(new Platform(p, Color.darkGray, Color.white));
        else {
            rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            aux.add(new Platform(p, new Color(r, g, b), Color.white));
        }
        aux.add(Box.createRigidArea(d));
        return aux;
    }

    private void refreshView() {
        contentContainer.remove(contentContainer.getComponentCount() - 1);

        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setAlignmentX(CENTER_ALIGNMENT);
        panel.setOpaque(false);

        if (games != null && !games.isEmpty()) {
            for (TGame game : games) {
                try {
                    panel.add(gamePanel(game));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                panel.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }

        contentContainer.add(panel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
