import java.util.*;

public class lt_40_combination_sum_ii {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        dfs(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] a, int start, int remain, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) { res.add(new ArrayList<>(path)); return; }
        if (remain < 0) return;
        for (int i = start; i < a.length; i++) {
            if (i > start && a[i] == a[i-1]) continue; // 同層去重避免重複組合
            if (a[i] > remain) break;                 // 提前剪枝
            path.add(a[i]);
            dfs(a, i + 1, remain - a[i], path, res);  // 元素只能用一次
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new lt_40_combination_sum_ii()
                .combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
    }
}

/*
解題思路：
1. 先排序，回溯時「同一層」若遇到與前一個相同的值就跳過（i>start 且 a[i]==a[i-1]），
   以避免同組合多次出現；且每個元素只能使用一次 → 遞迴下一層用 i+1。
2. 若 a[i] > remain 可直接 break（後面更大）。
時間複雜度：與有效組合數量相關，最壞指數級；測資上限可接受。
空間複雜度：O(k) 遞迴深度（k 為路徑長度）。
*/
