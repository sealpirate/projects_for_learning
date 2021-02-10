import java.util.Scanner;

public class CheckPalindrome {
    //Check if Palindrome - Checks if the string entered by the user is a palindrome.
    public static void main(String[] args) {
        System.out.println("Введите строку");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        StringBuilder string = new StringBuilder(line);
        string.reverse();
        if (string.toString().equals(line)) {
            System.out.println("Данная строка является палиндромом");
        } else {
            System.out.println("Данная строка не является палиндромом");
        }
    }
}
