package battlecode.world.maps;

import battlecode.common.GameConstants;
import battlecode.world.MapBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Generate a map.
 */
public class IceCream {

    // change this!!!
    public static final String mapName = "IceCream";

    // don't change this!!
    public static final String outputDirectory = "engine/src/main/battlecode/world/resources/";

    private static int width;
    private static int height;

    private static boolean usesIndex;

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

    public static ArrayList<ArrayList<Boolean>> makemap(Boolean contents,int w,int h) {
        ArrayList<ArrayList<Boolean>> arr = new ArrayList<>();
        for (int i=0; i<h; i++) {
            ArrayList<Boolean> b = new ArrayList<>();
            for (int j=0; j<w; j++) {
                b.add(contents);
            }
            arr.add(b);
        }
        return arr;
    }

    public static int loc2index(int x, int y) {
        if (usesIndex) {
            return (height - y)*65 + x + 1;
        }
        return (height-1-y)*width + x;
    }

    public static void makeSimple() throws IOException {

        String ds = "64	46	v	20	250	-1	/* Symbols: 'x' for symmetry-inferred, 'w' for infinite-depth water, 'W' for W-depth water, 's' and 'S' for soup, 'c' for cow, 'h' for HQ. Append a number to set elevation. 'Order must be w,s,c,h; so Wsch10' is valid syntactically (but not logically: the HQ can't start in water or have a cow on it) */																																																										 "+
"indx	0	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17	18	19	20	21	22	23	24	25	26	27	28	29	30	31	32	33	34	35	36	37	38	39	40	41	42	43	44	45	46	47	48	49	50	51	52	53	54	55	56	57	58	59	60	61	62	63 "+
"45	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	W	W	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"44	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	w	1	s1000	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"43	1	1	1	c1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	W	W	1	s1000	s1000	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"42	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	c1	1	1	1	1	1	1	1	1	1	W	1	s1000	s1000	s1000	s1000	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"41	1	1	1	1	1	1	1	c1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	W	W	s1000	s1000	s1000	s1000	s1000	s1000	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"40	1	1	1	1	1	1	1	1	1	1	1	1	1	1	S1	1	1	1	1	1	1	1	1	1	W	s1000	s1000	s1000	s1000	s1000	S1000	S1000	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"39	1	1	1	1	1	1	1	1	1	1	1	1	1	s1	S1	1	1	1	1	1	1	1	1	W	s1000	s1000	s1000	s1000	s1000	s1000	S1000	S1000	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"38	2	1	1	1	1	1	1	1	1	1	1	1	1	1	S1	S1	1	1	1	1	1	1	1	1	s1000	s1000	s1000	s1000	s1000	s1000	S1000	S1000	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"37	2	2	2	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	w	1	s1000	s1000	s1000	s1000	s1000	s1000	S1000	S1000	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"36	2	2	2	2	1	1	c1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	W	s1000	s1000	s1000	s1000	s1000	s1000	s1000	s1000	s1000	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"35	2	2	S2	S2	2	1	1	1	1	1	1	c1	1	1	1	1	1	1	1	1	1	1	W	s1000	s1000	s1000	s1000	s1000	s1000	s1000	s1000	s1000	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"34	2	2	S2	S2	S2	2	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	W	s1000	s1000	s1000	s1000	s1000	s1000	s1000	s1000	s1000	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"33	2	h4	4	S2	2	2	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	W	1	s4	s4	s4	s4	S100	s4	s4	s4	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"32	2	4	4	2	2	2	2	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	W	1	s4	S100	s4	s4	s4	s4	s4	s4	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"31	2	2	4	4	2	2	2	2	1	1	1	1	1	1	1	1	1	1	1	1	1	W	1	s4	s4	s4	s4	s4	s4	s4	S100	s4	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"30	2	2	2	4	4	2	2	2	2	1	1	1	1	c1	1	1	1	1	1	1	W	1	s4	s4	s4	s4	S100	s4	s4	s4	s4	s4	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"29	2	2	2	2	4	2	2	2	2	2	1	1	1	1	1	1	1	1	1	1	W	s4	s4	S100	s4	s4	s4	s4	s4	S100	s4	s4	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"28	2	2	2	2	4	4	2	2	2	2	2	1	1	1	1	1	1	1	1	1	W	s4	s4	s4	s4	s4	s4	s4	S100	s4	s4	s4	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"27	2	2	2	2	2	4	4	2	2	2	2	2	1	1	1	1	1	1	1	1	W	s4	s4	s4	s4	S100	s4	s4	s4	s4	s4	s4	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"26	2	2	2	2	2	2	4	2	2	2	2	2	2	1	1	1	1	1	1	1	W	30	s4	s4	s4	s4	s4	S100	s4	s4	S100	s4	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"25	1	2	2	2	2	2	4	4	2	2	2	2	2	1	1	1	1	1	1	1	1	w	30	s4	s4	s4	s4	s4	s4	s4	s4	s4	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"24	1	1	2	2	2	2	2	4	4	2	2	2	2	2	1	1	1	1	1	1	W	1	20	15	15	15	15	15	15	15	15	15	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"23	1	1	1	2	2	2	2	2	4	4	2	2	2	2	2	1	1	1	1	1	W	30	20	20	15	15	15	15	15	15	15	15	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"22	1	1	1	1	2	2	2	2	2	4	4	2	2	2	2	2	1	1	1	1	W	20	30	15	15	15	15	15	15	15	15	15	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"21	1	1	1	1	1	2	2	2	2	2	4	4	4	2	2	2	1	1	1	1	W	20	30	20	15	15	15	15	15	15	15	15	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"20	1	1	1	1	1	2	2	2	2	2	4	s0	S0	4	2	2	2	1	1	1	W	1	20	30	15	15	15	15	15	15	15	15	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"19	1	1	1	1	1	1	2	2	2	2	4	S0	S0	S0	4	2	2	2	1	1	1	W	30	15	15	15	15	15	20	20	15	15	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"18	1	1	1	1	1	1	1	2	2	2	4	S0	S0	S0	4	2	2	2	1	1	1	1	W	1	30	20	20	20	20	20	20	15	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"17	1	1	1	1	1	1	1	1	2	2	2	4	S0	s0	4	2	2	2	1	1	1	1	1	W	W	30	30	20	20	20	20	20	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"16	1	1	1	1	1	1	1	1	1	2	2	2	4	4	4	2	2	2	1	1	1	1	1	1	W	1	30	30	20	20	20	20	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"15	1	1	1	1	1	1	1	1	1	2	2	2	2	2	2	2	2	1	1	1	1	1	1	1	1	1	30	9	20	20	20	20	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"14	1	1	1	1	1	c1	1	1	1	1	2	2	2	2	2	2	2	1	1	1	1	1	1	1	1	W	30	30	30	20	20	20	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"13	1	1	1	1	1	1	1	1	1	1	1	2	2	2	2	2	1	1	1	1	1	1	1	1	1	W	1	30	9	20	20	20	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"12	1	1	1	1	1	1	S1	1	1	1	1	1	2	2	1	1	1	1	1	1	1	1	1	1	1	1	W	30	9	30	20	20	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"11	1	1	1	1	1	S1	s1	S1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	W	1	30	30	20	20	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"10	1	1	1	1	1	1	S1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	30	9	20	20	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"9	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	c1	1	1	1	1	1	1	1	1	1	W	30	30	30	20	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"8	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	W	1	30	30	20	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"7	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	W	30	30	20	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"6	1	1	1	1	1	1	1	1	1	c1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	W	1	30	30	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"5	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	c1	1	1	1	1	1	1	1	20	20	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"4	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	W	30	20	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"3	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	W	1	30	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"2	1	1	1	c1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	w	1	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	W	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"0	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	1	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x	x "+
"-1																																																																 "+
"-2																																																																 "+
"-3																																																																 "+
"-4																																																																 "+
"-5																																																																 "+
"-6																																																																 "+
"-7																																																																 "+
"-8																																																																 "+
"-9																																																																 "+
"-10																																																																 "+
"-11																																																																 "+
"-12																																																																 "+
"-13																																																																 "+
"-14																																																																 "+
"-15																																																																 "+
"-16																																																																 "+
"-17																																																																 "+
"-18																																																																";

        String[] splitDirt = ds.split("\\s+");

        width = Integer.parseInt(splitDirt[0]);
        height = Integer.parseInt(splitDirt[1]);
        MapBuilder mapBuilder = new MapBuilder(mapName, width, height, 43223);
        mapBuilder.setWaterLevel(0);
        String symmetry = splitDirt[2];
        switch (symmetry) {
            case "r":
                mapBuilder.setSymmetry(MapBuilder.MapSymmetry.rotational);
                break;
            case "h":
                mapBuilder.setSymmetry(MapBuilder.MapSymmetry.horizontal);
                break;
            case "v":
                mapBuilder.setSymmetry(MapBuilder.MapSymmetry.vertical);
                break;
            default:
                throw new RuntimeException("symmetry not specified in google sheets!!!");
        }
        int a = Integer.parseInt(splitDirt[3]);
        int b = Integer.parseInt(splitDirt[4]);
        int waterr = Integer.parseInt(splitDirt[5]);

        // check if there's a comment
        int startIndex = 6;
        while (splitDirt[startIndex].equals("/*")) {
            while (!splitDirt[startIndex].equals("*/")) {
                startIndex++;
            }
            startIndex++;
        }

        String[] dirtGrid = Arrays.copyOfRange(splitDirt, startIndex, splitDirt.length);

//        assert dirtGrid.length == width * height;

        if (dirtGrid[0].equals("indx")) {
            usesIndex = true;
        } else {
            usesIndex = false;
        }

        if (usesIndex) {
            assert dirtGrid.length == 65 * 65;
        } else {
            assert dirtGrid.length == width*height;
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int idx = loc2index(x,y);
                if (dirtGrid[idx].equals("x"))
                    continue;
                if (dirtGrid[idx].startsWith("w")) {
                    mapBuilder.setSymmetricWater(x,y,true);
                    mapBuilder.setSymmetricDirt(x,y,GameConstants.MIN_WATER_ELEVATION);
                    dirtGrid[idx] = dirtGrid[idx].substring(1);
                }
                if (dirtGrid[idx].startsWith("W")) {
                    mapBuilder.setSymmetricWater(x,y,true);
                    mapBuilder.setSymmetricDirt(x,y,waterr);
                    dirtGrid[idx] = dirtGrid[idx].substring(1);
                }
                if (dirtGrid[idx].startsWith("s")) {
                    mapBuilder.setSymmetricSoup(x,y,a);
                    dirtGrid[idx] = dirtGrid[idx].substring(1);
                }
                if (dirtGrid[idx].startsWith("S")) {
                    mapBuilder.setSymmetricSoup(x,y,b);
                    dirtGrid[idx] = dirtGrid[idx].substring(1);
                }
                if (dirtGrid[idx].startsWith("c")) {
                    mapBuilder.addSymmetricCow(x,y);
                    dirtGrid[idx] = dirtGrid[idx].substring(1);
                }
                if (dirtGrid[idx].startsWith("h")) {
                    mapBuilder.addSymmetricHQ(x,y);
                    dirtGrid[idx] = dirtGrid[idx].substring(1);
                }
                try {
                    int d = Integer.parseInt(dirtGrid[idx]);
                    mapBuilder.setSymmetricDirt(x,y,d);
                } catch (NumberFormatException e) {
                    System.out.println("INvalid: " + dirtGrid[idx]);
                    System.out.println("Invalid dirt at position (" + x + "," + y + "). Ignoring this.");
                }
            }
        }




