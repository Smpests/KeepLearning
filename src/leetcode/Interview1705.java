package leetcode;

import java.util.Arrays;

/**
 * 面试题 17.05.  字母与数字
 * 给定一个放有字母和数字的数组，找到最长的子数组，且包含的字母和数字的个数相同。
 * 返回该子数组，若存在多个最长子数组，返回左端点下标值最小的子数组。若不存在这样的数组，返回一个空数组。
 * 示例 1:
 *
 * 输入: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"]
 *
 * 输出: ["A","1","B","C","D","2","3","4","E","5","F","G","6","7"]
 * 示例 2:
 *
 * 输入: ["A","A"]
 *
 * 输出: []
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-longest-subarray-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Interview1705 {
    /**
     * 思路一：笨方法，从数组长度大小的窗口逐渐缩小，通过滑动窗口枚举结果。
     */
    public String[] findLongestSubarray(String[] array) {
        if (array.length < 2) {
            return new String[0];
        }
        int charCount = 0;
        int numCount = 0;
        // 循环一遍根据字母和数字的个数确定初始窗口大小
        for (String s : array) {
            if (Character.isDigit(s.charAt(0))) {
                numCount++;
            } else {
                charCount++;
            }
        }
        int windowLength = Math.min(charCount, numCount) * 2;
        while (windowLength > 0) {
            charCount = 0;
            numCount = 0;
            for (int i = 0; i < array.length; ++i) {
                if (i < windowLength) {
                    if (Character.isDigit(array[i].charAt(0))) {
                        ++numCount;
                    } else {
                        ++charCount;
                    }
                } else {
                    boolean nextIsDigit = Character.isDigit((array[i].charAt(0)));
                    boolean prevIsDigit = Character.isDigit(i - windowLength);
                    if (nextIsDigit != prevIsDigit) {
                        if (nextIsDigit) {
                            --charCount;
                            ++numCount;
                        } else {
                            ++charCount;
                            --numCount;
                        }
                    }
                }
                if (i >= windowLength - 1 && numCount == charCount) {
                    return Arrays.copyOfRange(array, i + 1 - windowLength, i + 1);
                }
            }
            --windowLength;
        }
        return new String[0];
    }

    public static void main(String[] args) {
        String[] input = new String[] {"A","1","B","C","D","2","3","4","E","5","F","G","6","7","H","I","J","K","L","M"};
        System.out.println(Arrays.toString(new Interview1705().findLongestSubarray(input)));
    }
}
