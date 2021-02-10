import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountVowels {
//Count Vowels - Enter a string and the program counts the number of vowels in the text.
    public static void main(String[] args) {
        System.out.println("Введите строку ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Pattern pattern = Pattern.compile("[aeoiuy]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        int countVowels = 0;
        while (matcher.find()) {
            countVowels++;
        }
        System.out.println("количество слогов: " + countVowels);
}
}
