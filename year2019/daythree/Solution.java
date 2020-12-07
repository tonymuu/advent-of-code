package year2019.daythree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {
    // add all points on path to a set. points are serialized to "{x} {y}"
    public int calculateP1(List<String> inputs) {
        String[] path1 = inputs.get(0).split(","), path2 = inputs.get(1).split(",");
        Map<String, Integer> map = new HashMap<>();
        Set<String> intersect = new HashSet<>();
        int res = Integer.MAX_VALUE, x = 0, y = 0;

        populateWireOnePath(map, path1);
        populateIntersections(intersect, map, path2);

        for (String point : intersect) {
            String[] strNums = point.split(" ");
            x = Integer.parseInt(strNums[0]);
            y = Integer.parseInt(strNums[1]);
            res = Math.min(res, Math.abs(x) + Math.abs(y));
        }

        return res;
    }

    public int calculateP2(List<String> inputs) {
        String[] path1 = inputs.get(0).split(","), path2 = inputs.get(1).split(",");
        Map<String, Integer> map = new HashMap<>();
        Set<String> intersect = new HashSet<>();
        int res = Integer.MAX_VALUE, x = 0, y = 0;

        populateWireOnePath(map, path1);
        populateIntersections(intersect, map, path2);

        for (String point : intersect) {
            res = Math.min(res, map.get(point));
        }

        return res;
    }


    private void populateWireOnePath(Map<String, Integer> map, String[] path1) {
        int x = 0, y = 0, currStep = 0; // start from (0, 0)
        for (String s : path1) {
            char direction = s.charAt(0);
            int num = Integer.parseInt(s.substring(1));
            if (direction == 'U') {
                while (num > 0) {
                    x++;
                    num--;
                    map.put(x + " " + y, ++currStep);
                }
            } else if (direction == 'D') {
                while (num > 0) {
                    x--;
                    num--;
                    map.put(x + " " + y, ++currStep);
                }
            } else if (direction == 'L') {
                while (num > 0) {
                    y--;
                    num--;
                    map.put(x + " " + y, ++currStep);
                }
            } else if (direction == 'R') {
                while (num > 0) {
                    y++;
                    num--;
                    map.put(x + " " + y, ++currStep);
                }
            }
        }
    }

    private void populateIntersections(
        Set<String> intersect, Map<String, Integer> map, String[] path2
        ) {
        int x = 0, y = 0, steps = 0;

        for (String s : path2) {
            char direction = s.charAt(0);
            int num = Integer.parseInt(s.substring(1));
            if (direction == 'U') {
                while (num > 0) {
                    x++;
                    num--;
                    steps++;
                    String curr = x + " " + y;
                    if (map.containsKey(curr)) {
                        intersect.add(curr);
                        map.put(curr, map.get(curr) + steps);
                    }
                }
            } else if (direction == 'D') {
                while (num > 0) {
                    x--;
                    num--;
                    steps++;
                    String curr = x + " " + y;
                    if (map.containsKey(curr)) {
                        intersect.add(curr);
                        map.put(curr, map.get(curr) + steps);
                    }
                }
            } else if (direction == 'L') {
                while (num > 0) {
                    y--;
                    num--;
                    steps++;
                    String curr = x + " " + y;
                    if (map.containsKey(curr)) {
                        intersect.add(curr);
                        map.put(curr, map.get(curr) + steps);
                    }
                }
            } else if (direction == 'R') {
                while (num > 0) {
                    y++;
                    num--;
                    steps++;
                    String curr = x + " " + y;
                    if (map.containsKey(curr)) {
                        intersect.add(curr);
                        map.put(curr, map.get(curr) + steps);
                    }
                }
            }
        }
    }
}
