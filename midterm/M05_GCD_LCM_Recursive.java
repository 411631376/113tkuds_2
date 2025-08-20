import java.util.*;

public class M05_GCD_LCM_Recursive {
    static long gcd(long a, long b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        long g = gcd(a, b);
        long l = (a / g) * b;
        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }
}

/*
時間複雜度：O(log min(a, b))  (遞迴歐幾里得)
空間複雜度：O(log min(a, b))  (遞迴呼叫堆疊)
*/
