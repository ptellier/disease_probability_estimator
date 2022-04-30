package ui.muiswing;

import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.Line2D;

public class DashedBottomBorder extends AbstractBorder {
    private Color borderColour;
    private int gap;

    public DashedBottomBorder(Color colour, int g) {
        borderColour = colour;
        gap = g;
    }

//    @Override
//    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
//        super.paintBorder(c, g, x, y, width, height);
//        Graphics2D g2d = null;
//        if (g instanceof Graphics2D) {
//            g2d = (Graphics2D) g;
//            g2d.setColor(borderColour);
//            // Bottom Border
//            g2d.draw(new Line2D.Double( (double)x, (double)(y + height), (double)(x+ width), (double)(y + height)));
//
//            // Lower Right Border
//            g2d.draw(new Line2D.Double( (double)width - 10, height - 1
//                    , (double)x, (double)height));
//            g2d.draw(new Line2D.Double( (double)width - 10, (double)height - 10
//                    , (double)width - 10, (double)height - 20));
//        }
//
//
//    }

    @Override
    public void paintBorder(Component comp, Graphics g, int x, int y, int w, int h) {
        super.paintBorder(comp, g, x, y, w, h);
        Graphics2D gg = null;
        if (g instanceof Graphics2D) {
            gg = (Graphics2D) g;

            Stroke prevStroke = gg.getStroke();
            Color prevColor = gg.getColor();

            gg.setColor(borderColour);
            gg.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{7}, 0));
            //gg.drawRect(x, y, w - 1, h - 1);
            gg.draw(new Line2D.Double((double) x + 1, (double) h - 1,
                    (double) x + w - 1, (double) h - 1));

            gg.setStroke(prevStroke);
            gg.setColor(prevColor);
        }
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return (getBorderInsets(c, new Insets(gap, gap, gap, gap)));
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = gap;
        return insets;
    }

    @Override
    public boolean isBorderOpaque()
    {
        return true;
    }
}
