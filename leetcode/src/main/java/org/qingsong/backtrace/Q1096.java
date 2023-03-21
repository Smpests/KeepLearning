package org.qingsong.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 1096. 花括号展开 II
 * 花括号展开的表达式可以看作一个由 花括号、逗号 和 小写英文字母 组成的字符串，定义下面几条语法规则：
 *
 * 如果只给出单一的元素 x，那么表达式表示的字符串就只有 "x"。R(x) = {x}
 * 例如，表达式 "a" 表示字符串 "a"。
 * 而表达式 "w" 就表示字符串 "w"。
 * 当两个或多个表达式并列，以逗号分隔，我们取这些表达式中元素的并集。
 * R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...
 * 例如，表达式 "{a,b,c}" 表示字符串 "a","b","c"。
 *
 * 而表达式 "{{a,b},{b,c}}" 也可以表示字符串 "a","b","c"。
 * 要是两个或多个表达式相接，中间没有隔开时，我们从这些表达式中各取一个元素依次连接形成字符串。
 * R(e_1 + e_2) = {a + b for (a, b) in R(e_1) × R(e_2)}
 * 例如，表达式 "{a,b}{c,d}" 表示字符串 "ac","ad","bc","bd"。
 *
 * 表达式之间允许嵌套，单一元素与表达式的连接也是允许的。
 * 例如，表达式 "a{b,c,d}" 表示字符串 "ab","ac","ad"​​​​​​。
 * 例如，表达式 "a{b,c}{d,e}f{g,h}" 可以表示字符串 "abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"。
 *
 * 给出表示基于给定语法规则的表达式 expression，返回它所表示的所有字符串组成的有序列表。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/brace-expansion-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q1096 {
    private final static char FLOWER_START = '{';
    private final static char FLOWER_END = '}';
    private final static String COMMA = ",";
    /**
     * 参考题解：https://leetcode.cn/problems/brace-expansion-ii/solution/python3javacgotypescript-yi-ti-yi-jie-di-gs64/
     * 递归解决（分治），每次减少一组花括号，和花括号前后字符构成新的表达式
     * 没有花括号用逗号分隔当前字符串即得部分结果，递归完则得所有结果
     */
    public List<String> braceExpansionII(String expression) {
        // 不可重复用set存结果，输出有序结果用TreeSet
        Set<String> result = new TreeSet<>();
        process(expression, result);
        return new ArrayList<>(result);
    }

    private void process(String exp, Set<String> result) {
        // 退出条件，无花括号
        int endIndex = exp.indexOf(FLOWER_END);
        // 有右花括号
        if (endIndex != -1) {
            // 找左花括号
            int startIndex = exp.lastIndexOf(FLOWER_START, endIndex);
            // 抛弃左右花括号，用逗号分割得到子字符串
            String[] subs = exp.substring(startIndex + 1, endIndex).split(COMMA);
            // 将得到的子字符串和花括号前后的字符串构成新表达式递归
            for (String sub : subs) {
                process(exp.substring(0, startIndex) + sub + exp.substring(endIndex + 1), result);
            }
        } else {
            // 没有花括号，直接添加当前表达式按逗号分割后的内容
            result.addAll(Arrays.asList(exp.split(COMMA)));
        }
    }

    public static void main(String[] args) {
        System.out.println(new Q1096().braceExpansionII("{a,b}{c,{d,e}}"));
    }
}
