package leetcode.recursion;

/**
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 * 示例 1：
 *
 * 输入：x = 123
 * 输出：321
 * 示例 2：
 *
 * 输入：x = -123
 * 输出：-321
 * 示例 3：
 *
 * 输入：x = 120
 * 输出：21
 * 示例 4：
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q7 {
    /**
     * 每次对10取模，得到的值乘以反转后的倍率
     * 比如1234，取模一次后得4，4要到最高位千位上
     * 因为1000 < 1234 < 10000，则倍率为1000
     * 每次取模倍率也要除以10
     * 以下方法无法满足超过32位最大最小数的时候返回0
     * 会在计算过程中溢出成错误值
     */
    public int reverseWrong(int x) {
        String numString = Math.abs(x) + "";
        int numericDigit = (int) Math.pow(10, numString.length() - 1);
        int reversedValue = mod(x, numericDigit);
        if (reversedValue >= Math.pow(-2, 31) && reversedValue <= Math.pow(2, 31) - 1) {
            return reversedValue;
        }
        return 0;
    }

    private int mod(int num, int numericalDigit) {
        if (num < 10 && num >= 0) {
            return num;
        }
        if (num > -10 && num <= 0) {
            return num;
        }
        int remainder = num % 10;
        // 有坑：超过32位最大最小的数会溢出成错误的数
        // 在不能使用long的情况下，不能从高到低计算
        // 只能从低位逐渐变大，并在过程中与最大最下1/10的数比较
        // 见下方题解的解决方案：每次循环上一次的结果乘以10即可从小算起
        int reversedValue = remainder * numericalDigit;
        num = num / 10;
        numericalDigit = numericalDigit / 10;
        return reversedValue + mod(num, numericalDigit);
    }

    /**
     * 作者：wang_ni_ma
     */
    public int reverse(int x) {
        int res = 0;
        while(x!=0) {
            //每次取末尾数字
            int tmp = x%10;
            //判断是否 大于 最大32位整数
            if (res>214748364 || (res==214748364 && tmp>7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (res<-214748364 || (res==-214748364 && tmp<-8)) {
                return 0;
            }
            res = res*10 + tmp;
            x /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new Q7().reverse(1534236469));
    }
}
