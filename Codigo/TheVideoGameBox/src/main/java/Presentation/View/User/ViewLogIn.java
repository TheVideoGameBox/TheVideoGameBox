package Presentation.View.User;

import Logic.User.TUser;
import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Main.JPanelConFondo;
import Presentation.View.Utils.Button;
import Presentation.View.Utils.PasswordField;
import Presentation.View.Utils.TextField;
import org.bson.types.ObjectId;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static Presentation.View.Main.ViewMain.id_logged;
import static Presentation.View.Main.ViewMain.logged;
import static Presentation.View.Utils.Images.backGround;
import static Presentation.View.Utils.Images.logo;

public class ViewLogIn extends JFrame implements IView {

    public ViewLogIn() {
        setTitle("Log In");
        init_GUI();
        refreshView();
    }

    @Override
    public void update(Context context) {
        switch (context.getEvent()) {
            case Event.RES_LOGIN_USER_OK:
                JOptionPane.showMessageDialog(this, "Logged In!", "Log In", JOptionPane.INFORMATION_MESSAGE);
                logged = true;
                id_logged = (ObjectId) context.getData();
                setVisible(false);
                ApplicationController.getInstance().action(new Context(Event.VIEW, null));
                break;
            case Event.RES_LOGIN_USER_KO:
                JOptionPane.showMessageDialog(null, "Incorrect Email/Password", "Log In", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    private void init_GUI() {
        this.setPreferredSize(new Dimension(600, 325));
        Image iconFrame = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(logo))).getImage();
        this.setIconImage(iconFrame);

        JPanelConFondo mainPanel = new JPanelConFondo();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setImagen(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(backGround))).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(mainPanel);

        JPanel auxPanel = createAuxPanel();
        mainPanel.add(auxPanel, BorderLayout.NORTH);
    }

    private JPanel createAuxPanel() {
        JPanel auxPanel = new JPanel();
        auxPanel.setLayout(new BoxLayout(auxPanel, BoxLayout.Y_AXIS));
        auxPanel.setOpaque(false);

        JPanel backPanel = new JPanel();
        backPanel.setLayout(new BoxLayout(backPanel, BoxLayout.X_AXIS));
        backPanel.setOpaque(false);

        Button backButton = new Button(null, "back_icon.png", Color.white, Color.orange);
        backButton.buttonIcon();
        backButton.setAlignmentX(RIGHT_ALIGNMENT);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().back();
                setVisible(false);
            }
        });

        backPanel.add(backButton);
        backPanel.add(Box.createRigidArea(new Dimension(540, 0)));

        auxPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        auxPanel.add(backPanel, LEFT_ALIGNMENT);

        // EMAIL LABEL
        auxPanel.add(label("Email"));

        // Input para el email
        TextField emailUser = textField();
        auxPanel.add(emailUser);
        auxPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // PASSWORD LABEL
        auxPanel.add(label("Password"));

        // Input para la password
        PasswordField passwordUser = passwordField();
        passwordUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailUser.getText();
                String password = String.valueOf(passwordUser.getPassword());

                TUser user = new TUser(email, password);
                ApplicationController.getInstance().action(new Context(Event.LOGIN_USER, user));
            }
        });

        auxPanel.add(passwordUser);
        auxPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        Button logInButton = new Button("Log In", new Color(50, 170, 0), new Dimension(80, 40));
        logInButton.button();
        logInButton.setAlignmentX(CENTER_ALIGNMENT);

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailUser.getText();
                String password = String.valueOf(passwordUser.getPassword());

                TUser user = new TUser(email, password);
                ApplicationController.getInstance().action(new Context(Event.LOGIN_USER, user));
            }
        });

        auxPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        auxPanel.add(logInButton);
        return auxPanel;
    }

    private JLabel label(String name) {
        JLabel aux = new JLabel(name);
        aux.setFont(new Font("Leelawadee", Font.BOLD, 18));
        aux.setForeground(Color.WHITE);
        aux.setAlignmentX(CENTER_ALIGNMENT);
        return aux;
    }

    private TextField textField() {
        TextField aux = new TextField(new Dimension(350, 30));
        aux.textField();
        aux.setAlignmentX(CENTER_ALIGNMENT);
        return aux;
    }

    private PasswordField passwordField() {
        PasswordField aux = new PasswordField(new Dimension(350, 30));
        aux.textField();
        aux.setAlignmentX(CENTER_ALIGNMENT);
        return aux;
    }

    private void refreshView() {
        pack();
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
