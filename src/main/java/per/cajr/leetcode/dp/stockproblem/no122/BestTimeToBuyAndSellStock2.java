package per.cajr.leetcode.dp.stockproblem.no122;

/**
 * 给你一个整数数组 prices ，其中prices[i] 表示某支股票第 i 天的价格。
 * <p>
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候最多只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * <p>
 * 返回 你能获得的 最大 利润。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：prices = [7,1,5,3,6,4]
 * 输出：7
 * <p>
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
 * 总利润为 4 + 3 = 7 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 * 总利润为 4 。
 * <p>
 * 示例3：
 * <p>
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii
 *
 * @author CAJR
 */
public class BestTimeToBuyAndSellStock2 {


    /**
     * 这里不限制最大交易笔数的 状态的改变不受k的影响
     * <p>
     * base case:
     * dp[-1][0] = 0
     * dp[-1][1] = -infinity
     * <p>
     * 状态转移方程:
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1]+prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0]-prices[i])
     */
    public int maxProfit(int[] prices) {
        int days = prices.length;
        if (days <= 0) {
            return 0;
        }
        int[][] dp = new int[days][2];
        for (int i = 0; i < days; i++) {
            //base case
            if (i == 0) {
                dp[0][0] = 0;
                dp[0][1] = -prices[0];
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[days - 1][0];
    }

    public int maxProfitOpt(int[] prices) {
        //base case
        int dpI0 = 0, dpI1 = Integer.MIN_VALUE;
        for (int p : prices) {
            int temp = dpI0;
            dpI0 = Math.max(dpI0, dpI1 + p);
            dpI1 = Math.max(dpI1, temp - p);
        }
        return dpI0;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(new BestTimeToBuyAndSellStock2().maxProfit(prices));
        System.out.println(new BestTimeToBuyAndSellStock2().maxProfitOpt(prices));
    }
}
