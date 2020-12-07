package year2020.dayseven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {

    // parse the input and build a graph - can use adjacency list to represent graph
    // search from shiny gold bag and count how many nodes are connected (directly/indirectly) to it
    // graph can look something like "color" => list of colors contains it
    // once we have graph we can start dfs from "shiny gold" bag
    public int calculateP1(List<String> inputs) {
        Map<String, List<String>> graph = new HashMap<>();
        parseInputProblemOne(inputs, graph);
        // minus one here because gold cannot contain itself
        return dfsProblemOne(graph, new HashSet<String>(), "shiny gold") - 1;
    }

    // similar to above, except we want to switch graph mapping
    // e.g. container => [list of containees]
    // dfs starting from shiny gold, also include nums. e.g. "shiny gold" => ["132 muted yellow"]
    public int calculateP2(List<String> inputs) {
        Map<String, List<String>> graph = new HashMap<>();
        parseInputProblemTwo(inputs, graph);
        return dfsProblemTwo(graph, "shiny gold");
    }

    private int dfsProblemOne(Map<String, List<String>> graph, Set<String> visited, String curr) {
        if (visited.contains(curr)) {
            return 0;
        }
        visited.add(curr);
        if (!graph.containsKey(curr)) {
            return 1;
        }
        List<String> neighbours = graph.get(curr);
        int res = 0;
        for (String n : neighbours) {
            res += dfsProblemOne(graph, visited, n);
        }
        return res + 1;
    }

    private int dfsProblemTwo(Map<String, List<String>> graph, String curr) {
        List<String> neighbours = graph.get(curr);
        int res = 0;
        for (String n : neighbours) {
            int begin = 0, end = 0;
            while (!Character.isDigit(n.charAt(begin++)));
            // begin at digit pos + 1
            end = begin;
            while (n.charAt(end++) != ' ');
            // end at first letter after digit and whitespace
            // how many this bags are there
            int num = Integer.parseInt(n.substring(begin - 1, end - 1));
            // for each of this bag, it contains this many child bags
            int count = dfsProblemTwo(graph, n.substring(end));
            System.out.println(num + ", " + count);
            res += num * count + num;
        }
        return res;
    }

    private void parseInputProblemOne(List<String> inputs, Map<String, List<String>> graph) {
        for (String input : inputs) {
            String[] segs = input.split(" bags contain "), contained = segs[1].split(" bag");
            String container = segs[0];

            for (String s : contained) {
                int begin = 0;
                while (begin < s.length() && !Character.isDigit(s.charAt(begin++)));
                // begin now is at digit or at end of string
                if (begin < s.length()) {
                    String containee = s.substring(begin + 1);
                    if (!graph.containsKey(containee)) {
                        graph.put(containee, new ArrayList<String>());
                    }
                    graph.get(containee).add(container);
                }
            }
        }
    }

    private void parseInputProblemTwo(List<String> inputs, Map<String, List<String>> graph) {
        for (String input : inputs) {
            String[] segs = input.split(" bags contain "), contained = segs[1].split(" bag");
            String container = segs[0];

            if (!graph.containsKey(container)) {
                graph.put(container, new ArrayList<String>());
            }

            for (String s : contained) {
                int begin = 0;
                while (begin < s.length() && !Character.isDigit(s.charAt(begin++)));
                // begin now is at digit + 1 or at end of string
                if (begin < s.length()) {
                    String containee = s.substring(begin - 1);
                    graph.get(container).add(containee);
                }
            }
        }

        // for (String k : graph.keySet()) {
        //     System.out.println(k + " => " + graph.get(k).toString());
        // }
    }

}
