import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// *****change here******
// import year2019.dayfour.Solution;
import year2020.dayeight.Solution;

public class Main {
    private static final String YEAR = "year2020";
    private static final String DAY = "dayeight";

    public static void main(String[] args) {
        List<String> inputs = new ArrayList<>();

        try {
            // *****change here******
            File file = new File("./" + YEAR + "/" + DAY + "/input");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                inputs.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // *****change here******
        // int res = new Solution().calculateP1(inputs);
        int res = new Solution().calculateP2(inputs);
        System.out.println(res);
    }
}
