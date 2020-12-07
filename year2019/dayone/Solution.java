package year2019.dayone;

import java.util.List;

public class Solution {
    public int calculateP1(List<String> inputs) {
        int res = 0;
        for (String input : inputs) {
            int num = Integer.parseInt(input);
            res += (num / 3 - 2);
        }
        return res;
    }

    public int calculateP2(List<String> inputs) {
        int res = 0;
        for (String input : inputs) {
            int num = Integer.parseInt(input);
            res += p2Helper(num);
        }
        return res;
    }

    // calculate recursively how much fuel is needed for mass num
    private int p2Helper(int num) {
        if (num <= 0) {
            return 0;
        }
        int needed = num / 3 - 2;
        if (needed <= 0) {
            return 0;
        }
        return needed + p2Helper(needed);
    }

}
