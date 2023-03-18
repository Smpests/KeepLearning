package leetcode;

/**
 * 给定一个罗马数字，将其转换成整数。
 * 细节描述见：leetcode.Q12.java
 */

public class Q13 {
    private final static String[] ROMAN = new String[] {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private final static int[] ROMAN_NUMBER = new int[] {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    public int romanToInt(String s) {
        int result = 0;
        int startIndex = 0;
        // TODO: 表示小值的符号在大值符号左边时做减法，右边时做加法，程序效率更快
        for (int i = 0; i < ROMAN.length; i++) {
            while (startIndex < s.length() && s.substring(startIndex, Math.min(startIndex + ROMAN[i].length(), s.length())).equals(ROMAN[i])) {
                result += ROMAN_NUMBER[i];
                startIndex += ROMAN[i].length();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Q13().romanToInt("DCXXI"));
    }
}
