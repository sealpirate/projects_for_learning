/*
In this Kata your task will be to return the count of pairs that have consecutive numbers as follows:

pairs([1,2,5,8,-4,-3,7,6,5]) = 3
The pairs are selected as follows [(1,2),(5,8),(-4,-3),(7,6),5]
--the first pair is (1,2) and the numbers in the pair are consecutive; Count = 1
--the second pair is (5,8) and are not consecutive
--the third pair is (-4,-3), consecutive. Count = 2
--the fourth pair is (7,6), also consecutive. Count = 3.
--the last digit has no pair, so we ignore.
 */
public class ConsecutivePairs {
    public static void main(String[] args) {
        System.out.println(solve(new int[]{-55, -56, -7, -6, 56, 55, 63, 62}));
    }

    public static int solve(int [] arr){
        int numberOfConcPairs = 0;
        for (int i = 0; i < arr.length - 1; i = i + 2) {
                if (Math.abs(arr[i] - arr[i + 1]) == 1) {
                    numberOfConcPairs++;
            }
        }
        return numberOfConcPairs;
    }
}
