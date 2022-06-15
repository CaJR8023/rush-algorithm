package per.cajr.leetcode.array.no912;

import java.util.Arrays;
import java.util.Random;

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
            if (right + 1 - left >= 0) {
                System.arraycopy(nums, left, temp, left, right + 1 - left);
            }

            int i = left, j = mid + 1;
            for (int k = left; k <= right; k++) {
                if (i == mid + 1) {
                    //左半边已经完全合并
                    nums[k] = temp[j++];
                } else if (j == right + 1) {
                    //右边已经完全合并
                    nums[k] = temp[i++];
                } else
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

        public static int[] sortArray(int[] nums) {
            // 为了避免出现耗时的极端情况，先随机打乱
            shuffle(nums);
            quickSort(nums, 0, nums.length - 1);
            return nums;
        }

        public static void quickSort(int[] nums, int lo, int hi) {
            //end
            if (lo >= hi) {
                return;
            }
            //对 nums[lo..hi] 进行切分 使得 nums[lo..p-1] <= nums[p] < nums[p+1..hi]
            int p = partition(nums, lo, hi);
            //对nums[lo...p-1] 进行排序
            quickSort(nums, lo, p - 1);
            //对nums[p+1...hi] 进行排序
            quickSort(nums, p + 1, hi);
        }

        private static int partition(int[] nums, int lo, int hi) {
            int pivot = nums[lo];
            // 关于区间的边界控制需格外小心，稍有不慎就会出错
            // 我这里把 i, j 定义为开区间，同时定义：
            // [lo, i) <= pivot；(j, hi] > pivot
            // 之后都要正确维护这个边界区间的定义
            int i = lo + 1, j = hi;
            // 当 i > j 时结束循环，以保证区间 [lo, hi] 都被覆盖
            while (i <= j) {
                while (i < hi && nums[i] <= pivot) {
                    i++;
                    // 此 while 结束时恰好 nums[i] > pivot
                }
                while (j > lo && nums[j] > pivot) {
                    j--;
                    // 此 while 结束时恰好 nums[j] <= pivot
                }
                // 此时 [lo, i) <= pivot && (j, hi] > pivot
                if (i >= j) {
                    break;
                }
                swap(nums, i, j);
            }
            // 将 pivot 放到合适的位置，即 pivot 左边元素较小，右边元素较大
            swap(nums, lo, j);
            return j;

        }

        /**
         * 洗牌算法
         */
        private static void shuffle(int[] nums) {
            Random rand = new Random();
            int n = nums.length;
            for (int i = 0; i < n; i++) {
                // 生成 [i, n - 1] 的随机数
                int r = i + rand.nextInt(n - i);
                swap(nums, i, r);
            }
        }

        private static void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
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
        System.out.println(Arrays.toString(QuickSort.sortArray(nums)));
    }
}
