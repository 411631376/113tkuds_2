public class lt_07_reverseInteger {

    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;

            // overflow / underflow 檢查
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7))
                return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8))
                return 0;

            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        lt_07_reverseInteger sol = new lt_07_reverseInteger();
        System.out.println(sol.reverse(123));   // 321
        System.out.println(sol.reverse(-123));  // -321
        System.out.println(sol.reverse(120));   // 21
        System.out.println(sol.reverse(1534236469)); // 0 (overflow)
    }
}
