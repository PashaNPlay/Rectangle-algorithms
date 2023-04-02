/* 
Rectangle-algorithms
*/

public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        int sideLength = a.length;
        int rectangleCount = 0;
        int[] coordinates = new int[4];

        coordinates = getRectangleCoordinates(a);

        while (coordinates != null) {

            for (int y = coordinates[0]; y <= coordinates[2]; y++) {
                for (int x = coordinates[1]; x <= coordinates[3]; x++) {
                    a[y][x] = 0;
                }
            }
            rectangleCount++;
            coordinates = getRectangleCoordinates(a);
        }

        //// print array
        /*
        for (byte[] y : a) {
            for (byte x : y) {
                System.out.print(x + "  ");
            }
            System.out.println();
        }
        */

        return rectangleCount;
    }

    public static int[] getRectangleCoordinates(byte[][] a) {
        int[] coordinates = {-1, 0, 0, 0};
        int sideLength = a.length;

        //////////search for top left point
        outerLoop:
        for (int y = 0; y < sideLength; y++) {
            for (int x = 0; x < sideLength; x++) {
                if (a[y][x] == 1) {
                    coordinates[0] = y;
                    coordinates[1] = x;
                    break outerLoop;
                }
            }
        }
        if (coordinates[0] < 0) return null;

        //////////search for low left point
        for (int y1 = coordinates[0]; y1 < sideLength; y1++) {
            if (a[y1][coordinates[1]] == 0) {
                coordinates[2] = y1 - 1;
                break;
            }
            coordinates[2] = sideLength - 1;
        }

        //////////search for low right point
        for (int x1 = coordinates[1]; x1 < sideLength; x1++) {
            if (a[coordinates[2]][x1] == 0) {
                coordinates[3] = x1 - 1;
                break;
            }
            coordinates[3] = sideLength - 1;
        }


        //////////coordinate check

        /*System.out.println("y = " + coordinates[0]);
        System.out.println("x = " + coordinates[1]);
        System.out.println("y1 = " + coordinates[2]);
        System.out.println("x1 = " + coordinates[3]);*/

        return coordinates;
    }
}
