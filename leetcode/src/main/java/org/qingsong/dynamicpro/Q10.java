package org.qingsong.dynamicpro;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 *  
 * 示例 1：
 *
 * 输入：s = "aa", p = "a"
 * 输出：false
 * 解释："a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入：s = "aa", p = "a*"
 * 输出：true
 * 解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例 3：
 *
 * 输入：s = "ab", p = ".*"
 * 输出：true
 * 解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/regular-expression-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q10 {
    private final static char WILDCARD = '*';
    private final static char POINT = '.';

    /**
     * Difficult for me.
     * TODO:题解均用动态规划，暂未掌握
     */
    public boolean isMatch(String s, String p) {
        char curWildCardCharacter;
        // 从头开始匹配字符串s
        int originStringIndex = 0;
        // 使用了.*的标记
        boolean flag = false;
        int pIndex = 0;
        for (; pIndex < p.length() && originStringIndex < s.length(); pIndex++) {
            char ch = p.charAt(pIndex);
            if (ch == POINT || ch == s.charAt(originStringIndex)) {
                originStringIndex++;
            } else if (ch == WILDCARD) {
                curWildCardCharacter = p.charAt(pIndex - 1);
                // .*模式，直接匹配到s的末尾
                if (curWildCardCharacter == POINT) {
                    flag = true;
                    originStringIndex = s.length();
                } else {
                    while (originStringIndex < s.length() && s.charAt(originStringIndex) == curWildCardCharacter) {
                        originStringIndex++;
                }
                }
            } else {
                return false;
            }
        }
//        if (flag) {
//            return s.endsWith(p.substring(i, p.length() - 1));
//        }
        // 还没有匹配到s的末尾，则无法全覆盖返回false
        return originStringIndex == s.length();
    }

    public static void main(String[] args) {
        Q10 q10 = new Q10();
        System.out.println(q10.isMatch("ab", ".*"));
        System.out.println(q10.isMatch("aa", "a"));
        System.out.println(q10.isMatch("aa", "a*"));
        System.out.println(q10.isMatch("aa", "a."));
        // 解决不了，重新理解题目：只要P包含s即true
        System.out.println(q10.isMatch("aab", "c*a*b"));
    }
}
