import java.sql.SQLOutput;
import java.util.Scanner;

public class ReverseString {
//Reverse a String - Enter a string and the program will reverse it and print it out.
    public static void main(String[] args) {
        System.out.println("Введите строку");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        StringBuilder stringBuilder = new StringBuilder(line);
        System.out.println("строка в обратном порядке " + stringBuilder.reverse());
    }
}
