public class RecursionVsIteration {
    public static int factorialR(int n) {
        if (n <= 1) return 1;
        return n * factorialR(n - 1);
    }

    public static int factorialI(int n) {
        int res = 1;
        for (int i = 2; i <= n; i++) res *= i;
        return res;
    }

    public static int sumRecursive(int[] arr, int i) {
        if (i >= arr.length) return 0;
        return arr[i] + sumRecursive(arr, i + 1);
    }

    public static int sumIterative(int[] arr) {
        int sum = 0;
        for (int v : arr) sum += v;
        return sum;
    }

    public static int countCharR(String str, char c, int i) {
        if (i >= str.length()) return 0;
        return (str.charAt(i) == c ? 1 : 0) + countCharR(str, c, i + 1);
    }

    public static int countCharI(String str, char c) {
        int count = 0;
        for (char ch : str.toCharArray()) if (ch == c) count++;
        return count;
    }

    public static boolean checkBrackets(String str) {
        int balance = 0;
        for (char ch : str.toCharArray()) {
            if (ch == '(') balance++;
            else if (ch == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }
}
