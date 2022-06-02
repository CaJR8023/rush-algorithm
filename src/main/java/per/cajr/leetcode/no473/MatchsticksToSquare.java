package per.cajr.leetcode.no473;


import java.util.Arrays;

/**
 * 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i个火柴棒的长度。你要用 所有的火柴棍拼成一个正方形。
 * 你不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
 * 如果你能使这个正方形，则返回 true ，否则返回 false 。
 * <p>
 * 示例1:
 * 输入: matchsticks = [1,1,2,2,2]
 * 输出: true
 * 解释: 能拼成一个边长为2的正方形，每边两根火柴。
 * <p>
 * 示例2:
 * 输入: matchsticks = [3,3,3,3,4]
 * 输出: false
 * 解释: 不能用所有火柴拼成一个正方形。
 *
 * @author CAJR
 */
public class MatchsticksToSquare {

    /**
     * 回溯
     */
    public boolean makesquare(int[] matchsticks) {
        if (matchsticks.length < 4) {
            return false;
        }
        int sum = 0;
        for (int matchstick : matchsticks) {
            sum += matchstick;
        }

        if (sum % 4 != 0) {
            return false;
        }
        int squareSide = sum / 4;
        Arrays.sort(matchsticks);
        for (int i = 0, j = matchsticks.length - 1; i < j; i++, j--) {
            int temp = matchsticks[i];
            matchsticks[i] = matchsticks[j];
            matchsticks[j] = temp;
        }
        //最大值大于squareSide 直接return false
        if (matchsticks[0] > squareSide) {
            return false;
        }

        return dfs(0, matchsticks, new int[4], squareSide);
    }

    private boolean dfs(int index, int[] matchsticks, int[] edges, int len) {
        // 如果所有的火柴都用完了，则返回true
        if (index == matchsticks.length) {
            return true;
        }
        for (int i = 0; i < edges.length; i++) {
            // 将火柴加入到该边
            edges[i] += matchsticks[index];
            //如果该边的长度小于等于需要的长度，则将下一个火柴加入该边
            if (edges[i] <= len && dfs(index + 1, matchsticks, edges, len)) {
                return true;
            }
            // 否则则将该火柴从该边移除，并考虑下一边
            edges[i] -= matchsticks[index];
        }

        return false;
    }

    public static void main(String[] args) {
        int[] matchsticks = {5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3};
        System.out.println(new MatchsticksToSquare().makesquare(matchsticks));
    }
}
