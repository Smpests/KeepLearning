import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 * 示例 2：
 *
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 * 示例 3：
 *
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q15 {
    /**
     * 题型类似Q982
     * 思路一：
     * 遍历所有三元组组成情况
     * 创建一个Map<Integer, List<Integer>>用于去重
     * 存储每个数字key出现在函数返回列表中的索引，如果三元组的每个数字落在map的value中的同一个索引上则判断为重复
     * 如map：{-1: [1, 2], 2: [2, 8], -1: [2, 3]}，那么三元组(-1, -1, 2)均出现在result的2索引上，则重复
     * 思路二：
     * 将nums数组按从小到大排序，nums[i] > 0时结束遍历，因为i后面的数字都大于0，不会再构成和为0的三元组，
     * 第一次两层循环构成二元组，存储的是在nums中的索引
     * 第二次两层循环构成三元组，从二元组的大索引右方遍历循环，不在下方map中即有效
     * 将三元组中的数字按从小到大顺序拼成字符串放入set中，若存在则重复
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // TODO(完成): 使用内存超出题目限制，优化result中只添加有效的
        // TODO: 该复杂度超出时间限制，添加步骤注释
        Set<String> threeSumSet = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        // 时间复杂度O(n^3)
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if (i > 1 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                for (int k = j + 1; k < nums.length; k++) {
                    if (k > j + 1 && nums[k] == nums[k - 1]) {
                        continue;
                    }
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum == 0) {
                        String threeSumString = String.format("%d%d%d", nums[i], nums[j], nums[k]);
                        if (!threeSumSet.contains(threeSumString)) {
                            threeSumSet.add(threeSumString);
                            List<Integer> threeNum = new ArrayList<>();
                            threeNum.add(nums[i]);
                            threeNum.add(nums[j]);
                            threeNum.add(nums[k]);
                            result.add(threeNum);
                        }
                        break;
                    } else if (sum > 0) {
                        break;
                    }
                }
            }
        }
//        for (Iterator<List<Integer>> it = result.iterator(); it.hasNext(); ) {
//            List<Integer> twoNum = it.next();
//            for (int i = twoNum.get(1) + 1; i < nums.length; i++) {
//                int firstIndex = twoNum.get(0);
//                int secondIndex = twoNum.get(1);
//                int sum = nums[firstIndex] + nums[secondIndex] + nums[i];
//                if (sum == 0) {
//                    String threeSumString = String.format("%d%d%d", nums[firstIndex], nums[secondIndex], nums[i]);
//                    if (!threeSumSet.contains(threeSumString)) {
//                        threeSumSet.add(threeSumString);
//                        twoNum.set(0, nums[firstIndex]);
//                        twoNum.set(1, nums[secondIndex]);
//                        twoNum.add(nums[i]);
//                    }
//                    break;
//                } else if (sum > 0) {
//                    break;
//                }
//            }
//            if (twoNum.size() < 3) {
//                it.remove();
//            }
//        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Q15().threeSum(new int[] {-1,0,1,2,-1,-4}));
        System.out.println(new Q15().threeSum(new int[] {0, 0, 0, 0}));
    }
}
