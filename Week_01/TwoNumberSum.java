import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jacky
 * @version 1.0.0
 */
public class TwoNumberSum {
    public static void main(String[] args) {
        int[] testNum = new int[]{2,3,4,5,6};

        int[] result;

        long start = System.currentTimeMillis();
        result = twoSumForce(testNum,7);
        result = twoSum(testNum,7);
        long end = System.currentTimeMillis();

        System.out.println(Arrays.toString(result));
    }


    /**
     * 一开始是直接使用栏暴力法
     * 大致就是进行两层for循环，去判断两个数加起来是否等于traget
     * 他的时间复杂度，每层都需要遍历数组，所以要消耗O(n^2)
     **/
    public static int[] twoSumForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }


    /**
     * 使用哈希表，进行迭代，如果发现了已经存在的元素，就将它返回，这时候时间复杂度由于只执行两一次
     * 所以时间复杂度为O(1) 空间复杂度为O(n)
     */
    //计算两数之和
    public static int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int otherNum = target - nums[i];
            if (result.containsKey(otherNum)) {
                return new int[] { result.get(otherNum), i };
            }
            result.put(nums[i], i);
        }
        return null;
    }
}