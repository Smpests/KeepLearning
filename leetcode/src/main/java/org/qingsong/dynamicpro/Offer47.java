package org.qingsong.dynamicpro;

/**
 * 剑指 Offer 47. 礼物的最大价值
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
 * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 *
 * 示例 1:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 *  
 *
 * 提示：
 *
 * 0 < grid.length <= 200
 * 0 < grid[0].length <= 200
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Offer47 {
    /**
     * 看到就知道用动态规划，于是查看题解。
     * 理解在该场景下，动规通过穷举各选择的下一步能得到得最大价值，在最终位置得到最优解。
     * 就像是高手下棋，模拟对手几个可能得落子位置来决定自己的落子位置。
     * 只看题解不看代码，尝试写。
     */
    public int maxValue(int[][] grid) {
        int width = grid[0].length;
        int height = grid.length;
        // 直接使用grid矩阵作为dp矩阵节省空间
        // 其实不考虑空间的话，新建一个宽高各增加1的矩阵可以不考虑一些边界问题

        // 处理第一行，只可能从左边来
        for (int col = 1; col < width; ++col) {
            grid[0][col] += grid[0][col - 1];
        }
        // 处理第一列，只可能从上方来
        for (int row = 1; row < height; ++row) {
            grid[row][0] += grid[row - 1][0];
        }
        // 处理其余格，有两种选择，选择价值最大的方向
        for (int i = 1; i < height; ++i) {
            for (int j = 1; j < width; ++j) {
                grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
            }
        }
        // 返回矩阵右下角就是解
        return  grid[height - 1][width - 1];
    }
}
