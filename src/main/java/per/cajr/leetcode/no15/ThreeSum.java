package per.cajr.leetcode.no15;

import java.util.*;

/**
 * 给你一个包含 n 个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * <p>
 * 示例 2：
 * 输入：nums = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[]
 *
 * @author CAJR
 */
public class ThreeSum {

    /**
     * 排序 + 双指针 (时间复杂度O(NlogN + N^2) = O(N^2))
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            //把问题回到两数之和的问题上 a+b = -c
            int twoSumTarget = -nums[i];
            List<List<Integer>> twoSumRes = twoSum(nums, i + 1, twoSumTarget);
            for (List<Integer> list : twoSumRes) {
                list.add(nums[i]);
                res.add(list);
            }
            //跳过相同的值, 避免出现出现重复结果
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }

        return res;
    }

    private List<List<Integer>> twoSum(int[] nums, int start, int target) {
        List<List<Integer>> res = new ArrayList<>();

        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            int lowValue = nums[left];
            int hiValue = nums[right];

            int sum = nums[left] + nums[right];
            if (sum > target) {
                while (left < right && nums[right] == hiValue) {
                    right--;
                }
            } else if (sum < target) {
                while (left < right && nums[left] == lowValue) {
                    left++;
                }
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(nums[left]);
                list.add(nums[right]);
                res.add(list);
                // 跳过所有重复的元素
                while (left < right && nums[left] == lowValue) {
                    left++;
                }
                while (left < right && nums[right] == hiValue) {
                    right--;
                }
            }
        }

        return res;
    }

    /**
     * 超时(hash 算法 时间复杂度:o(n的平方))
     */
    public List<List<Integer>> threeSumViolence(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            //可以降到两数之和 b+c = -a
            int twoSum = -nums[i];
            //排除i自己的位置
            Map<Integer, Set<Integer>> map = new HashMap<>(16);
            for (int n = 0; n < nums.length; n++) {
                if (n == i) {
                    continue;
                }
                Set<Integer> integerList;
                if (map.containsKey(nums[n])) {
                    integerList = map.get(nums[n]);
                } else {
                    integerList = new HashSet<>();
                }
                integerList.add(n);
                map.put(nums[n], integerList);
            }

            for (int j = 0; j < nums.length; j++) {
                if (j == i) {
                    continue;
                }
                boolean isAdd = map.containsKey(twoSum - nums[j]) && !(map.get(twoSum - nums[j]).contains(j) && map.get(twoSum - nums[j]).size() <= 1);
                if (isAdd) {
                    List<Integer> integers = new ArrayList<>();
                    integers.add(nums[i]);
                    integers.add(nums[j]);
                    integers.add(twoSum - nums[j]);
                    //[0,0,0]情况
                    if (nums[i] == 0 && nums[j] == 0 && (twoSum - nums[j]) == 0) {
                        for (List<Integer> intList : res) {
                            if (intList.get(0) == 0 && intList.get(1) == 0 && intList.get(2) == 0) {
                                isAdd = false;
                                break;
                            }
                        }
                    } else {
                        //去重
                        for (List<Integer> intList : res) {
                            if (intList.contains(nums[i]) && intList.contains(nums[j]) && intList.contains(twoSum - nums[j])) {
                                isAdd = false;
                                break;
                            }
                        }
                    }

                    if (isAdd) {
                        res.add(integers);
                    }
                }
            }

            //map 重置
            map.clear();
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(new ThreeSum().threeSumViolence(nums));
    }
}
