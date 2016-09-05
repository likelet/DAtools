package pub;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.StringTokenizer;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * <p>Tools.java</p>
 * <p>Created on Apr 18, 2009, 11:12:45 PM</p>
 * <p>Copyright (c) 2007-2009. CUCKOO Workgroup, USTC, P.R.China</p>
 * @author Ren Jian
 * @version 3.2
 */
public class Tools {

    public final static Image GPS_ICON = Toolkit.getDefaultToolkit().getImage(Tools.class.getResource("/gui/image/GPS.png"));
    public final static String AA = "ARNDCQEGHILKMFPSTWYVBZX*";
    public static HashMap<Character, Integer> aaMap = getAAMap();
    public static String CURRENT_FILE_PATH = getCurrentFilePath();
    public static int thread=23;

    public static HashMap<Character, Integer> getAAMap() {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        map.put('A', 0);
        map.put('R', 1);
        map.put('N', 2);
        map.put('D', 3);
        map.put('C', 4);
        map.put('Q', 5);
        map.put('E', 6);
        map.put('G', 7);
        map.put('H', 8);
        map.put('I', 9);
        map.put('L', 10);
        map.put('K', 11);
        map.put('M', 12);
        map.put('F', 13);
        map.put('P', 14);
        map.put('S', 15);
        map.put('T', 16);
        map.put('W', 17);
        map.put('Y', 18);
        map.put('V', 19);
        map.put('B', 20);
        map.put('Z', 21);
        map.put('X', 22);
        map.put('U', 22);
        map.put('*', 23);
        return map;
    }

