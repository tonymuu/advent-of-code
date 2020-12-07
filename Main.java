import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// *****change here******
import year2020.dayseven.Solution;

public class Main {
    public static void main(String[] args) {
        List<String> inputs = new ArrayList<>();

        try {
            // *****change here******
            File file = new File("./year2020/dayseven/input-day-7");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                inputs.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // *****change here******
        int res = new Solution().calculateP2(inputs);
        System.out.println(res);
    }
}