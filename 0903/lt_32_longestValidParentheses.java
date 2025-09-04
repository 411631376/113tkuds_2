import java.util.*;

public class lt_32_longestValidParentheses {
    public static int longestValidParentheses(String s) {
        int ans = 0;
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') st.push(i);
            else {
                st.pop();
                if (st.isEmpty()) st.push(i);
                else ans = Math.max(ans, i - st.peek());
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(()"));     // 2
        System.out.println(longestValidParentheses(")()())"));  // 4
        System.out.println(longestValidParentheses(""));        // 0
    }
}
/*
解題思路：
- 棧存索引，-1 為初始邊界。
- 遇 '(' 入棧；遇 ')' 先彈出，若空則以該位置成新邊界，否則用 i - 棧頂 更新答案。
時間 O(n)，空間 O(n)。可改 DP 寫法，複雜度同為 O(n)。
*/
