package per.cajr.leetcode.other;

/**
 * 0: 空白
 * 1: 水
 * 2: 墨水
 * 计算全部水(1) 被墨水(2) 同化的时间
 *
 * @author CAJR
 */
public class WaterAssimilation {

    public int waterAssimilation(int[] waters) {
        //如果可以同化,同化一轮,然后交给下一轮同化
        return assimilation(waters, 0);
    }

    private int assimilation(int[] waters, int n) {
        //如果同化超时,说明同化不了就返回-1 base case
        if (n > waters.length) {
            return -1;
        }
        //判断是否同化完成
        if (checkWaters(waters)) {
            return n;
        }

        //这轮同化后的数组
//        int[] assimilatedWaters = new int[waters.length];
//        for (int i = 0; i < waters.length; i++) {
//            if (waters[i] == 1) {
//                //检查左是否有墨水
//                if (i - 1 >= 0 && waters[i - 1] == 2) {
//                    assimilatedWaters[i] = 2;
//                } else
//                    //检查右边是否有墨水
//                    if (i + 1 < waters.length && waters[i + 1] == 2) {
//                        assimilatedWaters[i] = 2;
//                    } else {
//                        assimilatedWaters[i] = waters[i];
//                    }
//            } else {
//                assimilatedWaters[i] = waters[i];
//            }
//        }

        int lo = 0;
        int hi = waters.length - 1;
        while (lo <= hi) {
            if (waters[lo] == 2) {
                //看下左右有没有水(1)
                //左
                if (lo - 1 >= 0 && waters[lo - 1] == 1) {
                    waters[lo - 1] = 2;
                }
                //右
                if (lo + 1 < waters.length && waters[lo + 1] == 1) {
                    waters[lo + 1] = 2;
                    //跳过
                    lo += 1;
                }
            }
            lo++;
            if (lo > hi) {
                break;
            }
            if (waters[hi] == 2) {
                //左
                if (waters[hi - 1] == 1) {
                    waters[hi - 1] = 2;
                    //跳过
                    hi -= 1;
                }
                //右
                if (hi + 1 < waters.length && waters[hi + 1] == 1) {
                    waters[hi + 1] = 2;
                }
            }
            hi--;
        }

        return assimilation(waters, n + 1);
    }

    /**
     * 判断是否同化完成
     */
    private boolean checkWaters(int[] waters) {
        for (int water : waters) {
            if (water == 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] waters1 = {2, 1, 1, 1, 1, 2};
        int[] waters2 = {0, 1, 1, 2, 1, 2, 1};
        int[] waters3 = {1, 1, 2, 1, 1, 1};
        int[] waters4 = {1, 1, 1};
        System.out.println(new WaterAssimilation().waterAssimilation(waters1));
        System.out.println(new WaterAssimilation().waterAssimilation(waters2));
        System.out.println(new WaterAssimilation().waterAssimilation(waters3));
        System.out.println(new WaterAssimilation().waterAssimilation(waters4));
    }
}
