package net.mov51.smallWorld;

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0){
            System.out.println("This is the smallWorld application!");
            System.out.println("It's main purpose is to divide your world into smaller sections,");
            System.out.println("but you didn't tell it what to do, for information on how to do that go here https://github.com/mov51/smallWorld/wiki");
            System.out.println();
            System.out.println("Made by mov51");
            System.out.println("for support go to https://discord.mov51.net");
        }else{
            System.out.println();
            System.out.println("Starting......");
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
                    System.out.println(args[1] + " = cornerList");

                    String[] stringOfCords = args[1].split(",");
                    int[] intRectCords = new int[stringOfCords.length];
                    if(stringOfCords.length % 4 == 0){
                        System.out.println("length of coordinate string is " + stringOfCords.length);
                        System.out.println();
                        for (int i = 0; i < stringOfCords.length; i += 4){
                            String numberAsString = stringOfCords[i];
                            intRectCords[i] = Integer.parseInt(numberAsString);
                            numberAsString = stringOfCords[i + 1];
                            intRectCords[i + 1] = Integer.parseInt(numberAsString);
                            numberAsString = stringOfCords[i + 2];
                            intRectCords[i + 2] = Integer.parseInt(numberAsString);
                            numberAsString = stringOfCords[i + 3];
                            intRectCords[i + 3] = Integer.parseInt(numberAsString);
                        }
                    }else{
                        System.out.println("---------------------------------------------");
                        System.out.println("ERROR: provided coordinates not in pairs of 4");
                        System.out.println("---------------------------------------------");
                    }

                    for (int i = 0; i < intRectCords.length; i += 4){
                        listRegionsByRectangle(intRectCords[i], intRectCords[i + 1], intRectCords[i + 2], intRectCords[i + 3]);
                    }

//                    String[] stringC1Cords = args[1].split(",");
//                    int[] intC1Cords = new int[stringC1Cords.length];
//                    for (int i = 0; i < stringC1Cords.length; i++) {
//                        String numberAsString = stringC1Cords[i];
//                        intC1Cords[i] = Integer.parseInt(numberAsString);
//                    }
//
//                    String[] stringC2Cords = args[2].split(",");
//                    int[] intC2Cords = new int[stringC2Cords.length];
//                    for (int i = 0; i < stringC2Cords.length; i++) {
//                        String numberAsString = stringC2Cords[i];
//                        intC2Cords[i] = Integer.parseInt(numberAsString);
//                    }

//                    listRegionsByRectangle(intC1Cords[0], intC1Cords[1],intC2Cords[0], intC2Cords[1]);


                    break;
                case "preset":

                    break;

        }


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
        listRegionsByRectangle(corner1X, corner1Z, corner2X, corner2Z);


    }
    public static void listRegionsByRectangle(int x1, int z1, int x2, int z2){
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("Starting new rectangle search!");
        x1 = getRegion(getChuck(x1)); // converting input coordinates to regions with converted chunk coordinates
        x2 = getRegion(getChuck(x2));
        z1 = getRegion(getChuck(z1));
        z2 = getRegion(getChuck(z2));

        int max_x = x1 >= x2 ? x1 : x2; // finding high value of x
        int min_x = x2 < x1 ? x2 : x1 ; // finding low value of x

        int max_z = z1 >= z2 ? z1 : z2; // finding high value of z
        int min_z = z2 < z1 ? z2 : z1 ; // finding low value of z


        System.out.println(min_x + " -x " + max_x + " +x " + min_z + " -z " + max_z + " +z ");
        int numberOfRegions = 0;
        for(int i = min_x; i<=max_x; i++) { // looping for the length between max and min z
            for(int j = min_z; j<=max_z; j++) { // looping for the length between between max and min x
                numberOfRegions++; // counting the length of the new array for the region files

            }
        }

        String[] regionFiles = new String[numberOfRegions]; // array for the region files to be stored in using size counted in last step

        int arrayPlace = 0; // initializing count for array placement
        for(int i = min_x; i<=max_x; i++) { // looping for the length between max and min z
            for(int j = min_z; j<=max_z; j++) { // looping for the length between between max and min x

                System.out.println("discovered : r." + i + "." + j + ".mca exists within area"); // printing formatted region file names
                regionFiles[arrayPlace] = "r." + i + "." + j + ".mca"; // placing region files in array for further use
                arrayPlace++; // adding 1 to the count int to cycle through the region files array
                //TODO send array to a method that can copy files from a world directory, first step is into an empty "temp" folder
            }
        }
        System.out.println();
        System.out.println("need to find : " + Arrays.toString(regionFiles)); // prints array for testing // TODO output to text file in logs folder
        System.out.println();
        getFiles(regionFiles); //passes compiled array to retrieve file paths
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

        String currentDirectory = System.getProperty("user.dir"); // gets the current directory of the jar file

        File dir = new File(currentDirectory + "\\region"); // appends the region folder to the user.dir for our working dir
        String[] foundRegions = new String[regions.length]; // creates a new String array for the full file paths to be placed in
        int loopArrayCount = 0; // for compiling the array

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

            foundRegions[loopArrayCount] = (Arrays.toString(matchingFiles)); // places found file paths into String array for next step
            loopArrayCount++;
        }
        System.out.println(Arrays.toString(foundRegions)); // prints the foundRegions array for testing TODO send this to a method to move them to a temp folder or the end output dir
    }
}

