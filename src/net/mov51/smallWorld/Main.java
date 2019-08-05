package net.mov51.smallWorld;

import org.apache.logging.log4j.*;

import static net.mov51.smallWorld.util.IO.prepareWorld;
import static net.mov51.smallWorld.worldSearch.*;

import java.util.*;

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

                    listRegionsByRadius(args[1], args[2]);

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
                    //TODO take arguments for end reset
                    //back up mode | 1 should i back up the center islands | 2 should i backup the outer islands | 3 should i back them both up | 4 should they be together
                    //Saved areas | areas to keep and export back into the world after reset
                    //

                    break;
                case "preset":

                    break;
                default :
                    logger.error("No mode found matching your selection");
                    System.exit(1);


            }


        }

    }















}



