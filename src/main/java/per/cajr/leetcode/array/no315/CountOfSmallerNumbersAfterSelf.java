package per.cajr.leetcode.array.no315;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，按要求返回一个新数组counts 。数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于nums[i] 的元素的数量。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,2,6,1]
 * <p>
 * 输出：[2,1,1,0]
 * <p>
 * 解释：
 * <p>
 * 5 的右侧有 2 个更小的元素 (2 和 1)
 * <p>
 * 2 的右侧仅有 1 个更小的元素 (1)
 * <p>
 * 6 的右侧有 1 个更小的元素 (1)
 * <p>
 * 1 的右侧有 0 个更小的元素
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [-1]
 * <p>
 * 输出：[0]
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：nums = [-1,-1]
 * 输出：[0,0]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-of-smaller-numbers-after-self
 *
 * @author CAJR
 */
public class CountOfSmallerNumbersAfterSelf {

    private class Pair {
        int val, id;

        Pair(int val, int id) {
            // 记录数组的元素值
            this.val = val;
            // 记录元素在数组中的原始索引
            this.id = id;
        }
    }

    /**
     * 归并排序所用的辅助数组
     */
    private Pair[] temp;
    /**
     * 记录每个元素后面比自己小的元素个数
     */
    private int[] count;

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        count = new int[n];
        temp = new Pair[n];
        Pair[] arr = new Pair[n];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = new Pair(nums[i], i);
        }
        sort(arr, 0, n - 1);

        for (int c : count) {
            res.add(c);
        }
        return res;
    }

    /**
     * 归并排序
     */
    private void sort(Pair[] arr, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    private void merge(Pair[] arr, int lo, int mid, int hi) {
        if (hi + 1 - lo >= 0) {
            System.arraycopy(arr, lo, temp, lo, hi + 1 - lo);
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i == mid + 1) {
                arr[k] = temp[j++];
            } else if (j == hi + 1) {
                arr[k] = temp[i++];
                count[arr[k].id] += j - mid - 1;
            } else if (temp[i].val <= temp[j].val) {
                arr[k] = temp[i++];
                count[arr[k].id] += j - mid - 1;
            } else {
                arr[k] = temp[j++];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {5,2,6,1};
        System.out.println(new CountOfSmallerNumbersAfterSelf().countSmaller(nums));
    }
}
