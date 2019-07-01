package net.mov51.smallWorld;

import java.nio.file.*;
import java.util.*;
import java.io.*;
import java.io.File.*;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("This is the smallWorld application!");
            System.out.println("It's main purpose is to divide your world into smaller sections,");
            System.out.println("but you didn't tell it what to do, for information on how to do that go here https://github.com/mov51/smallWorld/wiki");
            System.out.println();
            System.out.println("Made by mov51");
            System.out.println("for support go to https://discord.mov51.net");
        } else {
            System.out.println();
            System.out.println("Starting......");
            System.out.println("Made by mov51");
            System.out.println("for support go to https://discord.mov51.net");
            System.out.println("--------------------------------------------------------------");
            System.out.println();
            switch (args[0]) {
                case "radius":
                    if (args.length == 1) {
                        System.out.println("--------------------------------------------------------------");
                        System.out.println("ERROR: You didn't provide any parameters for the radius option ");
                        System.out.println("ERROR: To learn what those are go here https://github.com/mov51/smallWorld/wiki/radius-area");
                        System.out.println("--------------------------------------------------------------");
                        System.exit(1);
                    }
                    System.out.println(args[1] + " = center coordinate list");
                    System.out.println(args[2] + " = range list");
                    if (args.length > 3 && args[3] != null) {
                        System.out.println("Received UUIDs");
                        System.out.println(args[3] + " = UUID list");

                        String[] stringOfUUIDs = args[3].split(",");


                        System.out.println(Arrays.toString(stringOfUUIDs));
                        System.out.println("UUIDs have been split into an array");


                        sendOutput(getFiles(stringOfUUIDs, "playerdata"), "output");
                    }


                    String[] stringOfRadCords = args[1].split(",");
                    int[] intRadCords = new int[stringOfRadCords.length];

                    String[] stringOfRadRanges = args[2].split(",");
                    int[] intRadRanges = new int[stringOfRadRanges.length];


                    if (stringOfRadCords.length % 2 == 0) {
                        System.out.println("length of RadCords string is " + stringOfRadCords.length);
                        System.out.println("length of RadRanges string is " + stringOfRadRanges.length);
                        System.out.println();
                        System.out.println("begin parsing loop.....");
                        System.out.println();

                        for (int i = 0; i < stringOfRadCords.length; i++) { // loops through stringOfRadCords and pareses it into a new int array
                            String numberAsString = stringOfRadCords[i];
                            intRadCords[i] = Integer.parseInt(numberAsString);
                        }

                        for (int i = 0; i < stringOfRadRanges.length; i++) { // loops through stringOfRadRanges and pareses it into a new int array
                            String numberAsString2 = stringOfRadRanges[i];
                            intRadRanges[i] = Integer.parseInt(numberAsString2);
                        }

                        System.out.println("int parsing loop complete");
                    } else {
                        System.out.println("-------------------------------------------------------------------------");
                        System.out.println("ERROR: provided radius center coordinates or radius are not in pairs of 2");
                        System.out.println("-------------------------------------------------------------------------");
                        System.exit(1);
                    }
                    for (int j = 0; j < intRadRanges.length; j++) { // iterates through intRadRanges array
                        listRegionsByRadius(intRadCords[j * 2], intRadCords[j * 2 + 1], intRadRanges[j]);
                            /* sends radius center cord and radius range to the list regions method
                            to double the read speed of the intRadCords array (we need 2 numbers from it)
                            the position in the array is doubled for one coordinate then shifted up once for
                            the second one*/
                    }

                    break;

                case "rectangle":
                    if (args.length == 1) {
                        System.out.println("--------------------------------------------------------------");
                        System.out.println("ERROR: You didn't provide any parameters for the rectangle option ");
                        System.out.println("ERROR: To learn what those are go here https://github.com/mov51/smallWorld/wiki/rectangle-area");
                        System.out.println("--------------------------------------------------------------");
                        System.exit(1);
                    }
                    System.out.println(args[1] + " = cornerList");

                    String[] stringOfRectCords = args[1].split(",");
                    int[] intRectCords = new int[stringOfRectCords.length];
                    if (stringOfRectCords.length % 4 == 0) {
                        System.out.println("length of coordinate string is " + stringOfRectCords.length);
                        System.out.println("begin parsing loop.....");
                        System.out.println();
                        for (int i = 0; i < stringOfRectCords.length; i++) {
                            String numberAsString = stringOfRectCords[i];
                            intRectCords[i] = Integer.parseInt(numberAsString);
                        }
                        System.out.println("int parsing loop complete");
                    } else {
                        System.out.println("--------------------------------------------------------------");
                        System.out.println("ERROR: provided rectangle corner coordinates not in pairs of 4");
                        System.out.println("--------------------------------------------------------------");
                        System.exit(1);
                    }

                    for (int i = 0; i < intRectCords.length; i += 4) {
                        listRegionsByRectangle(intRectCords[i], intRectCords[i + 1], intRectCords[i + 2], intRectCords[i + 3]);
                    }

                    break;

                case "preset":

                    break;

            }


        }

    }
