package leetcode.dynamicpro;

/**
 * 1653. 使字符串平衡的最少删除次数
 * 给你一个字符串 s ，它仅包含字符 'a' 和 'b'。
 * 你可以删除 s 中任意数目的字符，使得 s 平衡 。
 * 当不存在下标对 (i,j) 满足 i < j ，且 s[i] = 'b' 的同时 s[j]= 'a' ，此时认为 s 是 平衡 的。
 * 请你返回使 s 平衡 的 最少 删除次数。
 *
 * 示例 1：
 *
 * 输入：s = "aababbab"
 * 输出：2
 * 解释：你可以选择以下任意一种方案：
 * 下标从 0 开始，删除第 2 和第 6 个字符（"aababbab" -> "aaabbb"），
 * 下标从 0 开始，删除第 3 和第 6 个字符（"aababbab" -> "aabbbb"）。
 * 示例 2：
 *
 * 输入：s = "bbaaaaabb"
 * 输出：2
 * 解释：唯一的最优解是删除最前面两个字符。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-deletions-to-make-string-balanced
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q1653 {
    public int minimumDeletions(String s) {
        int deleteOperations = 0;
        // 字符a前面的字符b数量
        int bCountBeforeA = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'b') {
                bCountBeforeA++;
            } else {
                // 如果当前字符等于a，那么删除a操作数+1，删除b操作数等于a前面的b数量
                // 所以当前删除a的成本较小时，删除a，否则删除b
                // 在过程中得到最优解（动态规划）
                deleteOperations = Math.min(deleteOperations + 1, bCountBeforeA);
            }
        }
        return deleteOperations;
    }

    public static void main(String[] args) {
        System.out.println(new Q1653().minimumDeletions("bbaaaabbabbb"));
    }
}