    static String getCurrentFilePath() {
        String filePath = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader("CurrentFilePath.txt"));
            filePath = in.readLine();
            in.close();
        } catch (Exception ex) {
        }
        return filePath;
    }

    public static void setCurrentFilePath() {
        try {
            if (!CURRENT_FILE_PATH.isEmpty()) {
                FileWriter fw = new FileWriter("CurrentFilePath.txt");
                fw.append(CURRENT_FILE_PATH);
                fw.flush();
                fw.close();
            }
        } catch (Exception ex) {
        }
    }

    public static void WindowCenter(JFrame frame) {
        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2 - 10);
        frame.setVisible(true);
    }

    public static void WindowCenter(JDialog dialog) {
        dialog.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = dialog.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        dialog.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
        dialog.setVisible(true);
    }

    public static void WindowLeft(JFrame frame) {
        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) / 4, (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
    }

    public static void WindowRight(JFrame frame) {
        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        frame.setLocation((screenSize.width - frameSize.width) * 3 / 4, (screenSize.height - frameSize.height) / 2);
        frame.setVisible(true);
    }

    public static String FileNameFormat(String fileName, String suffix) {
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        //String extention = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        //return name + "." + suffix + "." + extention;
        return name + "." + suffix + ".txt";
    }

    public static String FileExtentionChange(String fileName, String extention) {
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        return name + "." + extention;
    }

    public static boolean copyFile(String filefrom, String fileto) {
        try {
            FileInputStream fosfrom = new java.io.FileInputStream(filefrom);
            FileOutputStream fosto = new FileOutputStream(fileto);
            byte[] bt = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0) {
                fosto.write(bt, 0, c);
            }
            fosfrom.close();
            fosto.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static int[][] getMatrix(String filePath) {
        int[][] matrix = new int[24][24];
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(Tools.class.getResourceAsStream(filePath)));
            in.readLine();
            for (int i = 0; i < 24; i++) {
                String row = in.readLine();
                StringTokenizer rowSt = new StringTokenizer(row, " ");
                for (int j = 0; j < 24; j++) {
                    matrix[i][j] = Integer.parseInt(rowSt.nextToken());
                }
            }
            in.close();
        } catch (Exception ex) {
            System.out.println("gui.Tools.getMatrix Matrix miss");
            return null;
        }
        return matrix;
    }

    public static int[][] getMatrix(File file) {
        int[][] matrix = new int[24][24];
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            in.readLine();
            for (int i = 0; i < 24; i++) {
                String row = in.readLine();
                StringTokenizer rowSt = new StringTokenizer(row, " ");
                for (int j = 0; j < 24; j++) {
                    matrix[i][j] = Integer.parseInt(rowSt.nextToken());
                }
            }
            in.close();
        } catch (Exception ex) {
            System.out.println("gui.Tools.getMatrix Matrix miss");
            return null;
        }
        return matrix;
    }

    public static int[][] MatrixReader(String filePath) {
        int[][] matrix = new int[24][24];
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(Tools.class.getResourceAsStream(filePath)));
            while (in.ready()) {
                String lineStr = in.readLine();
                if (lineStr.equals("") || lineStr.startsWith("#") || lineStr.startsWith(" ") || lineStr.startsWith("@")) {
                    //invalid value
                } else {
                    StringTokenizer st = new StringTokenizer(lineStr, " ");
                    char code = st.nextToken().charAt(0);
                    int row = AA.indexOf(code);
                    for (int column = 0; column < 24; column++) {
                        matrix[row][column] = Integer.parseInt(st.nextToken());
                    }
                }
            }
            in.close();
        } catch (Exception ex) {
            System.out.println("gui.Tools.MatrixReader Matrix miss");
            return null;
        }
        return matrix;
    }

    public static int[][] MatrixReader(File file) {
        int[][] matrix = new int[24][24];
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            while (in.ready()) {
                String lineStr = in.readLine();
                if (lineStr.equals("") || lineStr.startsWith("#") || lineStr.startsWith(" ") || lineStr.startsWith("@")) {
                    //invalid value
                } else {
                    StringTokenizer st = new StringTokenizer(lineStr, " ");
                    char code = st.nextToken().charAt(0);
                    int row = AA.indexOf(code);
                    for (int column = 0; column < 24; column++) {
                        matrix[row][column] = Integer.parseInt(st.nextToken());
                    }
                }
            }
            in.close();
        } catch (Exception ex) {
            System.out.println("gui.Tools.MatrixReader Matrix miss");
            return null;
        }
        return matrix;
    }

    public static String ColorToHex(Color color) {
        String R, G, B;
        StringBuffer sb = new StringBuffer();

        R = Integer.toHexString(color.getRed());
        G = Integer.toHexString(color.getGreen());
        B = Integer.toHexString(color.getBlue());

        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;

        sb.append(R);
        sb.append(G);
        sb.append(B);

        return "#" + sb.toString().toUpperCase();
    }

    public static Color HexToColor(String hex) {
        return new Color(Integer.parseInt(hex, 16));
    }

    public static String nowTime() {
        Date now = new Date();
        String nowString = now.toString();
        StringTokenizer st = new StringTokenizer(nowString, " ");
        String monTag = st.nextToken();
        String mon = st.nextToken();
        String day = st.nextToken();
        String timeString = st.nextToken();
        String timezone = st.nextToken();
        String year = st.nextToken();
        String month = "";
        if (mon.equals("Jan")) {
            month = "01";
        } else if (mon.equals("Feb")) {
            month = "02";
        } else if (mon.equals("Mar")) {
            month = "03";
        } else if (mon.equals("Apr")) {
            month = "04";
        } else if (mon.equals("May")) {
            month = "05";
        } else if (mon.equals("Jun")) {
            month = "06";
        } else if (mon.equals("Jul")) {
            month = "07";
        } else if (mon.equals("Aug")) {
            month = "08";
        } else if (mon.equals("Sep")) {
            month = "09";
        } else if (mon.equals("Oct")) {
            month = "10";
        } else if (mon.equals("Nov")) {
            month = "11";
        } else if (mon.equals("Dec")) {
            month = "12";
        }
        //return year + "-" + month + "-" + day + " " + timeString + "+8";
        return year + "-" + month + "-" + day;
    }

    public static String nowDate() {
        Date now = new Date();
        String nowString = now.toString();
        StringTokenizer st = new StringTokenizer(nowString, " ");
        String monTag = st.nextToken();
        String month = st.nextToken();
        String day = st.nextToken();
        String timeString = st.nextToken();
        String timezone = st.nextToken();
        String year = st.nextToken();

        return day + "-" + month + "-" + year;
    }

    public static int[][] InitialMatrix(String fileName) {
        String filePath = "/pub/" + fileName + ".matrix";
        return MatrixReader(filePath);
    }
    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)" + regex + "(?!.*?" + regex + ")", replacement);
    }

    public static String getDAtoolstr(){
        String str="==============================================================\n"+
                  "     ######     #                                      \n" +
                  "     #     #   # #   #####  ####   ####  #       ####  \n" +
                  "     #     #  #   #    #   #    # #    # #      #      \n" +
                  "     #     # #     #   #   #    # #    # #       ####  \n" +
                  "     #     # #######   #   #    # #    # #           # \n" +
                  "     #     # #     #   #   #    # #    # #      #    # \n" +
                  "     ######  #     #   #    ####   ####  ######  ####  \n" +
                  "================================================================\n";
        return str;
    }
}
