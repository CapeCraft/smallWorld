package net.mov51;

public class Main {

    public static void main(String[] args) {

        listRegionsByRadius(0, 0, 750);

    }
    public static void listRegionsByRadius(int x, int z, int radius ){
        int corner1X = x - radius;
        int corner1Z = z - radius;
        int corner2X = x + radius;
        int corner2Z = z + radius;

        double corner1ChunkX = corner1X / 16;
        double corner1ChunkZ = corner1Z / 16;
        double corner2ChunkX = corner2X / 16;
        double corner2ChunkZ = corner2Z / 16;

        int corner1RegionX = (int)Math.floor(corner1ChunkX / 32.0);
        int corner1RegionZ = (int)Math.floor(corner1ChunkZ / 32.0);
        int corner2RegionX = (int)Math.floor(corner2ChunkX / 32.0);
        int corner2RegionZ = (int)Math.floor(corner2ChunkZ / 32.0);

        for(int i = corner1RegionZ; i<=corner2RegionZ; i++) {
            for(int j = corner1RegionX; j<=corner2RegionX; j++){
                System.out.println("r." + i + "." + j + ".mca");
            }
        }
    }
}

