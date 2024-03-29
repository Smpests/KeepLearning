package org.qingsong.greedy;

/**
 * 给你一个整数数组 nums，每次 操作 会从中选择一个元素并 将该元素的值减少 1。
 * 如果符合下列情况之一，则数组 A 就是 锯齿数组：
 * 每个偶数索引对应的元素都大于相邻的元素，即 A[0] > A[1] < A[2] > A[3] < A[4] > ...
 * 或者，每个奇数索引对应的元素都大于相邻的元素，即 A[0] < A[1] > A[2] < A[3] > A[4] < ...
 * 返回将数组 nums 转换为锯齿数组所需的最小操作次数。
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：2
 * 解释：我们可以把 2 递减到 0，或把 3 递减到 1。
 * 示例 2：
 *
 * 输入：nums = [9,6,1,6,2]
 * 输出：4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/decrease-elements-to-make-array-zigzag
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q1144 {
    public int movesToMakeZigzag(int[] nums) {
        // 贪心
        // 从左到右每次奇数或偶数索引处做满足条件的最小操作次数
        // 比较奇数和偶数情况下的总操作次数
        // [5, 6, 6, 5, 4]在奇数索引下，索引1有两个均为1的不同操作，会影响后续的操作次数
        // 考虑增加一个条件，相同操作次数的不同操作以造成左右最小差值总和的为准
        // 即上述数组经过一次操作后变为[5, 6, 5, 5, 4]，因为6 - 5 + 6 - 6 = 2,
        // 而变为[5, 7, 6, 5, 4]时，7 - 5 + 7 - 6 = 3
        // 无法解决：[6, 6, 6, 4, 5] -> [7, 6, 6, 4, 5] -> [6, 5, 6, 4, 5]
        // 推翻上述考虑：在奇数索引条件下，修改奇数位置的值不会是最优解，递减其邻值才是最优，
        // 因为相邻的越小，对下一个奇数索引的值越有利。偶数情况相同。

        int odd = 0;
        int even = 0;
        for (int i = 0; i < nums.length; i++) {
            // 奇数情况下递减偶数位置的值，递减到比相邻偶数位置的最小值还小
            // 如果已经比相邻的小，则减法会是负数，则取0
            // 左邻值
            int curOperate = 0;
            if (i - 1 >= 0) {
                // nums[i] - nums[i - 1] + 1为减少到比左邻小的操作次数
                // Max则选出左邻右邻中操作次数最多的（即左邻右邻中最小的）
                curOperate = Math.max(nums[i] - nums[i - 1] + 1, curOperate);
            }
            if (i + 1 < nums.length) {
                curOperate = Math.max(nums[i] - nums[i + 1] + 1, curOperate);
            }
            if (i % 2 == 0) {
                odd += curOperate;
            } else {
                even += curOperate;
            }
        }
        return Math.min(odd, even);
    }
}
