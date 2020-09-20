/**
 * @author jacky
 * @version 1.0.0
 * @date 2020/9/20 关于二分查找
 */
public class BinarySearch {

    //查找一个半升序数组的中间无序下标（降序就翻转一下while里面的大于和小于符号）
    public int searchMinValueIndex(int[] nums) {
        //基准值，看mid索引的值小于基准值，大于基准点，说明mid左边是有序的，直接看mid右边，否则反之
        int point = nums[0];
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2;
            //如果i + 1 = j，说明j的下标值就是最小值，
            //但是还需要判断i j的 索引值，如果j值大于i值，说明这里就是无序点，返回j即可
            if (i + 1 == j) {
                if (nums[i] > nums[j]) {
                    return j;
                }
                //j 值不大于i 说明就是一个完全有序数组。反正0即可
                return 0;
            }
            if (point > nums[mid]) {
                j = mid;
            } else {
                i = mid;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        long start = System.currentTimeMillis();

        long end = System.currentTimeMillis();
        int index = binarySearch.searchMinValueIndex(new int[]{4, 5, 6, 7, 0, 1, 2});
        System.out.println("下标" + index);
        System.out.println("所用时间：" + (end - start));

//        Assert.assertEquals(3, new Test().searchMinValueIndex(new int[]{7, 8, 9, 0, 1, 2, 3, 4, 5, 6}));
//        Assert.assertEquals(1, new Test().searchMinValueIndex(new int[]{9, 0, 1, 2, 3, 4, 5, 6, 7, 8}));
//        Assert.assertEquals(6, new Test().searchMinValueIndex(new int[]{4, 5, 6, 7, 8, 9, 0, 1, 2, 3}));
//        Assert.assertEquals(8, new Test().searchMinValueIndex(new int[]{2, 3, 4, 5, 6, 7, 8, 9, 0, 1}));
//        Assert.assertEquals(0, new Test().searchMinValueIndex(new int[]{1, 2, 3}));
    }
}
