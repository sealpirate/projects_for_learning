import java.sql.SQLOutput;
import java.util.Scanner;

public class CountWords {
    //Count Words in a String
    public static void main(String[] args) {
        System.out.println("Введите строку: ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        System.out.println("Число слов в строке: " + countWords(line));
    }

    public static int countWords(String line) {
       if (line.isEmpty() || line == null){
           return 0;
       }
       String [] words = line.split("\\s+");
       return words.length;
    }
}
