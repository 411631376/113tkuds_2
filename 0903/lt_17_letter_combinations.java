import java.util.*;

public class lt_17_letter_combinations {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        // 數字到字母的映射
        String[] mapping = {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        backtrack(digits, 0, mapping, new StringBuilder(), result);
        return result;
    }

    // 回溯函數，生成所有組合
    private void backtrack(String digits, int index, String[] mapping, StringBuilder current, List<String> result) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // 根據當前的數字找到對應的字母
        String letters = mapping[digits.charAt(index) - '0']; // 映射數字到字母
        for (char letter : letters.toCharArray()) {
            current.append(letter);
            backtrack(digits, index + 1, mapping, current, result);  // 遞歸處理下一個數字
            current.deleteCharAt(current.length() - 1);  // 回溯
        }
    }

    public static void main(String[] args) {
        lt_17_letter_combinations solution = new lt_17_letter_combinations();
        System.out.println(solution.letterCombinations("23"));
    }
}
