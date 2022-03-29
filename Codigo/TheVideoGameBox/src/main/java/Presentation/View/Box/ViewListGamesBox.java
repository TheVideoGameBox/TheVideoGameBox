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
import com.sun.deploy.panel.ExceptionListDialog;
import org.apache.commons.lang3.tuple.Pair;
import org.bson.types.ObjectId;
import org.jdesktop.xswingx.PromptSupport;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ViewListGamesBox extends JFrame implements IView {
    private List<TGame> games;
    private TBox box;

    public ViewListGamesBox(TBox box) {
        setTitle("List of Games");
        this.box = box;
        init_GUI();
        this.setLocationRelativeTo(null);
    }

    public void init_GUI() {
        this.setPreferredSize(new Dimension(1150, 750));
        this.setLocation(400, 100);

        Image iconFrame = new ImageIcon(getClass().getClassLoader().getResource("caja_definitiva.png")).getImage();
        this.setIconImage(iconFrame);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanelConFondo mainPanel = new JPanelConFondo();
        mainPanel.setImagen(new ImageIcon(getClass().getClassLoader().getResource("fondo_triangular.png")).getImage());
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
        headerContainer.add(Box.createRigidArea(new Dimension(60, 0)));

        // TITLE
        JLabel title = new JLabel("Games of Box: ");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setForeground(Color.white);
        title.setFont(new Font("sans-serif", 1, 20));
        headerContainer.add(title);
        headerContainer.add(Box.createRigidArea(new Dimension(130, 0)));

        // ICONO DE MENU
        JButton icon = new JButton();
        icon.setIcon(new ImageIcon((getClass().getClassLoader().getResource("logo_small_blanco.png"))));
        icon.setToolTipText("Back to main window");
        icon.setBorderPainted(false);
        icon.setOpaque(false);
        icon.setContentAreaFilled(false);
        icon.setFocusPainted(false);
        icon.setAlignmentX(CENTER_ALIGNMENT);
        headerContainer.add(icon);
        headerContainer.add(Box.createRigidArea(new Dimension(120, 0)));

        icon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().action(new Context(Event.VIEW, null));
                dispose();
            }
        });

        JTextField textName = new JTextField();
        PromptSupport.setPrompt("Añadir juego", textName); //Libreria xswingx (se podria hacer con una clase propia sobreescribiendo paint())
        textName.setPreferredSize(new Dimension(200, 30));
        textName.setMinimumSize(new Dimension(200, 30));
        textName.setMaximumSize(new Dimension(200, 30));
        textName.setBorder(null);
        headerContainer.add(textName);
        headerContainer.add(Box.createRigidArea(new Dimension(0, 0)));
        textName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String search = textName.getText();
                if(search.length() <= 50 && search.length() > 0) {

                    Pair<List<TGame>, TBox> aux = new Pair<List<TGame>, TBox>() {
                        @Override
                        public List<TGame> getLeft() {
                            return SAAbstractFactory.getInstance().createSAGame().searchAllByName(search);
                        }

                        @Override
                        public TBox getRight() {
                            return box;
                        }

                        @Override
                        public TBox setValue(TBox value) {
                            return null;
                        }
                    };
                    ApplicationController.getInstance().action(new Context(Event.ADD_GAME_TO_BOX, aux));
                    dispose();
                }
                else {
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

        if(box.getGameList() != null) {
            for (ObjectId gameId : box.getGameList()) {
                try {
                    TGame game = SAAbstractFactory.getInstance().createSAGame().searchOne(gameId);
                    contentContainer.add(gamePanel(game));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                contentContainer.add(Box.createRigidArea(new Dimension(0, 10)));
            }
        }
        this.pack();
        this.setVisible(true);
    }

    private JPanelRound gamePanel(TGame game) throws IOException {

        JPanelRound panel = new JPanelRound(new Color(26, 59, 160), new Color(64, 147, 255));
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
        JLabel name = new JLabel(game.getName());
        name.setForeground(Color.white);
        name.setFont(new Font("Leelawadee", Font.BOLD, 20));
        JLabel cover = new JLabel();
        if(game.getCover() != null) {
            Image image = null;
            URL url = new URL( "https:"+ game.getCover());
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();
            image = ImageIO.read(connection.getInputStream());
            connection.getInputStream().close();
            cover = new JLabel(new ImageIcon(image));
        }
        else {
            cover.setIcon(new ImageIcon((getClass().getClassLoader().getResource("no_image.png"))));
        }

        // CONSTRUIR NAMEPANEL
        namePanel.add(Box.createRigidArea(new Dimension(10, 0)));
        namePanel.add(cover);
        namePanel.add(Box.createRigidArea(new Dimension(55, 0)));
        namePanel.add(name);

        //BUTTON PANEL
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);
        JButton viewInfo = new JButton("View Information");
        viewInfo.setIcon(new ImageIcon((getClass().getClassLoader().getResource("info_icon.png"))));
        viewInfo.setPreferredSize(new Dimension(200, 45));
        viewInfo.setMaximumSize(new Dimension(200, 45));
        viewInfo.setMinimumSize(new Dimension(200, 45));
        viewInfo.setForeground(Color.white);
        viewInfo.setFont(new Font("Leelawadee", Font.BOLD, 15));
        viewInfo.setBorderPainted(false);
        viewInfo.setOpaque(false);
        viewInfo.setContentAreaFilled(false);
        viewInfo.setFocusPainted(false);
        viewInfo.setAlignmentX(CENTER_ALIGNMENT);
        viewInfo.setAlignmentY(CENTER_ALIGNMENT);
        viewInfo.setToolTipText("Games fron the Box");
        viewInfo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().action(new Context(Event.SEARCH_ONE, game.getId()));
                dispose();
            }

        });
        buttonPanel.add(viewInfo, BorderLayout.CENTER);

        //CONSTRUIR PANEL

        panel.add(namePanel, BorderLayout.WEST);
        panel.add(buttonPanel, BorderLayout.EAST);

        return panel;
    }

    @Override
    public void update(Context context) {

    }
}
