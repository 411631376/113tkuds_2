public class lt_11_containerWithMostWater {

    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int h = Math.min(height[l], height[r]);
            ans = Math.max(ans, h * (r - l));
            if (height[l] < height[r]) l++;
            else r--;
        }
        return ans;
    }

    public static void main(String[] args) {
        lt_11_containerWithMostWater s = new lt_11_containerWithMostWater();
        System.out.println(s.maxArea(new int[]{1,8,6,2,5,4,8,3,7})); // 49
        System.out.println(s.maxArea(new int[]{1,1}));               // 1
        System.out.println(s.maxArea(new int[]{4,3,2,1,4}));         // 16
    }
}

/*
解題思路：
1. 題目要求找到能容納最多水的容器，容器由兩條垂直線構成。
2. 使用「雙指針」法，左指針 l 從頭開始，右指針 r 從尾開始。
3. 每次計算區間面積：高 = min(height[l], height[r])，寬 = (r - l)。
4. 更新最大面積，然後移動較短的那一側，因為移動長邊不會增加最小高度。
5. 時間複雜度 O(n)，空間複雜度 O(1)。
*/
