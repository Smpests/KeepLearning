package org.qingsong;

import java.util.Arrays;

/**
 * 1626. 无矛盾的最佳球队
 * 假设你是球队的经理。对于即将到来的锦标赛，你想组合一支总体得分最高的球队。球队的得分是球队中所有球员的分数 总和 。
 * 然而，球队中的矛盾会限制球员的发挥，所以必须选出一支 没有矛盾 的球队。
 * 如果一名年龄较小球员的分数 严格大于 一名年龄较大的球员，则存在矛盾。同龄球员之间不会发生矛盾。
 * 给你两个列表 scores 和 ages，其中每组 scores[i] 和 ages[i] 表示第 i 名球员的分数和年龄。
 * 请你返回 所有可能的无矛盾球队中得分最高那支的分数 。
 *
 * 示例 1：
 *
 * 输入：scores = [1,3,5,10,15], ages = [1,2,3,4,5]
 * 输出：34
 * 解释：你可以选中所有球员。
 * 示例 2：
 *
 * 输入：scores = [4,5,6,5], ages = [2,1,2,1]
 * 输出：16
 * 解释：最佳的选择是后 3 名球员。注意，你可以选中多个同龄球员。
 * 示例 3：
 *
 * 输入：scores = [1,2,3,5], ages = [8,9,10,1]
 * 输出：6
 * 解释：最佳的选择是前 3 名球员。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/best-team-with-no-conflicts
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q1626 {
    /**
     * 思路：遍历数组逐个添加，核心是添加时遇到矛盾的选择策略。
     * 比较矛盾时添加新成员的分数收益同去除已添加的与之矛盾的球员的分数成本（动态规划）。
     * 比较容易想到O（n²）的解法，外部循环所有球员，内部循环已添加球员。
     */
    public int bestTeamScore(int[] scores, int[] ages) {
        int bestScore = 0;
        // 先按分数升序、分数相同按年龄升序排序
        // 分数有序之后，只需要考虑年龄是否矛盾

        // 因scores和ages有对应关系，新建一个ids数组排序并存储排序前的位置
        Integer[] ids = new Integer[scores.length];
        for (int i = 0; i < scores.length; ++i) {
            ids[i] = i;
        }
        // 分数是否相同，不同则根据分数大小排，相同则按照年龄排
        Arrays.sort(ids, (cur, next) -> scores[cur] != scores[next] ? scores[cur] - scores[next] : ages[cur] - ages[next]);

        // 存ages[i]添加为新球员时的最高得分（去除比该球员年龄小分数高的）
        // 类似动态规划的过程值记录
        // 一开始想用加法只计算和第i位没有矛盾的球员，
        // 发现存在A球员在队伍添加B球员时被剔除，
        // 但在计算C球员加入后的总分数时把A和B均计算在内的情况，
        // 正确的应该是只加入球员B的分数或球员A的分数（互斥，取分数更大的）
         int[] teamScore = new int[scores.length];
        for (int i = 0; i < scores.length; ++i) {
            // 计算和第i位球员没有矛盾的球员组成队伍的最高分
            for (int j = 0; j < i; ++j) {
                // 分数有序后，之前的队员只要age小于等于ages[i]就没有矛盾即成队
                if (ages[ids[j]] <= ages[ids[i]]) {
                    teamScore[i] = Math.max(teamScore[i], teamScore[j]);
                }
            }
            // 加上第i位的分数
            teamScore[i] += scores[ids[i]];
            // 取加入i前后较大的队伍分数
            bestScore = Math.max(teamScore[i], bestScore);
        }
        return bestScore;
    }
}
