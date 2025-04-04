// LeetCode Medium - 865


// Approach 1 - Using concept of Depth of Tree + Map + LCA
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public void populateDepth(TreeNode root, int currDepth, Map<TreeNode, Integer> map, int[] maxDepth){
        if(root == null){
            return;
        }

        map.put(root, currDepth);
        maxDepth[0] = Math.max(maxDepth[0], currDepth);

        populateDepth(root.left, currDepth + 1, map, maxDepth);
        populateDepth(root.right, currDepth + 1, map, maxDepth);
    }

    public TreeNode subtreeNode(TreeNode root, int depth, Map<TreeNode, Integer> map){
        if(root == null || map.get(root) == depth){
            return root;
        }

        TreeNode l = subtreeNode(root.left, depth, map);
        TreeNode r = subtreeNode(root.right, depth, map);

        if(l == null){
            return r;
        }
        else if(r == null){
            return l;
        }
        else{
            return root;
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();
        int[] maxDepth = new int[1];
        populateDepth(root, 0, map, maxDepth); // mapping node -> depth

        return subtreeNode(root, maxDepth[0], map);
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

    public Pair subtreeNode(TreeNode root){
        if(root == null){
            return new Pair(0, null);
        }

        Pair l = subtreeNode(root.left);
        Pair r = subtreeNode(root.right);

        if(l.depth > r.depth){
            return new Pair(l.depth + 1, l.node);
        }
        else if(r.depth > l.depth){
            return new Pair(r.depth + 1, r.node);
        }
        else{
            return new Pair(l.depth + 1, root);
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return subtreeNode(root).node;
    }
}