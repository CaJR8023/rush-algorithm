package per.cajr.leetcode.binarytree.no94;

import per.cajr.leetcode.binarytree.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 *
 * @author CAJR
 */
public class BinaryTreeInorderTraversal {

    /**
     * 解法4: Morris遍历 (非递归) 空间复杂度降为 O(1)
     */
    public List<Integer> inorderTraversal4(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }

    /**
     * 解法3: 栈 (非递归)
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }

    /**
     * 解法2: 动态
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }

        res.addAll(inorderTraversal2(root.left));
        res.add(root.val);
        res.addAll(inorderTraversal2(root.right));

        return res;
    }


    /**
     * 解法1: 回溯DFS
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        traversal(root);
        return null;
    }

    private void traversal(TreeNode node) {
        if (node == null) {
            return;
        }
        traversal(node.left);
        System.out.println(node.val);
        traversal(node.right);
    }


    public static void main(String[] args) {
        TreeNode leftTree = new TreeNode(3, null, null);
        TreeNode rightTree = new TreeNode(2, leftTree, null);
        TreeNode root = new TreeNode(1, null, rightTree);

        System.out.println(new BinaryTreeInorderTraversal().inorderTraversal3(root));
    }

}
