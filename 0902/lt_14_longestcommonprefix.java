public class lt_14_longestcommonprefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String prefix = strs[0]; // 先假設第一個字串是前綴
        for (int i = 1; i < strs.length; i++) {
            // 不斷縮短 prefix，直到符合當前字串開頭
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        lt_14_longestcommonprefix sol = new lt_14_longestcommonprefix();
        System.out.println(sol.longestCommonPrefix(new String[]{"flower", "flow", "flight"})); // "fl"
        System.out.println(sol.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));   // ""
    }
}

/*
解題思路：
1. 問題要求找一組字串陣列的最長共同前綴。
2. 先假設第一個字串為前綴 (prefix)。
3. 從第二個字串開始，比對當前字串與 prefix：
   - 若不匹配，縮短 prefix（去掉最後一個字元）。
   - 直到匹配或 prefix 變成空字串。
4. 時間複雜度：O(S)，S = 所有字串的總長度。
5. 空間複雜度：O(1)，僅使用額外字串變數。
*/
