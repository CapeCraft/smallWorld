package net.mov51;

public class Main {

    public static void main(String[] args) {

        listRegionsByRadius(0, 0, 750);

    }
    public static void listRegionsByRadius(int x, int z, int radius ){
        int corner1X, corner2X, corner1Z, corner2Z; // initializing corner variables
        corner1X = corner2X = x - radius; // setting x coordinate corner variables to account for radius
        corner1Z = corner2Z = z + radius; // setting z coordinate corner variables to account for radius

        int corner1ChunkX = corner1X / 16; // getting chunk coordinates for the 2 corner positions TODO create a method that returns each coordinates chunk position
        int corner1ChunkZ = corner1Z / 16;
        int corner2ChunkX = corner2X / 16;
        int corner2ChunkZ = corner2Z / 16;

        int corner1RegionX = (int)Math.floor(corner1ChunkX / 32.0); // finding region positions for the 2 corners TODO create a method that returns each coordinates region position
        int corner1RegionZ = (int)Math.floor(corner1ChunkZ / 32.0);
        int corner2RegionX = (int)Math.floor(corner2ChunkX / 32.0);
        int corner2RegionZ = (int)Math.floor(corner2ChunkZ / 32.0);
//        TODO move region listing to "listRegionsByRectangle" and make radius processing cleaner.
        for(int i = corner1RegionZ; i<=corner2RegionZ; i++) { // looping for the length between corner 1 and 2 z
            for(int j = corner1RegionX; j<=corner2RegionX; j++){ // looping for the length between corner 1 and 2 x
                System.out.println("r." + i + "." + j + ".mca"); // printing formatted region file names TODO will need to change output in a way to be processed by another method
            }
        }

    }
    public static void listRegionsByRectangle(int x1, int z1, int x2, int z2){
//        TODO create a method that lists regions from 2 coordinate positions, called by other methods after processing their form of data I.E. radius or a predefined area.
    }
}

