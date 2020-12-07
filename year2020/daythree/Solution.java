package year2020.daythree;

import java.util.List;

public class Solution {
    /**
     * iterate until reached bottom
     * if reached right end of the list, go back (because it repeats)
     * can use mod like so: (j + 3) % list[0].size()
     * i: row, j: col
     * rm: row movement, cm: col movement
     */
    public int calculate(List<String> inputs, int rowMove, int colMove) {
        int res = 0, i = 0, j = 0, size = inputs.get(0).length();
        while (i < inputs.size()) {
            if (inputs.get(i).charAt(j) == '#') {
                res++;
            }
            i += rowMove;
            j = (j + colMove) % size;
        }
        return res;
    }

    public int calculateP1(List<String> inputs) {
        return calculate(inputs, 1, 3);
    }

    public int calculateP2(List<String> inputs) {
        int res = 1;
        res *= calculate(inputs, 1, 1);
        res *= calculate(inputs, 1, 3);
        res *= calculate(inputs, 1, 5);
        res *= calculate(inputs, 1, 7);
        res *= calculate(inputs, 2, 1);
        return res;
    }
}
