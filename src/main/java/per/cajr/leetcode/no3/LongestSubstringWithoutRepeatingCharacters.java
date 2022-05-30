package per.cajr.leetcode.no3;

import java.util.*;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的最长子串的长度。
 * <p>
 * 示例1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * <p>
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串。
 * <p>
 * <p>
 * 提示：
 * 0 <= s.length <= 5 * 104
 * <p>
 * s由英文字母、数字、符号和空格组成
 *
 * @author CAJR
 */
public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * 滑动窗口
     * <p>
     * 以这个字符串为例：abcabcbb，当i等于3时，也就是指向了第二个a, 此时我就需要查之前有没有出现过a,
     * 如果出现了是在哪一个位置出现的。然后通过last[index] 查到等于1, 也就是说，如果start 依然等于0的话，
     * 那么当前窗口就有两个a了，也就是字符串重复了，所以我们需要移动当前窗口的start指针，移动到什么地方呢？移动到什么地方，窗口内就没有重复元素了呢？
     * 对了，就是a上一次出现的位置的下一个位置，就是1 + 1 = 2。
     * 当start == 2, 当前窗口就没有了重复元素，那么以当前字符为结尾的最长无重复子串就是bca,然后再和之前的res取最大值。
     * 然后i指向后面的位置，按照同样思路计算。
     */
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        Arrays.fill(last, -1);
        int sLen = s.length();
        int start = 0;
        for (int i = 0; i < sLen; i++) {
            int index = s.charAt(i);

            start = Math.max(start, last[index] + 1);

            res = Math.max(res, i - start + 1);
            last[index] = i;
        }
        return res;
    }


    public int lengthOfLongestSubstring4Self(String s) {
        int res = 0;
        char[] sChars = s.toCharArray();
        if (sChars.length <= 1) {
            return sChars.length;
        }

        for (int i = 0; i < sChars.length; i++) {
            Map<Character, Integer> characterIndexMap = new HashMap<>(16);
            for (int j = i; j < sChars.length; j++) {
                if (characterIndexMap.containsKey(sChars[j])) {
                    i = characterIndexMap.get(sChars[j]);
                    break;
                }
                characterIndexMap.put(sChars[j], j);
            }
            res = Math.max(res, characterIndexMap.keySet().size());
        }
        return res;
    }


    public static void main(String[] args) {
        String s = "aab";
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().
                lengthOfLongestSubstring(s));
//        System.out.println(new LongestSubstringWithoutRepeatingCharacters().
//                lengthOfLongestSubstring4Self(s));
    }

}
