public class lt_28_strstr {
    public static int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public static void main(String[] args) {
        String haystack = "sadbutsad";
        String needle = "sad";
        int result = strStr(haystack, needle);
        System.out.println("First occurrence index = " + result);
    }
}

/*
解題思路：
1. 題目要找 needle 在 haystack 的第一個出現位置。
2. Java 內建函式 indexOf() 可直接解決，若不存在回傳 -1。
3. 符合題目要求且最簡潔。
時間複雜度：O(n*m) 在最差情況 (n=haystack 長度, m=needle 長度)。
*/
