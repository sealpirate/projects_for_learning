import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoNumbers {
    public static void main(String[] args) {
        int[] arrayTwoNumbers = {2, 15, 19, 4, 8, 102, 36, 14, 5, 6, 2};
        System.out.println("Индексы: " + Arrays.toString(twoSum(arrayTwoNumbers, 9)));
        int[] arrayTwoNumbers2 = {-1, -2, -3, -4, -5};
        System.out.println("Индексы: " + Arrays.toString(twoSum(arrayTwoNumbers2, -8)));

    }

    //Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
    //You may assume that each input would have exactly one solution, and you may not use the same element twice.
    private static int[] twoSum(int[] nums, int target) {
        List<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            arrayList.add(nums[i]);
        }
        int indexFirst = -1;
        int indexSecond = -1;
        int number;
        for (int i = 0; i < arrayList.size(); i++) {
            number = arrayList.get(i);
            indexFirst = arrayList.indexOf(target - number);
            if (indexFirst != - 1 && indexFirst != i){
                indexSecond = i;
                break;
            }
        }
        return new int[]{indexFirst, indexSecond};
    }
}
