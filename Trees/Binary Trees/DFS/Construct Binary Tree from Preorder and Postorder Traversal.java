// LeetCode Medium - 889


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

// Approach 1
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public TreeNode buildTree(int preStart, int preEnd, int postStart, int[] pre, int[] post){
        if(preStart > preEnd){
            return null;
        }

        TreeNode root = new TreeNode(pre[preStart]);

        if(preStart == preEnd){
            return root;
        }
        
        int nextNode = pre[preStart + 1]; // root of left subtree

        int j = postStart; // nextNode index

        while(post[j] != nextNode){
            j++;
        }

        int numOfElements = j - postStart + 1;

        root.left = buildTree(preStart+1, preStart+numOfElements, postStart, pre, post);
        root.right = buildTree(preStart+numOfElements+1, preEnd, j+1, pre, post);

        return root;
    }

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;
        
        int preStart = 0;
        int preEnd = n-1;
        int postStart = 0;

        return buildTree(preStart, preEnd, postStart, preorder, postorder);
    }
}






// Approach 2 (Optimal) - Using Map
// T.C. - O(n)
// S.C. - O(n + n)
class Solution {
    public TreeNode buildTree(int preStart, int preEnd, int postStart, int[] pre, int[] post, HashMap<Integer, Integer> map){
        if(preStart > preEnd){
            return null;
        }

        TreeNode root = new TreeNode(pre[preStart]);

        if(preStart == preEnd){
            return root;
        }
        
        int nextNode = pre[preStart + 1]; // root of left subtree

        int j = map.get(nextNode); // nextNode index
        int numOfElements = j - postStart + 1;

        root.left = buildTree(preStart+1, preStart+numOfElements, postStart, pre, post, map);
        root.right = buildTree(preStart+numOfElements+1, preEnd, j+1, pre, post, map);

        return root;
    }

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        int n = preorder.length;

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i<n; i++){
            map.put(postorder[i], i);
        }
        
        int preStart = 0;
        int preEnd = n-1;
        int postStart = 0;

        return buildTree(preStart, preEnd, postStart, preorder, postorder, map);
    }
}