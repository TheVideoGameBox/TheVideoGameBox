package Presentation.View.Utils;

import org.jdesktop.xswingx.PromptSupport;

import javax.swing.*;
import java.awt.*;

public class TextField {

    Dimension dimension;
    String prompt;

    public TextField(Dimension dimension, String prompt){
        this.dimension = dimension;
        this.prompt = prompt;
    }

    public JTextField textField(){
        JTextField aux = new JTextField();
        PromptSupport.setPrompt(prompt, aux);
        aux.setPreferredSize(dimension);
        aux.setMinimumSize(dimension);
        aux.setMaximumSize(dimension);
        aux.setBorder(null);

        return aux;
    }

}
