public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(maxArea(heights));  // 輸出 49
    }

    public static int maxArea(int[] heights) {
        int left = 0, right = heights.length - 1;
        int maxArea = 0;

        while (left < right) {
            // 計算當前面積
            int width = right - left;
            int minHeight = Math.min(heights[left], heights[right]);
            maxArea = Math.max(maxArea, width * minHeight);

            // 移動較小的一側
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}
