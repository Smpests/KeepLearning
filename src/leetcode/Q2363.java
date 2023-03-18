package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 给你两个二维整数数组 items1 和 items2 ，表示两个物品集合。每个数组 items 有以下特质：
 * items[i] = [valuei, weighti] 其中 valuei 表示第 i 件物品的 价值 ，weighti 表示第 i 件物品的 重量 。
 * items 中每件物品的价值都是 唯一的 。
 * 请你返回一个二维数组 ret，其中 ret[i] = [valuei, weighti]， weighti 是所有价值为 valuei 物品的 重量之和 。
 *
 * 注意：ret 应该按价值 升序 排序后返回。
 *
 * 示例 1：
 *
 * 输入：items1 = [[1,1],[4,5],[3,8]], items2 = [[3,1],[1,5]]
 * 输出：[[1,6],[3,9],[4,5]]
 * 解释：
 * value = 1 的物品在 items1 中 weight = 1 ，在 items2 中 weight = 5 ，总重量为 1 + 5 = 6 。
 * value = 3 的物品再 items1 中 weight = 8 ，在 items2 中 weight = 1 ，总重量为 8 + 1 = 9 。
 * value = 4 的物品在 items1 中 weight = 5 ，总重量为 5 。
 * 所以，我们返回 [[1,6],[3,9],[4,5]] 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-similar-items
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q2363 {
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        // map以value做key，循环items数组累加weight
        Map<Integer, Integer> valueWeightMap = new HashMap<>(items1.length);
        Stream.concat(Arrays.stream(items1), Arrays.stream(items2))
                .forEach(vw -> {
                    valueWeightMap.put(vw[0], valueWeightMap.getOrDefault(vw[0], 0) + vw[1]);
                });
        return valueWeightMap.keySet().stream()
                .sorted()
                .map(key -> Arrays.asList(key, valueWeightMap.get(key)))
                .collect(Collectors.toList());
    }
}
