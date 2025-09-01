public class lt_09_palindromeNumber {

    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return (x == rev || x == rev / 10);
    }

    public static void main(String[] args) {
        lt_09_palindromeNumber sol = new lt_09_palindromeNumber();
        System.out.println(sol.isPalindrome(121));   // true
        System.out.println(sol.isPalindrome(-121));  // false
        System.out.println(sol.isPalindrome(10));    // false
        System.out.println(sol.isPalindrome(0));     // true
        System.out.println(sol.isPalindrome(1221));  // true
    }
}
