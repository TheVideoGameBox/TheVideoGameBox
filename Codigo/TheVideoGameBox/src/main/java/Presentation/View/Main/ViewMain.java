package Presentation.View.Main;

import Logic.Game.TGame;
import Logic.SAAbstractFactory;
import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Utils.AutoCompleteTextField;
import Presentation.View.Utils.Button;
import Presentation.View.Utils.TextField;
import org.bson.types.ObjectId;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static Presentation.View.Utils.Images.backGround;
import static Presentation.View.Utils.Images.logo;

public class ViewMain extends JFrame implements IView {

    public static boolean logged = false;
    public static boolean viewOptions = false;
    public static ObjectId id_logged = null;
    private boolean desplegado;
    private List<TGame> randomGames = new ArrayList<>();
    private Button createBox;
    private Button myBox;
    private boolean hideView;        //Para poder controlar si la vista se ve o no desde el update

    public ViewMain() {
        super();
        desplegado = true;
        hideView = true;
        initGUI();
        refreshView();
    }

    @Override
    public void update(Context context) {
        ApplicationController.getInstance().clearViewStack();

        switch (context.getEvent()) {
            case Event.RES_SEARCH_ALL_BY_NAME_KO:
                JOptionPane.showMessageDialog(null, "There isn't any game with that name");
                hideView = false;
                break;
            case Event.RES_SEARCH_ALL_BOXES_BY_NAME_KO:
                JOptionPane.showMessageDialog(null, "There isn't any box with that name");
                hideView = false;
                break;
            case Event.RES_USER_BOXES_KO:
                JOptionPane.showMessageDialog(null, "You don't have any Box yet");
                hideView = false;
                break;
            case Event.RES_RANDOM_GAMES_OK:
                randomGames = (List<TGame>) context.getData();
                refreshView();
                break;
            case Event.RES_LOGIN_USER_OK:
                id_logged = (ObjectId) context.getData();
                break;
            case Event.RES_SEARCH_ALL_BY_PLATFORM_KO:
                JOptionPane.showMessageDialog(null, "There isn't any game on that platform.");
                hideView = false;
                break;
            case Event.BACK:
                refreshView();
                break;
            default:
                break;
        }
    }

