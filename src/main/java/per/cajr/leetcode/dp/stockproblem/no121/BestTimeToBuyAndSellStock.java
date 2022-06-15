package per.cajr.leetcode.dp.stockproblem.no121;

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * <p>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 * <p>
 * 示例 1：
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * <p>
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * <p>
 * 示例 2：
 * <p>
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * <p>
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock
 *
 * @author CAJR
 */
public class BestTimeToBuyAndSellStock {

    /**
     * base case:
     * dp[-1][...][0] = dp[...][0][0] = 0;
     * dp[-1][...][1] = dp[...][0][1] = -infinity;
     * <p>
     * 状态: i -> 天数  k -> 最多交易数  0 -> 没有持有股票 1 -> 持有股票
     * <p>
     * 状态转移方程:
     * dp[i][k][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i]);
     * <p>
     * //根据base case 可知 dp[i-1][0][0] = 0;
     * dp[i][k][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i]) = max(dp[i-1][1][1],  - prices[i]);
     * <p>
     * 因为k固定是1,对状态没有影响了, 可以化为:
     * <p>
     * base case:
     * dp[-1][0] = 0
     * dp[-1][1] = -infinity
     * <p>
     * 状态转移方程:
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], -prices[i])
     */
    public int maxProfit(int[] prices) {
        int days = prices.length;
        int[][] dp = new int[days][2];

        for (int i = 0; i < days; i++) {
            if (i - 1 == -1) {
                dp[i][0] = Math.max(0, Integer.MIN_VALUE + prices[i]);
                dp[i][1] = Math.max(Integer.MIN_VALUE, -prices[i]);
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }

        return Math.max(dp[days - 1][0], dp[days - 1][1]);
    }

    /**
     * 空间压缩优化
     */
    public int maxProfitOpt(int[] prices) {
        //base case
        int dpI0 = 0, dpI1 = Integer.MIN_VALUE;

        for (int price : prices) {
            dpI0 = Math.max(dpI0, dpI1 + price);
            dpI1 = Math.max(dpI1, -price);
        }

        return dpI0;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(new BestTimeToBuyAndSellStock().maxProfit(prices));
        System.out.println(new BestTimeToBuyAndSellStock().maxProfitOpt(prices));
    }
}
