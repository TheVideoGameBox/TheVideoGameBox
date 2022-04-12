package Presentation.View.Utils;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Button extends JButton {

    String name;
    String icon;
    Color foregroundColor = Color.white;        //Color por defecto de TODOS los botones
    Color backgroundColor;
    Dimension dimension = new Dimension(120, 50);       //Dimension por defecto de la vista principal
    Color pressColor = Color.white;
    Boolean press = true;

    public Button(String name, String icon, Color foregroundColor, Color backgroundColor, Dimension dimension) {
        this.name = name;
        this.icon = icon;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        this.dimension = dimension;
    }
<<<<<<< HEAD
    
    public Button(String name, String icon, Color foregroundColor, Color backgroundColor, Dimension dimension, Boolean press) {
        this.name = name;
        this.icon = icon;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        this.dimension = dimension;
        this.press = press;
    }
    
=======

>>>>>>> branch 'refactorizacionVistas' of https://github.com/TheVideoGameBox/TheVideoGameBox.git
    public Button(String name, String icon, Color foregroundColor, Color backgroundColor, Dimension dimension, Color pressColor) {
        this.name = name;
        this.icon = icon;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        this.dimension = dimension;
        this.pressColor = pressColor;
    }

    public Button(String name, String icon, Color backgroundColor, Dimension dimension) {
        this.name = name;
        this.icon = icon;
        this.backgroundColor = backgroundColor;
        this.dimension = dimension;
    }

    public Button(String name, String icon, Color backgroundColor) {
        this.name = name;
        this.icon = icon;
        this.backgroundColor = backgroundColor;
    }

    public Button(String name, String icon, Dimension dimension) {
        this.name = name;
        this.icon = icon;
        this.dimension = dimension;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
    }
<<<<<<< HEAD
    
    public Button(String name, String icon, Dimension dimension, Boolean press) {
        this.name = name;
        this.icon = icon;
        this.dimension = dimension;
        this.press = press;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
    }
    
=======

>>>>>>> branch 'refactorizacionVistas' of https://github.com/TheVideoGameBox/TheVideoGameBox.git
    public Button(String name, String icon, Dimension dimension, Color pressColor) {
        this.name = name;
        this.icon = icon;
        this.dimension = dimension;
        this.pressColor = pressColor;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
    }

    public Button(String name, Color backgroundColor) {
        this.name = name;
        this.backgroundColor = backgroundColor;
    }


    public Button(String name, Color backgroundColor, Dimension dimension) {
        this.name = name;
        this.backgroundColor = backgroundColor;
        this.dimension = dimension;
    }

    public Button(String name, String icon, Color foregroundColor, Color pressColor) {
        this.name = name;
        this.icon = icon;
        this.pressColor = pressColor;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
    }

    public Button(String name, Color foregroundColor, Color pressColor) {
        this.name = name;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
    }

    public void button() {
        setText(name);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setBackground(backgroundColor);
        setForeground(foregroundColor);
        setFont(new Font("Leelawadee", Font.BOLD, 15));
        setFocusPainted(false);
        setBorder(null);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                setForeground(pressColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(foregroundColor);
            }
        });
    }

    public void buttonIcon() {
        setText(name);
        setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(icon))));
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setBackground(backgroundColor);
        setForeground(foregroundColor);
        setFont(new Font("Leelawadee", Font.BOLD, 15));
        setFocusPainted(false);
        setBorder(null);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
<<<<<<< HEAD
            	setForeground(pressColor);
            	String iconOrange = icon.replace("_", "orange_");
            	if(press) setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(iconOrange))));
=======
                setForeground(pressColor);
                String iconHover = icon.substring(0, icon.length() - 4) + "_hover.png";
                try {
                    setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(iconHover))));
                } catch (NullPointerException e) {
                }
>>>>>>> branch 'refactorizacionVistas' of https://github.com/TheVideoGameBox/TheVideoGameBox.git
            }

            @Override
            public void mouseExited(MouseEvent e) {
<<<<<<< HEAD
            	setForeground(foregroundColor);
            	if(press) setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(icon))));
=======
                setForeground(foregroundColor);
                setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(icon))));
>>>>>>> branch 'refactorizacionVistas' of https://github.com/TheVideoGameBox/TheVideoGameBox.git
            }
        });
    }


}
