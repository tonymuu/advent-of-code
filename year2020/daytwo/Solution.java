package year2020.daytwo;

import java.util.List;

public class Solution {
    // 1-3 a: abcde
    public int calculateP1(List<String> inputs) {
        int res = inputs.size();
        for (String input : inputs) {
            String[] segs = input.split(" ");
            String[] limits = segs[0].split("-");
            char rule = segs[1].charAt(0);
            String pw = segs[2];

            int lower = Integer.parseInt(limits[0]), upper = Integer.parseInt(limits[1]), count = 0;

            for (char c : pw.toCharArray()) {
                if (c == rule) count++;
            }

            if (count > upper || count < lower) {
                res--;
            }
        }
        return res;
    }

    public int calculateP2(List<String> inputs) {
        int res = inputs.size();
        for (String input : inputs) {
            String[] segs = input.split(" ");
            String[] positions = segs[0].split("-");
            char rule = segs[1].charAt(0);
            String pw = segs[2];

            int first = Integer.parseInt(positions[0]) - 1,
                second = Integer.parseInt(positions[1]) - 1;

            if ((pw.charAt(first) == rule && pw.charAt(second) == rule) ||
                (pw.charAt(first) != rule && pw.charAt(second) != rule)) {
                res--;
            }
        }
        return res;
    }
}
