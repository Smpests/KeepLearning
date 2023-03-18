package leetcode;

/**
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。
 * 这个特殊的规则只适用于以下六种情况：
 *
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给你一个整数，将其转为罗马数字。
 *
 * 示例 1:
 *
 * 输入: num = 3
 * 输出: "III"
 * 示例 2:
 *
 * 输入: num = 4
 * 输出: "IV"
 * 示例 3:
 *
 * 输入: num = 9
 * 输出: "IX"
 *
 * 提示：1 <= num <= 3999
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/integer-to-roman
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q12 {
    private final static String[] ROMAN_NUMBER = new String[]{"I", "V", "X", "L", "C", "D", "M"};
    /**
     * 从数字高位到低位遍历，获取每一位的罗马表示拼接起来
     * 方法比较笨，参考题解：
     * 方法一：将特殊情况和正常情况的罗马表示存为数组，从大到小遍历，
     * 传入的数字大于当前罗马表示，则保存并减去表示的值，剩下的值继续遍历。
     * 即优先用尽可能大的罗马数字去表示（贪心）
     * 方法二：将个数位上所有罗马表示编码，从高到低，什么数位什么数直接从对应的编码表中获取。O（1）
     */
    public String intToRoman(int num) {
        int numLength = ("" + num).length();
        int numericDigit = (int) Math.pow(10, numLength - 1);
        StringBuilder romanString = new StringBuilder();
        while (num > 0) {
            int temp = num / numericDigit;
            // 获取数字位数对应的罗马数字中位数的数组索引
            // 如3位数是500，索引5；2位数是50，索引3
            int anchor = numLength == 1 && num < 4 ? 0 : numLength-- * 2 - 1;
            num -= temp * numericDigit;
            numericDigit /= 10;
            if (temp == 4) {
                romanString.append(ROMAN_NUMBER[anchor - 1])
                        .append(ROMAN_NUMBER[anchor]);
            } else if (temp == 9) {
                romanString.append(ROMAN_NUMBER[anchor - 1])
                        .append(ROMAN_NUMBER[anchor + 1]);
            } else if (temp < 4) {
                while (temp > 0) {
                    romanString.append(ROMAN_NUMBER[Math.max(0, anchor - 1)]);
                    temp--;
                }
            } else if (temp < 9) {
                temp -= 5;
                romanString.append(ROMAN_NUMBER[anchor]);
                while (temp > 0) {
                    romanString.append(ROMAN_NUMBER[anchor - 1]);
                    temp--;
                }
            }
        }
        return romanString.toString();
    }

    public static void main(String[] args) {
        // 输出 CMXLV
        System.out.println(new Q12().intToRoman(1994));
    }
}
