package net.mov51.smallWorld;

import net.mov51.smallWorld.util.IO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;

import static net.mov51.smallWorld.util.IO.sendOutput;
import static net.mov51.smallWorld.util.math.*;

public class worldSearch {

    private static Logger logger = LogManager.getRootLogger();

    public static void listRegionsByRadius(String args1, String args2) {
        logger.debug(args1 + " = center coordinate list");
        logger.debug(args2 + " = range list");

        String[] stringOfRadCords = args1.split(",");
        int[] intRadCords = new int[stringOfRadCords.length];

        String[] stringOfRadRanges = args2.split(",");
        int[] intRadRanges = new int[stringOfRadRanges.length];


        if (stringOfRadCords.length % 2 == 0) {
            logger.info("Parsing radius coordinates");

            for (int i = 0; i < stringOfRadCords.length; i++) { // loops through stringOfRadCords and pareses it into a new int array
                String numberAsString = stringOfRadCords[i];
                intRadCords[i] = Integer.parseInt(numberAsString);
            }

            for (int i = 0; i < stringOfRadRanges.length; i++) { // loops through stringOfRadRanges and pareses it into a new int array
                String numberAsString2 = stringOfRadRanges[i];
                intRadRanges[i] = Integer.parseInt(numberAsString2);
            }

        } else {
            logger.error("provided radius center coordinates or radius are not in pairs of 2");
            System.exit(1);
        }

        for (int j = 0; j < intRadRanges.length; j++) { // iterates through intRadRanges array
            int x = intRadCords[j * 2];
            int z = intRadCords[j * 2 + 1];
            int radius = intRadRanges[j];
            logger.info("Starting new radius conversion");
            logger.debug(x + " X " + z + " Z " + radius + " Radius ");
            int corner1X, corner2X, corner1Z, corner2Z; // initializing corner variables
            corner1X = x - radius; // defining new rectangle by radius
            corner1Z = z - radius;
            corner2X = x + radius;
            corner2Z = z + radius;
            logger.debug(corner1X + " X1 " + corner1Z + " Z1 " + corner2X + " X2 " + corner2Z + " Z2 ");

            // calling the listRegionsByRectangle method by calling the getRegion and getChunk methods after turning the radius and point coordinate into a rectangle
            listRegionsByRectangle(corner1X, corner1Z, corner2X, corner2Z);
        }





    }

    public static void listRegionsByRectangle(String args1) {

        logger.debug("corner list = " + args1);

        String[] stringOfRectCords = args1.split(",");
        int[] intRectCords = new int[stringOfRectCords.length];
        if (stringOfRectCords.length % 4 == 0) {
            logger.info("Parsing rectangle coordinates");
            for (int i = 0; i < stringOfRectCords.length; i++) {
                String numberAsString = stringOfRectCords[i];
                intRectCords[i] = Integer.parseInt(numberAsString);
                logger.debug(intRectCords[i] + " = " + numberAsString);
            }
            logger.info("int parsing loop complete");
        } else {
            logger.error("provided rectangle corner coordinates are not in pairs of 4");
            System.exit(1);
        }

        for (int i = 0; i < intRectCords.length; i += 4) {
            listRegionsByRectangle(intRectCords[i], intRectCords[i + 1], intRectCords[i + 2], intRectCords[i + 3]);
        }
    }

