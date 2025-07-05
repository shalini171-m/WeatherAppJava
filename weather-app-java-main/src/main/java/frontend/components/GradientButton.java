package main.java.frontend.components;

import javax.swing.*;
import java.awt.*;

public class GradientButton extends JButton {
    private GradientGenerator gradientGenerator;

    public GradientButton(GradientGenerator gradientGenerator, ImageIcon imageIcon)
    {
        super(imageIcon);
        this.gradientGenerator = gradientGenerator;
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        BorderRounder.roundComponent(this, g);
        gradientGenerator.paintComponent(this, g);
        super.paintComponent(g);
    }

    public Object getGradientGenerator() {
        return gradientGenerator;
    }
}
