package year2020.dayeleven;

import java.util.Arrays;
import java.util.List;

public class Solution {
    List<String> inputs;

    private final char empty = 'L';
    private final char occupied = '#';
    private final char floor = '.';

    private int[][] coords;

    public Solution(List<String> inputs) {
        this.inputs = inputs;
        this.coords = new int[][] {
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1}
        };
    }

    public int calculateP1() {
        char[][] matrix = initMatrix(), updated = copyMatrix(matrix);
        int m = matrix.length, n = matrix[0].length, change = 1;
        while (change > 0) {
            change = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == empty) {
                        boolean isEmpty = true;
                        for (int[] coord : coords) {
                            int x = j + coord[1], y = i + coord[0];
                            if (y >= 0 && y < m && x >= 0 && x < n && matrix[y][x] == occupied) {
                                isEmpty = false;
                                break;
                            }
                        }
                        if (isEmpty) {
                            updated[i][j] = occupied;
                            change++;
                        }
                    } else if (matrix[i][j] == occupied) {
                        int count = 0;
                        for (int[] coord : coords) {
                            int x = j + coord[1], y = i + coord[0];
                            if (y >= 0 && y < m && x >= 0 && x < n && matrix[y][x] == occupied) {
                                count++;
                            }
                        }
                        if (count >= 4) {
                            updated[i][j] = empty;
                            change++;
                        }
                    }
                }
            }
            matrix = updated;
            updated = copyMatrix(matrix);
        }

        return countSeats(matrix);
    }

    public int calculateP2() {
        char[][] matrix = initMatrix(), updated = copyMatrix(matrix);
        int m = matrix.length, n = matrix[0].length, change = 1;
        while (change > 0) {
            change = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == floor) {
                        continue;
                    }
                    int count = countOccupiedSeats(matrix, i, j);
                    if (matrix[i][j] == empty && count == 0) {
                        updated[i][j] = occupied;
                        change++;
                    } else if (matrix[i][j] == occupied && count >= 5) {
                        updated[i][j] = empty;
                        change++;
                    }
                }
            }
            matrix = updated;
            updated = copyMatrix(matrix);
        }

        return countSeats(matrix);
    }

    private int countOccupiedSeats(char[][] matrix, int i, int j) {
        int res = 0, m = matrix.length, n = matrix[0].length, offset = 1;
        int[] dirs = new int[8];
        Arrays.fill(dirs, -1);
        for (int row = i - 1; row >= 0; row--) {
            if (matrix[row][j] != floor && dirs[0] < 0) {
                dirs[0] = matrix[row][j] == occupied ? 1 : 0;
            }
            if (j + offset < n && matrix[row][j + offset] != floor && dirs[1] < 0) {
                dirs[1] = matrix[row][j + offset] == occupied ? 1 : 0;
            }
            if (j - offset >= 0 && matrix[row][j - offset] != floor && dirs[2] < 0) {
                dirs[2] = matrix[row][j - offset] == occupied ? 1 : 0;
            }
            offset++;
        }
        offset = 1;
        for (int row = i + 1; row < m; row++) {
            if (matrix[row][j] != floor && dirs[3] < 0) {
                dirs[3] = matrix[row][j] == occupied ? 1 : 0;
            }
            if (j + offset < n && matrix[row][j + offset] != floor && dirs[4] < 0) {
                dirs[4] = matrix[row][j + offset] == occupied ? 1 : 0;
            }
            if (j - offset >= 0 && matrix[row][j - offset] != floor && dirs[5] < 0) {
                dirs[5] = matrix[row][j - offset] == occupied ? 1 : 0;
            }
            offset++;
        }
        for (int col = j + 1; col < n; col++) {
            if (matrix[i][col] != floor && dirs[6] < 0) {
                dirs[6] = matrix[i][col] == occupied ? 1 : 0;
            }
        }
        for (int col = j - 1; col >= 0; col--) {
            if (matrix[i][col] != floor && dirs[7] < 0) {
                dirs[7] = matrix[i][col] == occupied ? 1 : 0;
            }
        }
        for (int dir : dirs) {
            res += Math.max(0, dir);
        }
        return res;
    }

    private char[][] initMatrix() {
        int m = inputs.size(), n = inputs.get(0).length();
        char[][] matrix = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = inputs.get(i).charAt(j);
            }
        }
        return matrix;
    }

    private char[][] copyMatrix(char[][] input) {
        int m = input.length, n = input[0].length;
        char[][] matrix = new char[m][n];
        for (int i = 0; i < m; i++) {
            matrix[i] = Arrays.copyOf(input[i], input[i].length);
        }
        return matrix;
    }

    private int countSeats(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length, res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == occupied) {
                    res++;
                }
            }
        }
        return res;
    }
}
