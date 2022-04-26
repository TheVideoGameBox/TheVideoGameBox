package Presentation.View.Utils;

import Presentation.View.Main.JPanelRound;

import javax.swing.*;
import java.awt.*;

public class Platform extends JPanelRound {

    private String p;
    private Color f;

    public Platform(String p, Color b, Color f) {
        super(b, b, b);
        this.p = p;
        this.f = f;
        //setPreferredSize(new Dimension(70, 30));
        setMaximumSize(new Dimension(80, 30));
        setMinimumSize(new Dimension(50, 30));
        platformType();
    }

    private void platformType() {
        JLabel plat = new JLabel();
        plat.setForeground(f);
        plat.setText(p);
        if (p.contains("Commodore")) {
            plat.setText("Commodore");
        } else if (p.contains("Sega")) {
            plat.setText("SEGA");
        } else if (p.contains("Nintendo")) {
            if (p.contains("Switch")) plat.setText("NS");
            else if (p.contains("DSi")) plat.setText("DSi");
            else if (p.contains("Virtual Console")) plat.setText("VC");
            else if (p == "Super Nintendo Entertainment System") plat.setText("SNES");
            else if (p.contains("New Nintendo 3DS")) plat.setText("N3DS");
            else if (p.contains("Entertainment System")) plat.setText("NES");
            else if (p.contains("3DS")) plat.setText("3DS");
            else if (p.contains("GameCube")) plat.setText("NGC");
            else if (p.contains("DS")) plat.setText("DS");
            else if (p.contains("64")) plat.setText("N64");
        } else if (p.contains("PlayStation")) {
            if (p.contains("2")) plat.setText("PS2");
            else if (p.contains("VR2")) plat.setText("PSVR2");
            else if (p.contains("3")) plat.setText("PS3");
            else if (p.contains("Vita")) plat.setText("PSVita");
            else if (p.contains("4")) plat.setText("PS4");
            else if (p.contains("5")) plat.setText("PS5");
            else if (p.contains("VR")) plat.setText("PSVR");
            else plat.setText("PS");
        } else if (p.contains("iOS")) {
            plat.setText("iOS");
        } else if (p.contains("PC")) {
            plat.setText("PC");
        } else if (p.contains("Philips")) {
            plat.setText("PC");
        } else if (p.contains("Xbox")) {
            if (p.contains("360")) plat.setText("Xbox360");
            else if (p.contains("One")) plat.setText("XboxOne");
            plat.setText("Xbox");
        } else if (p.contains("Game Boy")) {
            plat.setText("GameBoy");
        } else if (p.contains("Neo")) {
            plat.setText("NEO");
        } else if (p.contains("Wii")) {
            plat.setText("Wii");
        } else if (p.contains("Oculus")) {
            plat.setText("Oculus");
        } else if (p.contains("VR")) {
            plat.setText("VR");
        } else if (p.contains("Google")) {
            plat.setText("Google");
        } else if (p == "+4") {
            plat.setText("+ 4");
        }


        add(plat);
    }

}
