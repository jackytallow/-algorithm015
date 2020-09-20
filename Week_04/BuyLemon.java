/**
 * @author jacky
 * @version 1.0.0
 * 柠檬水找零 使用到了贪心的思想
 */
public class BuyLemon {

    /*
     * 根据题意，我需要模拟一下场景
     */
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            //如果顾客支付了 5 美元钞票，那么我们就得到 5 美元的钞票。
            if (bill == 5) {
                five++;
            }
            //如果顾客支付了 10 美元钞票，我们必须找回一张 5 美元钞票。如果我们没有 5 美元的钞票，答案就是 False ，
            // 因为我们无法正确找零。
            else if (bill == 10) {
                ten++;
                five--;
            }
            //如果顾客支付了 20 美元钞票，我们必须找回 15 美元。
            else {
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } else {
                    five -= 3;
                }
            }
            //如果我们有一张 10 美元和一张 5 美元，那么我们总会更愿意这样找零，这比用三张 5 美元进行找零更有利。
            //否则，如果我们有三张 5 美元的钞票，那么我们将这样找零。
            //否则，我们将无法给出总面值为 15 美元的零钱，答案是 False
            if (five < 0 || ten < 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        BuyLemon buyLemon = new BuyLemon();
        boolean isRight = buyLemon.lemonadeChange(new int[]{5,5,10});

        System.out.println(isRight);

    }
}
