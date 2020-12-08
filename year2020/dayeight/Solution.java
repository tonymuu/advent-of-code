package year2020.dayeight;

import java.util.List;

public class Solution {
    // use a visited array to save executed instructions
    public int calculateP1(List<String> inputs) {
        int n = inputs.size(), i = 0, res = 0;
        boolean[] visited = new boolean[n];
        while (i < n) {
            if (visited[i]) {
                break;
            }

            String[] segs = inputs.get(i).split(" ");
            String op = segs[0];
            boolean isPos = segs[1].charAt(0) == '+';
            int num = Integer.parseInt(segs[1].substring(1));

            if (!isPos) {
                num = -num;
            }

            visited[i] = true;

            if (op.equals("nop")) {
                i++;
            } else if (op.equals("acc")) {
                res += num;
                i++;
            } else {
                i += num;
            }
        }

        return res;
    }

    // remove cycle in a linkedlist
    // construct a linkedlist
    public int calculateP2(List<String> inputs) {
        int n = inputs.size(), i = 0, lastNegJump = -1, maxJump = 0;
        while (i < n) {
            String ins = inputs.get(i);
            String[] segs = ins.split(" ");
            String op = segs[0];
            boolean isPos = segs[1].charAt(0) == '+';
            int num = Integer.parseInt(segs[1].substring(1));

            if (op.equals("jmp") || op.equals("nop")) {
                String newIns = op.equals("nop") ? "jmp " + segs[1] : "nop " + segs[1];
                inputs.set(i, newIns);
                if (!hasLoop(inputs)) {
                    return calculateP1(inputs);
                }
                inputs.set(i, ins);
            }
            i++;
        }

        return calculateP1(inputs);
    }

    private boolean hasLoop(List<String> inputs) {
        int n = inputs.size(), i = 0;
        boolean[] visited = new boolean[n];
        while (i < n) {
            if (visited[i]) {
                return true;
            }

            String[] segs = inputs.get(i).split(" ");
            String op = segs[0];
            boolean isPos = segs[1].charAt(0) == '+';
            int num = Integer.parseInt(segs[1].substring(1));

            if (!isPos) {
                num = -num;
            }

            visited[i] = true;

            if (op.equals("nop")) {
                i++;
            } else if (op.equals("acc")) {
                i++;
            } else {
                i += num;
            }
        }

        return false;
    }
}
