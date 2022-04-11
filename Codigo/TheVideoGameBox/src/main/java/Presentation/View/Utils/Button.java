package Presentation.View.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Button extends JButton{

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
    
    public Button(String name, String icon, Dimension dimension) {
        this.name = name;
        this.icon = icon;
        this.dimension = dimension;
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

    public Button(String name, String icon){
        this.name = name;
        this.icon = icon;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
    }

    public Button(String name){
        this.name = name;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
    }

    public void button(){
    	this.setText(name);
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setMinimumSize(dimension);
        this.setBackground(backgroundColor);
        this.setForeground(foregroundColor);
        this.setFont(new Font("Leelawadee", Font.BOLD, 15));
        this.setFocusPainted(false);
        this.setBorder(null);
    }

    public void buttonIcon(){
        this.setText(name);
        this.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(icon))));
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setMinimumSize(dimension);
        this.setBackground(backgroundColor);
        this.setForeground(foregroundColor);
        this.setFont(new Font("Leelawadee", Font.BOLD, 15));
        this.setFocusPainted(false);
        this.setBorder(null);
    }


}
