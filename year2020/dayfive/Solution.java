package year2020.dayfive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    /**
     * calc each boarding pass's id, find max
     * see the seatmap as a matrix
     */
    public int calculateP1(List<String> inputs) {
        int res = 0;
        List<Integer> nums = calc(inputs);
        for (int num : nums) {
            res = Math.max(res, num);
        }
        return res;
    }

    public int calculateP2(List<String> inputs) {
        List<Integer> nums = calc(inputs);
        Collections.sort(nums);
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) - 1 != nums.get(i - 1)) {
                return nums.get(i) - 1;
            }
        }
        return -1;
    }

    private List<Integer> calc(List<String> inputs) {
        List<Integer> list = new ArrayList<>();

        for (String input : inputs) {
            int left = 0, right = 7, top = 0, bottom = 127;
            int row = (top + bottom) / 2, col = (left + right) / 2;

            for (int i = 0; i < 7; i++) {
                if (input.charAt(i) == 'F') {
                    bottom = row;
                } else {
                    top = row + 1;
                }
                row = (top + bottom) / 2;
            }

            for (int i = 7; i < 10; i++) {
                if (input.charAt(i) == 'L') {
                    right = col;
                } else {
                    left = col + 1;
                }
                col = (left + right) / 2;
            }

            list.add(row * 8 + col);
        }

        return list;
    }
}
