import java.util.*;

public class M09_AVLValidate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();

        boolean isBST = validateBST(a, 0, Long.MIN_VALUE, Long.MAX_VALUE, n);
        if (!isBST) {
            System.out.println("Invalid BST");
            return;
        }
        boolean isAVL = checkAVL(a, 0, n) != -1;
        if (!isAVL) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }

    static boolean validateBST(int[] a, int i, long low, long high, int n) {
        if (i >= n || a[i] == -1) return true;
        int v = a[i];
        if (!(low < v && v < high)) return false;
        return validateBST(a, 2 * i + 1, low, v, n) &&
               validateBST(a, 2 * i + 2, v, high, n);
    }

    static int checkAVL(int[] a, int i, int n) {
        if (i >= n || a[i] == -1) return 0;
        int hl = checkAVL(a, 2 * i + 1, n);
        if (hl == -1) return -1;
        int hr = checkAVL(a, 2 * i + 2, n);
        if (hr == -1) return -1;
        if (Math.abs(hl - hr) > 1) return -1;
        return Math.max(hl, hr) + 1;
    }
}

/*
時間複雜度：O(n)（各自一次 DFS：BST 檢查 O(n) + AVL 檢查 O(n)）
空間複雜度：O(h)（遞迴深度；h 為樹高，最壞 O(n)）
*/
