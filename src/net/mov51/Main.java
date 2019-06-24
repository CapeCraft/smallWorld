package net.mov51;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        listRegionsByRadius(0, 0, 750);

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
        // takes a predefined rectangle and lists all regions within that area
        int numberOfRegions = 0;
        for(int i = z1; i<=z2; i++) { // looping for the length between corner 1 and 2 z
            for(int j = x1; j<=x2; j++) { // looping for the length between corner 1 and 2 x

                numberOfRegions++; // counting the length of the new array for the region files

            }
        }

        String[] regionFiles = new String[numberOfRegions]; // array for the region files to be stored in
        int count = 0; // count for the array

        for(int i = z1; i<=z2; i++) { // looping for the length between corner 1 and 2 z
            for(int j = x1; j<=x2; j++) { // looping for the length between corner 1 and 2 x

                System.out.println("r." + i + "." + j + ".mca"); // printing formatted region file names
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
        File dir = new File("D:\\git\\smallWorld\\out\\production\\smallWorld\\net\\mov51\\region");
        System.out.println(dir); // prints working directory TODO change to looking in the local directory of the program
        for (String region: regions) { // loops for the length of the string array that was passed to it
                File[] matchingFiles = dir.listFiles(new FilenameFilter() { // TODO look intro how this works, read through the documentation!
                    // not quite sure how this works, need to look a bit more into it. seems to take from the dir variable from above
                public boolean accept(File dir, String name) {
                    return name.matches(region); // looks for the iterated section of the array as a file name in the directory.
                }
            });
            System.out.println(Arrays.toString(matchingFiles) + " Matching files");
            // prints the files it found in the directory after iterating through the whole passed array
        }

    }
}

