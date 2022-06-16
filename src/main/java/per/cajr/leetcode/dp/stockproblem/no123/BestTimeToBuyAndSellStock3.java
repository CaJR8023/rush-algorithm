package per.cajr.leetcode.dp.stockproblem.no123;

/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * <p>
 * 示例1:
 * <p>
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * <p>
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 * 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2：
 * <p>
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出
 * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * <p>
 * 示例 3：
 * <p>
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这个情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii
 *
 * @author CAJ
 */
public class BestTimeToBuyAndSellStock3 {

    /**
     * base case:
     * dp[-1][...][0] = dp[...][0][0] = 0
     * dp[-1][...][1] = dp[...][0][1] = -infinity
     * <p>
     * 状态转移方程:
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     */
    public int maxProfit(int[] prices) {
        int days = prices.length;
        if (days <= 0) {
            return 0;
        }
        int maxK = 2;
        int[][][] dp = new int[days][maxK + 1][2];
        for (int i = 0; i < days; i++) {
            for (int k = maxK; k > 0; k--) {
                if (i - 1 == -1) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }

                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[days - 1][maxK][0];
    }

    /**
     * // 状态转移方程：
     * // dp[i][2][0] = max(dp[i-1][2][0], dp[i-1][2][1] + prices[i])
     * // dp[i][2][1] = max(dp[i-1][2][1], dp[i-1][1][0] - prices[i])
     * // dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
     * // dp[i][1][1] = max(dp[i-1][1][1], -prices[i])
     */
    public int maxProfitOpt(int[] prices) {
        int dpI10 = 0, dpI11 = Integer.MIN_VALUE;
        int dpI20 = 0, dpI21 = Integer.MIN_VALUE;

        for (int p : prices) {
            dpI10 = Math.max(dpI10, dpI11 + p);
            dpI11 = Math.max(dpI11, -p);
            dpI20 = Math.max(dpI20, dpI21 + p);
            dpI21 = Math.max(dpI21, dpI10 - p);
        }
        return dpI20;
    }

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(new BestTimeToBuyAndSellStock3().maxProfit(prices));
        System.out.println(new BestTimeToBuyAndSellStock3().maxProfitOpt(prices));
    }
}
