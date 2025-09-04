public class lt_27_removeelement {
    public static int removeElement(int[] nums, int val) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        int val = 3;
        int k = removeElement(nums, val);
        System.out.println("k = " + k);
        System.out.print("Modified nums = [");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + (i < k - 1 ? ", " : ""));
        }
        System.out.println("]");
    }
}

/*
解題思路：
1. 使用雙指針 (一個指向當前元素，一個指向覆蓋位置)。
2. 當前元素 != val，就放到前面位置，並移動指針。
3. 回傳新陣列長度 k，nums 前 k 個就是結果。
時間複雜度：O(n)，空間複雜度：O(1)。
*/
