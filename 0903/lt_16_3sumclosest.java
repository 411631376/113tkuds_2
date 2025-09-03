import java.util.*;

public class lt_16_3sumclosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // O(n log n)
        int closest = Integer.MAX_VALUE; // 初始化為最大值

        // 固定 nums[i]，對其餘元素進行雙指針操作
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == target) return sum;  // 若找到精確和則直接返回
                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;  // 更新最接近 target 的和
                }
                if (sum < target) l++;  // 若和小於 target，增加 sum
                else r--;  // 若和大於 target，減小 sum
            }
        }
        return closest;  // 返回最接近的和
    }

    public static void main(String[] args) {
        lt_16_3sumclosest s = new lt_16_3sumclosest();
        System.out.println(s.threeSumClosest(new int[]{-1,2,1,-4}, 1)); // 2
        System.out.println(s.threeSumClosest(new int[]{0,0,0}, 1));    // 0
        System.out.println(s.threeSumClosest(new int[]{-1,-1,-1,-1}, -2)); // -3
    }
}

/*
解題思路：
1. 題目要求找到三個數字的和最接近 target。
2. 使用排序 + 雙指針來實現，先對陣列進行排序，然後對每個 nums[i] 固定一個，對其餘的 nums[l] 和 nums[r] 使用雙指針來進行搜索。
3. 在每次計算和時，比較 sum 與 target 的差距，更新最接近的和。
4. 若當前和等於 target，直接返回該和；否則，根據 sum 與 target 的關係調整雙指針的指向。
5. 複雜度：
   - 時間 O(n^2)：外層迴圈 O(n)，內層雙指針操作 O(n)。
   - 空間 O(1)：僅用常數額外空間。
*/
