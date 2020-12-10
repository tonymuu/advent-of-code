package year2020.dayten;

import java.util.Arrays;
import java.util.List;

public class Solution {

    // sort the list and count 1-diff and 3-diff
    public int calculateP1(List<String> inputs) {
        int ones = 0, threes = 0, prev = 0;
        int[] nums = getNums(inputs);
        for (int num : nums) {
            int diff = num - prev;
            if (diff == 3) {
                threes++;
            } else if (diff == 1) {
                ones++;
            }
            prev = num;
        }
        threes++;
        return threes * ones;
    }

    // totalWays(0) = totalWays(1) + total(2) + total(3)
    // if diff is within three, then two choices: take or not take
    // otherwise, only choice is not take
    public long calculateP2(List<String> inputs) {
        int[] nums = getNums(inputs);
        long[] dp = new long[nums.length];
        Arrays.fill(dp, -1);
        return helper(nums, dp, 0, 0);
    }

    private long helper(int[] nums, long[] dp, int index, int prev) {
        int n = nums.length;
        if (index >= n) {
            return 1;
        }
        if (dp[index] >= 0) return dp[index];
        if (nums[index] - prev > 3) return 0;
        if (nums[index] - prev <= 3) {
            dp[index] = helper(nums, dp, index + 1, nums[index]);
        }
        if (index < n - 1 && nums[index + 1] - prev <= 3) {
            dp[index] += helper(nums, dp, index + 2, nums[index + 1]);
        }
        if (index < n - 2 && nums[index + 2] - prev <= 3) {
            dp[index] += helper(nums, dp, index + 3, nums[index + 2]);
        }

        return dp[index];
    }

    private int[] getNums(List<String> inputs) {
        int n = inputs.size();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(inputs.get(i));
        }
        Arrays.sort(nums);
        return nums;
    }

}
