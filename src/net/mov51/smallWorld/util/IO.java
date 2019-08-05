package net.mov51.smallWorld.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

public class IO {
    private static Logger logger = LogManager.getRootLogger();

    public static void sendOutput(Path[] fileList) {
        logger.info("list of files to move = " + Arrays.toString(fileList));
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].toString().length() == 0) {
                continue;
            }

            File create = new File(System.getProperty("output"));
            if (System.getProperty("output") != null) {
                if (System.getProperty("output").matches("\\$saves(.*)")) {
                    URI Folder = Paths.get(System.getenv("AppData"), ".minecraft", "saves", System.getProperty("output").replaceAll("\\$saves\\\\", " ")).toUri();
                    logger.debug("Folder = " + Folder);
                    logger.info("File = " + fileList[i].getFileName());
                    create = new File(Folder);
                } else {
                    create = new File(System.getProperty("output"));
                }

            }

            Path pathCreate = create.toPath().resolve(fileList[i].getParent());
            Path folderOut = pathCreate.resolve(fileList[i].getFileName());

            if (!Files.exists(pathCreate)) {
                logger.warn("Desired output folder not found");
                try {
                    boolean worked = pathCreate.toFile().mkdirs();
                    logger.warn("has output file been created? = " + worked);
                    logger.warn(pathCreate);
                } catch (Exception e) {
                    logger.error("Failed to create output file");
                    logger.error(pathCreate);
                    e.printStackTrace();
                }

            } else {
                logger.debug("Output folder found");
                logger.debug(pathCreate);
            }
            Path workingFile = (fileList[i]);

            try {
                Files.copy(workingFile, folderOut, StandardCopyOption.REPLACE_EXISTING);
                logger.debug(workingFile + " copied");
            } catch (java.io.IOException e) {
                logger.error("file " + workingFile + " not copied");
                logger.error(workingFile + " is the working file");
                e.printStackTrace();
            }
        }
    }

        public static void prepareWorld(){
            Path[] fred  = new Path[1];
            fred[0] = Paths.get(".\\level.dat");
            sendOutput(fred);
        }

    }
