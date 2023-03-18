package leetcode;

/**
 * 1616. 分割两个字符串得到回文串
 * 给你两个字符串 a 和 b ，它们长度相同。请你选择一个下标，将两个字符串都在 相同的下标 分割开。
 * 由 a 可以得到两个字符串： aprefix 和 asuffix ，满足 a = aprefix + asuffix ，
 * 同理，由 b 可以得到两个字符串 bprefix 和 bsuffix ，满足 b = bprefix + bsuffix 。
 * 请你判断 aprefix + bsuffix 或者 bprefix + asuffix 能否构成回文串。
 * 当你将一个字符串 s 分割成 sprefix 和 ssuffix 时， ssuffix 或者 sprefix 可以为空。
 * 比方说， s = "abc" 那么 "" + "abc" ， "a" + "bc" ， "ab" + "c" 和 "abc" + "" 都是合法分割。
 * 如果 能构成回文字符串 ，那么请返回 true，否则返回 false 。
 *
 * 注意， x + y 表示连接字符串 x 和 y 。
 *
 * 示例 1：
 *
 * 输入：a = "x", b = "y"
 * 输出：true
 * 解释：如果 a 或者 b 是回文串，那么答案一定为 true ，因为你可以如下分割：
 * aprefix = "", asuffix = "x"
 * bprefix = "", bsuffix = "y"
 * 那么 aprefix + bsuffix = "" + "y" = "y" 是回文串。
 * 示例 2：
 *
 * 输入：a = "abdef", b = "fecab"
 * 输出：true
 * 示例 3：
 *
 * 输入：a = "ulacfd", b = "jizalu"
 * 输出：true
 * 解释：在下标为 3 处分割：
 * aprefix = "ula", asuffix = "cfd"
 * bprefix = "jiz", bsuffix = "alu"
 * 那么 aprefix + bsuffix = "ula" + "alu" = "ulaalu" 是回文串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/split-two-strings-to-make-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q1616 {

    /**
     * 参考题解中心扩展法：
     * 因对称性，则a、b构成的回文字符串必定从中间往两边扩展时，对应位置的字符均相等
     */
    public boolean checkPalindromeFormation(String a, String b) {
        // 如果有一边长度为1，则一定是回文（"" + "x"或"x" + ""）
        if (a.length() == 1) {
            return true;
        }
        // left即中心，因a、b等长，算一边即可
        int left = a.length() / 2 - 1;
        // 在a、b字符串中心找回文终点（若有）
        // left为不是回文的截断点，在这个点a头要接b尾，b头要接a尾
        // 组合字符串若能一路匹配到头尾(left = -1到头)，则构成回文
        left = Math.min(centerExpand(a, a, left), centerExpand(b, b, left));
        // a头b尾、b头a尾的情况，有构成回文的返回-1
        left = Math.min(centerExpand(a, b, left), centerExpand(b, a, left));
        return left == -1;
    }

    /**
     * 中心扩展
     * @return 返回左匹配终止索引，若为-1，则字符串构成回文
     */
    private int centerExpand(String a, String b, int left) {

        // 有扩展起始索引（奇数回文跳过最中间字符+2，偶数回文+1）
        // int right = a.length() % 2 == 0 ? left + 1 : left + 2;
        int right = a.length() - 1 - left;
        // 只要未到头就继续扩展，只有能连续匹配到头尾的才能是回文
        while (left >= 0 && right < b.length()) {
            if (a.charAt(left) != b.charAt(right)) {
                // 遇到不匹配的字符，返回中断匹配的left索引位置
                break;
            }
            // 匹配则继续比较下一字符
            --left;
            ++right;
        }
        return left;
    }

    /**
     * 遍历字符串有重复计算，超过限定时间
     */
    public boolean checkPalindromeFormationSlow(String a, String b) {
        // 如果有一边长度为1，则一定是回文（"" + "x"或"x" + ""）
        if (a.length() == 1 || b.length() == 1) {
            return true;
        }
        // 如果有一边自身是回文，则true
        if (isPalindrome(a) || isPalindrome(b)) {
            return true;
        }
        // 下面的情况一定是a、b搭配才可能构成回文的情况
        int maxCutIndex = Math.min(a.length(), b.length());
        // a头b尾的情况
        for (int i = 1; i < maxCutIndex; ++i) {
            if (isPalindrome(a.substring(0, i) + b.substring(i))) {
                return true;
            }
        }
        for (int i = 1; i < maxCutIndex; ++i) {
            if (isPalindrome(b.substring(0, i) + a.substring(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断一个字符串是否是回文字符串
     */
    private boolean isPalindrome(String s) {
        if (s.length() <= 1) {
            return true;
        }
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            ++left;
            --right;
        }
        return true;
    }
}
