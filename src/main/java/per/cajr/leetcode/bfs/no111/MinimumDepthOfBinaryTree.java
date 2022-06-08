package per.cajr.leetcode.bfs.no111;

import per.cajr.leetcode.binarytree.TreeNode;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明：叶子节点是指没有子节点的节点。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 * <p>
 * 示例 2：
 * <p>
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 * <p>
 * 提示：
 * <p>
 * 树中节点数的范围在 [0, 105] 内
 * -1000 <= Node.val <= 1000
 *
 * @author CAJR
 */
public class MinimumDepthOfBinaryTree {

    /**
     * BFS
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> treeNodeQueue = new ArrayDeque<>();
        treeNodeQueue.offer(root);

        int depth = 1;
        while (!treeNodeQueue.isEmpty()) {
            int size = treeNodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = treeNodeQueue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    treeNodeQueue.offer(node.left);
                }
                if (node.right != null) {
                    treeNodeQueue.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(new MinimumDepthOfBinaryTree().minDepth(root));
    }
}
