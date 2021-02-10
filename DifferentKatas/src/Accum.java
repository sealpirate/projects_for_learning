/*
This time no story, no theory. The examples below show you how to write
function accum:

Examples:

accum("abcd") -> "A-Bb-Ccc-Dddd"
accum("RqaEzty") -> "R-Qq-Aaa-Eeee-Zzzzz-Tttttt-Yyyyyyy"
accum("cwAt") -> "C-Ww-Aaa-Tttt"

The parameter of accum is a string which includes only letters from a..z and A..Z.
 */
public class Accum {
    public static void main(String[] args) {
        System.out.println(accum("ZpglnRxqenU"));
    }

    public static String accum(String s) {
        s = s.toLowerCase();
        int i = 1;
        String result = "";
        while (i <= s.length()){
            if (i != 1){
                result += "-";
            }
            result += s.substring(i-1, i).toUpperCase();
            for (int j = 0; j < i - 1; j++) {
                result += s.substring(i-1, i);
            }
            i++;
        }
        return result;
    }
}
