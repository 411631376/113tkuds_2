public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        double[] nums1 = {1.1, 2.0};
        double[] nums2 = {1.5, 3.2, 4.0};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(double[] nums1, double[] nums2) {
        if (nums1.length > nums2.length) {
            // 保證 nums1 是較短的陣列
            double[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int n = nums1.length, m = nums2.length;
        int left = 0, right = n;

        while (left <= right) {
            int partition1 = (left + right) / 2;
            int partition2 = (n + m + 1) / 2 - partition1;

            double maxLeft1 = (partition1 == 0) ? Double.NEGATIVE_INFINITY : nums1[partition1 - 1];
            double minRight1 = (partition1 == n) ? Double.POSITIVE_INFINITY : nums1[partition1];

            double maxLeft2 = (partition2 == 0) ? Double.NEGATIVE_INFINITY : nums2[partition2 - 1];
            double minRight2 = (partition2 == m) ? Double.POSITIVE_INFINITY : nums2[partition2];

            if (maxLeft1 <= minRight2 && maxLeft2 <= minRight1) {
                // 找到合適的分割點
                if ((n + m) % 2 == 0) {
                    return (Math.max(maxLeft1, maxLeft2) + Math.min(minRight1, minRight2)) / 2.0;
                } else {
                    return Math.max(maxLeft1, maxLeft2);
                }
            } else if (maxLeft1 > minRight2) {
                right = partition1 - 1;  // 移動 partition1
            } else {
                left = partition1 + 1;  // 移動 partition1
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted.");
    }
}
