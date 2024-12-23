// LeetCode Medium - 102



/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    // BFS/Level Order Traversal
    // T.C. - O(n); as each node is visited only once
    // S.C. - O(n)
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        List<List<Integer>> result = new ArrayList<>();

        while(!q.isEmpty()){
            int n = q.size();
            List<Integer> list = new ArrayList<>();
            
            // processing level by level
            for(int i = 0; i<n; i++){
                TreeNode node = q.remove();
                list.add(node.val);

                if(node.left != null){
                    q.add(node.left);
                }

                if(node.right != null){
                    q.add(node.right);
                }
            }

            result.add(list);
        }

        return result;
    }
}