package Presentation.View.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Button {

    String name;
    String icon;
    Color foregroundColor = Color.white;        //Color por defecto de TODOS los botones
    Color backgroundColor;
    Dimension dimension = new Dimension(120, 50);       //Dimension por defecto de la vista principal

    public Button(String name, String icon, Color foregroundColor, Color backgroundColor, Dimension dimension) {
        this.name = name;
        this.icon = icon;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        this.dimension = dimension;
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

    public Button(String name, Color backgroundColor) {
        this.name = name;
        this.backgroundColor = backgroundColor;
    }

    public Button(String name, Color backgroundColor, Dimension dimension) {
        this.name = name;
        this.backgroundColor = backgroundColor;
        this.dimension = dimension;
    }

    public Button(String name, String icon){
        this.name = name;
        this.icon = icon;
    }

    public Button(String name){
        this.name = name;
    }

    public JButton button(){
        JButton button = new JButton(name);
        button.setPreferredSize(dimension);
        button.setMaximumSize(dimension);
        button.setMinimumSize(dimension);
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFont(new Font("Leelawadee", Font.BOLD, 15));
        //button.setBorderPainted(false);
        //button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(null);

        return button;
    }

    public JButton buttonIcon(){
        JButton button = new JButton(name);
        button.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(icon))));
        button.setPreferredSize(dimension);
        button.setMaximumSize(dimension);
        button.setMinimumSize(dimension);
        button.setBackground(backgroundColor);
        button.setForeground(foregroundColor);
        button.setFont(new Font("Leelawadee", Font.BOLD, 15));
        //button.setBorderPainted(false);
        //button.setContentAreaFilled(false);
        button.setFocusPainted(false);

        button.setBorder(null);

        return button;
    }


}
