import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。*  
 *
 * 示例 1：
 *
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 *
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 *
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q17 {
    private final static Map<Character, String> NUMBER_TO_LETTERS = new HashMap<Character, String>() {
        {
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }
    };
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return Collections.emptyList();
        }
        List<String> resultContainer = new ArrayList<>();
        backtrace(new StringBuilder() , digits, 0, resultContainer);
        return resultContainer;
    }

    private void backtrace(StringBuilder combineString, String digits, int curIndex, List<String> resultContainer) {
        if (curIndex >= digits.length()) {
            resultContainer.add(combineString.toString());
        } else {
            String letters = NUMBER_TO_LETTERS.getOrDefault(digits.charAt(curIndex), "");
            for (int i = 0; i < letters.length(); i++) {
                backtrace(combineString.append(letters.charAt(i)), digits, curIndex + 1, resultContainer);
                // 回溯出来删除回溯时添加的字符，该字符在curIndex索引上
                combineString.deleteCharAt(curIndex);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("123" + 'w');
    }
}
