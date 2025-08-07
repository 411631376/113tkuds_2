public class AdvancedStringRecursion {
    public static void permute(String str, String out) {
        if (str.length() == 0) {
            System.out.println(out);
            return;
        }
        for (int i = 0; i < str.length(); i++)
            permute(str.substring(0, i) + str.substring(i + 1), out + str.charAt(i));
    }

    public static boolean match(String text, String pattern) {
        if (pattern.length() == 0) return text.length() == 0;
        boolean firstMatch = (!text.isEmpty() && (text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.'));
        if (pattern.length() >= 2 && pattern.charAt(1) == '*')
            return match(text, pattern.substring(2)) || (firstMatch && match(text.substring(1), pattern));
        else
            return firstMatch && match(text.substring(1), pattern.substring(1));
    }

    public static String removeDuplicates(String s) {
        if (s.length() <= 1) return s;
        if (s.charAt(0) == s.charAt(1)) return removeDuplicates(s.substring(1));
        return s.charAt(0) + removeDuplicates(s.substring(1));
    }

    public static void substrings(String str, String out, int index) {
        if (index == str.length()) {
            System.out.println(out);
            return;
        }
        substrings(str, out, index + 1);
        substrings(str, out + str.charAt(index), index + 1);
    }
}