    public static void listRegionsByRectangle(int x1, int z1, int x2, int z2){
        logger.info("Starting new rectangle search");
        x1 = getRegion(getChuck(x1)); // converting input coordinates to regions with converted chunk coordinates
        x2 = getRegion(getChuck(x2));
        z1 = getRegion(getChuck(z1));
        z2 = getRegion(getChuck(z2));

        int max_x = x1 >= x2 ? x1 : x2; // finding high value of x
        int min_x = x2 < x1 ? x2 : x1; // finding low value of x

        int max_z = z1 >= z2 ? z1 : z2; // finding high value of z
        int min_z = z2 < z1 ? z2 : z1; // finding low value of z


        logger.debug(min_x + " -x " + max_x + " +x " + min_z + " -z " + max_z + " +z ");
        int numberOfRegions = 0;
        for (int i = min_x; i <= max_x; i++) { // looping for the length between max and min z
            for (int j = min_z; j <= max_z; j++) { // looping for the length between between max and min x
                numberOfRegions++; // counting the length of the new array for the region files

            }
        }

        String[] regionFiles = new String[numberOfRegions]; // array for the region files to be stored in using size counted in last step

        int arrayPlace = 0; // initializing count for array placement
        for (int i = min_x; i <= max_x; i++) { // looping for the length between max and min z
            for (int j = min_z; j <= max_z; j++) { // looping for the length between between max and min x

                logger.debug("discovered : r." + i + "." + j + ".mca exists within area"); // printing formatted region file names
                regionFiles[arrayPlace] = "r." + i + "." + j + ".mca"; // placing region files in array for further use
                arrayPlace++; // adding 1 to the count int to cycle through the region files array

            }
        }
        //passes compiled array to retrieve file paths

        String subDir = null;
        int subDirNum = 0;
        logger.info(System.getProperty("dim") + " set dim");

        String dim;
        if ((System.getProperty("dim") != null)) {
            dim = (System.getProperty("dim"));
        } else {
            dim = "case";
        }

        switch (dim){
            case "end":
                subDirNum = 1;
                break;
            case "nether" :
                subDirNum = -1;
                break;
            case "overworld" :
            case "0":
            default :
                subDir = "region";
                dim = "region";
                break;
        }
        if (isNumeric(dim)){
            subDir = "DIM" + dim + "\\region";
        } else if (subDirNum != 0){
            subDir = "DIM" + subDirNum + "\\region";
        }

        IO.sendOutput(getFiles(regionFiles, subDir));
    }

    public static void listUUIDsByString(String stringOfUUIDs){
        logger.info("Received UUIDs");
        logger.debug( "UUID list = " + stringOfUUIDs);
        String[] arrayOfUUIDs = stringOfUUIDs.split(",");
        System.out.println(Arrays.toString(arrayOfUUIDs));
        sendOutput(getFiles(arrayOfUUIDs, "playerdata,advancements,stats"));
    }

    public static Path[] getFiles(String[] fileList, String subDirectory) {
        logger.debug(Arrays.toString(fileList) + " Files to look for");

        String[] newSubDirectory = subDirectory.split(",");
        Path[] foundFiles = new Path[fileList.length * newSubDirectory.length]; // creates a new String array for the full file paths to be placed in
        int loopArrayCount = 0; // for compiling the array

        for (int i = 0; i < newSubDirectory.length; i++){
            File dir = new File(newSubDirectory[i]); // appends the subDirectory folder to the user.dir for our working dir
            logger.info("looking in " + dir);

            for (String singleFile : fileList) { // loops for the length of the string array that was passed to it

                File[] matchingFiles = dir.listFiles(new FilenameFilter() {

                    // not quite sure how this works, need to look a bit more into it. seems to take from the dir variable from above
                    public boolean accept(File dir, String name) {
                        return name.contains(singleFile); // looks for the iterated section of the array as a file name in the directory.
                    }
                });
                if ((Arrays.toString(matchingFiles) != "[]")) {
                    logger.debug("Found " + singleFile + " in " + newSubDirectory[i] + " folder");
                } else {
                    logger.debug("File " + singleFile + " not found");
                }
                logger.debug(Arrays.toString(matchingFiles));
                foundFiles[loopArrayCount] = FileSystems.getDefault().getPath(Arrays.toString(matchingFiles).replace("[", "").replace("]", "")); // places found file paths into String array for next step
                loopArrayCount++;
            }
            logger.info("Found files = " + Arrays.toString(foundFiles)); // prints the foundRegions array for testing
        }
        return foundFiles;

    }
}

