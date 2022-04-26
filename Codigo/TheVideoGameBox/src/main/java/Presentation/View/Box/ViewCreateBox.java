package Presentation.View.Box;

import Logic.Box.Genres;
import Logic.Box.Privacy;
import Logic.Box.TBox;
import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Main.JPanelConFondo;
import Presentation.View.Utils.Button;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.bson.types.ObjectId;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static Presentation.View.Main.ViewMain.id_logged;
import static Presentation.View.Utils.Images.backGround;
import static Presentation.View.Utils.Images.logo;

public class ViewCreateBox extends JFrame implements IView {

    private JPanelConFondo mainPanel = new JPanelConFondo();
    private JPanel backButtonContainer;
    private JTextField textFieldName;
    private JTextField textFieldDescription;
    private JCheckBox checkBoxShooter;
    private JCheckBox checkBoxStrategy;
    private JCheckBox checkBoxIndie;
    private JCheckBox checkBoxRPG;
    private JCheckBox checkBoxRacing;
    private JCheckBox checkBoxSandbox;
    private JCheckBox checkBoxHorror;
    private JCheckBox checkBoxSports;
    private JCheckBox checkBoxSurvival;
    private JPanel panelCategories;
    private JPanel midPanel;
    private JComboBox comboBoxPrivacy;
    private ObjectId boxId;

