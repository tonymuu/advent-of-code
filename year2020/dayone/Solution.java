package year2020.dayone;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    private final int TWENTY_TWENTY = 2020;

    /**
     * Go through each num in nums, check if 2020-num exists in map
     * if it does, return this pair's multiply
     * otherwise, add it to map/set
     */
    public int calculateP1(List<Integer> nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(TWENTY_TWENTY - num)) {
                return num * (TWENTY_TWENTY - num);
            }
            set.add(num);
        }
        return -1;
    }

    /**
     * similar to above, but instead of find two sum to 2020, find two sum of 2020 - num
     */
    public int calculateP2(List<Integer> nums) {
        Set<Integer> set = new HashSet<>();
        int n = nums.size();
        for (int i = n - 1; i >= 0; i--) {
            int target = TWENTY_TWENTY - nums.get(i);
            for (int j = 0; j < i; j++) {
                if (set.contains(target - nums.get(j))) {
                    return nums.get(i) * nums.get(j) * (target - nums.get(j));
                }
                set.add(nums.get(j));
            }
            set.clear();
        }
        return -1;
    }
}
