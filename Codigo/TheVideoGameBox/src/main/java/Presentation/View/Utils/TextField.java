package Presentation.View.Utils;

import org.jdesktop.xswingx.PromptSupport;

import javax.swing.*;
import java.awt.*;

public class TextField extends JTextField {

    Dimension dimension;
    String prompt = null;

    public TextField(Dimension dimension, String prompt) {
        this.dimension = dimension;
        this.prompt = prompt;
    }

    public TextField(Dimension dimension) {
        this.dimension = dimension;
    }

    public void textField() {
        PromptSupport.setPrompt(prompt, this);
        this.setPreferredSize(dimension);
        this.setMinimumSize(dimension);
        this.setMaximumSize(dimension);
        this.setBorder(null);

    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
        PromptSupport.setPrompt(prompt, this);
    }

}
