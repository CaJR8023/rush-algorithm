package per.cajr.leetcode.array.no42;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水
 * <p/>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 *
 * @author CAJR
 */
public class TrappingRainWater {

    /**
     * O(n)
     */
    public int trap(int[] height) {
        int left = 0;
        int right = height.length <= 1 ? left : left + 1;
        int rainWater = 0;

        //计算最高柱左边的雨水
        while (left < right && right < height.length) {
            if (height[right] >= height[left]) {
                //计算 left right之间的雨水
                rainWater += calculateRainWater(height, left, right);
                left = right;
            }
            right++;
        }

        //计算最高柱右边的雨水
        if (left <= height.length - 1) {
            //记录最高柱位置
            int maxPillarLocation = left;
            right = height.length - 1;
            left = right - 1;
            while (left >= maxPillarLocation) {
                if (height[left] >= height[right]) {
                    //计算 left right之间的雨水
                    rainWater += calculateRainWater(height, left, right);
                    right = left;
                }
                left--;
            }
        }

        return rainWater;
    }

    private int calculateRainWater(int[] height, int left, int right) {
        int separateArea = 0;
        for (int i = left + 1; i < right; i++) {
            separateArea += height[i];
        }
        return (right - (left + 1)) * Math.min(height[left], height[right]) - separateArea;
    }

    /**
     * 实现手法上非常巧妙
     */
    public int trapOptimal(int[] height) {
        int left = 0;
        int right = height.length - 1;

        //这里的lMax rMax代表的是 height[0..left] 和 height[right..end] 的最高柱子高度
        int lMax = 0, rMax = 0;

        int res = 0;
        while (left < right) {
            lMax = Math.max(lMax, height[left]);
            rMax = Math.max(rMax, height[right]);

            //我们已经知道 lMax < rMax 了，至于这个 rMax 是不是右边最大的，不重要。重要的是 height[i] 能够装的水只和较低的 lMax 之差有关
            if (lMax < rMax) {
                res += lMax - height[left];
                left++;
            } else {
                res += rMax - height[right];
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(new TrappingRainWater().trap(height));
        System.out.println(new TrappingRainWater().trapOptimal(height));
    }
}