        mapBuilder.saveMap(outputDirectory);

    }
    public static void setWaterInCyl(MapBuilder mapBuilder, float centerX, float centerY, float radius) {
        for (int[] point : pointsInCyl(centerX, centerY, radius)) {
            mapBuilder.setSymmetricWater(point[0],point[1],true);
        }
    }
    public static void setHeightInCyl(MapBuilder mapbuilder, float centerX, float centerY, float radius, int height) {
        for (int [] point : pointsInCyl(centerX, centerY, radius)) {
            mapbuilder.setSymmetricDirt(point[0], point[1], height);
        }
    }

    public static void addRectangleDirt(MapBuilder mapBuilder, int xl, int yb, int xr, int yt, int v) {
        for (int i = xl; i < xr+1; i++) {
            for (int j = yb; j < yt+1; j++) {
                mapBuilder.setSymmetricDirt(i, j, v);
                if (((i + j) % 10) == 0 && j >= 35)
                    try {
                        mapBuilder.addSymmetricCow(i, j);
                    } catch (RuntimeException e) {}
            }
        }
    }
    public static void addRectangleWater(MapBuilder mapBuilder, int xl, int yb, int xr, int yt, int v) {
        for (int i = xl; i < xr+1; i++) {
            for (int j = yb; j < yt+1; j++) {
                mapBuilder.setSymmetricDirt(i, j, v);
                mapBuilder.setSymmetricWater(i, j, true);
            }
        }
    }
    public static void setSoupInCyl(MapBuilder mapbuilder, float centerX, float centerY, float radius, int soup) {
        for (int [] point : pointsInCyl(centerX, centerY, radius)) {
            mapbuilder.setSymmetricSoup(point[0], point[1], soup);
        }
    }
    public static int[][] pointsInCyl(float centerX, float centerY, float radius) {
        ArrayList<int[]> points = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2)) <= radius)
                    points.add(new int[]{x, y});
            }
        }
        return points.toArray(new int[0][]);
    }


    /*
     * Add a nice circular lake centered at (x,y).
     */
    public static void addSoup(MapBuilder mapBuilder, int x, int y, int r2, int v) {
        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int d = (xx-x)*(xx-x)/2 + (yy-y)*(yy-y);
                if (d <= r2) {
                    mapBuilder.setSymmetricSoup(xx, yy, v*(d+v));
                }
            }
        }
    }

    /*
     * Add a nice circular lake centered at (x,y).
     */
    public static void addLake(MapBuilder mapBuilder, int x, int y, int r2, int v) {
        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int d = (xx-x)*(xx-x) + (yy-y)*(yy-y);
                if (d <= r2) {
                    mapBuilder.setSymmetricWater(xx, yy, true);
                    mapBuilder.setSymmetricDirt(xx, yy, v);
                }
            }
        }
        mapBuilder.setSymmetricDirt(x, y, GameConstants.MIN_WATER_ELEVATION);
    }
}