package Presentation.View.User;

import Logic.Box.TBox;
import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Main.JPanelConFondo;
import Presentation.View.Main.JPanelRound;
import Presentation.View.Utils.Button;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.bson.types.ObjectId;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static Presentation.View.Main.ViewMain.id_logged;
import static Presentation.View.Main.ViewMain.viewOptions;
import static Presentation.View.Utils.Images.backGround;
import static Presentation.View.Utils.Images.logo;

public class ViewUserBoxes extends JFrame implements IView {
    private List<TBox> boxes;
    private JPanel contentContainer;

    @Override
    public void update(Context context) {
        if (context.getEvent() == Event.RES_USER_BOXES_OK) {
            this.boxes = (List<TBox>) context.getData();
            viewOptions = true;
            init_GUI();
        } else if (context.getEvent() == Event.RES_UPDATE_USER_BOX_LIST_OK) {
            this.boxes = (List<TBox>) context.getData();
            refreshBoxes();
        }
        refreshView();
    }

    private void init_GUI() {
        setTitle("Your Boxes");
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

        // BACK BUTTON
        Presentation.View.Utils.Button backButton = new Presentation.View.Utils.Button(null, "back_icon.png", Color.white, Color.orange);
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
        JLabel title = new JLabel("Your Boxes");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 25));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setForeground(Color.white);
        title.setFont(new Font("sans-serif", 1, 20));
        headerContainer.add(title);
        headerContainer.add(Box.createRigidArea(new Dimension(50, 0)));

        // ICONO DE MENU
        Presentation.View.Utils.Button icon = new Presentation.View.Utils.Button(null, "logo_small_blanco.png", new Dimension(500, 80));
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

        JPanel boxesPanel = new JPanel();
        boxesPanel.setLayout(new BoxLayout(boxesPanel, BoxLayout.Y_AXIS));
        boxesPanel.setAlignmentX(CENTER_ALIGNMENT);
        boxesPanel.setOpaque(false);
        for (TBox box : boxes) {
            try {
                if (box.isActive()) boxesPanel.add(boxPanel(box));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            boxesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }


        contentContainer.add(boxesPanel);
    }

    private Component boxPanel(TBox box) throws IOException {

        JPanelRound panel = new JPanelRound(new Color(26, 59, 160), new Color(64, 147, 255), new Color(26, 59, 160));
        panel.setLayout(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 20));
        panel.setMaximumSize(new Dimension(1000, 135));
        panel.setPreferredSize(new Dimension(1000, 135));
        panel.setMinimumSize(new Dimension(1000, 135));

        JPanel namePanel = new JPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.setOpaque(false);
        namePanel.setMaximumSize(new Dimension(610, 135));
        namePanel.setPreferredSize(new Dimension(610, 135));
        namePanel.setMinimumSize(new Dimension(610, 135));

        // NAME
        JLabel name = new JLabel(box.getName());
        name.setForeground(Color.white);
        name.setFont(new Font("Leelawadee", Font.BOLD, 20));

        // CONSTRUIR NAMEPANEL
        namePanel.add(Box.createRigidArea(new Dimension(65, 0)));
        namePanel.add(name);

        //BUTTON PANEL
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setOpaque(false);
        Button viewGames = new Button("Games", "info_icon.png", Color.white, new Color(50, 170, 0), new Dimension(120, 45), Color.orange);
        viewGames.buttonIcon();
        viewGames.setBorderPainted(false);
        viewGames.setContentAreaFilled(false);
        viewGames.setToolTipText("View Box's games");
        viewGames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().action(new Context(Event.LIST_GAMES_OF_BOX, box));
                setVisible(false);
            }
        });
        buttonPanel.add(viewGames);
        //buttonPanel.add(Box.createRigidArea(new Dimension(20, 0)));


        Button viewAttributes = new Button("Details", "info_icon.png", Color.white, new Color(50, 170, 0), new Dimension(120, 45), Color.orange);
        viewAttributes.buttonIcon();
        viewAttributes.setBorderPainted(false);
        viewAttributes.setContentAreaFilled(false);
        viewAttributes.setToolTipText("View Box's attributes");
        viewAttributes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().action(new Context(Event.SHOW_BOX, box));
                setVisible(false);
            }
        });
        buttonPanel.add(viewAttributes, BorderLayout.EAST);

        JPanel deletePanel = new JPanel(new BorderLayout());
        deletePanel.setOpaque(false);
        Button deleteButton = new Button(null, "delete_icon.png", new Dimension(100, 45), Color.orange);
        deleteButton.buttonIcon();
        deleteButton.setVisible(viewOptions);
        deleteButton.setBorderPainted(false);
        deleteButton.setContentAreaFilled(false);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pair<ObjectId, ObjectId> aux = new ImmutablePair<>(id_logged, box.getId());
                ApplicationController.getInstance().action(new Context(Event.DELETE_BOX, box.getId()));
                ApplicationController.getInstance().action(new Context(Event.DELETE_BOX_FROM_USER, aux));
                setVisible(false);
                ApplicationController.getInstance().action(new Context(Event.UPDATE_USER_BOX_LIST, id_logged));
            }
        });
        deletePanel.add(deleteButton);

        //CONSTRUIR PANEL

        panel.add(namePanel, BorderLayout.WEST);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(deletePanel, BorderLayout.EAST);

        return panel;
    }

    private void refreshView() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void refreshBoxes() {
        contentContainer.remove(contentContainer.getComponentCount() - 1);

        JPanel boxesPanel = new JPanel();
        boxesPanel.setLayout(new BoxLayout(boxesPanel, BoxLayout.Y_AXIS));
        boxesPanel.setAlignmentX(CENTER_ALIGNMENT);
        boxesPanel.setOpaque(false);
        for (TBox box : boxes) {
            try {
                if (box.isActive()) boxesPanel.add(boxPanel(box));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            boxesPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }


        contentContainer.add(boxesPanel);
    }
}
