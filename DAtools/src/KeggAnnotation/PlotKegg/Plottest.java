/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package KeggAnnotation.PlotKegg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ZHAO Qi
 * @date 2014-10-30 10:33:04
 * @version 1.6.0
 */
public class Plottest {
    public static void main(String[] args) throws IOException {
        BufferedImage bimg=ImageIO.read(new FileInputStream("G:/keggScriptBGI/used_data/map/map00010.png"));
//得到Graphics2D 对象
Graphics2D g2d=(Graphics2D)bimg.getGraphics();
//设置颜色和画笔粗细
 g2d.setColor(Color.RED);
 g2d.setStroke(new BasicStroke(3));
//绘制图案或文字
g2d.drawRect(435,327, 481-435,344-327);
ImageIO.write(bimg, "PNG",new FileOutputStream("E:/test.png"));
    }
}
