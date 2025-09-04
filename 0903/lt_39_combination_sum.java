import java.util.*;

public class lt_39_combination_sum {
    private List<List<Integer>> ans = new ArrayList<>();
    private int[] cand;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        cand = candidates;
        dfs(0, target, new ArrayList<>());
        return ans;
    }

    private void dfs(int i, int remain, List<Integer> path) {
        if (remain == 0) { ans.add(new ArrayList<>(path)); return; }
        if (i == cand.length || remain < 0) return;
        path.add(cand[i]);           // 取用 cand[i]，可重複使用
        dfs(i, remain - cand[i], path);
        path.remove(path.size() - 1);
        dfs(i + 1, remain, path);    // 跳過 cand[i]
    }

    public static void main(String[] args) {
        System.out.println(new lt_39_combination_sum()
                .combinationSum(new int[]{2,3,6,7}, 7));
    }
}

/*
解題思路：
1. 排序後用回溯 DFS，對每個數字有「取/不取」兩種決策；
   取的分支可重複取同一數（索引不前進）。
2. 當剩餘和 remain==0 收集當前組合；remain<0 或索引越界則剪枝。
時間複雜度：與答案數量相關，最壞指數級；n<=30、target<=40 可接受。
空間複雜度：O(target/最小值) 的遞迴深度。
*/
