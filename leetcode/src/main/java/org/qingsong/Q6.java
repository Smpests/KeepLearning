package org.qingsong;

import java.util.ArrayList;
import java.util.List;

/**
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   | A   | H   | N
 * A P | L S | I I | G
 * Y   | I   | R   |
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 *
 * 示例 1：
 *
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q6 {

    /**
     * 上面的琢磨半天自己写的，太烂了，下面的参考学习题解
     */
    public String convertStupid(String s, int numRows) {
        // 只有一个字符的列有numRows - 2个
        // 列数 y + numRows * x = s.length()
        // 每个独立单元(见示例中被竖线分割)的字符数
        if (s.length() <= numRows || numRows <= 1) {
            return s;
        }
        int unitCharNums = numRows + numRows - 2;
        int unitColNums = 1 + numRows - 2;
        // 计算得到列数
        int numCols = 0;
        if (s.length() <= unitCharNums) {
            numCols = s.length() - numRows + 1;
        } else {
            numCols = s.length() / unitCharNums * unitColNums + Math.max(s.length() % unitCharNums - numRows + 1, 1);
        }
        // 单元数量
        int unitNums = s.length() % unitCharNums == 0 ? s.length() / unitCharNums : s.length() / unitCharNums + 1;
        // 转换后的矩阵，输出时两层循环不输出0值
        char[][] convertMatrix = new char[numRows][numCols];
        // 当前循环的单元编号
        int unitNo = 0;
        int count = 0;
        int col = 0;
        while (unitNo * unitCharNums + count < s.length() && col < numCols) {
            if (count >= unitCharNums) {
                count = 0;
                unitNo++;
                col++;
                continue;
            }
            if (count < numRows) {
                convertMatrix[count][col] = s.charAt(unitNo * unitCharNums + count);
            } else {
                convertMatrix[numRows - 1 - (count + 1 - numRows)][++col] = s.charAt(unitNo * unitCharNums + count);
            }
            count++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (convertMatrix[i][j] != 0) {
                    sb.append(convertMatrix[i][j]);
                }
            }
        }
        return sb.toString();
    }

    /**
     * Z字形填充矩阵时，发现行索引从小到大，再从大到小
     * 不用管列号，只需要按Z字形顺序将字符填到对应行上
     * 最后拼接所有行输出即可
     */
    public String convert(String s, int numRows) {
        // 不足以变形，直接返回
        if (s.length() <= numRows || numRows <= 1) {
            return s;
        }
        // 存放行字符，因变长，所以用StringBuilder
        List<StringBuilder> rowStringList = new ArrayList<>();
        // 初始化
        for (int i = 0; i < numRows; i++) {
            rowStringList.add(new StringBuilder());
        }
        // 应插入的行索引
        int rowPointer = 0;
        // 递增或递减，-1是因为一开始索引是0会触发循环内条件
        int changeDirection = -1;
        for (char ch : s.toCharArray()) {
            rowStringList.get(rowPointer).append(ch);
            // 判断下一个插入索引是变小还是变大
            // 转折点在当前索引等于首行索引或末行索引时
            if (rowPointer == numRows - 1 || rowPointer == 0) {
                changeDirection = 0 - changeDirection;
            }
            rowPointer += changeDirection;
        }
        return rowStringList.stream()
                .reduce(StringBuilder::append)
                .map(StringBuilder::toString)
                .orElse("");
    }


    public static void main(String[] args) {
        Q6 q6 = new Q6();
        System.out.println(q6.convert("PAYPALISHIRING", 5));
    }
}
