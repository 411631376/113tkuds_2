public class lt_05_longestPalindrome {

    // --- 解法1：中心擴展 O(n^2) ---
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) return s;

        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(s, i, i);
            int len2 = expand(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end   = i +  len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expand(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--; r++;
        }
        return r - l - 1;
    }

    // --- 解法2：Manacher O(n)（可選進階）---
    public String longestPalindromeManacher(String s) {
        if (s == null || s.isEmpty()) return s;

        // 轉換: "^#a#b#c#$" 形式，避免奇偶差異
        char[] t = new char[2 * s.length() + 3];
        t[0] = '^';
        t[t.length - 1] = '$';
        for (int i = 0; i < s.length(); i++) {
            t[2 * i + 1] = '#';
            t[2 * i + 2] = s.charAt(i);
        }
        t[t.length - 2] = '#';

        int[] p = new int[t.length]; // 以 i 為中心的半徑
        int center = 0, right = 0;

        for (int i = 1; i < t.length - 1; i++) {
            int mirror = 2 * center - i;
            if (i < right) p[i] = Math.min(right - i, p[mirror]);

            while (t[i + 1 + p[i]] == t[i - 1 - p[i]]) p[i]++;

            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
        }

        int maxLen = 0, idx = 0;
        for (int i = 1; i < t.length - 1; i++) {
            if (p[i] > maxLen) { maxLen = p[i]; idx = i; }
        }
        int start = (idx - maxLen) / 2; // 映回原字串起點
        return s.substring(start, start + maxLen);
    }

    // quick test
    public static void main(String[] args) {
        lt_05_longestPalindrome sol = new lt_05_longestPalindrome();
        System.out.println(sol.longestPalindrome("babad")); // "bab" or "aba"
        System.out.println(sol.longestPalindrome("cbbd"));  // "bb"
        System.out.println(sol.longestPalindromeManacher("babad"));
        System.out.println(sol.longestPalindromeManacher("cbbd"));
    }
}
