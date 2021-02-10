public class LongestRepetition {
    public static void main(String[] args) {
        System.out.println(longestRepetition("bbbaaabaaaa"));
    }

    public static Object[] longestRepetition(String s) {
        if (s.length() == 0) {
            return new Object[]{"", 0};
        }
        int max = 0;
        int curNumb = 1;
        String maxChar = "";
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i+1)){
                curNumb++;
            } else if (curNumb > max){
                max = curNumb;
                maxChar = String.valueOf(s.charAt(i));
                curNumb = 1;
            } else {
                curNumb = 1;
            }
            if ((i == s.length() - 2) && (curNumb > max)){
                max = curNumb;
                maxChar = String.valueOf(s.charAt(i));
            }
        }
        return new Object[]{maxChar, max};
    }
}
