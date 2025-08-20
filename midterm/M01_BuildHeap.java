import java.util.*;

public class M01_BuildHeap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        buildHeap(arr, type.equals("max"));
        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(arr[i]);
        }
    }

    static void buildHeap(int[] arr, boolean isMax) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i, isMax);
        }
    }

    static void heapify(int[] arr, int n, int i, boolean isMax) {
        int extreme = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && compare(arr[left], arr[extreme], isMax)) {
            extreme = left;
        }
        if (right < n && compare(arr[right], arr[extreme], isMax)) {
            extreme = right;
        }

        if (extreme != i) {
            int temp = arr[i];
            arr[i] = arr[extreme];
            arr[extreme] = temp;
            heapify(arr, n, extreme, isMax);
        }
    }

    static boolean compare(int a, int b, boolean isMax) {
        return isMax ? a > b : a < b;
    }
}

/*
時間複雜度：O(n) （自底向上的 Build-Heap）
空間複雜度：O(log n) （遞迴呼叫堆化 heapify 的遞迴深度）
*/
