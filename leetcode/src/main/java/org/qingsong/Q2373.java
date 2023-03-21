package org.qingsong;

import java.util.Arrays;

/**
 * 给你一个大小为 n x n 的整数矩阵 grid 。
 * 生成一个大小为 (n - 2) x (n - 2) 的整数矩阵  maxLocal ，并满足：
 * maxLocal[i][j] 等于 grid 中以 i + 1 行和 j + 1 列为中心的 3 x 3 矩阵中的 最大值 。
 * 换句话说，我们希望找出 grid 中每个 3 x 3 矩阵中的最大值。
 * 返回生成的矩阵。
 *
 * 示例 1：
 *
 * 输入：grid = [[9,9,8,1],[5,6,2,6],[8,2,6,4],[6,2,2,2]]
 * 输出：[[9,9],[8,6]]
 * 解释：原矩阵和生成的矩阵如上图所示。
 * 注意，生成的矩阵中，每个值都对应 grid 中一个相接的 3 x 3 矩阵的最大值。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/largest-local-values-in-a-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q2373 {
    public int[][] largestLocal(int[][] grid) {
        int matrixLength = grid.length;
        int[][] maxLocal = new int[matrixLength - 2][matrixLength - 2];
        for (int i = 0; i < matrixLength - 2; i++) {
            for (int j = 0; j < matrixLength - 2; j++) {
                // stream().max()速度一般
//                maxLocal[i][j] = Stream.of(
//                        grid[i][j], grid[i][j + 1], grid[i][j + 2],
//                        grid[i + 1][j], grid[i + 1][j + 1], grid[i + 1][j + 2],
//                        grid[i + 2][j], grid[i + 2][j + 1], grid[i + 2][j + 2]
//                ).max(Comparator.comparingInt(Integer::intValue)).get();
                for (int p = 0; p < 3; p++) {
                    for (int q = 0; q < 3; q++) {
                        maxLocal[i][j] = Math.max(maxLocal[i][j], grid[i + p][j + q]);
                    }
                }
            }
        }
        return maxLocal;
    }

    public static void main(String[] args) {
        int[][] input = {{1,1,1,1,1},{1,1,1,1,1},{1,1,2,1,1},{1,1,1,1,1},{1,1,1,1,1}};
        System.out.println(Arrays.deepToString(new Q2373().largestLocal(input)));
    }
}
