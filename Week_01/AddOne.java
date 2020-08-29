import java.util.Arrays;

/**
 * @author jacky
 * @version 1.0.0
 */
public class AddOne {

    /**
     * 第一次可以使用数学来解题
     * 根据题意加一，没错就是加一这很重要，因为它是只加一的所以有可能的情况就只有两种：
     * 除 99 之外的数字加一；
     * 数字 99。
     */

    public static void main(String[] args) {
        int[] test = new int[]{2, 3, 4};
        long start = System.currentTimeMillis();
        System.out.println(Arrays.toString(plusOne(test)));
        long end = System.currentTimeMillis();
    }


    /**
     * 末位无进位，则末位加一即可，因为末位无进位，前面也不可能产生进位，比如 45 => 46
     * 末位有进位，在中间位置进位停止，则需要找到进位的典型标志，即为当前位 %10 后为 0，则前一位加 1，直到不为 0 为止，比如 499 => 500
     * 末位有进位，并且一直进位到最前方导致结果多出一位，对于这种情况，需要在第 2 种情况遍历结束的基础上，进行单独处理，比如 999 => 1000
     *
     * @return
     */
    public static int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = digits.length - 1; i >= 0; --i) {
            if (digits[i] < 9) {
                ++digits[i];
                return digits;
            }
            digits[i] = 0;
        }
        int[] res = new int[n + 1];
        res[0] = 1;
        return res;
    }
}
