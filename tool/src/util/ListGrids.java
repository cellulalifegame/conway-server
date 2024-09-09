package util;

import java.util.ArrayList;
import java.util.List;

public class ListGrids {

    public static List<int[]> getListGrids(int matrix, String pattern, int size) {
        int x = (matrix / size) * size;
        int y = (matrix % size) * size;
        List<int[]> litGrids = new ArrayList<>();
        for (int i = 0; i < (size * size); i++) {
            char digit = pattern.charAt(i);
            if (digit == '1') {
                int row = (i / size) + x;
                int col = (i % size) + y;
                litGrids.add(new int[]{row, col});
            }
        }
        return litGrids;
    }

    public static String getPatternFromListGrids(List<int[]> litGrids, int size) {
        StringBuilder patternBuilder = new StringBuilder();
        int gridSize = size * size;
        for (int i = 0; i < gridSize; i++) {
            int row = i / size;
            int col = i % size;
            boolean isLit = false;
            for (int[] grid : litGrids) {
                if (grid[0] == row && grid[1] == col) {
                    isLit = true;
                    break;
                }
            }
            patternBuilder.append(isLit ? '1' : '0');
        }
        return patternBuilder.toString();
    }

}