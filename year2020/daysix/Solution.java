package year2020.daysix;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    /**
     * For each group, count the number of unique letters
     */
    public int calculateP1(List<String> inputs) {
        int res = 0;
        Set<Character> set = new HashSet<>();
        for (String input : inputs) {
            if (input.isEmpty()) {
                res += set.size();
                set.clear();
                continue;
            }
            for (char c : input.toCharArray()) {
                set.add(c);
            }
        }
        res += set.size();
        return res;
    }

    /**
     * use a map to store count of each yes
     * also keep track of how many ppl in the group
     */
    public int calculateP2(List<String> inputs) {
        int res = 0, numPeople = 0;
        int[] map = new int[26];
        for (String input : inputs) {
            if (input.isEmpty()) {
                for (int i = 0; i < 26; i++) {
                    if (map[i] == numPeople) {
                        res++;
                    }
                    map[i] = 0;
                }
                numPeople = 0;
                continue;
            }
            numPeople++;
            for (char c : input.toCharArray()) {
                map[c - 'a']++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (map[i] == numPeople) {
                res++;
            }
        }
        return res;
    }
}
