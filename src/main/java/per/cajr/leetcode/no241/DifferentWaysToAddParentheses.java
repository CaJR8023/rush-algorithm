package per.cajr.leetcode.no241;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个由数字和运算符组成的字符串expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
 * <p>
 * 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：expression = "2-1-1"
 * 输出：[0,2]
 * <p>
 * 解释：
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * <p>
 * 示例 2：
 * <p>
 * 输入：expression = "2*3-4*5"
 * <p>
 * 输出：[-34,-14,-10,-10,10]
 * <p>
 * 解释：
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= expression.length <= 20
 * expression 由数字和算符 '+'、'-' 和 '*' 组成。
 * 输入表达式中的所有整数值在范围 [0, 99] 
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/different-ways-to-add-parentheses
 *
 * @author CAJR
 */
public class DifferentWaysToAddParentheses {

    Map<String, List<Integer>> memo = new HashMap<>();

    /**
     * 分治 递归
     *
     * 分治思想: 重点是聚焦局部也就是子问题的处理, 将子问题解决再延展到整体
     */
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> res = new ArrayList<>();

        if (memo.containsKey(expression)) {
            return memo.get(expression);
        }

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> leftRes = diffWaysToCompute(expression.substring(0, i));
                List<Integer> rightRes = diffWaysToCompute(expression.substring(i + 1));

                leftRes.forEach(lr -> {
                    rightRes.forEach(rr -> {
                        if (c == '+') {
                            res.add(lr + rr);
                        } else if (c == '-') {
                            res.add(lr - rr);
                        } else {
                            res.add(lr * rr);
                        }
                    });
                });
            }
        }

        if (res.isEmpty()){
            res.add(Integer.parseInt(expression));
        } else {
            memo.put(expression, res);
        }

        return res;
    }

    public static void main(String[] args) {
        String expression = "2-1-1";
        System.out.println(new DifferentWaysToAddParentheses().diffWaysToCompute(expression));
    }
}
