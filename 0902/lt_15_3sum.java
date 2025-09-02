import java.util.*;

public class lt_15_3sum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去重
            if (nums[i] > 0) break; // 之後都更大，和不可能為 0

            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) l++; // 去重
                    while (l < r && nums[r] == nums[r - 1]) r--; // 去重
                    l++; r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        lt_15_3sum s = new lt_15_3sum();
        System.out.println(s.threeSum(new int[]{-1,0,1,2,-1,-4})); // [[-1,-1,2],[-1,0,1]]
        System.out.println(s.threeSum(new int[]{0,1,1}));          // []
        System.out.println(s.threeSum(new int[]{0,0,0}));          // [[0,0,0]]
        System.out.println(s.threeSum(new int[]{-2,0,0,2,2}));     // [[-2,0,2]]
    }
}

/*
解題思路：
1. 目標：找出陣列中所有不重複的三元組，使得 a+b+c=0。
2. 先對陣列排序（O(n log n)），有助於：
   - 雙指針從兩端夾逼（O(n)）。
   - 方便跳過重複元素避免重複解。
3. 迴圈固定索引 i，對區間 (i+1 ... n-1) 用左右指針 l、r：
   - 計算 sum = nums[i] + nums[l] + nums[r]。
   - 若 sum==0：加入答案，並跳過連續重複的 nums[l]、nums[r]，再 l++、r--。
   - 若 sum<0：需要更大 → l++；若 sum>0：需要更小 → r--。
4. 去重策略：
   - 外層：若 nums[i]==nums[i-1]，continue。
   - 內層：找到一組解後，l、r 分別跳過相同值。
5. 複雜度：
   - 時間 O(n^2)：外層 O(n) × 內層雙指針 O(n)。
   - 空間 O(1)（不計輸出），就地排序與常數額外變數。
*/
