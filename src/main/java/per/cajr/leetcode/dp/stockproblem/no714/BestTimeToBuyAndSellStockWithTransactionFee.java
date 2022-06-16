package per.cajr.leetcode.dp.stockproblem.no714;

/**
 * 给定一个整数数组prices，其中 prices[i]表示第i天的股票价格 ；整数fee 代表了交易股票的手续费用。
 * <p>
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * <p>
 * 返回获得利润的最大值。
 * <p>
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出：8
 * 解释：能够达到的最大利润:
 * 在此处买入prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润:((8 - 1) - 2) + ((9 - 4) - 2) = 8
 * <p>
 * 示例 2：
 * <p>
 * 输入：prices = [1,3,7,5,10,3], fee = 3
 * 输出：6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 *
 * @author CAJR
 */
public class BestTimeToBuyAndSellStockWithTransactionFee {

    /**
     * 相当于整个股价都高了fee
     */
    public int maxProfit(int[] prices, int fee) {
        int days = prices.length;
        if (days <= 0) {
            return 0;
        }
        int[][] dp = new int[days][2];
        for (int i = 0; i < days; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i] - fee;
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i] - fee);
        }

        return dp[days - 1][0];
    }

    public int maxProfitOpt(int[] prices, int fee) {
        int dpI0 = 0, dpI1 = Integer.MIN_VALUE;
        for (int p : prices){
            int temp = dpI0;
            dpI0 = Math.max(dpI0, dpI1 + p);
            dpI1 = Math.max(dpI1, temp - p - fee);
        }
        return dpI0;
    }

    public static void main(String[] args) {
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println(new BestTimeToBuyAndSellStockWithTransactionFee().maxProfit(prices, fee));
        System.out.println(new BestTimeToBuyAndSellStockWithTransactionFee().maxProfitOpt(prices, fee));
    }
}
