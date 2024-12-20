// LeetCode Easy - 101


// Approach 1 - Recursion/DFS
// T.C. - O(n), visiting every node
// S.C. - O(recursion height)
class Solution {
    public boolean solve(TreeNode leftNode, TreeNode rightNode){
        if(leftNode == null && rightNode == null){
            return true;
        }

        if(leftNode == null || rightNode == null){
            return false;
        }

        if(leftNode.val != rightNode.val){
            return false;
        }

        return solve(leftNode.left, rightNode.right) && solve(leftNode.right, rightNode.left);
    }

    public boolean isSymmetric(TreeNode root) {
        return solve(root.left, root.right);
    }
}