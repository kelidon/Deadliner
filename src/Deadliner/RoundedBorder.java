package Deadliner;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class RoundedBorder implements Border {

    RoundedBorder(int radius, int width, Color buttonColor) {
        this.radius = radius;
        this.width = width;
        this.buttonColor = buttonColor;
        this.borderColor = Color.lightGray;
    }
    RoundedBorder(int radius, int width, Color buttonColor, Color borderColor) {
        this.radius = radius;
        this.width = width;
        this.buttonColor = buttonColor;
        this.borderColor = borderColor;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D gr = (Graphics2D)g;
        gr.clearRect(x, y, width, height);
        gr.setStroke(new BasicStroke(this.width));
        gr.setColor(borderColor);
        gr.drawRoundRect(x, y+1, width-1, height-3, radius, radius);
        gr.setColor(buttonColor);
        gr.fillRoundRect(x, y+1, width-1, height-3, radius, radius);
    }

    private int radius;
    private int width;
    private Color
            buttonColor,
            borderColor;
}
