public class lt_38_count_and_say {
    public static String countAndSay(int n) {
        String s = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder t = new StringBuilder();
            for (int j = 0; j < s.length(); ) {
                char ch = s.charAt(j);
                int k = j;
                while (k < s.length() && s.charAt(k) == ch) k++;
                t.append(k - j).append(ch);
                j = k;
            }
            s = t.toString();
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(4)); // 1211
    }
}

/*
解題思路：
1. 遞推字串，第 i 步對第 i-1 個字串做 Run-Length Encoding（連續段落的長度 + 字元）。
2. 逐段掃描並輸出「count + char」即可。
時間複雜度：所有步驟總字元量為 O(生成字串長度總和)，<= O(2^n) 上界但 n<=30 可接受。
空間複雜度：O(當前字串長度)。
*/
