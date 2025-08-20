import java.io.*;

public class M06_PalindromeClean {
    static boolean isLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int i = 0, j = s.length() - 1;
        boolean ok = true;
        while (i < j) {
            while (i < j && !isLetter(s.charAt(i))) i++;
            while (i < j && !isLetter(s.charAt(j))) j--;
            if (i < j) {
                char a = Character.toLowerCase(s.charAt(i));
                char b = Character.toLowerCase(s.charAt(j));
                if (a != b) { ok = false; break; }
                i++; j--;
            }
        }
        System.out.println(ok ? "Yes" : "No");
    }
}
