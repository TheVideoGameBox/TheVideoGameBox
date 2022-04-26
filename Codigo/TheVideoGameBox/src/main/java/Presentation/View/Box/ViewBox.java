package Presentation.View.Box;

import Logic.Box.TBox;
import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Main.BorderTitle;
import Presentation.View.Main.JPanelConFondo;
import Presentation.View.Main.JPanelRound;
import Presentation.View.Utils.TextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

import static Presentation.View.Utils.Images.backGround;
import static Presentation.View.Utils.Images.logo;

public class ViewBox extends JFrame implements IView {

    private static final long serialVersionUID = 1L;
    TBox box;

    public ViewBox(TBox box) {
        super();
        this.box = box;
        initGUI(box);
    }


    private void initGUI(TBox box) {

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
        this.setResizable(true);
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private JPanel createTopPanel() {

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(1150, 100));
        topPanel.setMaximumSize(new Dimension(1150, 100));
        topPanel.setMinimumSize(new Dimension(1150, 100));
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        //ICONO DE LA APLICACION
        JLabel icon = new JLabel();
        icon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("logo_small_blanco.png")));
        topPanel.add(Box.createRigidArea(new Dimension(110, 0)));
        topPanel.add(icon);

        return topPanel;
    }

    private JPanel createMidPanel() {
        JPanel midPanel = new JPanel();
        midPanel.setPreferredSize(new Dimension(1150, 600));
        midPanel.setMaximumSize(new Dimension(1150, 600));
        midPanel.setMinimumSize(new Dimension(1150, 600));
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
        midPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        midPanel.setBackground(new Color(64, 147, 255));
        midPanel.setBorder(new BorderTitle("Info about box", Color.black));

        // Nombre de box
        JLabel boxNameLabel = new JLabel("nombre box");        // Aqui es box.getName();
        boxNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        //boxNameLabel.setAlignmentX(CENTER_ALIGNMENT);
        boxNameLabel.setFont(new Font("Leelawadee", Font.BOLD, 20));
        boxNameLabel.setForeground(Color.WHITE);

        midPanel.add(boxNameLabel);
        midPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        midPanel.add(createAddGamePanel());

        return midPanel;
    }

    private JPanel createAddGamePanel() {
        JPanel addPanel = new JPanelRound(new Color(26, 59, 160), new Color(64, 147, 255), new Color(26, 59, 160));
        addPanel.setMaximumSize(new Dimension(1150, 70));
        addPanel.setMinimumSize(new Dimension(1150, 70));
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
        // En esta HU (Añadir un juego a una box), solamente va a haber un botón para añadir juegos. El resto de la vista
        // de la box se realizará en Ver juegos de la box.

        //ADD GAME
        // ADD GAME LABEL
        JLabel addGameLabel = new JLabel("Search here to add a new game to the box");
        addGameLabel.setFont(new Font("ComicSans", Font.ITALIC, 15));
        addGameLabel.setForeground(Color.WHITE);
        //addPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        addPanel.add(addGameLabel);


        // Input para introducir el nombre del juego que se desea añadir
        JPanel textButtonPanel = new JPanel();
        //textButtonPanel.setMinimumSize(new Dimension(750, 150));
        textButtonPanel.setLayout(new BoxLayout(textButtonPanel, BoxLayout.X_AXIS));
        textButtonPanel.setOpaque(false);

        TextField nameGame = new TextField(new Dimension(200, 30), "Name of Game");
        nameGame.textField();
        nameGame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nameGame.setText(null);
                nameGame.setForeground(Color.DARK_GRAY);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        addPanel.add(textButtonPanel);
        textButtonPanel.add(Box.createRigidArea(new Dimension(15, 10)));
        textButtonPanel.add(nameGame);

        // ADD GAME BUTTON
        JButton addGameButton = new JButton();
        addGameButton.setPreferredSize(new Dimension(40, 40));
        addGameButton.setMaximumSize(new Dimension(40, 40));
        addGameButton.setMinimumSize(new Dimension(40, 40));
        addGameButton.setIcon(new ImageIcon((Objects.requireNonNull(getClass().getClassLoader().getResource("search_icon.png")))));
        addGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Llamamos al evento de search all by name, pues la vista va a ser la misma, pero cambiará que al hacer click
                    // en un juego, en lugar de mostrar su info, se añadirá a la box.
                    String name = nameGame.getText();
                    ApplicationController.getInstance().action(new Context(Event.SEARCH_ALL_BY_NAME, name));
                    ;
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Incorrect name format");
                }
            }
        });
        textButtonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        textButtonPanel.add(addGameButton);

        return addPanel;
    }

    @Override
    public void update(Context context) {
        // TODO Auto-generated method stub

    }

}
