/*
Given time in seconds, return formatted string, as shown in following example:
Example:

Input: 90061 sec

Output: 1 1 1 1

e.g

    90061 sec: 1 1 1 1 (1 day, 1 hour, 1 min and 1 seconds)
    93784 sec: 1 2 3 4 (1 day, 2 hours, 3 mins, 4 seconds)
 */
public class TimeToString {
    public static void main(String[] args) {
        System.out.println(convertTime(90061));
    }

    public static String convertTime(int timeDiff) {
        int days = timeDiff / 86400;
        int seconds = timeDiff % 86400;
        int hours = seconds / 3600;
        seconds = seconds % 3600;
        int minutes = seconds / 60;
        seconds = seconds % 60;
        return String.valueOf(days) + " " + String.valueOf(hours) + " " +
                String.valueOf(minutes) + " " + String.valueOf(seconds);
    }
}
