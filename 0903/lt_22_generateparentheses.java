import java.util.*;

public class lt_22_generateparentheses {

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(n, 0, 0, new StringBuilder(), ans);
        return ans;
    }

    private void dfs(int n, int open, int close, StringBuilder path, List<String> ans) {
        if (path.length() == 2 * n) {
            ans.add(path.toString());
            return;
        }
        if (open < n) {                 // 仍可放 '('
            path.append('(');
            dfs(n, open + 1, close, path, ans);
            path.deleteCharAt(path.length() - 1);
        }
        if (close < open) {             // 僅當已有未配對 '(' 才能放 ')'
            path.append(')');
            dfs(n, open, close + 1, path, ans);
            path.deleteCharAt(path.length() - 1);
        }
    }

    // quick test
    public static void main(String[] args) {
        lt_22_generateparentheses g = new lt_22_generateparentheses();
        System.out.println(g.generateParenthesis(1)); // ["()"]
        System.out.println(g.generateParenthesis(3)); // ["((()))","(()())","(())()","()(())","()()()"]（順序可不同）
    }
}

/*
解題思路：
1. 目標：生成 n 對括號的所有「合法」組合；合法定義為任一前綴中 '(' 數量 >= ')' 數量。
2. 回溯狀態：
   - open：已放置 '(' 的數量，最多 n。
   - close：已放置 ')' 的數量，且必須滿足 close <= open。
   - path：當前構造中的字串。
3. 遞迴轉移：
   - 若 open < n，可以放 '('。
   - 若 close < open，可以放 ')'（保證合法）。
   - 當 path 長度達到 2n，加入答案。
4. 時間複雜度：約為卡特蘭數 C_n 的量級（n≤8 可輕鬆通過），空間複雜度 O(n)（遞迴深度與臨時字串）。
*/
