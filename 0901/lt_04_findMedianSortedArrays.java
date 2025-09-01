public class lt_04_findMedianSortedArrays {

    // --- 最佳解：二分切割 O(log min(m,n)) ---
    public double findMedianSortedArrays(int[] A, int[] B) {
        if (A.length > B.length) return findMedianSortedArrays(B, A);
        int m = A.length, n = B.length;
        int left = 0, right = m, half = (m + n + 2) / 2;

        while (left <= right) {
            int i = (left + right) / 2;
            int j = half - i;

            int Aleft  = (i == 0) ? Integer.MIN_VALUE : A[i - 1];
            int Aright = (i == m) ? Integer.MAX_VALUE : A[i];
            int Bleft  = (j == 0) ? Integer.MIN_VALUE : B[j - 1];
            int Bright = (j == n) ? Integer.MAX_VALUE : B[j];

            if (Aleft <= Bright && Bleft <= Aright) {
                if (((m + n) & 1) == 1) return Math.max(Aleft, Bleft);
                return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
            } else if (Aleft > Bright) {
                right = i - 1;
            } else {
                left = i + 1;
            }
        }
        throw new IllegalStateException("Should not reach here");
    }

    // --- 直觀合併：O(m+n)（用來對拍/理解） ---
    public double findMedianMerged(int[] A, int[] B) {
        int m = A.length, n = B.length, k1 = (m + n - 1) / 2, k2 = (m + n) / 2;
        int i = 0, j = 0, idx = -1, a = 0, b = 0;
        while (i < m || j < n) {
            int take;
            if (j == n || (i < m && A[i] <= B[j])) { take = A[i++]; }
            else { take = B[j++]; }
            idx++;
            if (idx == k1) a = take;
            if (idx == k2) { b = take; break; }
        }
        return ((m + n) % 2 == 1) ? b : (a + b) / 2.0;
    }

    // quick test
    public static void main(String[] args) {
        lt_04_findMedianSortedArrays s = new lt_04_findMedianSortedArrays();
        System.out.println(s.findMedianSortedArrays(new int[]{1,3}, new int[]{2}));      // 2.0
        System.out.println(s.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));    // 2.5
        System.out.println(s.findMedianMerged(new int[]{1,3}, new int[]{2}));            // 2.0
        System.out.println(s.findMedianMerged(new int[]{1,2}, new int[]{3,4}));          // 2.5
    }
}
