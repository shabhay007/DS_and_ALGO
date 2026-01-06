// LeetCode Medium - 1161



// Approach 1 - BFS
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int maxSum = Integer.MIN_VALUE;
        int level = 0;
        int resultLevel = 0;

        while(!q.isEmpty()){
            int size = q.size();
            int sum = 0;

            for(int i = 0; i<size; i++){
                TreeNode curr = q.poll();
                sum += curr.val;

                if(curr.left != null){
                    q.offer(curr.left);
                }

                if(curr.right != null){
                    q.offer(curr.right);
                }
            }

            level++;

            if(sum > maxSum){
                maxSum = sum;
                resultLevel = level;
            }
            
        }

        return resultLevel;
    }
}