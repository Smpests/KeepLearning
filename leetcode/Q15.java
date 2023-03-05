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
    public List<List<Integer>> threeSumSlow(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        // TODO: 时间复杂度O(n^3)，超出时间限制
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            // 重复的第1位数字跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                // 重复的第2位数字跳过
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                for (int k = j + 1; k < nums.length; k++) {
                    // 重复的第3位数字跳过
                    // 上述3个步骤就是去重
                    if (k > j + 1 && nums[k] == nums[k - 1]) {
                        continue;
                    }
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum == 0) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        // 只会有一个数字与二元组的和为0，找到后退出当前循环继续下一个二元组
                        break;
                    } else if (sum > 0) {
                        // 如果第3个数会让和大于0，因为数组排序过，所以不会再有数字构成3元组的和为0
                        break;
                    }
                }
            }
        }
        return result;
    }

    // 题解答案
    //定义三个指针，保证遍历数组中的每一个结果
    //画图，解答
    public List<List<Integer>> threeSum(int[] nums) {
        //定义一个结果集
        List<List<Integer>> res = new ArrayList<>();
        //数组的长度
        int len = nums.length;
        //当前数组的长度为空，或者长度小于3时，直接退出
        if(nums == null || len <3){
            return res;
        }
        //将数组进行排序
        Arrays.sort(nums);
        //遍历数组中的每一个元素
        for(int i = 0; i<len;i++){
            //如果遍历的起始元素大于0，就直接退出
            //原因，此时数组为有序的数组，最小的数都大于0了，三数之和肯定大于0
            if(nums[i]>0){
                break;
            }
            //去重，当起始的值等于前一个元素，那么得到的结果将会和前一次相同
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int l = i +1;
            int r = len-1;
            //当 l 不等于 r时就继续遍历
            while(l<r){
                //将三数进行相加
                int sum = nums[i] + nums[l] + nums[r];
                //如果等于0，将结果对应的索引位置的值加入结果集中
                if(sum==0){
                    // 将三数的结果集加入到结果集中
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    //在将左指针和右指针移动的时候，先对左右指针的值，进行判断
                    //如果重复，直接跳过。
                    //去重，因为 i 不变，当此时 l取的数的值与前一个数相同，所以不用在计算，直接跳
                    while(l < r && nums[l] == nums[l+1]) {
                        l++;
                    }
                    //去重，因为 i不变，当此时 r 取的数的值与前一个相同，所以不用在计算
                    while(l< r && nums[r] == nums[r-1]){
                        r--;
                    }
                    //将 左指针右移，将右指针左移。
                    l++;
                    r--;
                    //如果结果小于0，将左指针右移
                }else if(sum < 0){
                    l++;
                    //如果结果大于0，将右指针左移
                }else if(sum > 0){
                    r--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Q15().threeSum(new int[] {-1,0,1,2,-1,-4}));
        System.out.println(new Q15().threeSum(new int[] {0, 0, 0, 0}));
    }
}
