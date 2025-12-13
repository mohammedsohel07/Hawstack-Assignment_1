import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class CountUniqueElements {

    public static int countUnique(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }

        Set<Integer> uniqueSet = new HashSet<>();
        for (Integer num : numbers) {
            uniqueSet.add(num);
        }

        return uniqueSet.size();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();

        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            numbers.add(scanner.nextInt());
        }

        int result = countUnique(numbers);
        System.out.println("Number of unique elements: " + result);

        scanner.close();
    }
}
