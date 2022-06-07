package per.cajr.leetcode.binarytree.no144;

import per.cajr.leetcode.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历
 *
 * @author CAJR
 */
public class BinaryTreePreorderTraversal {

    /**
     * 递归前序遍历二叉树 : 根左右
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.add(root.val);
        res.addAll(preorderTraversal(root.left));
        res.addAll(preorderTraversal(root.right));
        return res;
    }

}
