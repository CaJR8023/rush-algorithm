package per.cajr.leetcode.dp.no887;

import java.util.Arrays;

/**
 * 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
 * <p>
 * 已知存在楼层 f ，满足0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
 * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
 * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
 * <p>
 * 示例 1：
 * <p>
 * 输入：k = 1, n = 2
 * 输出：2
 * 解释：
 * 鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。
 * 如果它没碎，那么肯定能得出 f = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。
 * <p>
 * 示例 2：
 * <p>
 * 输入：k = 2, n = 6
 * 输出：3
 * <p>
 * 示例 3：
 * <p>
 * 输入：k = 3, n = 14
 * 输出：4
 *
 * @author CAJR
 */
public class SuperEggDrop {
    int[][] memo;

    /**
     * 经典动态
     * <p>
     * 第三部分，借助函数单调性继续二分优化
     * <p>
     * 我们注意到，函数dp(k, n)在k固定时，随着n的增加而增加，这很好理解，因为鸡蛋数量一定，如果楼层数目增加，那么需要确定哪一层楼鸡蛋破碎时所需的尝试次数必然需要增加。
     * <p>
     * 既然如此，对于关于x的两个函数f1(x)=dp(k-1, x-1)和f2(x)=dp(k, n-x)，定义域x是1到n的整数，根据嵌套函数的单调性判定规则，可以知道f1(x)随x的增加单调递增，f2(x)随x的增加单调递减。
     * <p>
     * 为了获得res = min(max(dp(k-1, x-1), dp(k, n-x)) for x in range(1, n+1)) + 1，我们需要计算两个函数的交点，而且两个函数具有同样的定义域，在定义域之内都是单调的，因此可以使用二分法。
     * <p>
     * 设f(x) = f1(x) - f2(x)，则f(x)单调递增。
     * <p>
     * 二分法的区间下界和上界显然是从1到n，判定规则为：
     * <p>
     * 如果x处f1(x)>f2(x)：即f(x) = f1(x) - f2(x) > 0，这时搜寻空间应该从[low, high]变成[low, mid-1]，更新上界即可。
     * <p>
     * 如果x处f1(x)<=f2(x)：即f(x) = f1(x) - f2(x) <= 0，这时搜寻空间应该从[low, high]变成[mid+1, high]，更新下界即可。
     * <p>
     * 注意在每次搜寻后都需要及时更新结果变量。
     */
    public int superEggDrop(int k, int n) {
        int res = n;
        //base case
        if (k == 1) {
            return n;
        }
        if (n <= 0) {
            return 0;
        }
        if (memo == null) {
            memo = new int[k + 1][n + 1];
            for (int[] row : memo) {
                Arrays.fill(row, -1);
            }
        } else {
            if (memo[k][n] != -1) {
                return memo[k][n];
            }
        }
//        //选择不同的楼层去扔鸡蛋
//        for (int i = 1; i <= n; i++) {
//            res = Math.min(res, Math.max(superEggDrop(k - 1, i - 1), superEggDrop(k, n - i)) + 1);
//        }
        //二分法优化
        int low = 1;
        int hi = n;
        while (low < hi) {
            int mid = low + (hi - low) / 2;
            int brokenValue = superEggDrop(k - 1, mid - 1);
            int notBrokenValue = superEggDrop(k, n - mid);
            res = Math.min(res, Math.max(brokenValue, notBrokenValue) + 1);
            if (brokenValue < notBrokenValue) {
                low = mid + 1;
            }
            if (brokenValue > notBrokenValue) {
                hi = mid;
            }
            if (brokenValue == notBrokenValue) {
                break;
            }
        }
        memo[k][n] = res;
        return res;
    }

    public static void main(String[] args) {
        int k = 6, n = 1000;
        System.out.println(new SuperEggDrop().superEggDrop(k, n));
    }
}
