package per.cajr.leetcode.dfs.no46;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * <p>
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 *
 * @author CAJR
 */
public class Permutations {

    List<List<Integer>> resList = new ArrayList<>();

    /**
     * 回溯
     */
    public List<List<Integer>> permute(int[] nums) {
        permutation(new ArrayList<>(), nums);
        return this.resList;
    }

    private void permutation(List<Integer> chooses, int[] nums)  {
        if (chooses.size() == nums.length) {
            List<Integer> permutationList = new ArrayList<>();
            for (int choose : chooses) {
                permutationList.add(nums[choose]);
            }
            resList.add(permutationList);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            //做选择
            if (chooses.contains(i)) {
                continue;
            }
            chooses.add(i);
            permutation(chooses, nums);
            //撤销选择
            chooses.remove((Integer) i);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2};
        System.out.println(new Permutations().permute(nums));
    }
}
