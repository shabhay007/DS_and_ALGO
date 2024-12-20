// LeetCode Medium - 2415


// Approach 1 - BFS
// T.C. - O(n), we are visiting every node once & odd level nodes twice
// S.C. - O(n)
class Solution {
    public TreeNode reverseOddLevels(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int levelCount = 0;

        while(!q.isEmpty()){
            int currLevelSize = q.size();
            List<TreeNode> list = new ArrayList<>();

            while(currLevelSize > 0){
                TreeNode temp = q.poll();
                list.add(temp);

                if(temp.left != null){
                    q.add(temp.left);
                }

                if(temp.right != null){
                    q.add(temp.right);
                }

                currLevelSize--;
            }

            if(levelCount % 2 == 1){ // odd level
                //reverse
                int l = 0; 
                int r = list.size() - 1;

                while(l < r){
                    int tempVal = list.get(r).val;
                    list.get(r).val = list.get(l).val;
                    list.get(l).val = tempVal;

                    l++;
                    r--;
                }
            }

            levelCount++;
        }

        return root;
    }
}




// Approach 2 - DFS
// T.C. - O(n)
// S.C. - O(recursion depth)
class Solution {
    public void solve(TreeNode leftNode, TreeNode rightNode, int level){
        if(leftNode == null || rightNode == null){
            return;
        }

        // reversing nodes of level 1, we have to reverse only two nodes
        if(level % 2 == 1){
            int temp = leftNode.val;
            leftNode.val = rightNode.val;
            rightNode.val = temp;
        }

        // reversal of rest of the levels 
        // OR
        // generalised reversal logic
        solve(leftNode.left, rightNode.right, level+1);
        solve(leftNode.right, rightNode.left, level+1);
    }

    public TreeNode reverseOddLevels(TreeNode root) {
        solve(root.left, root.right, 1);

        return root;
    }
}