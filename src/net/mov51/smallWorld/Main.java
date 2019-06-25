package net.mov51.smallWorld;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        System.out.println(args[0]);
        switch(args[0]){
            case "radius":
                System.out.println(args[1] + " = center coordinate");
                System.out.println(args[2] + " = range");

                String[] stringCords = args[1].split(",");
                int[] intCords = new int[stringCords.length];
                for (int i = 0; i < stringCords.length; i++) {
                    String numberAsString = stringCords[i];
                    intCords[i] = Integer.parseInt(numberAsString);
                }

                int radius =  Integer.parseInt(args[2]);

                listRegionsByRadius(intCords[0], intCords[1], radius);
                break;
            case "rectangle":

                break;
            case "preset":

                break;

        }

    }
//    TODO take player UUIDs and output files associated with them.
//    TODO function to prepare and modify output world (copy files from output directory, copy level.dat, change world type, place user files, copy DataPacks.

    public static void listRegionsByRadius(int x, int z, int radius ){
        int corner1X, corner2X, corner1Z, corner2Z; // initializing corner variables
        corner1X = x - radius; // defining new rectangle by radius
        corner1Z = z - radius;
        corner2X = x + radius;
        corner2Z = z + radius;

        // calling the listRegionsByRectangle method by calling the getRegion and getChunk methods after turning the radius and point coordinate into a rectangle
        listRegionsByRectangle(getRegion(getChuck(corner1X)), getRegion(getChuck(corner1Z)), getRegion(getChuck(corner2X)), getRegion(getChuck(corner2Z)));


    }
    public static void listRegionsByRectangle(int x1, int z1, int x2, int z2){
//      TODO verify coordinates validity then format with largest first.
        int max_x = x1 >= x2 ? x1 : x2; // finding high value of x
        int min_x = x2 < x1 ? x2 : x1 ; // finding low value of x

        int max_z = z1 >= z2 ? z1 : z2; // finding high value of z
        int min_z = z2 < z1 ? z2 : z1 ; // finding low value of z

        int numberOfRegions = 0;
        for(int i = min_x; i<=max_x; i++) { // looping for the length between max and min z
            for(int j = min_z; j<=max_z; j++) { // looping for the length between between max and min x
                numberOfRegions++; // counting the length of the new array for the region files

            }
        }

        String[] regionFiles = new String[numberOfRegions]; // array for the region files to be stored in
        int count = 0; // count for the array

        for(int i = min_x; i<=max_x; i++) { // looping for the length between max and min z
            for(int j = min_z; j<=max_z; j++) { // looping for the length between between max and min x

                System.out.println("discovered : r." + i + "." + j + ".mca exists within area"); // printing formatted region file names
                regionFiles[count] = "r." + i + "." + j + ".mca"; // placing region files in array for further use
                count++; // adding 1 to the count int to cycle through the region files array
                //TODO send array to a method that can copy files from a world directory, first step is into an empty "temp" folder
            }
        }

        System.out.println("need to find : " + Arrays.toString(regionFiles)); // prints array for testing

        getFiles(regionFiles);
    }

    public static int getChuck(int inputCord){
        return inputCord / 16;
        // takes input block coordinates and returns the chunk coordinates
    }
    public static int getRegion(int inputChunk){
        return (int)Math.floor(inputChunk / 32.0);
        // takes input chunk coordinates and returns the region file coordinates
    }

    public static void getFiles(String[] regions){

        String currentDirectory = System.getProperty("user.dir");

        File dir = new File(currentDirectory + "\\region");
        String[] foundRegions = new String[regions.length];
        int loopCount = 0;

        for (String region: regions) { // loops for the length of the string array that was passed to it

                File[] matchingFiles = dir.listFiles(new FilenameFilter() { // TODO look intro how this works, read through the documentation!

                    // not quite sure how this works, need to look a bit more into it. seems to take from the dir variable from above
                public boolean accept(File dir, String name) {
                    return name.matches(region); // looks for the iterated section of the array as a file name in the directory.
                }
            });
            if ((Arrays.toString(matchingFiles) != "[]")){
                System.out.println("Found " + region + " in region folder");
            }else{
                System.out.println("File " + region + " not found");
            }

            foundRegions[loopCount] = (Arrays.toString(matchingFiles));
            loopCount++;
            // prints the files it found in the directory after iterating through the whole passed array
        }
        System.out.println(Arrays.toString(foundRegions));
    }
}

