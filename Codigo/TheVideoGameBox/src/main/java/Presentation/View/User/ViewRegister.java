package Presentation.View.User;

import Logic.User.TUser;
import Presentation.Controller.ApplicationController;
import Presentation.Controller.Context;
import Presentation.Controller.Event;
import Presentation.View.IView;
import Presentation.View.Main.BorderTitle;
import Presentation.View.Main.JPanelConFondo;
import Presentation.View.Main.JPanelRound;
import Presentation.View.Utils.Button;
import Presentation.View.Utils.TextField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import static Presentation.View.Utils.Images.backGround;
import static Presentation.View.Utils.Images.logo;

public class ViewRegister extends JFrame implements IView {

    public ViewRegister() {
        setTitle("Register user");
        init_GUI();
        refreshView();
    }

    @Override
    public void update(Context context) {
        switch (context.getEvent()) {
            case Event.RES_CREATE_USER_OK:
                JOptionPane.showMessageDialog(this, "Signed Up!", "Sign Up", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                ApplicationController.getInstance().action(new Context(Event.VIEW, null));
                break;
            case Event.RES_CREATE_USER_KO:
                JOptionPane.showMessageDialog(null, "Failed to Sign Up", "Sign Up", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    private void init_GUI() {
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

        // BACK BUTTON
        Button backButton = new Button(null, "back_icon.png");
        backButton.buttonIcon();
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setBounds(0, 11, 119, 50);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ApplicationController.getInstance().back();
                setVisible(false);
            }
        });

        // ICONO DE MENU
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
		topPanel.add(backButton);
        topPanel.add(Box.createRigidArea(new Dimension(175, 0)));
        topPanel.add(icon);

        return topPanel;
    }

    private JPanel createMidPanel() {
        JPanel midPanel = new JPanel();
        midPanel.setPreferredSize(new Dimension(1150, 600));
        midPanel.setMaximumSize(new Dimension(1150, 600));
        midPanel.setMinimumSize(new Dimension(1150, 600));
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.Y_AXIS));
        midPanel.setOpaque(false);

        // Create account label
        JLabel createLabel = new JLabel("Create your account");
        createLabel.setHorizontalAlignment((SwingConstants.LEFT));
        createLabel.setFont(new Font("Leelawadee", Font.BOLD, 30));
        createLabel.setForeground(Color.WHITE);
        createLabel.setAlignmentX(CENTER_ALIGNMENT);

        midPanel.add(createLabel);
        midPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        midPanel.add(registerPanel());

        return midPanel;
    }

    private JPanel registerPanel() {
        JPanel auxPanel = new JPanel();
        auxPanel.setMaximumSize(new Dimension(1150, 550));
        auxPanel.setMinimumSize(new Dimension(1150, 550));
        auxPanel.setLayout(new BoxLayout(auxPanel, BoxLayout.Y_AXIS));
        auxPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        auxPanel.setOpaque(false);

        // EMAIL LABEL
        JLabel emailLabel = new JLabel("Email Adress");
        emailLabel.setFont(new Font("Leelawadee", Font.BOLD, 20));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setAlignmentX(CENTER_ALIGNMENT);
        auxPanel.add(emailLabel);

        // Input para el email
        TextField emailUser = new TextField(new Dimension(500, 40));
        emailUser.setAlignmentX(CENTER_ALIGNMENT);
        emailUser.textField();
        auxPanel.add(emailUser);
        auxPanel.add(Box.createRigidArea(new Dimension(50, 15)));

        // USERNAME LABEL
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Leelawadee", Font.BOLD, 20));
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setAlignmentX(CENTER_ALIGNMENT);
        auxPanel.add(usernameLabel);

        // Input para el username

        TextField usernameUser = new TextField(new Dimension(500, 40));
        usernameUser.textField();
        usernameUser.setAlignmentX(CENTER_ALIGNMENT);
        auxPanel.add(usernameUser);
        auxPanel.add(Box.createRigidArea(new Dimension(50, 15)));

        // PASSWORD LABEL
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Leelawadee", Font.BOLD, 20));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setAlignmentX(CENTER_ALIGNMENT);
        auxPanel.add(passwordLabel);

        // Input para la password
        TextField passwordUser = new TextField(new Dimension(500, 40));
        passwordUser.textField();
        passwordUser.setAlignmentX(CENTER_ALIGNMENT);
        auxPanel.add(passwordUser);
        auxPanel.add(Box.createRigidArea(new Dimension(50, 15)));

        // CONFIRM PASSWORD LABEL
        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setFont(new Font("Leelawadee", Font.BOLD, 20));
        confirmPasswordLabel.setForeground(Color.WHITE);
        confirmPasswordLabel.setAlignmentX(CENTER_ALIGNMENT);
        auxPanel.add(confirmPasswordLabel);

        // Input para la confirm password
        TextField confirmPasswordUser = new TextField(new Dimension(500, 40));
        confirmPasswordUser.textField();
        confirmPasswordLabel.setAlignmentX(CENTER_ALIGNMENT);
        auxPanel.add(confirmPasswordUser);
        auxPanel.add(Box.createRigidArea(new Dimension(50, 15)));

        // SIGN UP BUTTON
        Button signUpButton = new Button("Sign Up", new Color(50, 170, 0), new Dimension(80, 40));
        signUpButton.button();
        signUpButton.setAlignmentX(CENTER_ALIGNMENT);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailUser.getText();
                String username = usernameUser.getText();
                String password = passwordUser.getText();
                String confirmPassword = confirmPasswordUser.getText();

                if (password.equals(confirmPassword)) {
                    TUser user = new TUser(email, username, password);
                    ApplicationController.getInstance().action(new Context(Event.CREATE_USER, user));
                } else JOptionPane.showMessageDialog(null, "Incorrect password");
            }
        });

        auxPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        auxPanel.add(signUpButton);

        return auxPanel;
    }

    private void refreshView(){
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
}
