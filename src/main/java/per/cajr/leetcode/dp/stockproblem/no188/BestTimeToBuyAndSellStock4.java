package per.cajr.leetcode.dp.stockproblem.no188;

/**
 * 给定一个整数数组prices ，它的第 i 个元素prices[i] 是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：k = 2, prices = [2,4,1]
 * 输出：2
 * <p>
 * 解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：k = 2, prices = [3,2,6,5,0,3]
 * 输出：7
 * <p>
 * 解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= k <= 100
 * 0 <= prices.length <= 1000
 * 0 <= prices[i] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv
 *
 * @author CAJR
 */
public class BestTimeToBuyAndSellStock4 {

    /**
     * base case:
     * dp[-1][...][0] = dp[...][0][0] = 0;
     * dp[-1][...][1] = dp[...][0][1] = -infinity;
     * <p>
     * 状态: i -> 天数  k -> 最多交易数  0 -> 没有持有股票 1 -> 持有股票
     * <p>
     * 状态转移方程:
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
     */
    public int maxProfit(int K, int[] prices) {
        int days = prices.length;
        if (days <= 0){
            return 0;
        }
        //dp table
        int[][][] dp = new int[days][K + 1][2];

        for (int i = 0; i < days; i++) {
            for (int k = 0; k <= K; k++) {
                //base case
                if (i - 1 == -1) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                if (k == 0) {
                    dp[i][0][0] = 0;
                    dp[i][0][1] = Integer.MIN_VALUE;
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[days - 1][K][0];
    }


    public static void main(String[] args) {
        int[] prices = {};
        int k = 2;
        System.out.println(new BestTimeToBuyAndSellStock4().maxProfit(k, prices));
    }
}
