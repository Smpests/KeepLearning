package leetcode;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 *
 * 示例 1：
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q5 {
    /**
     * 理解：回文正逆序相等，则在正逆序字符串中对应索引位置的字符相同
     * 如：abba中，索引0和3处字符相同，索引1和2处字符相同
     * 则回文子串subString中索引i处字符等于subString.length() - 1 - i处字符
     * @param s 字符串
     * @return 最长回文子字符串
     */
    public String longestPalindrome(String s) {
        // 方法一：穷举两个相同的字符框定的子串，计算剩余字符在对应索引处是否相等
        // 方法二：中心扩散法（类似贪心，每次扩散成功保证局部是回文）
        // 扩散时有奇数长度回文和偶数长度回文的区别，
        // 奇数从自身往两边扩，偶数时从自身和右边邻居（因循环从左到右）两边扩
        int minLeft = 0;
        int maxRight = 0;
        for (int i = 0; i < s.length(); i++) {
            int oddSituation = expandLoop(s, i, i);
            int evenSituation = expandLoop(s, i, i + 1);
            int biggerLength = Math.max(oddSituation, evenSituation);
            if (biggerLength > maxRight - minLeft + 1) {
                minLeft = i - (biggerLength - 1) / 2;
                // 通过长度3和长度4的串，可以推出下面的公式
                maxRight = i + biggerLength / 2;
            }
        }
        return s.substring(minLeft, maxRight + 1);
    }

    private int expandLoop(String s, int left, int right) {

        while (left >= 0 && right <= s.length() - 1 && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 之前写的是 right - left + 1，总是数组越界，debug后分析如下：
        // 简写前是 right - left + 1 - 2，
        // +1是因为索引转长度，
        // -2是因为循环判断false时，
        // 是由于上一次循环left--和right++导致false，因此要减2的长度
        return right - left - 1;
    }

    public static void main(String[] args) {
        Q5 q5 = new Q5();
        System.out.println(q5.longestPalindrome("babad"));
    }
}
