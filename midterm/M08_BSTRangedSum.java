import java.util.*;

public class M08_BSTRangedSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        int L = sc.nextInt(), R = sc.nextInt();
        long ans = rangeSum(a, 0, n, L, R);
        System.out.println("Sum: " + ans);
    }

    static long rangeSum(int[] a, int i, int n, int L, int R) {
        if (i >= n || a[i] == -1) return 0;
        int v = a[i];
        if (v < L) return rangeSum(a, 2 * i + 2, n, L, R);
        if (v > R) return rangeSum(a, 2 * i + 1, n, L, R);
        return v + rangeSum(a, 2 * i + 1, n, L, R) + rangeSum(a, 2 * i + 2, n, L, R);
    }
}

