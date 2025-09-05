import java.util.Stack;

public class LC20_ValidParentheses_AlertFormat {
    public static void main(String[] args) {
        String s1 = "{{()()}}";
        String s2 = "([)]";
        
        System.out.println(isValid(s1));  // true
        System.out.println(isValid(s2));  // false
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);  // 開括號進堆疊
            } else {
                if (stack.isEmpty()) {
                    return false;  // 如果堆疊為空，表示無對應的開括號
                }
                char top = stack.pop();
                if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top != '[')) {
                    return false;  // 括號不匹配
                }
            }
        }
        return stack.isEmpty();  // 最後堆疊應該為空，若為空則匹配成功
    }
}
