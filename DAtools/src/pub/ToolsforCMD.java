/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pub;

import java.text.NumberFormat;

/**
 * <p>
 * ToolsforCMD</p>
 * <p>
 * Created on 2015-11-14 13:10:03</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-11-14 13:10:03
 * @version java 1.6.0
 * @version
 */
public class ToolsforCMD {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static String print_ansi_BLACK(String str) {
        if (System.getProperty("os.name").contains("Windows")) {
            
            return  str;
        } else {
            return ToolsforCMD.ANSI_BLACK + str + ToolsforCMD.ANSI_RESET;
        }
    }

    public static String print_ansi_RED(String str) {
        if (System.getProperty("os.name").contains("Windows")) {
              return  str;
        } else {
            return ToolsforCMD.ANSI_RED + str + ToolsforCMD.ANSI_RESET;
        }
    }

    public static String print_ansi_GREEN(String str) {
        if (System.getProperty("os.name").contains("Windows")) {
              return  str;
        } else {
            return ToolsforCMD.ANSI_GREEN + str + ToolsforCMD.ANSI_RESET;
        }

    }

    public static String print_ansi_YELLOW(String str) {
        if (System.getProperty("os.name").contains("Windows")) {
              return  str;
        } else {
            return ToolsforCMD.ANSI_YELLOW + str + ToolsforCMD.ANSI_RESET;
        }

    }

    public static String print_ansi_BLUE(String str) {
        if (System.getProperty("os.name").contains("Windows")) {
             return  str;
        } else {
            return ToolsforCMD.ANSI_BLUE + str + ToolsforCMD.ANSI_RESET;
        }

    }

    public static String print_ansi_PURPLE(String str) {
        if (System.getProperty("os.name").contains("Windows")) {
              return  str;
        } else {
            return ToolsforCMD.ANSI_PURPLE + str + ToolsforCMD.ANSI_RESET;
        }

    }

    public static String print_ansi_CYAN(String str) {
        if (System.getProperty("os.name").contains("Windows")) {
             return  str;
        } else {
            return ToolsforCMD.ANSI_CYAN + str + ToolsforCMD.ANSI_RESET;
        }
    }

    public static String print_ansi_WHITE(String str) {
        if (System.getProperty("os.name").contains("Windows")) {
            return  str;
        } else {
            return ToolsforCMD.ANSI_WHITE + str + ToolsforCMD.ANSI_RESET;
        }
    }

    public static String getDAtoolstr() {
        String str = "==============================================================\n"
                + "     ######     #                                      \n"
                + "     #     #   # #   #####  ####   ####  #       ####  \n"
                + "     #     #  #   #    #   #    # #    # #      #      \n"
                + "     #     # #     #   #   #    # #    # #       ####  \n"
                + "     #     # #######   #   #    # #    # #           # \n"
                + "     #     # #     #   #   #    # #    # #      #    # \n"
                + "     ######  #     #   #    ####   ####  ######  ####  \n"
                + "================================================================\n";
        return str;
    }

    public static String startruningSTR() {
        String str = "  ____                                            _             _                     \n"
                + " |  _ \\ _ __ ___   __ _ _ __ __ _ _ __ ___    ___| |_ __ _ _ __| |_                   \n"
                + " | |_) | '__/ _ \\ / _` | '__/ _` | '_ ` _ \\  / __| __/ _` | '__| __|                  \n"
                + " |  __/| | | (_) | (_| | | | (_| | | | | | | \\__ | || (_| | |  | |_ _ _ _ _ _ _ _ _ _ \n"
                + " |_|   |_|  \\___/ \\__, |_|  \\__,_|_| |_| |_| |___/\\__\\__,_|_|   \\__(_(_(_(_(_(_(_(_(_)\n"
                + "                  |___/                                                               ";
        return str;
    }

    public static double getlog2(double a) {
        return Math.log(a) / Math.log(2);
    }

    public static String replaceSuffix(String a, String orinsuffix, String altersuffix) {
        return a.replaceAll(orinsuffix + "$", altersuffix);
    }

    public static String getmemoryUsedInfomation() {
        Runtime runtime = Runtime.getRuntime();

        NumberFormat format = NumberFormat.getInstance();

        StringBuilder sb = new StringBuilder();
        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();

        sb.append("Free memory: " + format.format(freeMemory / (1024 * 1024)) + "M\r\n");
        sb.append("Allocated memory: " + format.format(allocatedMemory / (1024 * 1024)) + "M\r\n");
        sb.append("Max memory: " + format.format(maxMemory / (1024 * 1024)) + "M\r\n");
        sb.append("Total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / (1024 * 1024)) + "M\r\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(ToolsforCMD.print_ansi_CYAN("Bb"));
        System.out.println(System.getProperty("os.name"));
    }
}
