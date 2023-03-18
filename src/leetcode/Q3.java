package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q3 {
    public int lengthOfLongestSubstring(String s) {
        int totalLength = s.length();
        // 存储遍历过的字符及其索引
        Map<Character, Integer> charset = new HashMap<>();
        int result = 0;
        // 当前子串起始索引
        int startIndex = 0;
        for (int i = 0;i < totalLength;i++) {
            char curChar = s.charAt(i);
            // 发现重复字符
            if (charset.containsKey(curChar)) {
                // 若重复前的长度大于已知最长长度，则更新其为最长子串长度
                if (i - startIndex > result) {
                    result = i - startIndex;
                }
                // 更新子串起始索引为重复字符索引的后面
                startIndex = Math.max(startIndex, charset.get(curChar) + 1);
            }
            // 更新字符集，若重复则更新索引i
            charset.put(curChar, i);
        }
        // 退出循环时表示已遍历到最后，可能未更新result,
        // 再判断一次末尾到子串起始位置的长度是否大于已知最长子串长度
        if (totalLength - startIndex > result) {
            result = totalLength - startIndex;
        }
        return result;
    }
}
