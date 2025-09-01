public class lt_03_lengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        int[] last = new int[128];
        int left = 0, ans = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            left = Math.max(left, last[c]);
            ans = Math.max(ans, i - left + 1);
            last[c] = i + 1;
        }
        return ans;
    }

    // quick test
    public static void main(String[] args) {
        lt_03_lengthOfLongestSubstring sol = new lt_03_lengthOfLongestSubstring();
        System.out.println(sol.lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(sol.lengthOfLongestSubstring("bbbbb"));    // 1
        System.out.println(sol.lengthOfLongestSubstring("pwwkew"));   // 3
        System.out.println(sol.lengthOfLongestSubstring(" "));        // 1
        System.out.println(sol.lengthOfLongestSubstring("dvdf"));     // 3
    }
}
