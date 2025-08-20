import java.util.*;

public class M10_RBPropertiesCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] val = new int[n];
        char[] col = new char[n];

        for (int i = 0; i < n; i++) {
            val[i] = sc.nextInt();
            String c = sc.next();          // "B" or "R"
            col[i] = (val[i] == -1) ? 'B' : c.charAt(0); // 空節點視為黑色
        }

        if (val[0] != -1 && col[0] != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (i >= n || val[i] == -1) continue;
            if (col[i] == 'R') {
                int l = 2 * i + 1, r = 2 * i + 2;
                if (l < n && val[l] != -1 && col[l] == 'R') {
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
                if (r < n && val[r] != -1 && col[r] == 'R') {
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
            }
        }

        if (blackHeight(val, col, 0, n) == -1) {
            System.out.println("BlackHeightMismatch");
        } else {
            System.out.println("RB Valid");
        }
    }

    static int blackHeight(int[] val, char[] col, int i, int n) {
        if (i >= n || val[i] == -1) return 1; // NIL（黑）
        int l = blackHeight(val, col, 2 * i + 1, n);
        if (l == -1) return -1;
        int r = blackHeight(val, col, 2 * i + 2, n);
        if (r == -1) return -1;
        if (l != r) return -1;
        return l + (col[i] == 'B' ? 1 : 0);
    }
}

