import java.util.*;

public class LC34_SearchRange_DelaySpan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        int L = lowerBound(a, target);
        if (L == n || a[L] != target) {
            System.out.println("-1 -1");
            return;
        }
        int R = lowerBound(a, target + 1) - 1;
        System.out.println(L + " " + R);
    }

    static int lowerBound(int[] a, int x) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (a[m] >= x) r = m; else l = m + 1;
        }
        return l;
    }
}
