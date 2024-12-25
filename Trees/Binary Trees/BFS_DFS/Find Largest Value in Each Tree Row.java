// LeetCode Medium - 515


// Approach 1 - BFS 
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        List<Integer> result = new ArrayList<>();

        // BFS Traversal
        while(!q.isEmpty()){
            int currSize = q.size();
            int maxVal = Integer.MIN_VALUE;

            for(int i = 0; i<currSize; i++){
                TreeNode node = q.poll();

                // processing max element for entire row/level
                maxVal = Math.max(maxVal, node.val);

                if(node.left != null){
                    q.add(node.left);
                }

                if(node.right != null){
                    q.add(node.right);
                }
            }

            // adding max element in the result
            result.add(maxVal);
        }

        return result;
    }
}