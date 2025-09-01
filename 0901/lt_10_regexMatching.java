public class lt_10_regexMatching {

    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 2];
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1);
                if (pc != '*') {
                    if (pc == '.' || pc == s.charAt(i - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    dp[i][j] = dp[i][j - 2]; // use zero time
                    char prev = p.charAt(j - 2);
                    if (prev == '.' || prev == s.charAt(i - 1)) {
                        dp[i][j] |= dp[i - 1][j]; // consume one s char
                    }
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        lt_10_regexMatching r = new lt_10_regexMatching();
        System.out.println(r.isMatch("aa", "a"));     // false
        System.out.println(r.isMatch("aa", "a*"));    // true
        System.out.println(r.isMatch("ab", ".*"));    // true
        System.out.println(r.isMatch("aab", "c*a*b"));// true
        System.out.println(r.isMatch("mississippi", "mis*is*p*.")); // false
    }
}
