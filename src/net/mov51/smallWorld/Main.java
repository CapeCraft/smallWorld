package net.mov51.smallWorld;

import org.apache.logging.log4j.*;

import static net.mov51.smallWorld.util.IO.prepareWorld;
import static net.mov51.smallWorld.worldSearch.*;

import java.util.*;
import java.util.Scanner;

public class Main {

    private static Logger logger = LogManager.getRootLogger();

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

            logger.debug("Arguments are as follows " + Arrays.toString(args));
            switch (args[0]) {
                case "radius":
                    if (args.length == 1) {
                        logger.error("You didn't provide any parameters for the radius option ");
                        logger.error("To learn what those are go here https://github.com/mov51/smallWorld/wiki/radius-area");
                        System.exit(1);
                    }
                    if (args.length > 3 && args[3] != null) {
                        listUUIDsByString(args[3]);
                    }

                    listRegionsByRadius(args[1]);

                    prepareWorld();


                    break;

                case "rectangle":
                    if (args.length < 2) {
                        logger.error("You didn't provide any parameters for the rectangle option ");
                        logger.error("To learn what those are go here https://github.com/mov51/smallWorld/wiki/rectangle-area");
                        System.exit(1);
                    }
                    if (args.length > 2 && args[2] != null) {
                        listUUIDsByString(args[2]);
                    }

                    listRegionsByRectangle(args[1]);

                    break;
                case "endReset":
                    if (args.length == 1){
                        logger.warn("You have selected the default end reset mode!");
                        logger.warn("Only do this if you know what it does, you can loose data!");
                        logger.warn("Information on the different reset mods can be found here"); //TODO add wiki link!
                        askAgain("Do you want to reset the end?");

                    }else if(args.length == 2){
                        String backupMode = args[1];
                        logger.warn("backup mode is " + args[1]);

                        switch (backupMode) {
                            case "fullBackup":
                                logger.info("fullBackup mode selected!");
                                break;
                            case "partialBackup":
                                logger.info("partialBackup mode selected!");
                                break;
                            case "backupBoth":
                                logger.info("backupBoth mode selected!");
                                break;
                            default:
                                logger.info("no backup mode matching your selection found!");
                                break;
                        }
                    }else if(args[2] != null && args[3] != null){
                        switch (args[2]){
                            case"rectangle":
                                listRegionsByRectangle(args[3]);
                                break;
                            case"radius":
                                listRegionsByRadius(args[2]);
                        }
                    }


                    break;
                case "preset":

                    break;
                default :
                    logger.error("No mode matching your selection found!");
                    System.exit(1);


            }


        }

    }
    private static void askAgain(String ask){
        System.out.println(ask);
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        if (input.equals("yes") || input.equals("y")){
            logger.warn("yes!");
        }else if(input.equals("no") || input.equals("n")){
            logger.warn("ok! make sure to check out the documentation!"); //TODO add link to documentation!
            System.exit(0);
        }else{
            askAgain(ask);
        }
    }














}



