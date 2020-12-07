package year2019.daytwo;

import java.util.List;

public class Solution {
    private final int target = 19690720;

    public int calculateP1(List<String> inputs) {
        int[] nums = initArray(inputs, 76, 10);
        return calcP1Helper(nums);
    }

    /**
     * brute force is probably easiest but let's try a recursive approach
     * start from last occurance of output = 0 before first opcode = 99
     */
    public int calculateP2(List<String> inputs) {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                int[] nums = initArray(inputs, i, j);
                if (calcP1Helper(nums) == target) {
                    return 100 * i + j;
                }
            }
        }
        return 0;
    }

    private int calcP1Helper(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i += 4) {
            if (nums[i] == 99) {
                break;
            }
            int in1 = nums[i + 1], in2 = nums[i + 2], out = nums[i + 3];
            nums[out] = nums[i] == 1 ? nums[in1] + nums[in2] : nums[in1] * nums[in2];
        }
        return nums[0];
    }

    private int[] initArray(List<String> inputs, int in1, int in2) {
        String[] strNums = inputs.get(0).split(",");
        int n = strNums.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(strNums[i]);
        }
        nums[1] = in1;
        nums[2] = in2;
        return nums;
    }
}
