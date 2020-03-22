import java.util.*;

public class Main {
    public static void main(String[] args) {

    }

    public boolean hasValidPath(int[][] grid) {
        //  1
        //4   2
        //  3
        if (grid.length == 1 && grid[0].length == 1) return true;
        int[][] dir = new int[][]{{0, 0}, {4, 2}, {1, 3}, {4, 3}, {2, 3}, {1, 4}, {1, 2}};
        int[] dirX = new int[]{0, -1, 0, 1, 0};
        int[] dirY = new int[]{0, 0, 1, 0, -1};

        int i = 0, j = 0, last = 0;
        if (grid[0][0] == 1) {
            j++;
            last = 2;
        } else {
            i++;
            last = 3;
        }
        switch (grid[0][0]) {
            case 1:
                j++;
                last = 2;
                break;
            case 2:
                i++;
                last = 3;
                break;
            case 3:
                i++;
                last = 3;
                break;
            case 4:
                grid[0][0] = 1;
                if (hasValidPath(grid)) return true;
                grid[0][0] = 2;
                if (hasValidPath(grid)) return true;
                return false;
            case 5:
                return false;
            case 6:
                j++;
                last = 2;
                break;
        }
        while (i < grid.length && j < grid[0].length) {
            int key = grid[i][j];
            int inx = 0;
            if (Math.abs(dir[key][inx] - last) != 2) inx++;
            if (inx == 1 && Math.abs(dir[key][inx] - last) != 2) return false;
            if (i == grid.length - 1 && j == grid[0].length - 1) return true;
            int next = inx ^ 1;
            i += dirX[dir[key][next]];
            j += dirY[dir[key][next]];
            last = dir[key][inx ^ 1];
        }
        return false;
    }
}
