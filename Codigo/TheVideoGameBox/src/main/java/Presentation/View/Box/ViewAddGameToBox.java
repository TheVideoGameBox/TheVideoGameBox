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
import org.apache.commons.lang3.tuple.Pair;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Objects;

import static Presentation.View.Utils.Images.backGround;
import static Presentation.View.Utils.Images.logo;

// Clase vista para añadir juegos a una box. Es igual que ViewSearchByName pero cambiando el Event.
public class ViewAddGameToBox extends JFrame implements IView {

    private static final long serialVersionUID = 1L;

    private List<TGame> games;
    private TBox box;

    public ViewAddGameToBox(Pair<List<TGame>, TBox> aux) {
        setTitle("Add a game to box");
        games = aux.getLeft();
        box = aux.getRight();
        init_GUI(aux);
        this.setLocationRelativeTo(null);
    }

    private void init_GUI(Pair<List<TGame>, TBox> aux) {
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
        headerContainer.add(Box.createRigidArea(new Dimension(7, 0)));

        // TITLE
        JLabel title = new JLabel("Select a game to add");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setForeground(Color.white);
        title.setFont(new Font("sans-serif", 1, 20));
        headerContainer.add(title);
        //headerContainer.add(Box.createRigidArea(new Dimension(, 0)));

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

        for (TGame game : games) {
            try {
                contentContainer.add(gamePanel(game, box, aux));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        this.pack();
        this.setVisible(true);
    }

    private JPanelRound gamePanel(TGame game, TBox box, Pair<List<TGame>, TBox> aux) throws IOException {

        JPanelRound panel = new JPanelRound(new Color(26, 59, 160), new Color(64, 147, 255), new Color(26, 59, 160));
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
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
        JLabel name = new JLabel(game.getName());
        name.setForeground(Color.white);
        name.setFont(new Font("Leelawadee", Font.BOLD, 20));
        name.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ApplicationController.getInstance().action(new Context(Event.ADD_GAME_TO_BOX, aux));
                setVisible(false);
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

        });

        //COVER
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
        namePanel.add(Box.createRigidArea(new Dimension(15, 0)));
        namePanel.add(name);

        //BUTTON PANEL
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.setMaximumSize(new Dimension(300, 135));
        buttonPanel.setPreferredSize(new Dimension(300, 135));
        buttonPanel.setMinimumSize(new Dimension(300, 135));


        Button addGame = new Button("Add Game", "add_icon.png", Color.white, null, new Dimension(200, 45), Color.orange);
        addGame.buttonIcon();
        addGame.setBorderPainted(false);
        addGame.setContentAreaFilled(false);
        addGame.setAlignmentX(CENTER_ALIGNMENT);
        addGame.setAlignmentY(CENTER_ALIGNMENT);
        addGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SAAbstractFactory.getInstance().createSABox().addGame(box.getId(), game.getId());
                ApplicationController.getInstance().backAux();
                //ApplicationController.getInstance().action(new Context(Event.UPDATE_GAME_LIST, aux));
                setVisible(false);
            }
        });

        buttonPanel.add(addGame, BorderLayout.CENTER);

        //CONSTRUIR PANEL
        panel.add(namePanel, BorderLayout.WEST);
        panel.add(buttonPanel, BorderLayout.EAST);

        return panel;
    }

    @Override
    public void update(Context context) {
        switch (context.getEvent()) {
            case Event.RES_ADD_GAME_TO_BOX_OK:
                JOptionPane.showMessageDialog(this, "Game added!", "Add Game", JOptionPane.INFORMATION_MESSAGE);
                break;
            case Event.RES_ADD_GAME_TO_BOX_KO:
                JOptionPane.showMessageDialog(null, "Repeated game!", "Add Game", JOptionPane.ERROR_MESSAGE);
                break;
        }

    }

}
