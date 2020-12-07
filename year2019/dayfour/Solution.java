package year2019.dayfour;

import java.util.List;

    //
public class Solution {
    // brute force is to increment starting from low, and keep checking if all rules apply
    public int calculateP1(List<String> inputs) {
        String[] segs = inputs.get(0).split("-");
        int low = Integer.parseInt(segs[0]), high = Integer.parseInt(segs[1]), res = 0;
        while (low <= high) {
            if (rulesApply(low)) {
                res++;
            }
            low++;
        }
        return res;
    }

    public int calculateP2(List<String> inputs) {
        String[] segs = inputs.get(0).split("-");
        int low = Integer.parseInt(segs[0]), high = Integer.parseInt(segs[1]), res = 0;
        while (low <= high) {
            if (rulesApplyP2(low)) {
                res++;
            }
            low++;
        }
        return res;
    }

    private boolean rulesApply(int num) {
        boolean hasDouble = false;
        int prev = 10; // prev digit, init to 10 so first never fails
        while (num > 0) {
            int curr = num % 10; // current digit
            if (curr > prev) { // if previous digit is smaller than current digit (right to left)
                return false;
            }
            if (prev == curr) {
                hasDouble = true;
            }
            num /= 10;
            prev = curr;
        }
        return hasDouble; // non-decreasing, then res is determined by having doubles or not
    }

    private boolean rulesApplyP2(int num) {
        String s = String.valueOf(num);
        boolean hasDouble = false;
        int i = s.length() - 1;
        while (i > 0) {
            int prev = i;
            while (i >= 0 && s.charAt(i) == s.charAt(prev)) {
                i--;
            }
            // i is at first index of differing char or -1
            if (prev - i == 2) {
                hasDouble = true;
            }
            if (i >= 0 && s.charAt(i) > s.charAt(prev)) {
                // if previous digit is smaller than current digit (right to left)
                return false;
            }
        }
        return hasDouble; // non-decreasing, then res is determined by having doubles or not
    }
}
