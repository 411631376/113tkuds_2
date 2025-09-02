public class lt_12_integertoroman {

    public String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        lt_12_integertoroman sol = new lt_12_integertoroman();
        System.out.println(sol.intToRoman(3749)); // "MMMDCCXLIX"
        System.out.println(sol.intToRoman(58));   // "LVIII"
        System.out.println(sol.intToRoman(1994)); // "MCMXCIV"
    }
}

/*
解題思路：
1. 題目要求將整數轉換為羅馬數字，範圍 1 ≤ num ≤ 3999。
2. 使用「貪心法」：準備一組數值表 (values) 與對應羅馬符號表 (symbols)。
   - 特殊數字 (如 900=CM, 400=CD, 90=XC, 40=XL, 9=IX, 4=IV) 需單獨列出。
3. 從最大值開始，能減多少就減多少，並將符號加入結果。
4. 重複直到 num 減為 0。
5. 時間複雜度 O(1)，因為最大數字 3999，迴圈長度固定（常數 13）。
*/
