// LeetCode Medium - 1123


// Approach 1 - Using concept of Depth of Tree + Map + LCA
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public void populateDepth(TreeNode root, int currNodeDepth, Map<TreeNode, Integer> map, int[] maxDepth){
        if(root == null){
            return;
        }

        map.put(root, currNodeDepth);
        maxDepth[0] = Math.max(maxDepth[0], currNodeDepth);

        populateDepth(root.left, currNodeDepth + 1, map, maxDepth);
        populateDepth(root.right, currNodeDepth + 1, map, maxDepth);
    }

    public TreeNode LCA(TreeNode root, int maxDepth, Map<TreeNode, Integer> map){
        if(root == null || map.get(root) == maxDepth){
            return root;
        }

        TreeNode leftNode = LCA(root.left, maxDepth, map);
        TreeNode rightNode = LCA(root.right, maxDepth, map);

        if(leftNode == null){
            return rightNode;
        }
        else if(rightNode == null){
            return leftNode;
        }
        else{
            return root;
        }
    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();

        int[] maxDepth = new int[1];
        populateDepth(root, 0, map, maxDepth);

        return LCA(root, maxDepth[0], map);
    }
}




// Approach 2 - One Pass, storing depth of every node in pair
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    class Pair {
        int depth;
        TreeNode node;

        Pair(int depth, TreeNode node){
            this.depth = depth;
            this.node = node;
        }
    }

    public Pair LCA(TreeNode root){
        if(root == null){
            return new Pair(0, null);
        }

        Pair l = LCA(root.left);
        Pair r = LCA(root.right);

        
        if(l.depth > r.depth){
            return new Pair(l.depth + 1, l.node);
        }
        else if(l.depth < r.depth){
            return new Pair(r.depth + 1, r.node);
        }
        else{
            return new Pair(l.depth + 1, root);
        }
    }

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return LCA(root).node;
    }
}