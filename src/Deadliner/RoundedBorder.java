package Deadliner;

import javax.swing.border.Border;
import java.awt.*;

public class RoundedBorder implements Border {

    RoundedBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D gr = (Graphics2D)g;
        gr.setStroke(new BasicStroke(2));
        gr.setColor((Color.GRAY).darker());
        gr.drawRoundRect(x, y, width-1, height-2, radius, radius);
    }

    private int radius;
}
