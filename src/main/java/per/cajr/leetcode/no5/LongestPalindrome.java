package per.cajr.leetcode.no5;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * <p>
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 示例 2:
 * 输入：s = "cbbd"
 * 输出："bb"
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 *
 * @author CAJR
 */
public class LongestPalindrome {

    public String longestPalindrome(String s) {
        if (s == null) {
            return "";
        }
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            // 以 s[i] 为中心的最长回文子串
            String singularPalindrome = palindrome(s, i, i);
            // 以 s[i] s[i+1] 为中心的最长回文子串
            String evenPalindrome = palindrome(s, i, i + 1);
            res = Math.max(res.length(), singularPalindrome.length()) == singularPalindrome.length() ? singularPalindrome : res;
            res = Math.max(res.length(), evenPalindrome.length()) == evenPalindrome.length() ? evenPalindrome : res;
        }
        return res;
    }

    private String palindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left --;
            right ++;
        }
        System.out.println("left = " + left + "    right = " + right);
        return s.substring(left + 1, right);
    }


    public static void main(String[] args) {
        String str = "babad";
        System.out.println(new LongestPalindrome().longestPalindrome(str));
    }

}
