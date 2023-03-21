package org.qingsong.backtrace;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 *
 * 输入：n = 1
 * 输出：["()"]
 *
 * 提示：
 * 1 <= n <= 8
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q22 {
    /**
     * chatGPT answered this question:
     * 可以使用回溯算法来解决这个问题。具体步骤如下：
     * 1. 定义一个列表来存储所有可能的括号组合。
     * 2. 定义一个递归函数 backtrack(current_str, left, right)，
     * 其中 current_str 表示当前正在构建的括号组合，left 表示还需要添加的左括号数，right 表示还需要添加的右括号数。
     * 3. 如果 left 和 right 都为 0，则将 current_str 添加到列表中。
     * 4. 如果 left 大于 0，则在 current_str 后面添加一个左括号，
     * 然后递归调用 backtrack(current_str + '(', left - 1, right)。
     * 5. 如果 right 大于 left，则在 current_str 后面添加一个右括号，
     * 然后递归调用 backtrack(current_str + ')', left, right - 1)。
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrace("", n, n, result);
        return result;
    }

    private void backtrace(String target, int left, int right, List<String> container) {
        // 左右括号都消耗完，加入当前字符串
        if (left == 0 && right == 0) {
            container.add(target);
        }
        // 每次选择添加左括号还是右括号，当回溯时，各种情况都会枚举出来
        if (left > 0) {
            backtrace(target + "(", left - 1, right, container);
        }
        // 在没有左括号时，不可能添加右括号
        if (right > left) {
            backtrace(target + ")", left, right - 1, container);
        }
    }
}
