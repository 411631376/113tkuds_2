import java.util.*;
public class SelectionSortImplementation {
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        int n = arr.length, compares = 0, swaps = 0;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                compares++;
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            if (i != minIdx) {
                int temp = arr[i]; arr[i] = arr[minIdx]; arr[minIdx] = temp;
                swaps++;
            }
            System.out.println("第" + (i+1) + "輪: " + Arrays.toString(arr));
        }
        System.out.println("總比較次數: " + compares);
        System.out.println("總交換次數: " + swaps);
    }
}
