import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 *
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例 3：
 *
 * 输入：s = "(]"
 * 输出：false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q20 {
    private final static Map<Character, Character> BRACKETS_PAIR = new HashMap<Character, Character>() {{
        put('(', ')');
        put('[', ']');
        put('{', '}');
    }};

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        stackProcess(stack, s, 0);
        return stack.isEmpty();
    }

    private void stackProcess(Stack<Character> stack, String s, int index) {
        // 索引大于等于字符串长度，已遍历完直接退出
        if (index >= s.length()) {
            return;
        }
        // 因为要按顺序左右闭合括号，所以只会先遇到左括号，
        // 当栈不为空，查看当前字符与栈顶字符是否成对，
        // 成对则消除栈顶和当前字符，不成对则添加当前字符
        if (!stack.isEmpty() && BRACKETS_PAIR.getOrDefault(stack.peek(), '#').equals(s.charAt(index))) {
            stack.pop();
        } else {
            stack.add(s.charAt(index));
        }
        stackProcess(stack, s, index + 1);
    }

    public static void main(String[] args) {
        System.out.println(new Q20().isValid("([)]"));
    }
}
