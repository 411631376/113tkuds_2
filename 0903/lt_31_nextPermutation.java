import java.util.*;

public class lt_31_nextPermutation {
    public static void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;
        if (i >= 0) {
            int j = n - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }
        reverse(nums, i + 1, n - 1);
    }

    private static void swap(int[] a, int i, int j) { int t = a[i]; a[i] = a[j]; a[j] = t; }
    private static void reverse(int[] a, int l, int r) { while (l < r) swap(a, l++, r--); }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums)); // [1,3,2]
    }
}
/*
解題思路：
1. 從右往左找第一個下降點 i（nums[i] < nums[i+1]）。
2. 從右找第一個 > nums[i] 的 j，交換 i/j。
3. 反轉 i+1..end，讓右半成為最小遞增序。
時間 O(n)，空間 O(1)。
*/
