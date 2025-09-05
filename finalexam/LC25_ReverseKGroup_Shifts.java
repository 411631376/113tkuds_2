import java.util.*;

public class LC25_ReverseKGroup_Shifts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        while (sc.hasNextInt()) {
            list.add(sc.nextInt());
        }
        int n = list.size();
        for (int i = 0; i + k <= n; i += k) {
            reverse(list, i, i + k - 1);
        }
        for (int x : list) {
            System.out.print(x + " ");
        }
    }

    private static void reverse(List<Integer> list, int l, int r) {
        while (l < r) {
            int tmp = list.get(l);
            list.set(l, list.get(r));
            list.set(r, tmp);
            l++;
            r--;
        }
    }
}
