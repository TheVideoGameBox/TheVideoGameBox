package Presentation.View.Main;

import javax.swing.*;
import java.awt.*;

public class JPanelConFondo extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Image imagen;

    public void setImagen(Image nuevaImagen) {
        imagen = nuevaImagen;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(),
                    this);

            setOpaque(false);
        } else {
            setOpaque(true);
        }

        super.paint(g);
    }

}
