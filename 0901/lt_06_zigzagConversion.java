public class lt_06_zigzagConversion {

    // 方法一：指針上下走（建議解） O(n)
    public String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) rows[i] = new StringBuilder();

        int r = 0, dir = 1;
        for (int i = 0; i < s.length(); i++) {
            rows[r].append(s.charAt(i));
            if (r == 0) dir = 1;
            else if (r == numRows - 1) dir = -1;
            r += dir;
        }

        StringBuilder ans = new StringBuilder();
        for (StringBuilder row : rows) ans.append(row);
        return ans.toString();
    }

    // 方法二：數學索引跳步（每列以 step = 2*(numRows-1) 為周期）
    public String convertByIndexJump(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;

        StringBuilder sb = new StringBuilder();
        int step = 2 * (numRows - 1);

        for (int row = 0; row < numRows; row++) {
            for (int i = row; i < s.length(); i += step) {
                sb.append(s.charAt(i)); // 垂直列
                int diag = i + step - 2 * row; // 斜對角列（中間行才適用）
                if (row != 0 && row != numRows - 1 && diag < s.length()) {
                    sb.append(s.charAt(diag));
                }
            }
        }
        return sb.toString();
    }

    // quick test
    public static void main(String[] args) {
        lt_06_zigzagConversion z = new lt_06_zigzagConversion();
        System.out.println(z.convert("PAYPALISHIRING", 3)); // "PAHNAPLSIIGYIR"
        System.out.println(z.convert("PAYPALISHIRING", 4)); // "PINALSIGYAHRPI"
        System.out.println(z.convert("A", 1));              // "A"
        System.out.println(z.convertByIndexJump("PAYPALISHIRING", 3));
        System.out.println(z.convertByIndexJump("PAYPALISHIRING", 4));
    }
}
