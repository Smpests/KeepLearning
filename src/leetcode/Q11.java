package leetcode;

/**
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例 2：
 *
 * 输入：height = [1,1]
 * 输出：1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q11 {
    /**
     * 穷举组合，计算过程中记录容量最大值
     * 时间复杂度：O（n²）
     * 运行结果：对于长数组，计算超时了，需要降低时间复杂度
     */
    public int maxAreaSlow(int[] height) {
        int curMaxCap = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i; j < height.length; j++) {
                int curCap = Math.min(height[i], height[j]) * (j - i);
                curMaxCap = Math.max(curCap, curMaxCap);
            }
        }
        return curMaxCap;
    }

    /**
     * 参考题解理解：从数组头尾双向开始遍历，当头尾相遇时退出
     * 因为容量是取height[i]、height[j]中较小的一方（短板）做高，
     * 因此移动头尾中的短板，可能会遇到更高的短板，使得容量变大
     * 若移动头尾中的长板，因短板不变，容量只可能会不变或变小（遇到更小的height）
     * 因此每次移动头尾中的短板，直到头尾相遇，即遍历完毕
     * 时间复杂度：O（n），n为数组长度
     */
    public int maxArea(int[] height) {
        int curMaxCap = 0;
        int i = 0;
        int j = height.length - 1;
        while (j > i) {
            int minSide = height[i];
            int baseLength = j - i;
            if (height[i] > height[j]) {
                minSide = height[j];
                j--;
            } else {
                i++;
            }
            int curCap = minSide * baseLength;
            if (curCap > curMaxCap) {
                curMaxCap = curCap;
            }
        }
        return curMaxCap;
    }
}
