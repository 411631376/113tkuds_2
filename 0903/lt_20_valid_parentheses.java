import java.util.Stack;

public class lt_20_valid_parentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        // 遍歷每個字符
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c); // 開括號入堆疊
            } else {
                if (stack.isEmpty()) return false; // 如果堆疊空，意味著沒有匹配的開括號
                char top = stack.pop();
                // 判斷是否匹配對應的開括號
                if (c == ')' && top != '(') return false;
                if (c == '}' && top != '{') return false;
                if (c == ']' && top != '[') return false;
            }
        }
        
        // 如果堆疊非空，則有未匹配的括號
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        lt_20_valid_parentheses solution = new lt_20_valid_parentheses();
        System.out.println(solution.isValid("()[]{}")); // true
        System.out.println(solution.isValid("(]")); // false
    }
}
