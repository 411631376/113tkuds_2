import java.util.*;

public class lt_30_substringwithconcat {
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return result;

        int wordLen = words[0].length();
        int totalLen = wordLen * words.length;
        Map<String, Integer> wordCount = new HashMap<>();
        for (String w : words) wordCount.put(w, wordCount.getOrDefault(w, 0) + 1);

        for (int i = 0; i < wordLen; i++) {
            int left = i, count = 0;
            Map<String, Integer> seen = new HashMap<>();
            for (int right = i; right + wordLen <= s.length(); right += wordLen) {
                String word = s.substring(right, right + wordLen);
                if (wordCount.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    count++;
                    while (seen.get(word) > wordCount.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        seen.put(leftWord, seen.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }
                    if (count == words.length) {
                        result.add(left);
                    }
                } else {
                    seen.clear();
                    count = 0;
                    left = right + wordLen;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(findSubstring("barfoothefoobarman", new String[]{"foo","bar"})); // [0,9]
        System.out.println(findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","word"})); // []
    }
}

/*
解題思路：
1. 題目要找出所有由 words 串接組成的子字串起始索引。
2. 使用滑動視窗 + HashMap 記錄單字出現次數。
3. 每次取固定長度 wordLen 的子字串判斷是否合法。
4. 當出現次數超過應有數量，縮小視窗直到合法。
5. 當匹配到 words.length 個字時，紀錄當前 left 為解答。
6. 時間複雜度 O(n * wordLen)，n = s.length()。
*/
