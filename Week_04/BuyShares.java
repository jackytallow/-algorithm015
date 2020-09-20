/**
 * @author jacky
 * @version 1.0.0
 * 买股票的最佳时机2
 */
public class BuyShares {

    /**
     * 1.可以使用暴力破解法
     * 计算所有组合找出最大利润
     */
    public int maxProfit(int[] prices) {
        if(prices.length == 0)
            return 0;
        int min = prices[0];
        int sum = 0;
        for(int i = 1;i<prices.length;i++){
            if(min <prices[i]){
                sum += prices[i] - min;
                min = prices[i];
            }else{
                min = prices[i];
            }
        }
        return sum;
    }
    /**
     * 这是使用一个数学公式，他们之前的差值就是所需要的
     * 在这种情况下，我们可以简单地继续在斜坡上爬升并持续增加从连续交易中获得的利润，
     * 而不是在谷之后寻找每个峰值。
     * 最后，我们将有效地使用峰值和谷值，
     * 但我们不需要跟踪峰值和谷值对应的成本以及最大利润，
     * 但我们可以直接继续增加加数组的连续数字之间的差值，
     * 如果第二个数字大于第一个数字，我们获得的总和将是最大利润。
     * 这种方法将简化解决方案。
     *
     */
    public int maxProfitOne(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }


    public static void main(String[] args) {

        BuyShares buyShares = new BuyShares();
       int testOne =  buyShares.maxProfit(new int[]{7,1,5,3,6,4});
       int testTwo = buyShares.maxProfit(new int[]{7,1,5,3,6,4});

       System.out.println(testOne);
       System.out.println(testTwo);

        /** print:
         * 7
         * 7
         */
    }




}
