public class lt_29_dividetwointegers {
    public static int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE; // overflow case
        }
        boolean negative = (dividend < 0) ^ (divisor < 0);
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        int result = 0;

        while (a >= b) {
            long temp = b, multiple = 1;
            while (a >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            a -= temp;
            result += multiple;
        }

        return negative ? -result : result;
    }

    public static void main(String[] args) {
        System.out.println(divide(10, 3));   // 3
        System.out.println(divide(7, -3));   // -2
    }
}

/*
解題思路：
1. 不允許直接使用 / 和 %，必須模擬除法。
2. 利用位移運算 (<<) 找出 divisor 在 dividend 中可減去的最大倍數。
3. 每次減去 divisor 的最大倍數，並累加結果。
4. 注意邊界條件：dividend = MIN_VALUE 且 divisor = -1 會溢出。
5. 時間複雜度 O(logN)，每次倍增 divisor。
*/
