/*
A spoonerism is a spoken phrase in which the first letters of two of the words are swapped around, often with amusing results.

In its most basic form a spoonerism is a two word phrase in which only the first letters of each word are swapped:

"not picking" --> "pot nicking"

Your task is to create a function that takes a string of two words, separated by a space: words and returns a spoonerism of those words in a string, as in the above example.
 */
public class SpoonerizeMe {
    public static void main(String[] args) {
        System.out.println(spoonerize("not picking"));
    }
    public static String spoonerize(String words) {
        String[] array = words.split(" ");
        return array[1].substring(0, 1) + array[0].substring(1) + " " + array[0].substring(0, 1) + array[1].substring(1);
    }
}
