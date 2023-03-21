package org.qingsong;

/**
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 例如，121 是回文，而 123 不是。
 *  
 * 示例 1：
 *
 * 输入：x = 121
 * 输出：true
 * 示例 2：
 *
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3：
 *
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q9 {
    /**
     * 利用Q7的解法整数反转后判断是否相等即可（推翻：反转之后可能超出int边界导致错误；解决方案：反转一半数字即可）
     * 将整数转成字符串后，利用Q5的最长回文子串中的中心扩散法（可以从中间往两边，也可以从首尾往中间）
     * 个位数是0（反转后长度不一样）、负数直接排除，不可能构成回文
     */
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int reversedPart = 0;
        while (x > reversedPart) {
            reversedPart = reversedPart * 10 + x % 10;
            x /= 10;
        }
        return x == reversedPart || x == reversedPart / 10;
    }
}