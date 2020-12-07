package year2020.dayfour;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution {
    Set<String> set;
    Map<String, Rule> map;
    private final int REQUIRED = 7;

    public void initSet() {
        set = new HashSet<>();
        set.add("byr");
        set.add("iyr");
        set.add("eyr");
        set.add("hgt");
        set.add("hcl");
        set.add("ecl");
        set.add("pid");
    }

    public void initMap() {
        map = new HashMap<>();
        map.put("byr", new BirthYearRule());
        map.put("iyr", new IssueYearRule());
        map.put("eyr", new ExpYearRule());
        map.put("hgt", new HeightRule());
        map.put("hcl", new HairColorRule());
        map.put("ecl", new EyeColorRule());
        map.put("pid", new PassportNumRule());
    }

    /**
     * check if all 7 required fields present, count them
     * if not, invalid
     * for each line, if empty, calc previous passport
     * if not empty, process fields present and increment count
     */
    public int calculateP1(List<String> inputs) {
        initSet();
        int res = 0, count = 0;
        for (String input : inputs) {
            if (input.isEmpty()) {
                res += count == REQUIRED ? 1 : 0;
                count = 0;
                continue;
            }
            String[] fields = input.split(" ");
            for (String field : fields) {
                if (set.contains(field.split(":")[0])) {
                    count++;
                }
            }
        }
        return res;
    }

    public int calculateP2(List<String> inputs) {
        initMap();
        boolean isValid = true;
        int res = 0, count = 0, n = inputs.size();
        for (int i = 0; i < n; i++) {
            String input = inputs.get(i);
            if (input.isEmpty()) {
                res += (isValid && count == REQUIRED) ? 1 : 0;
                isValid = true;
                count = 0;
                continue;
            }

            if (!isValid) {
                continue;
            }

            String[] fields = input.split(" ");
            for (String field : fields) {
                String[] pair = field.split(":");
                String key = pair[0], value = pair[1];
                if (map.containsKey(key)) {
                    count++;
                    isValid = map.get(key).apply(value);
                }
                if (!isValid) {
                    break;
                }
            }
        }
        return res;
    }
}

interface Rule {
    public boolean apply(String val);
}

class BirthYearRule implements Rule {
    public boolean apply(String val) {
        int num = Integer.parseInt(val);
        return val.length() == 4 && num >= 1920 && num <= 2002;
    }
}

class IssueYearRule implements Rule {
    public boolean apply(String val) {
        int num = Integer.parseInt(val);
        return val.length() == 4 && num >= 2010 && num <= 2020;
    }
}

class ExpYearRule implements Rule {
    public boolean apply(String val) {
        int num = Integer.parseInt(val);
        return val.length() == 4 && num >= 2020 && num <= 2030;
    }
}

class HeightRule implements Rule {
    public boolean apply(String val) {
        int i = 0, n = val.length();
        while (i < n && Character.isDigit(val.charAt(i))) {
            i++;
        }
        if (i == val.length()) {
            return false;
        }
        int num = Integer.parseInt(val.substring(0, i));
        String unit = val.substring(i, n);
        if (unit.equals("cm")) {
            return num >= 150 && num <= 193;
        }
        if (unit.equals("in")) {
            return num >= 59 && num <= 76;
        }
        // neither cm or in
        return false;
    }
}

class HairColorRule implements Rule {
    public boolean apply(String val) {
        if (val.length() != 7) {
            return false;
        }
        if (val.charAt(0) != '#') {
            return false;
        }
        for (int i = 1; i < val.length(); i++) {
            if (!Character.isDigit(val.charAt(i)) && (val.charAt(i) < 'a' || val.charAt(i) > 'f')) {
                return false;
            }
        }
        return true;
    }
}

class EyeColorRule implements Rule {
    public boolean apply(String val) {
        Set<String> set = new HashSet<>();
        set.add("amb");
        set.add("blu");
        set.add("brn");
        set.add("gry");
        set.add("grn");
        set.add("hzl");
        set.add("oth");

        return set.contains(val);
    }
}

class PassportNumRule implements Rule {
    public boolean apply(String val) {
        if (val.length() != 9) {
            return false;
        }
        for (char c : val.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
