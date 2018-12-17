package Deadliner;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class RoundedBorder implements Border {

    RoundedBorder(int radius, int width) {
        this.radius = radius;
        this.width = width;
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D gr = (Graphics2D)g;
        //var roundRect = new RoundRectangle2D.Double(x, y, width-1, height-2, radius, radius);
        gr.setStroke(new BasicStroke(this.width));
        gr.setColor((Color.GRAY).darker());
        //var rect = new Rectangle(x, y, width-1, height-2);
        //var borderRegion = new Area(rect);
        //var area = new Area(roundRect);
        //borderRegion.subtract(area);
        //gr.setClip(borderRegion);
        gr.drawRoundRect(x, y+1, width-1, height-3, radius, radius);
        //gr.setClip(null);
    }

    private int radius;
    private int width;
}
