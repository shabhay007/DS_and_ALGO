// LeetCode - 110



// Approach 1 - Using Height
// T.C. - O(n)
// S.C. - O(height)
class Solution {
    public int getHeight(TreeNode root){
        if(root == null){
            return 0;
        }

        // post order traversal
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        if(leftHeight == -1 || rightHeight == -1){
            return -1;
        }

        if(Math.abs(leftHeight - rightHeight) > 1){
            return -1;
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public boolean isBalanced(TreeNode root) {
        int height = getHeight(root);

        return height != -1;
    }
}