package per.cajr.leetcode.dp.stockproblem.no309;

/**
 * 给定一个整数数组prices，其中第prices[i]表示第i天的股票价格 。
 * <p>
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * <p>
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * <p>
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * <p>
 * 示例 2:
 * <p>
 * 输入: prices = [1]
 * 输出: 0
 * <p>
 * 提示：
 * <p>
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown
 *
 * @author CAJR
 */
public class BestTimeToBuyAndSellStockWithCoolDown {

    public int maxProfit(int[] prices) {
        int days = prices.length;
        if (days <= 0){
            return 0;
        }

        return 0;
    }

    public static void main(String[] args) {

    }
}
