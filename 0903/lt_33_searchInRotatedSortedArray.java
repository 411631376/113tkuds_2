public class lt_33_searchInRotatedSortedArray {
    public static int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) return m;
            if (nums[l] <= nums[m]) { // 左邊有序
                if (nums[l] <= target && target < nums[m]) r = m - 1;
                else l = m + 1;
            } else { // 右邊有序
                if (nums[m] < target && target <= nums[r]) l = m + 1;
                else r = m - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 0)); // 4
        System.out.println(search(new int[]{4,5,6,7,0,1,2}, 3)); // -1
        System.out.println(search(new int[]{1}, 0));             // -1
    }
}
/*
解題思路：
- 改良二分。每輪判斷哪半邊有序，再看 target 是否落於該範圍內。
- 依此調整左右邊界，直到找到或區間空。
時間 O(log n)，空間 O(1)。
*/