    public ViewCreateBox() {
        Image iconFrame = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(logo))).getImage();
        setIconImage(iconFrame);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().removeAll();
        setPreferredSize(new Dimension(900, 550));
        setLocation(400, 100);
        setBounds(100, 100, 887, 536);
        mainPanel = new JPanelConFondo();
        mainPanel.setBorder(null);
        mainPanel.setImagen(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(backGround))).getImage());
        getContentPane().add(mainPanel);
        mainPanel.setLayout(null);

        midPanel = new JPanel();
        midPanel.setBounds(203, 23, 556, 446);
        midPanel.setOpaque(false);
        mainPanel.add(midPanel);
        midPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Create Box");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Leelawadee", Font.BOLD, 40));
        titleLabel.setBounds(158, 22, 225, 59);
        titleLabel.setForeground(SystemColor.controlHighlight);
        midPanel.add(titleLabel);

        textFieldName = new JTextField();
        textFieldName.setToolTipText("Type new Box name");
        textFieldName.setFont(new Font("Leelawadee", Font.PLAIN, 13));
        textFieldName.setBounds(81, 114, 401, 30);
        midPanel.add(textFieldName);
        textFieldName.setColumns(10);

        JLabel labelName = new JLabel("Name:");
        labelName.setToolTipText("");
        labelName.setForeground(Color.WHITE);
        labelName.setFont(new Font("Leelawadee", Font.BOLD, 15));
        labelName.setAlignmentX(CENTER_ALIGNMENT);
        labelName.setBounds(257, 97, 66, 14);
        midPanel.add(labelName);

        JLabel labelDescription = new JLabel("Description:");
        labelDescription.setToolTipText("");
        labelDescription.setForeground(Color.WHITE);
        labelDescription.setFont(new Font("Leelawadee", Font.BOLD, 15));
        labelDescription.setAlignmentX(0.5f);
        labelDescription.setBounds(238, 162, 97, 14);
        midPanel.add(labelDescription);

        textFieldDescription = new JTextField();
        textFieldDescription.setToolTipText("Type new Box description");
        textFieldDescription.setFont(new Font("Leelawadee", Font.PLAIN, 13));
        textFieldDescription.setColumns(10);
        textFieldDescription.setBounds(81, 180, 401, 30);
        midPanel.add(textFieldDescription);

        JLabel labelCategories = new JLabel("Genres:");
        labelCategories.setToolTipText("Select genres");
        labelCategories.setForeground(Color.WHITE);
        labelCategories.setFont(new Font("Leelawadee", Font.BOLD, 15));
        labelCategories.setAlignmentX(0.5f);
        labelCategories.setBounds(62, 277, 60, 19);
        midPanel.add(labelCategories);


        comboBoxPrivacy = new JComboBox(Privacy.values());
        comboBoxPrivacy.setBackground(UIManager.getColor("Button.light"));
        comboBoxPrivacy.setFont(new Font("Leelawadee", Font.PLAIN, 14));
        comboBoxPrivacy.setBounds(395, 276, 87, 22);
        midPanel.add(comboBoxPrivacy);

        Button btnCreate = new Button("CREATE", new Color(64, 147, 255), new Dimension(120, 30));
        btnCreate.button();
        btnCreate.setBounds(232, 378, 127, 39);
        btnCreate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                createBox();
            }
        });
        midPanel.add(btnCreate);

        addPanelCheckboxes();

        backButtonContainer = new JPanel();
        backButtonContainer.setLayout(null);
        backButtonContainer.setOpaque(false);
        backButtonContainer.setBounds(10, 11, 196, 67);
        addBackButton();
        mainPanel.add(backButtonContainer);

        this.pack();
        refreshView();
    }

    @Override
    public void update(Context context) {
        switch (context.getEvent()) {
            case Event.RES_CREATE_BOX_OK:
                JOptionPane.showMessageDialog(this, "Box created!", "Create Box", JOptionPane.INFORMATION_MESSAGE);
                boxId = (ObjectId) context.getData();
                ApplicationController.getInstance().action(new Context(Event.VIEW, null));
                setVisible(false);
                break;
            case Event.RES_CREATE_BOX_KO:
                JOptionPane.showMessageDialog(null, "Failed to create the Box", "Create Box", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    protected void createBox() {
        //Capturar datos
        String name = textFieldName.getText();
        String description = textFieldDescription.getText();
        List<Genres> categories = getCategories();
        Privacy privacy = getPrivacy();

        TBox box = new TBox(name, description, privacy, categories, id_logged);

        ApplicationController.getInstance().action(new Context(Event.CREATE_BOX, box));
        Pair<ObjectId, ObjectId> aux = new ImmutablePair<>(id_logged, boxId);
        ApplicationController.getInstance().action(new Context(Event.ASSOCIATE_BOX, aux));
    }

    private Privacy getPrivacy() {
        String privacy = comboBoxPrivacy.getSelectedItem().toString();

        if (privacy.equals(Privacy.PRIVATE.toString())) return Privacy.PRIVATE;
        else if (privacy.equals(Privacy.PUBLIC.toString())) return Privacy.PUBLIC;
        else return Privacy.PRIVATE;
    }

    private List<Genres> getCategories() {
        List<Genres> categories = new ArrayList<>();

        if (checkBoxShooter.isSelected()) {
            categories.add(Genres.SHOOTER);
        }
        if (checkBoxStrategy.isSelected()) {
            categories.add(Genres.STRATEGY);
        }
        if (checkBoxIndie.isSelected()) {
            categories.add(Genres.INDIE);
        }
        if (checkBoxRPG.isSelected()) {
            categories.add(Genres.RPG);
        }
        if (checkBoxRacing.isSelected()) {
            categories.add(Genres.RACING);
        }
        if (checkBoxSandbox.isSelected()) {
            categories.add(Genres.SANDBOX);
        }
        if (checkBoxHorror.isSelected()) {
            categories.add(Genres.HORROR);
        }
        if (checkBoxSports.isSelected()) {
            categories.add(Genres.SPORTS);
        }
        if (checkBoxSurvival.isSelected()) {
            categories.add(Genres.SURVIVAL);
        }
        return categories;
    }

    private void addPanelCheckboxes() {
        panelCategories = new JPanel();
        panelCategories.setBounds(120, 221, 215, 143);
        panelCategories.setOpaque(false);
        panelCategories.setLayout(null);
        midPanel.add(panelCategories);

        checkBoxShooter = new JCheckBox("Shooter");
        checkBoxShooter.setOpaque(false);
        checkBoxShooter.setForeground(Color.WHITE);
        checkBoxShooter.setFont(new Font("Leelawadee", Font.BOLD, 13));
        checkBoxShooter.setFocusable(false);
        checkBoxShooter.setBounds(6, 7, 83, 23);
        panelCategories.add(checkBoxShooter);

        checkBoxStrategy = new JCheckBox("Strategy");
        checkBoxStrategy.setOpaque(false);
        checkBoxStrategy.setForeground(Color.WHITE);
        checkBoxStrategy.setFont(new Font("Leelawadee", Font.BOLD, 13));
        checkBoxStrategy.setFocusable(false);
        checkBoxStrategy.setBounds(6, 33, 91, 23);
        panelCategories.add(checkBoxStrategy);

        checkBoxIndie = new JCheckBox("Indie");
        checkBoxIndie.setOpaque(false);
        checkBoxIndie.setForeground(Color.WHITE);
        checkBoxIndie.setFont(new Font("Leelawadee", Font.BOLD, 13));
        checkBoxIndie.setFocusable(false);
        checkBoxIndie.setBounds(6, 59, 83, 23);
        panelCategories.add(checkBoxIndie);

        checkBoxRPG = new JCheckBox("RPG");
        checkBoxRPG.setOpaque(false);
        checkBoxRPG.setForeground(Color.WHITE);
        checkBoxRPG.setFont(new Font("Leelawadee", Font.BOLD, 13));
        checkBoxRPG.setFocusable(false);
        checkBoxRPG.setBounds(6, 85, 83, 23);
        panelCategories.add(checkBoxRPG);

        checkBoxRacing = new JCheckBox("Racing");
        checkBoxRacing.setOpaque(false);
        checkBoxRacing.setForeground(Color.WHITE);
        checkBoxRacing.setFont(new Font("Leelawadee", Font.BOLD, 13));
        checkBoxRacing.setFocusable(false);
        checkBoxRacing.setBounds(6, 111, 83, 23);
        panelCategories.add(checkBoxRacing);

        checkBoxSandbox = new JCheckBox("Sandbox");
        checkBoxSandbox.setHorizontalAlignment(SwingConstants.CENTER);
        checkBoxSandbox.setOpaque(false);
        checkBoxSandbox.setForeground(Color.WHITE);
        checkBoxSandbox.setFont(new Font("Leelawadee", Font.BOLD, 13));
        checkBoxSandbox.setFocusable(false);
        checkBoxSandbox.setBounds(99, 20, 83, 23);
        panelCategories.add(checkBoxSandbox);

        checkBoxHorror = new JCheckBox("Horror");
        checkBoxHorror.setOpaque(false);
        checkBoxHorror.setForeground(Color.WHITE);
        checkBoxHorror.setFont(new Font("Leelawadee", Font.BOLD, 13));
        checkBoxHorror.setFocusable(false);
        checkBoxHorror.setBounds(101, 46, 81, 23);
        panelCategories.add(checkBoxHorror);

        checkBoxSports = new JCheckBox("Sports");
        checkBoxSports.setOpaque(false);
        checkBoxSports.setForeground(Color.WHITE);
        checkBoxSports.setFont(new Font("Leelawadee", Font.BOLD, 13));
        checkBoxSports.setFocusable(false);
        checkBoxSports.setBounds(101, 72, 81, 23);
        panelCategories.add(checkBoxSports);

        checkBoxSurvival = new JCheckBox("Survival");
        checkBoxSurvival.setOpaque(false);
        checkBoxSurvival.setForeground(Color.WHITE);
        checkBoxSurvival.setFont(new Font("Leelawadee", Font.BOLD, 13));
        checkBoxSurvival.setFocusable(false);
        checkBoxSurvival.setBounds(101, 96, 114, 23);
        panelCategories.add(checkBoxSurvival);

        JLabel labelPrivacy = new JLabel("Privacy:");
        labelPrivacy.setToolTipText("Select privacy");
        labelPrivacy.setForeground(Color.WHITE);
        labelPrivacy.setFont(new Font("Leelawadee", Font.BOLD, 15));
        labelPrivacy.setAlignmentX(0.5f);
        labelPrivacy.setBounds(334, 277, 66, 19);
        midPanel.add(labelPrivacy);
    }

    private void addBackButton() {
        Button backButton = new Button(null, "back_icon.png", Color.white, Color.orange);
        backButton.buttonIcon();
        backButton.setBounds(0, 11, 119, 50);
        backButton.setToolTipText("Back to the main window");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().back();
                setVisible(false);
            }
        });
        backButtonContainer.add(backButton);
    }

    private void refreshView() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
