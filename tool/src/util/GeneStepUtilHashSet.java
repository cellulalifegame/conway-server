package util;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class GeneStepUtilHashSet {
    private Set<Cell> activeCells;
    private Set<Cell> previousActiveCells;

    public GeneStepUtilHashSet() {
        activeCells = new HashSet<>();
    }

    public void addCell(int x, int y) {
        activeCells.add(new Cell(x, y));
    }

    public void removeCell(int x, int y) {
        activeCells.remove(new Cell(x, y));
    }

    public boolean isActive(int x, int y) {
        return activeCells.contains(new Cell(x, y));
    }

    private Set<Cell> getNeighbors(int x, int y) {
        Set<Cell> neighbors = new HashSet<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                neighbors.add(new Cell(x + dx, y + dy));
            }
        }
        return neighbors;
    }

    private int countActiveNeighbors(int x, int y) {
        int count = 0;
        for (Cell neighbor : getNeighbors(x, y)) {
            if (isActive(neighbor.getX(), neighbor.getY())) {
                count++;
            }
        }
        return count;
    }

    public Set<Cell> step() {
        Set<Cell> toEvaluate = new HashSet<>();
        Set<Cell> nextState = new HashSet<>();

        for (Cell cell : activeCells) {
            int x = cell.getX();
            int y = cell.getY();
            toEvaluate.add(new Cell(x, y));
            toEvaluate.addAll(getNeighbors(x, y));
        }

        for (Cell cell : toEvaluate) {
            int x = cell.getX();
            int y = cell.getY();
            boolean isActive = isActive(x, y);
            int activeNeighbors = countActiveNeighbors(x, y);
            if ((isActive && (activeNeighbors == 2 || activeNeighbors == 3)) || (!isActive && activeNeighbors == 3)) {
                nextState.add(new Cell(x, y));
            }
        }
        activeCells = nextState;
        return activeCells;
    }


    public Set<Cell> reverseStep() {
        Set<Cell> temp = activeCells;
        activeCells = previousActiveCells;
        previousActiveCells = temp;
        return activeCells;
    }

    public static class Cell {
        private int x;
        private int y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return x == cell.x && y == cell.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }


}
