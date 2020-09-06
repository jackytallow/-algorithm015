/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> result = new ArrayList<>();
        preOrder(root,result);
        return result;
    }
    // 前序遍历以node为根的二分搜索树, 递归算法
    public void preOrder(TreeNode node,List<Integer> result) {
        if(node == null) {
            return;
        }
        if(node != null) {
           if(node.left != null) {
                preOrder(node.left,result);
           }
             result.add(node.val);
           if(node.right != null) {
               preOrder(node.right,result);
           }
      
        }
       
    }
}