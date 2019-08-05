package net.mov51.smallWorld.util;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.appender.RollingFileAppender;

public class math {
    public static boolean isNumeric(String string){
        boolean numeric = true;

        try {
            int num = Integer.parseInt(string);
        } catch (NumberFormatException e) {
            numeric = false;
        }

        return numeric;
    }

    public static int getChuck(int inputCord) {
        return inputCord / 16;
        // takes input block coordinates and returns the chunk coordinates
    }

    public static int getRegion(int inputChunk) {
        return (int) Math.floor(inputChunk / 32.0);
        // takes input chunk coordinates and returns the region file coordinates
    }
}