//    TODO take player UUIDs and output files associated with them.
//    TODO function to prepare and modify output world (copy files from output directory, copy level.dat, change world type, place user files, copy DataPacks.

    public static void listRegionsByRadius(int x, int z, int radius) {
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("Starting new radius conversion!");
        System.out.println(x + " X " + z + " Z " + radius + " Radius ");
        int corner1X, corner2X, corner1Z, corner2Z; // initializing corner variables
        corner1X = x - radius; // defining new rectangle by radius
        corner1Z = z - radius;
        corner2X = x + radius;
        corner2Z = z + radius;
        System.out.println(corner1X + " X1 " + corner1Z + " Z1 " + corner2X + " X2 " + corner2Z + " Z2 ");

        // calling the listRegionsByRectangle method by calling the getRegion and getChunk methods after turning the radius and point coordinate into a rectangle
        listRegionsByRectangle(corner1X, corner1Z, corner2X, corner2Z);


    }

    public static void listRegionsByRectangle(int x1, int z1, int x2, int z2) {
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("Starting new rectangle search!");
        x1 = getRegion(getChuck(x1)); // converting input coordinates to regions with converted chunk coordinates
        x2 = getRegion(getChuck(x2));
        z1 = getRegion(getChuck(z1));
        z2 = getRegion(getChuck(z2));

        int max_x = x1 >= x2 ? x1 : x2; // finding high value of x
        int min_x = x2 < x1 ? x2 : x1; // finding low value of x

        int max_z = z1 >= z2 ? z1 : z2; // finding high value of z
        int min_z = z2 < z1 ? z2 : z1; // finding low value of z


        System.out.println(min_x + " -x " + max_x + " +x " + min_z + " -z " + max_z + " +z ");
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

                System.out.println("discovered : r." + i + "." + j + ".mca exists within area"); // printing formatted region file names
                regionFiles[arrayPlace] = "r." + i + "." + j + ".mca"; // placing region files in array for further use
                arrayPlace++; // adding 1 to the count int to cycle through the region files array
                //TODO send array to a method that can copy files from a world directory, first step is into an empty "temp" folder
            }
        }
        System.out.println();
        System.out.println("need to find : " + Arrays.toString(regionFiles)); // prints array for testing // TODO output to text file in logs folder
        System.out.println();
        getFiles(regionFiles, "region"); //passes compiled array to retrieve file paths
    }

    public static int getChuck(int inputCord) {
        return inputCord / 16;
        // takes input block coordinates and returns the chunk coordinates
    }

    public static int getRegion(int inputChunk) {
        return (int) Math.floor(inputChunk / 32.0);
        // takes input chunk coordinates and returns the region file coordinates
    }

    public static Path[] getFiles(String[] fileList, String subDirectory) {

        String currentDirectory = System.getProperty("user.dir"); // gets the current directory of the jar file

        File dir = new File(subDirectory); // appends the subDirectory folder to the user.dir for our working dir
        System.out.println("looking in " + dir);
        Path[] foundFiles = new Path[fileList.length]; // creates a new String array for the full file paths to be placed in
        int loopArrayCount = 0; // for compiling the array

        for (String singleFile : fileList) { // loops for the length of the string array that was passed to it

            File[] matchingFiles = dir.listFiles(new FilenameFilter() {

                // not quite sure how this works, need to look a bit more into it. seems to take from the dir variable from above
                public boolean accept(File dir, String name) {
                    return name.contains(singleFile); // looks for the iterated section of the array as a file name in the directory.
                }
            });
            if ((Arrays.toString(matchingFiles) != "[]")) {
                System.out.println("Found " + singleFile + " in " + subDirectory + " folder");
            } else {
                System.out.println("File " + singleFile + " not found");
            }
            System.out.println(Arrays.toString(matchingFiles));
            foundFiles[loopArrayCount] = FileSystems.getDefault().getPath(Arrays.toString(matchingFiles).replace("[", "").replace("]", "")).toAbsolutePath(); // places found file paths into String array for next step
            loopArrayCount++;
        }
        System.out.println(Arrays.toString(foundFiles)); // prints the foundRegions array for testing

        return foundFiles;

    }

    public static void sendOutput(Path[] fileList, String desiredOutput){
        Path folderOut = FileSystems.getDefault().getPath(desiredOutput + "\\").toAbsolutePath();
        System.out.println(folderOut);

        if (! Files.exists(folderOut)){
            System.out.println("Desired output folder not found");

            File create = folderOut.toFile();
            create.mkdir();

        }else{
            System.out.println("Output folder found");
            System.out.println("moving along...");
        }

        for (int i = 0; i < fileList.length; i++){
            Path workingFile = (fileList[i]);

            try{
                Files.copy(workingFile, folderOut, StandardCopyOption.REPLACE_EXISTING);
                System.out.println(workingFile + "copied");
            }catch(java.io.IOException ex){
                System.out.println("file " + workingFile + " not copied");
            }

        }



    }

}

