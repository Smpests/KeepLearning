package org.qingsong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18.四数之和
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
 * 请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] 
 * （若两个四元组元素一一对应，则认为两个四元组重复）：
 *
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 *
 * 示例 1：
 *
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 *
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q18 {
    /**
     * 思路：参考三数之和（leetcode.Q16），先排序，然后先双指针构成三元组，然后在遍历最后一个数
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 从小到大排序，O(nlogn)
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        // 最小的数大于目标，无解
        if (nums[0] > 0 && nums[0] > target) {
            return result;
        }
        // 枚举第一个数，因有序，大于target的时候结束遍历
        for (int i = 0; i < nums.length; i++) {
            // 第一个数重复枚举则忽略（去重）
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                // 第二个数重复枚举则跳过
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 同三数之和
                int head = j + 1;
                int tail = nums.length - 1;
                while (head < tail) {
                    // 第三个数重复枚举则忽略
                    if (head > j + 1 && nums[head] == nums[head - 1]) {
                        head++;
                    } else if (tail < nums.length - 2 && nums[tail] == nums[tail + 1]) {
                        // 第四个数重复枚举则跳过
                        tail--;
                    } else {
                        // TODO: int溢出情况
                        int sum = nums[i] + nums[j] + nums[head] + nums[tail];
                        if (sum > target) {
                            tail--;
                        } else if (sum < target) {
                            head++;
                        } else {
                            // 找到一个放入结果集合中
                            result.add(Arrays.asList(nums[i], nums[j], nums[head], nums[tail]));
                            head++;
                            tail--;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Q18().fourSum(new int[] {1000000000,1000000000,1000000000,1000000000}, -294967296));
    }
}
