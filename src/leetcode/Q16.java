package leetcode;

import java.util.Arrays;

/**
 * 给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在恰好一个解。
 *
 * 示例 1：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * 示例 2：
 *
 * 输入：nums = [0,0,0], target = 1
 * 输出：0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/3sum-closest
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q16 {
    /**
     * 类似Q15，用双指针
     */
    public int threeSumClosest(int[] nums, int target) {
        // 从小到大排序，方便边界条件的判断
        Arrays.sort(nums);
        // 初始值，因排序所以最小
        int answer = nums[0] + nums[1] + nums[2];
        // 和target的距离
        int minDistance = Math.abs(target - answer);
        // 只有三个数，直接返回
        if (nums.length == 3) {
            return answer;
        }
        for (int i = 0; i < nums.length; i++) {
            // 头指针，从i的右侧开始，因为i前面的值已经遍历过组合了
            int head = i + 1;
            // 尾指针
            int tail = nums.length - 1;
            // 当头尾不相等时，还有数没遍历组合
            while (head < tail) {
                int sum = nums[i] + nums[head] + nums[tail];
                int distance = Math.abs(target - sum);
                // 找到唯一解
                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    // 小于目标，则head往右移，增大sum
                    head++;
                } else {
                    // 大于目标，则tail往左移，减小sum
                    tail--;
                }
                // 记录当前与目标的最小距离及sum
                if (distance < minDistance) {
                    minDistance = distance;
                    answer = sum;
                }
            }
        }
        return answer;
    }
}
