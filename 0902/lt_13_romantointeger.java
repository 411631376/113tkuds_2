public class lt_13_romantointeger {

    public int romanToInt(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < n; i++) {
            int cur = val(s.charAt(i));
            if (i + 1 < n && cur < val(s.charAt(i + 1))) {
                ans -= cur;  // 減法對：IV, IX, XL, XC, CD, CM
            } else {
                ans += cur;  // 其餘直接加
            }
        }
        return ans;
    }

    private int val(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            default : return 1000; // 'M'
        }
    }

    public static void main(String[] args) {
        lt_13_romantointeger sol = new lt_13_romantointeger();
        System.out.println(sol.romanToInt("III"));      // 3
        System.out.println(sol.romanToInt("LVIII"));    // 58
        System.out.println(sol.romanToInt("MCMXCIV"));  // 1994
    }
}

/*
解題思路：
1. 羅馬數字採加減混合：若出現「小值在大值左邊」即減法組合（IV, IX, XL, XC, CD, CM）。
2. 從左到右掃描字串：
   - 若當前值 < 下一個值 → 減去當前值；
   - 否則 → 加上當前值。
3. 以 switch 把 7 個符號映射到數值；不需要 Map 以免額外開銷。
4. 時間複雜度 O(n)，空間複雜度 O(1)；題目保證輸入是合法羅馬數字。
*/
