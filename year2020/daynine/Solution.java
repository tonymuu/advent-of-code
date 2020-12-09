package year2020.daynine;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public long calculateP1(List<String> inputs) {
        int n = inputs.size();
        Map<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < 25; i++) {
            for (int j = i + 1; j < 25; j++) {
                long num = Long.parseLong(inputs.get(i)) + Long.parseLong(inputs.get(j));
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }

        for (int i = 25; i < n; i++) {
            long num = Long.parseLong(inputs.get(i));
            if (!map.containsKey(num) || map.get(num) == 0) {
                return num;
            }
            for (int j = i - 24; j < i; j++) {
                long curr = Long.parseLong(inputs.get(j)) + Long.parseLong(inputs.get(i - 25));
                map.put(curr, Math.max(map.getOrDefault(curr, 0) - 1, 0));
            }
            for (int j = i - 24; j < i; j++) {
                long curr = Long.parseLong(inputs.get(j)) + num;
                map.put(curr, map.getOrDefault(curr, 0) + 1);
            }
        }

        return -1;
    }

    // sliding window
    public long calculateP2(List<String> inputs) {
        int n = inputs.size(), begin = 0, end = 0;
        long num = calculateP1(inputs), sum = 0;
        Deque<Long> deque = new ArrayDeque<>();
        while (begin < n) {
            while (end < n && sum < num) {
                long curr = Long.parseLong(inputs.get(end));
                sum += curr;
                deque.addLast(curr);
                end++;
            }
            if (sum == num) {
                break;
            }
            if (sum > num) {
                sum -= deque.removeFirst();
                begin++;
            }
        }
        long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
        while (!deque.isEmpty()) {
            long curr = deque.removeFirst();
            min = Math.min(min, curr);
            max = Math.max(max, curr);
        }
        return min + max;
    }
}
