package per.cajr.leetcode.array.no912;

import java.util.Arrays;

/**
 * 给你一个整数数nums，请你将该数组升序排列。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5 * 104
 * -5 * 104 <= nums[i] <= 5 * 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sort-an-array
 *
 * @author CAJR
 */
public class SortArray {

    public int[] sortArray(int[] nums) {
        return MergeSort.sortArray(nums);
    }


    /**
     * 归并排序
     * <p>
     * 基本思路：借助额外空间，合并两个有序数组，得到更长的有序数组。例如：「力扣」第 88 题：合并两个有序数组。
     * <p>
     * 算法思想：分而治之（分治思想）。「分而治之」思想的形象理解是「曹冲称象」、MapReduce，在一定情况下可以并行化。
     */
    public static class MergeSort {
        /**
         * 用于辅助合并有序数组
         */
        private static int[] temp;

        public static int[] sortArray(int[] nums) {
            temp = new int[nums.length];
            mergeSort(nums, 0, nums.length - 1);
            return nums;
        }

        public static void mergeSort(int[] nums, int left, int right) {
            if (left == right) {
                return;
            }
            int mid = left + (right - left) / 2;
            //排序nums[left..mid]
            mergeSort(nums, left, mid);
            //排序nums[mid+1..right]
            mergeSort(nums, mid + 1, right);

            //合并两个有序的数组
            merge(nums, left, mid, right);
        }

        private static void merge(int[] nums, int left, int mid, int right) {
            //先将left-right备份到temp数组
            if (right + 1 - left >= 0) System.arraycopy(nums, left, temp, left, right + 1 - left);


            int i = left, j = mid + 1;
            for (int k = left; k <= right; k++) {
                if (i == mid + 1) {
                    //左半边已经完全合并
                    nums[k] = temp[j++];
                }else if (j == right + 1) {
                    //右边已经完全合并
                    nums[k] = temp[i++];
                }else
                //对比合并
                if (temp[i] <= temp[j]) {
                    nums[k] = temp[i++];
                    //i++;
                } else {
                    nums[k] = temp[j++];
                    //j++;
                }
            }
        }
    }

    /**
     * 快速排序
     */
    public static class QuickSort {
        public static int[] quickSort(int[] nums) {
            return nums;
        }
    }

    /**
     * 栈排序
     */
    public static class StackSort {
        public static int[] stackSort(int[] nums) {
            return nums;
        }
    }


    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 1};
        System.out.println(Arrays.toString(new SortArray().sortArray(nums)));
    }
}
