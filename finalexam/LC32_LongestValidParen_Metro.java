import java.util.Stack;

public class LC32_LongestValidParen_Metro {
    public static void main(String[] args) {
        String s = "(()())";
        System.out.println(longestValidParentheses(s));  // 輸出 6
    }

    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);  // 初始化，避免空堆疊時出錯
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i);  // 開括號，將其索引放入堆疊
            } else {
                stack.pop();  // 關括號，彈出堆疊頂部元素

                if (stack.isEmpty()) {
                    stack.push(i);  // 如果堆疊為空，將當前索引推入作為新的基準
                } else {
                    maxLength = Math.max(maxLength, i - stack.peek());  // 更新最大長度
                }
            }
        }

        return maxLength;
    }
}
