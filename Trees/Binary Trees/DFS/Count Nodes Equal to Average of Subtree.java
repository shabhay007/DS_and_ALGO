// LeetCode Medium - 2265



// Approach 1 - Optimal
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    int result;

    public int[] solve(TreeNode root){
        if(root == null){
            return new int[]{0, 0};
        }
        


        int[] left = solve(root.left);
        int[] right = solve(root.right);

        int totalSum = left[0] + right[0] + root.val;
        int totalCount = left[1] + right[1] + 1;

        int avg = totalSum/totalCount;

        if(avg == root.val){
            result += 1;
        }

        return new int[]{totalSum, totalCount};
    }

    public int averageOfSubtree(TreeNode root) {
        result = 0;
        solve(root);

        return result;
    }
}