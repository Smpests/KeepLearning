/**
 * 2379. 得到 K 个黑块的最少涂色次数
 * 给你一个长度为 n 下标从 0 开始的字符串 blocks ，blocks[i] 要么是 'W' 要么是 'B' ，表示第 i 块的颜色。
 * 字符 'W' 和 'B' 分别表示白色和黑色。
 * 给你一个整数 k ，表示想要 连续 黑色块的数目。
 * 每一次操作中，你可以选择一个白色块将它 涂成 黑色块。
 * 请你返回至少出现 一次 连续 k 个黑色块的 最少 操作次数。
 *
 * 示例 1：
 *
 * 输入：blocks = "WBBWWBBWBW", k = 7
 * 输出：3
 * 解释：
 * 一种得到 7 个连续黑色块的方法是把第 0 ，3 和 4 个块涂成黑色。
 * 得到 blocks = "BBBBBBBWBW" 。
 * 可以证明无法用少于 3 次操作得到 7 个连续的黑块。
 * 所以我们返回 3 。
 * 示例 2：
 *
 * 输入：blocks = "WBWBBBW", k = 2
 * 输出：0
 * 解释：
 * 不需要任何操作，因为已经有 2 个连续的黑块。
 * 所以我们返回 0 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-recolors-to-get-k-consecutive-black-blocks
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q2739 {
    /**
     * 得到题目看到例题，第一反应就是用滑动窗口：
     * 思路一：因为k个连续黑块的设定，只要我们在字符串上从左到右框定k个字符的窗口，
     * 在这个窗口上求使该窗口全改为B的操作次数，最后得到各个窗口的操作次数，返回最小的；
     * 时间复杂度：O（N²），有重复遍历的部分，速度会比较慢，超过25%。
     */
    public int minimumRecolors1(String blocks, int k) {
        // 滑动窗口个数
        int windows = blocks.length() - k + 1;
        int minOperations = blocks.length();
        for (int windowNo = 0; windowNo < windows; ++windowNo) {
            int operations = 0;
            for (int index = windowNo; index < k + windowNo; ++index) {
                if (blocks.charAt(index) == 'W') {
                    ++operations;
                }
            }
            minOperations = Math.min(minOperations, operations);
        }
        return minOperations;
    }

    /**
     * 思路二：考虑到速度问题，能否降低时间复杂度到O（n）?
     * 其实在思路一中，只需要知道窗口滑动时，前一个窗口的第一位和当前窗口的最后一位是什么；
     * 分几种情况，前窗口第一位和当前窗口最后一位一样（操作次数不变）；
     * 前窗口第第一位和当前窗口最后一位不同（前白后黑，操作次数-1，前黑后白操作次数+1），
     * 增加操作次数的情况直接跳到下一个窗口。
     * 时间复杂度：O(n)。
     * 实验结果：只快于58%，还需优化，判断是受多个if-else影响
     */
    public int minimumRecolors(String blocks, int k) {
        int operations = 0;
        int minOperations = 0;
        for (int index = 0; index < blocks.length(); ++index) {
//            if (index < k) {
//                if (blocks.charAt(index) == 'W') {
//                    ++operations;
//                    ++minOperations;
//                }
//            } else if (blocks.charAt(index - k) == 'B' && blocks.charAt(index) == 'W') {
//                ++operations;
//            } else if (blocks.charAt(index - k) == 'W' && blocks.charAt(index) == 'B') {
//                --operations;
//                minOperations = Math.min(operations, minOperations);
//            }
            if (index < k) {
                if (blocks.charAt(index) == 'W') {
                    ++operations;
                    ++minOperations;
                }
            } else if (blocks.charAt(index - k) != blocks.charAt(index)) {
                if (blocks.charAt(index) == 'W') {
                    ++operations;
                } else {
                    --operations;
                    minOperations = Math.min(operations, minOperations);
                }
            }
        }
        return minOperations;
    }
}
