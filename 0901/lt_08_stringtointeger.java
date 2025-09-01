public class lt_08_stringtointeger {

    public int myAtoi(String s) {
        int n = s.length(), i = 0;

        // 1. 去除前導空白
        while (i < n && s.charAt(i) == ' ') i++;
        if (i == n) return 0;

        // 2. 處理符號
        int sign = 1;
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 3. 轉換數字
        int res = 0;
        while (i < n) {
            char ch = s.charAt(i);
            if (ch < '0' || ch > '9') break;  // 非數字停止
            int d = ch - '0';

            // 4. overflow 檢查
            if (res > Integer.MAX_VALUE / 10 ||
               (res == Integer.MAX_VALUE / 10 && d > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            res = res * 10 + d;
            i++;
        }
        return sign * res;
    }

    // 測試用 main
    public static void main(String[] args) {
        lt_08_stringtointeger a = new lt_08_stringtointeger();
        System.out.println(a.myAtoi("42"));                 // 42
        System.out.println(a.myAtoi("   -042"));            // -42
        System.out.println(a.myAtoi("1337c0d3"));           // 1337
        System.out.println(a.myAtoi("0-1"));                // 0
        System.out.println(a.myAtoi("words and 987"));      // 0
        System.out.println(a.myAtoi("+-12"));               // 0
        System.out.println(a.myAtoi("21474836460"));        // 2147483647
        System.out.println(a.myAtoi("-91283472332"));       // -2147483648
    }
}
