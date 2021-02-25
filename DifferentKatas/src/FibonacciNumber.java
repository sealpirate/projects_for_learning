public class FibonacciNumber {
    public static void main(String[] args) {
        System.out.println(getFibonacciNumber(8));
    }
    public static int getFibonacciNumber(int n){
        if (n == 1 || n == 2)
            return 1;
        else {
            return getFibonacciNumber(n - 1) + getFibonacciNumber(n - 2);
        }
    }
}
