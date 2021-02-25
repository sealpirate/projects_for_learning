/*
Complete the solution so that the function will break up camel casing, using a space between words.
Example

solution("camelCasing")  ==  "camel Casing"
 */
public class CamelCase {
    public static void main(String[] args) {
        System.out.println(camelCase("houseIsOnTheHill"));
    }

    public static String camelCase(String input) {
        String result = "";
        for (int i = 0; i < input.length(); i++) {
            if ((input.charAt(i) <= 'Z') && (input.charAt(i) >= 'A')) {
                result += " ";
            }
            result += String.valueOf(input.charAt(i));
        }
        return result;
    }

}

