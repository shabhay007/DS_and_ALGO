// LeetCode Medium - 515


// Approach 1 - BFS 
// T.C. - O(n); we are visiting every node once
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




// Approach 2 - DFS 
// T.C. - O(n); we are visiting every node once
// S.C. - O(Recursion depth)
class Solution {
    public void DFS(TreeNode root, int depth, List<Integer> list){
        if(root == null){
            return;
        }

        if(list.size() == depth){
            list.add(root.val);
        }
        else{
            // updating the max element at that depth
            list.set(depth, Math.max(list.get(depth), root.val));
        }

        DFS(root.left, depth+1, list);
        DFS(root.right, depth+1, list);
    }

    public List<Integer> largestValues(TreeNode root) {
        if(root == null){
            return new ArrayList<>();
        }

        List<Integer> result = new ArrayList<>();

        // DFS Traversal
        // starting node (root), depth of starting node
        DFS(root, 0, result);

        return result;
    }
}