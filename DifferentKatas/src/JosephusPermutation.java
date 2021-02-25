import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
You are now to create a function that returns a Josephus permutation, taking as parameters the initial array/list of items to be permuted as if they were in a circle and counted out every k places until none remained.

Tips and notes: it helps to start counting from 1 up to n, instead of the usual range 0..n-1; k will always be >=1.

For example, with n=7 and k=3 josephus(7,3) should act this way.

[1,2,3,4,5,6,7] - initial sequence
[1,2,4,5,6,7] => 3 is counted out and goes into the result [3]
[1,2,4,5,7] => 6 is counted out and goes into the result [3,6]
[1,4,5,7] => 2 is counted out and goes into the result [3,6,2]
[1,4,5] => 7 is counted out and goes into the result [3,6,2,7]
[1,4] => 5 is counted out and goes into the result [3,6,2,7,5]
[4] => 1 is counted out and goes into the result [3,6,2,7,5,1]
[] => 4 is counted out and goes into the result [3,6,2,7,5,1,4]

So our final result is:

josephus([1,2,3,4,5,6,7],3)==[3,6,2,7,5,1,4]
 */
public class JosephusPermutation {
    public static void main(String[] args) {
        System.out.println(josephusPermutation(new ArrayList(Arrays.asList(new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10})), 2));
        System.out.println(josephusPermutation(new ArrayList(Arrays.asList(new Object[]{"C", "o", "d", "e", "W", "a", "r", "s"})), 4));

    }

    public static <T> List<T> josephusPermutation(List<T> items,  int k) {
        List <T> result = new ArrayList<T>();
        if (items.isEmpty() || items.size() <= 1){
            return items;
        }
        int sizeList = items.size();
        k--;
        int j = k % sizeList;
        while (!items.isEmpty()) {
           result.add(items.get(j));
           items.remove(j);
            if (items.size() > 0)
                j = (j + k) % items.size();
        }
      return result;
    }
}
