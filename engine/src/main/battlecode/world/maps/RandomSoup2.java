package battlecode.world.maps;

import battlecode.world.MapBuilder;

import java.io.IOException;

/**
 * Generate a map.
 */
public class RandomSoup2 {

    // change this!!!
    // this needs to be the same as the name of the file
    // it also cannot be the same as the name of an existing engine map
    public static final String mapName = "RandomSoup2";

    // don't change this!!
    public static final String outputDirectory = "engine/src/main/battlecode/world/resources/";

    private static int width;
    private static int height;

    /**
     * @param args unused
     */
    public static void main(String[] args) {
        try {
            makeSimple();
        } catch (IOException e) {
            System.out.println(e);
        }
        System.out.println("Generated a map!");
    }

    public static void makeSimple() throws IOException {
        width = 64;
        height = 64;
        MapBuilder mapBuilder = new MapBuilder(mapName, width, height, 2739853);
        mapBuilder.setWaterLevel(0);
        mapBuilder.setSymmetry(MapBuilder.MapSymmetry.rotational);
        mapBuilder.addSymmetricHQ(0, 0);

        addRectangleSoup(mapBuilder, 3, 4, 5, 6, 130);
        addRectangleSoup(mapBuilder, 1, 11, 2, 14, 90);
        addRectangleSoup(mapBuilder, 6, 8, 8, 10, 50);
        addRectangleSoup(mapBuilder, 11, 20, 13, 21, 330);
        addRectangleSoup(mapBuilder, 7, 20, 7+1, 20+1, 80);
        addRectangleSoup(mapBuilder, 18, 1, 18+1, 1+1, 150);
        addRectangleSoup(mapBuilder, 18, 3, 18+1, 3+1, 280);
        addRectangleSoup(mapBuilder, 19, 5, 19+1, 5+1, 83);
        addRectangleSoup(mapBuilder, 17, 4, 17+1, 4+1, 280);
        addRectangleSoup(mapBuilder, 18, 0, 18+1,0+1, 280);
        addRectangleSoup(mapBuilder, 50, 5, 53, 5+1, 60);
        addRectangleSoup(mapBuilder, 20, 4, 20+1, 4+1, 50);
        addRectangleSoup(mapBuilder, 21, 17, 21+1, 17, 60);
        addRectangleSoup(mapBuilder, 24, 7, 24+1, 7+1, 130);
        addRectangleSoup(mapBuilder, 27, 8, 27+1, 8+1, 24);
        addRectangleSoup(mapBuilder, 30, 5, 30+1, 5+1, 50);
        addRectangleSoup(mapBuilder, 34, 20, 34+1, 20, 100);
        addRectangleSoup(mapBuilder, 34, 15, 34+1, 15, 140);
        addRectangleSoup(mapBuilder, 35, 4, 35+1, 4+1, 64);
        addRectangleSoup(mapBuilder, 39, 25, 39+1, 25, 80);
        addRectangleSoup(mapBuilder, 41, 16, 41+1, 16, 80);
        addRectangleSoup(mapBuilder, 42, 9, 42+1, 9+1, 150);
        addRectangleSoup(mapBuilder, 44, 1, 44+1, 1+1, 30);
        addRectangleSoup(mapBuilder, 46, 5, 46+1, 5+1, 60);
        addRectangleSoup(mapBuilder, 49, 4, 49+1, 4+1, 50);
        addRectangleSoup(mapBuilder, 52, 17, 52+1, 17+1, 60);


        for(int i = 0; i < mapBuilder.width; i++) {
            for (int j = 0; j < mapBuilder.height; j++) {
                mapBuilder.setSymmetricDirt(i, j, 3);
            }
        }

        for(int i = 11; i < mapBuilder.width-35; i++) {
            for (int j = 25; j < mapBuilder.height-30; j++) {
                mapBuilder.setSymmetricWater(i,j,true);
                mapBuilder.setSymmetricDirt(i, j, Integer.MIN_VALUE / 2);
            }
        }

        mapBuilder.addSymmetricCow(5, 18);
        mapBuilder.addSymmetricCow(17, 3);

        mapBuilder.saveMap(outputDirectory);

    }

    public static void addRectangleSoup(MapBuilder mapBuilder, int xl, int yb, int xr, int yt, int v) {
        for (int i = xl; i < xr+1; i++) {
            for (int j = yb; j < yt+1; j++) {
                mapBuilder.setSymmetricSoup(i, j, v);
            }
        }
    }
}
