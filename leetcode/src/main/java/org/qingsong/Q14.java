package org.qingsong;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1：
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 * 提示：
 *
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q14 {
    /**
     * 循环数组与公共前缀比较
     * 若遇到不包含公共前缀的字符串，直接返回""
     * 若遇到不能完全包含公共前缀的字符串，则删减公共前缀
     */
    public String longestCommonPrefix(String[] strs) {
        // 默认最长公共前缀为第一个字符串
        String commonPrefix = strs[0];
        for (String str : strs) {
            while (commonPrefix.length() > 0
                    && !str.substring(0, Math.min(str.length(), commonPrefix.length())).equals(commonPrefix)) {
                commonPrefix = commonPrefix.substring(0, commonPrefix.length() - 1);
            }
            if (commonPrefix.length() == 0) {
                return "";
            }
        }
        return commonPrefix;
    }
}
