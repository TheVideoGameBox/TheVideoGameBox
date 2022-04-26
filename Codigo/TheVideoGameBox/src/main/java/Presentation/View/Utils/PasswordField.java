package Presentation.View.Utils;

import org.jdesktop.xswingx.PromptSupport;

import javax.swing.*;
import java.awt.*;

public class PasswordField extends JPasswordField {

    Dimension dimension;
    String prompt = null;

    public PasswordField(Dimension dimension, String prompt) {
        this.dimension = dimension;
        this.prompt = prompt;
    }

    public PasswordField(Dimension dimension) {
        this.dimension = dimension;
    }

    public void textField() {
        PromptSupport.setPrompt(prompt, this);
        this.setPreferredSize(dimension);
        this.setMinimumSize(dimension);
        this.setMaximumSize(dimension);
        this.setBorder(null);
    }

}
