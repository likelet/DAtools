/*
 * CanvasPanel.java
 *
 * Created on October 8, 2008, 1:28 PM
 */
package pub;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * <p>CanvasPanel</p>
 * <p>Copyright (c) 2008. CUCKOO Workgroup, USTC, P.R.China</p>
 * @author Ren Jian
 * @version 2.9
 */
public class CanvasPanel extends javax.swing.JPanel {

    private Image image;
    private LinkedList pointList1;
    private int r1;
    private Color color1;
    private LinkedList pointList2;
    private int r2;
    private Color color2;
    private double zoomRate = 1.0;
    private Image tempImage;

    /** Creates new form CanvasPanel */
    public CanvasPanel() {
        pointList1 = new LinkedList();
        pointList2 = new LinkedList();
        initComponents();
    }

    public void setPointList1(LinkedList pointList1) {
        this.pointList1 = pointList1;
    }

    public void setPointList2(LinkedList pointList2) {
        this.pointList2 = pointList2;
    }

    public void zoomIn() {
        zoomRate = zoomRate * 1.25;
        ImageIcon icon = new ImageIcon(image);
        int width = (int) (icon.getIconWidth() * zoomRate);
        int height = (int) (icon.getIconHeight() * zoomRate);
        tempImage = image.getScaledInstance(width, height, Image.SCALE_FAST);
        this.setPreferredSize(new Dimension(width, height));
        repaint();
    }

    public double getZoomRate() {
        return zoomRate;
    }

    public void setZoomRate(double zoomRate) {
        this.zoomRate = zoomRate;
    }

    public void zoomOut() {
        zoomRate = zoomRate * 0.8;
        ImageIcon icon = new ImageIcon(image);
        int width = (int) (icon.getIconWidth() * zoomRate);
        int height = (int) (icon.getIconHeight() * zoomRate);
        tempImage = image.getScaledInstance(width, height, Image.SCALE_FAST);
        this.setPreferredSize(new Dimension(width, height));
        repaint();
    }

    public void setImage(Image image) {
        this.image = image;
        ImageIcon icon = new ImageIcon(image);
        int width = (int) (icon.getIconWidth() * zoomRate);
        int height = (int) (icon.getIconHeight() * zoomRate);
        tempImage = image.getScaledInstance(width, height, Image.SCALE_FAST);
        this.setPreferredSize(new Dimension(width, height));
    }

    public void setR1(int r1) {
        this.r1 = r1;
    }

    public void setR2(int r2) {
        this.r2 = r2;
    }

    public void setColor1(Color color1) {
        this.color1 = color1;
    }

    public void setColor2(Color color2) {
        this.color2 = color2;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tempImage != null) {
            g.drawImage(tempImage, 0, 0, this);
            //g.setXORMode(Color.black);
            g.setColor(color1);
            for (Iterator it = pointList1.iterator(); it.hasNext();) {
                Point point = (Point) it.next();
                g.fillOval((int) (point.getX() * zoomRate - r1 / 2.0), (int) (point.getY() * zoomRate - r1 / 2.0), r1, r1);
            }
            g.setColor(color2);
            for (Iterator it = pointList2.iterator(); it.hasNext();) {
                Point point = (Point) it.next();
                g.fillOval((int) (point.getX() * zoomRate - r2 / 2.0), (int) (point.getY() * zoomRate - r2 / 2.0), r2, r2);
            }
        }
    }

    public void outputImage(String outputFilePath, String imageType) {
        try {
            int width = this.getWidth();
            int height = this.getHeight();
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            if (imageType.equals("PNG")) {
                bi = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
            } else {
                bi = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
            }
            Graphics2D g2d = bi.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setFont(new Font("Arial", Font.BOLD, 30));
            g2d.setBackground(Color.white);
            g2d.fillRect(0, 0, width, height);
            paintComponent(g2d);
            int high = 40;
            if (pointList1.size() > 0) {
                g2d.setColor(color1);
                g2d.drawString(String.valueOf(pointList1.size()), 20, high);
                high = 80;
            }
            if (pointList2.size() > 0) {
                g2d.setColor(color2);
                g2d.drawString(String.valueOf(pointList2.size()), 20, high);
            }
            g2d.dispose();
            bi.flush();
            ImageIO.write(bi, imageType, new File(outputFilePath));
        } catch (IOException ex) {
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