    private void initGUI() {
        Image iconFrame = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(logo))).getImage();
        this.setIconImage(iconFrame);
        this.setPreferredSize(new Dimension(1150, 750));
        this.setLocation(400, 100);

        JPanelConFondo mainpanel = new JPanelConFondo();
        mainpanel.setLayout(new BorderLayout());
        mainpanel.setImagen(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(backGround))).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(mainpanel);

        JPanel topPanel = creaTopPanel();
        JPanel midPanel = creaMidPanel();

        this.setPreferredSize(new Dimension(1150, 750));

        mainpanel.add(topPanel, BorderLayout.WEST);
        mainpanel.add(midPanel, BorderLayout.CENTER);
    }


    private JPanel creaTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(new Color(62, 80, 90));
        topPanel.setPreferredSize(new Dimension(250, 750));
        topPanel.setMinimumSize(new Dimension(250, 750));
        topPanel.setMaximumSize(new Dimension(250, 750));

        //TOP PANEL

        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
        top.setOpaque(false);
        top.setPreferredSize(new Dimension(250, 50));
        top.setMinimumSize(new Dimension(250, 50));
        top.setMaximumSize(new Dimension(250, 50));

        JPanel game = new JPanel();
        JPanel box = new JPanel();
        JPanel user = new JPanel();

        //PARA CENTRAR EL TEXTO DE LOS JCOMBOBOX
        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

        //BOTON DESPEGABLE

        Button despleg = new Button(null, "desplegable_icon.png", Color.white, new Color(62, 80, 90), new Dimension(50, 50));
        despleg.buttonIcon();
        despleg.setBorder(BorderFactory.createBevelBorder(0));
        despleg.setToolTipText("Desplegar el menu");
        top.add(despleg);
        despleg.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (desplegado) {
                    desplegado = !desplegado;
                    topPanel.setOpaque(false);
                    topPanel.setPreferredSize(new Dimension(50, 750));
                    topPanel.setMinimumSize(new Dimension(50, 750));
                    topPanel.setMaximumSize(new Dimension(50, 750));
                    top.setOpaque(false);
                    game.setVisible(false);
                    box.setVisible(false);
                    user.setVisible(false);
                } else {
                    desplegado = !desplegado;
                    topPanel.setOpaque(true);
                    topPanel.setBackground(new Color(62, 80, 90));
                    topPanel.setPreferredSize(new Dimension(250, 750));
                    topPanel.setMinimumSize(new Dimension(250, 750));
                    topPanel.setMaximumSize(new Dimension(250, 750));
                    top.setBackground(new Color(62, 80, 90));
                    game.setVisible(true);
                    box.setVisible(true);
                    user.setVisible(true);
                }
            }

        });

        //LOGUEO DEL USUARIO

        Button logIn = new Button(null, "user_icon.png", Color.white, new Color(62, 80, 90), new Dimension(50, 50));
        logIn.buttonIcon();
        despleg.setBorder(BorderFactory.createBevelBorder(0));
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().action(new Context(Event.VIEW_LOGIN, null));
                setVisible(false);
            }
        });
        logIn.setBorder(BorderFactory.createBevelBorder(0));
        logIn.setToolTipText("Log In");

        top.add(logIn);

        //REGISTRO DEL USUARIO

        Button registry = new Button(null, "register_icon.png", Color.white, new Color(62, 80, 90), new Dimension(50, 50));
        registry.buttonIcon();
        registry.setBorder(BorderFactory.createBevelBorder(0));
        registry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().action(new Context(Event.VIEW_CREATE_USER, null));
                setVisible(false);
            }
        });
        registry.setBorder(BorderFactory.createBevelBorder(0));
        registry.setToolTipText("Register");

        top.add(registry);

        //CIERRE DE SESION DEL USUARIO

        Button logout = new Button(null, "logout_icon.png", Color.white, new Color(252, 147, 3), new Dimension(50, 50));
        logout.buttonIcon();
        logout.setBorder(BorderFactory.createBevelBorder(0));
        logout.setVisible(false);
        logout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                logIn.setVisible(true);
                registry.setVisible(true);
                logout.setVisible(false);
                game.setVisible(true);
                box.setVisible(true);
                user.setVisible(true);
                createBox.setVisible(false);
                myBox.setVisible(false);
                logged = false;
            }

        });
        logout.setBorder(BorderFactory.createBevelBorder(0));
        logout.setToolTipText("Log out");
        //En función de si esta logueado se ve visible o no
        if (logged) {
            logIn.setVisible(false);
            registry.setVisible(false);
            logout.setVisible(true);
        } else {                            //Cambios para probar que funciona... Deben adaptarse a los criterios de PO
            logIn.setVisible(true);
            registry.setVisible(true);
            logout.setVisible(false);
        }
        game.setVisible(true);
        box.setVisible(true);
        user.setVisible(true);

        top.add(logout);

        //PANEL DE JUEGOS

        game.setLayout(new GridLayout(2, 0));
        game.setOpaque(false);
        game.setPreferredSize(new Dimension(250, 217));
        game.setMinimumSize(new Dimension(250, 217));
        game.setMaximumSize(new Dimension(250, 217));
        game.setBorder(new BorderTitle("Games", Color.white));

        //BUSCAR JUEGOS

        JPanel searchGameType = new JPanel();
        searchGameType.setLayout(new GridLayout(0, 2));
        searchGameType.setOpaque(false);
        searchGameType.setPreferredSize(new Dimension(250, 70));
        searchGameType.setMinimumSize(new Dimension(250, 70));
        searchGameType.setMaximumSize(new Dimension(250, 70));

        JLabel searchGameText = new JLabel("Type of Search:");
        searchGameText.setForeground(Color.white);
        searchGameText.setFont(new Font("Leelawadee", Font.BOLD, 16));

        JPanel comboBoxGamePanel = new JPanel();
        comboBoxGamePanel.setLayout(new BoxLayout(comboBoxGamePanel, BoxLayout.Y_AXIS));
        comboBoxGamePanel.setOpaque(false);
        comboBoxGamePanel.setPreferredSize(new Dimension(100, 30));
        comboBoxGamePanel.setMinimumSize(new Dimension(100, 30));
        comboBoxGamePanel.setMaximumSize(new Dimension(100, 30));

        JComboBox<String> comboBoxGame = new JComboBox<String>();
        comboBoxGame.addItem("By Name");
        comboBoxGame.addItem("By Platform");
        comboBoxGame.setRenderer(listRenderer);


        //Falta hacer la comprobación de en que combobox está en función de eso cambia el TextField y la búsqueda

        comboBoxGamePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        comboBoxGamePanel.add(comboBoxGame);
        comboBoxGamePanel.add(Box.createRigidArea(new Dimension(0, 30)));

        searchGameType.add(searchGameText);
        searchGameType.add(comboBoxGamePanel);

        game.add(searchGameType);

        JPanel textGPanel = new JPanel();
        textGPanel.setLayout(new GridLayout(2, 0));
        textGPanel.setOpaque(false);
        textGPanel.setPreferredSize(new Dimension(250, 100));
        textGPanel.setMinimumSize(new Dimension(250, 100));
        textGPanel.setMaximumSize(new Dimension(250, 100));


        JPanel textGamePanel = new JPanel();
        textGamePanel.setOpaque(false);
        textGamePanel.setPreferredSize(new Dimension(250, 100));
        textGamePanel.setMinimumSize(new Dimension(250, 100));
        textGamePanel.setMaximumSize(new Dimension(250, 100));

        TextField textGame = new TextField(new Dimension(225, 30), "Name of the Game");
        textGame.textField();

        AutoCompleteTextField textPlatform = new AutoCompleteTextField(new Dimension(225, 30), "Name of the Platform", new PlatformNames());
        textPlatform.textField();
        textPlatform.setupAutoComplete();
        textPlatform.setVisible(false);


        class actionListenerSearchAllByName implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String search = textGame.getText();
                if (search.length() <= 50 && search.length() > 0) {
                    textGame.setText(null);
                    ApplicationController.getInstance().action(new Context(Event.SEARCH_ALL_BY_NAME, search));
                    if (hideView)
                        setVisible(false);

                    hideView = true;
                } else if (search.length() > 50) {
                    JOptionPane.showMessageDialog(null, "Too many characters");
                }

            }

        }
        class actionListenerSearchAllByPlatform implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String search = textPlatform.getText();
                if (search.length() <= 50 && search.length() > 0) {
                    textPlatform.setText(null);
                    ApplicationController.getInstance().action(new Context(Event.SEARCH_ALL_BY_PLATFORM, search));
                    if (hideView)
                        setVisible(false);

                    hideView = true;
                } else if (search.length() > 50) {
                    JOptionPane.showMessageDialog(null, "Too many characters");
                }

            }

        }
        textGame.addActionListener(new actionListenerSearchAllByName());
        textPlatform.addActionListener(new actionListenerSearchAllByPlatform());


        JPanel gameButtonPanel = new JPanel();
        gameButtonPanel.setOpaque(false);
        gameButtonPanel.setPreferredSize(new Dimension(250, 100));
        gameButtonPanel.setMinimumSize(new Dimension(250, 100));
        gameButtonPanel.setMaximumSize(new Dimension(250, 100));

        Button gameButton = new Button("SEARCH", "tinylupa_icon.png", new Dimension(120, 35), Color.orange);
        gameButton.buttonIcon();
        gameButton.setToolTipText("Search a Game by Name");
        gameButton.addActionListener(new actionListenerSearchAllByName());

        textGamePanel.add(textGame);
        textGamePanel.add(textPlatform);
        gameButtonPanel.add(gameButton);

        textGPanel.add(textGamePanel);
        textGPanel.add(gameButtonPanel);

        game.add(textGPanel);

        comboBoxGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemPicked = (String) comboBoxGame.getSelectedItem();
                switch (Objects.requireNonNull(itemPicked)) {
                    case "By Name": {
                        textGame.setVisible(true);
                        textPlatform.setVisible(false);
                        textGamePanel.revalidate();
                        textGamePanel.repaint();
                        gameButton.addActionListener(new actionListenerSearchAllByName());

                        break;
                    }
                    case "By Platform": {
                        textGame.setVisible(false);
                        textPlatform.setVisible(true);
                        textGamePanel.revalidate();
                        textGamePanel.repaint();
                        gameButton.addActionListener(new actionListenerSearchAllByPlatform());
                        break;
                    }

                }
            }
        });


        //PANEL DE BOX

        box.setLayout(new GridLayout(2, 0));
        box.setOpaque(false);
        box.setPreferredSize(new Dimension(250, 217));
        box.setMinimumSize(new Dimension(250, 217));
        box.setMaximumSize(new Dimension(250, 217));
        box.setBorder(new BorderTitle("Boxes", Color.white));

        //BUSCAR BOXES

        JPanel searchBoxType = new JPanel();
        searchBoxType.setLayout(new GridLayout(0, 2));
        searchBoxType.setOpaque(false);
        searchBoxType.setPreferredSize(new Dimension(250, 70));
        searchBoxType.setMinimumSize(new Dimension(250, 70));
        searchBoxType.setMaximumSize(new Dimension(250, 70));

        JLabel searchBoxText = new JLabel("Type of Search:");
        searchBoxText.setForeground(Color.white);
        searchBoxText.setFont(new Font("Leelawadee", Font.BOLD, 16));

        JPanel comboBoxBoxPanel = new JPanel();
        comboBoxBoxPanel.setLayout(new BoxLayout(comboBoxBoxPanel, BoxLayout.Y_AXIS));
        comboBoxBoxPanel.setOpaque(false);
        comboBoxBoxPanel.setPreferredSize(new Dimension(100, 30));
        comboBoxBoxPanel.setMinimumSize(new Dimension(100, 30));
        comboBoxBoxPanel.setMaximumSize(new Dimension(100, 30));

        JComboBox<String> comboBoxBox = new JComboBox<String>();
        comboBoxBox.addItem("By Name");
        comboBoxBox.setRenderer(listRenderer);

        comboBoxBoxPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        comboBoxBoxPanel.add(comboBoxBox);
        comboBoxBoxPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        searchBoxType.add(searchBoxText);
        searchBoxType.add(comboBoxBoxPanel);

        box.add(searchBoxType);

        JPanel textBPanel = new JPanel();
        textBPanel.setLayout(new GridLayout(2, 0));
        textBPanel.setOpaque(false);
        textBPanel.setPreferredSize(new Dimension(250, 100));
        textBPanel.setMinimumSize(new Dimension(250, 100));
        textBPanel.setMaximumSize(new Dimension(250, 100));

        JPanel textBoxPanel = new JPanel();
        textBoxPanel.setOpaque(false);
        textBoxPanel.setPreferredSize(new Dimension(250, 100));
        textBoxPanel.setMinimumSize(new Dimension(250, 100));
        textBoxPanel.setMaximumSize(new Dimension(250, 100));

        TextField textBox = new TextField(new Dimension(225, 30), "Name of the Box");
        textBox.textField();
        textBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = textBox.getText();
                if (search.length() <= 50 && search.length() > 0) {
                    textBox.setText(null);
                    ApplicationController.getInstance().action(new Context(Event.SEARCH_ALL_BOXES_BY_NAME, search));
                    if (hideView)
                        setVisible(false);

                    hideView = true;
                } else if (search.length() > 50) {
                    JOptionPane.showMessageDialog(null, "Too many characters");
                }
            }
        });

        JPanel boxButtonPanel = new JPanel();
        boxButtonPanel.setOpaque(false);
        boxButtonPanel.setPreferredSize(new Dimension(250, 100));
        boxButtonPanel.setMinimumSize(new Dimension(250, 100));
        boxButtonPanel.setMaximumSize(new Dimension(250, 100));

        Button boxButton = new Button("SEARCH", "tinylupa_icon.png", new Dimension(120, 35), Color.orange);
        boxButton.buttonIcon();
        boxButton.setToolTipText("Search a Box by Name");
        boxButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String search = textBox.getText();
                if (search.length() <= 50 && search.length() > 0) {
                    textBox.setText(null);
                    ApplicationController.getInstance().action(new Context(Event.SEARCH_ALL_BOXES_BY_NAME, search));        //CAMBIAR EL EVENTO CUANDO ESTE HECHA LA VISTA
                    if (hideView)
                        setVisible(false);

                    hideView = true;
                } else {
                    JOptionPane.showMessageDialog(null, "There is no Boxes with that name");
                }
            }
        });

        textBoxPanel.add(textBox);
        boxButtonPanel.add(boxButton);

        textBPanel.add(textBoxPanel);
        textBPanel.add(boxButtonPanel);

        box.add(textBPanel);

        //PANEL DE USERS

        user.setLayout(new GridLayout(2, 0));
        user.setOpaque(false);
        user.setPreferredSize(new Dimension(250, 217));
        user.setMinimumSize(new Dimension(250, 217));
        user.setMaximumSize(new Dimension(250, 217));
        user.setBorder(new BorderTitle("Users", Color.white));

        //BUSCAR USERS

        JPanel searchUserType = new JPanel();
        searchUserType.setLayout(new GridLayout(0, 2));
        searchUserType.setOpaque(false);
        searchUserType.setPreferredSize(new Dimension(250, 70));
        searchUserType.setMinimumSize(new Dimension(250, 70));
        searchUserType.setMaximumSize(new Dimension(250, 70));

        JLabel searchUserText = new JLabel("Type of Search:");
        searchUserText.setForeground(Color.white);
        searchUserText.setFont(new Font("Leelawadee", Font.BOLD, 16));

        JPanel comboBoxUserPanel = new JPanel();
        comboBoxUserPanel.setLayout(new BoxLayout(comboBoxUserPanel, BoxLayout.Y_AXIS));
        comboBoxUserPanel.setOpaque(false);
        comboBoxUserPanel.setPreferredSize(new Dimension(100, 30));
        comboBoxUserPanel.setMinimumSize(new Dimension(100, 30));
        comboBoxUserPanel.setMaximumSize(new Dimension(100, 30));

        JComboBox<String> comboBoxUser = new JComboBox<String>();
        comboBoxUser.addItem("By Name");
        comboBoxUser.setRenderer(listRenderer);

        comboBoxUserPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        comboBoxUserPanel.add(comboBoxUser);
        comboBoxUserPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        searchUserType.add(searchUserText);
        searchUserType.add(comboBoxUserPanel);

        user.add(searchUserType);

        JPanel textUPanel = new JPanel();
        textUPanel.setLayout(new GridLayout(2, 0));
        textUPanel.setOpaque(false);
        textUPanel.setPreferredSize(new Dimension(250, 100));
        textUPanel.setMinimumSize(new Dimension(250, 100));
        textUPanel.setMaximumSize(new Dimension(250, 100));

        JPanel textUserPanel = new JPanel();
        textUserPanel.setOpaque(false);
        textUserPanel.setPreferredSize(new Dimension(250, 100));
        textUserPanel.setMinimumSize(new Dimension(250, 100));
        textUserPanel.setMaximumSize(new Dimension(250, 100));

        TextField textUser = new TextField(new Dimension(225, 30), "Name of the User");
        textUser.textField();
        textUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = textUser.getText();
                if (search.length() <= 50 && search.length() > 0) {
                    textUser.setText(null);
                    //LLAMADA AL EVENTO
                    setVisible(false);
                } else if (search.length() > 50) {
                    JOptionPane.showMessageDialog(null, "Too many characters");
                }
            }
        });

        JPanel userButtonPanel = new JPanel();
        userButtonPanel.setOpaque(false);
        userButtonPanel.setPreferredSize(new Dimension(250, 100));
        userButtonPanel.setMinimumSize(new Dimension(250, 100));
        userButtonPanel.setMaximumSize(new Dimension(250, 100));

        Button userButton = new Button("SEARCH", "tinylupa_icon.png", new Dimension(120, 35), Color.orange);
        userButton.buttonIcon();
        userButton.setToolTipText("Search a Box by Name");
        userButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String search = textUser.getText();
                if (search.length() <= 50 && search.length() > 0) {
                    textUser.setText(null);
                    //LLAMADA AL EVENTO
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "There is no Boxes with that name");
                }
            }
        });

        textUserPanel.add(textUser);
        userButtonPanel.add(userButton);

        textUPanel.add(textUserPanel);
        textUPanel.add(userButtonPanel);

        user.add(textUPanel);

        topPanel.add(top);
        topPanel.add(game);
        topPanel.add(box);
        topPanel.add(user);

        return topPanel;
    }


    private JPanel creaMidPanel() {
        JPanel midpanel = new JPanel();
        midpanel.setLayout(new BoxLayout(midpanel, BoxLayout.Y_AXIS));
        midpanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        midpanel.setOpaque(false);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setMaximumSize(new Dimension(1150, 172));
        topPanel.setMinimumSize(new Dimension(950, 172));

        JPanel iconPanel = new JPanel();
        iconPanel.setOpaque(false);

        JLabel icono = new JLabel();
        icono.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("logo_medium_blanco.png"))));
        icono.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        icono.setAlignmentY(JPanel.CENTER_ALIGNMENT);

        iconPanel.add(icono);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setMaximumSize(new Dimension(215, 35));
        buttonPanel.setMinimumSize(new Dimension(215, 35));
        buttonPanel.setPreferredSize(new Dimension(215, 35));

        createBox = new Button("Create Box", null, Color.white, null, new Dimension(90, 35), Color.orange);
        createBox.button();
        createBox.setVisible(logged);
        createBox.setBorderPainted(false);
        createBox.setContentAreaFilled(false);
        createBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().action(new Context(Event.VIEW_CREATE_BOX, null));
                setVisible(false);
            }
        });

        buttonPanel.add(createBox);

        myBox = new Button("My Boxes", null, Color.BLUE, null, new Dimension(100, 35), Color.orange);
        myBox.button();
        myBox.setVisible(logged);
        myBox.setBorderPainted(false);
        myBox.setContentAreaFilled(false);
        myBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().action(new Context(Event.USER_BOXES, id_logged));
                if (hideView) setVisible(false);
                hideView = true;
            }
        });
        buttonPanel.add(myBox);
        topPanel.add(iconPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        //JPanelRound randomPanel = new JPanelRound(new Color(0, 0, 128), new Color(47, 79, 79), new Color(0, 0, 128)); //Azul-gris
        //JPanelRound randomPanel = new JPanelRound(new Color(46, 56, 90), new Color(107, 113, 144), new Color(46, 56, 90)); //Grises
        //JPanelRound randomPanel = new JPanelRound(new Color(0, 0, 0), new Color(107, 113, 144), new Color(0, 0, 0)); //Negro-Gris
        //JPanelRound randomPanel = new JPanelRound(new Color(62, 65, 68), new Color(101, 114, 124), new Color(62, 65, 68)); //Grises más oscuros
        //JPanelRound randomPanel = new JPanelRound(new Color(122, 122, 122), new Color(200, 200, 200), new Color(122, 122, 122)); //Grises claros
        JPanelRound randomPanel = new JPanelRound(new Color(52, 69, 77), new Color(107, 138, 156), new Color(52, 69, 77)); //Relacionados con el fondo
        randomPanel.setMaximumSize(new Dimension(950, 420));
        randomPanel.setMinimumSize(new Dimension(900, 420));
        randomPanel.setLayout(new BoxLayout(randomPanel, BoxLayout.Y_AXIS));
        randomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel panel1 = new JPanel();
        panel1.setOpaque(false);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

        randomPanel.add(panel1);

        //Falta funcion para coger 3 juegos randoms
        //TODO REVISAR ESTO
        if (randomGames.isEmpty()) {
            randomGames = SAAbstractFactory.getInstance().createSAGame().random();
            for (TGame g : randomGames) {
                panel1.add(gamePanel(g));
            }
        }
        midpanel.add(topPanel);
        midpanel.add(Box.createRigidArea(new Dimension(0, 20)));
        midpanel.add(randomPanel);

        return midpanel;
    }

    private JPanel gamePanel(TGame g) {
        JPanel main = new JPanel(new BorderLayout());
        main.setMaximumSize(new Dimension(300, 400));
        main.setMinimumSize(new Dimension(200, 400));
        main.setOpaque(false);

        JLabel coverLabel = new JLabel();
        coverLabel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        coverLabel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        if (g.getCover() != null) {
            Image image = null;
            URL url;
            try {
                url = new URL("https:" + g.getImage());
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
            coverLabel.setIcon(new ImageIcon((Objects.requireNonNull(getClass().getClassLoader().getResource("No_Image_big.png")))));
        }

        JPanel namePanel = new JPanel();
        namePanel.setOpaque(false);

        JTextPane nameTitle = new JTextPane();
        nameTitle.setForeground(Color.white);
        nameTitle.setOpaque(false);
        nameTitle.setFont(new Font("sans-serif", 1, 18));
        nameTitle.setText(g.getName());
        nameTitle.setEditable(false);
        StyledDocument doc = nameTitle.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        namePanel.add(nameTitle);

        main.add(coverLabel, BorderLayout.NORTH);
        main.add(namePanel, BorderLayout.SOUTH);

        return main;
    }

    private void refreshView() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
