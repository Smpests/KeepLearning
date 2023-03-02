import java.math.BigDecimal;

/**
 * 二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。
 * 如果该数字无法精确地用32位以内的二进制表示，则打印“ERROR”。
 *
 * 示例1:
 *  输入：0.625
 *  输出："0.101"
 * 示例2:
 *
 *  输入：0.1
 *  输出："ERROR"
 *  提示：0.1无法被二进制准确表示
 *  
 * 提示：
 *
 * 32位包括输出中的 "0." 这两位。
 * 题目保证输入用例的小数位数最多只有 6 位
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/bianry-number-to-string-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Interview0502 {
    private final static int MAX_BIT_LENGTH = 32;
    /**
     * 小数转二进制表示方式如下（整数部分用常规方式，下方只讨论小数部分）：
     * 将该数字乘以2，取出整数部分作为二进制表示的第1位；
     * 然后再将小数部分乘以2，将得到的整数部分作为二进制表示的第2位；
     * 以此类推，直到小数部分为0。
     * 特殊情况：小数部分出现循环，无法停止，则用有限的二进制位无法准确表示一个小数，这也是在编程语言中表示小数会出现误差的原因
     */
    public String printBin(double num) {
        BigDecimal bigDecimalNum = new BigDecimal(String.valueOf(num));
        BigDecimal bigDecimalMulti = new BigDecimal("2");
        BigDecimal zero = new BigDecimal("0");
        int bitLength = 2;
        StringBuilder bin = new StringBuilder("0.");
        while (bin.length() <= MAX_BIT_LENGTH && bigDecimalNum.compareTo(zero) != 0) {
            BigDecimal temp = bigDecimalNum.multiply(bigDecimalMulti);
            BigDecimal intPart = temp.setScale(0, BigDecimal.ROUND_DOWN);
            bin.append(intPart.intValue());
            bitLength++;
            bigDecimalNum = temp.subtract(intPart);
        }
        // 其它题解没有考虑double计算精度丢失问题是为什么？
        return bitLength <= MAX_BIT_LENGTH ? bin.toString() : "ERROR";
    }

    public static void main(String[] args) {
        System.out.println(new Interview0502().printBin(0.1));
    }
}
