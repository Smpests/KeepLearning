package leetcode;

/**
 * 给你一个整数数组 nums ，返回其中 按位与三元组 的数目。
 * 按位与三元组 是由下标 (i, j, k) 组成的三元组，并满足下述全部条件：
 *
 * 0 <= i < nums.length
 * 0 <= j < nums.length
 * 0 <= k < nums.length
 * nums[i] & nums[j] & nums[k] == 0 ，其中 & 表示按位与运算符。
 *  
 * 示例 1：
 *
 * 输入：nums = [2,1,3]
 * 输出：12
 * 解释：可以选出如下 i, j, k 三元组：
 * (i=0, j=0, k=1) : 2 & 2 & 1
 * (i=0, j=1, k=0) : 2 & 1 & 2
 * (i=0, j=1, k=1) : 2 & 1 & 1
 * (i=0, j=1, k=2) : 2 & 1 & 3
 * (i=0, j=2, k=1) : 2 & 3 & 1
 * (i=1, j=0, k=0) : 1 & 2 & 2
 * (i=1, j=0, k=1) : 1 & 2 & 1
 * (i=1, j=0, k=2) : 1 & 2 & 3
 * (i=1, j=1, k=0) : 1 & 1 & 2
 * (i=1, j=2, k=0) : 1 & 3 & 2
 * (i=2, j=0, k=1) : 3 & 2 & 1
 * (i=2, j=1, k=0) : 3 & 1 & 2
 * 示例 2：
 *
 * 输入：nums = [0,0,0]
 * 输出：27
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/triples-with-bitwise-and-equal-to-zero
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q982 {
    /**
     * 容易想到用穷举，时间复杂度O(n^3)预计超时
     * 参考题解：先枚举i和j，正整数的与操作得到的结果>=0，
     * 将i & j的结果作为数组索引，数组值为i & j的结果出现次数。
     * 然后将上述数组和nums再做k的循环，只要上述数组中的索引号与上k=0，
     * 则为有效三元组
     */
    public int countTriplets(int[] nums) {
        int[] cnt = new int[1 << 16];
        for (int x : nums) {
            for (int y : nums) {
                ++cnt[x & y];
            }
        }
        int ans = 0;
        for (int x : nums) {
            for (int mask = 0; mask < (1 << 16); ++mask) {
                if ((x & mask) == 0) {
                    ans += cnt[mask];
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Q982().countTriplets(new int[] {0, 0, 0}));
    }
}
