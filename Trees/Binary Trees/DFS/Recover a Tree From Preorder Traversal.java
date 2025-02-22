// LeetCode Hard - 1028




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



// Approach 1 - DFS
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public TreeNode buildTree(String str, int[] idx, int depth, int n){
        if(idx[0] >= n){
            return null;
        }

        // step 1 : finding the dash count
        int j = idx[0];
        while(j < n && str.charAt(j) == '-'){
            j++;
        }

        int dashCount = j - idx[0];
        
        // step 2 : if dash count != depth, return null i.e. return to previous depth
        if(dashCount != depth){
            return null;
        }

        // step 3 : now generating the value of the node
        idx[0] = j;
        // idx[0] += dashCount;
        long val = 0;

        while(idx[0] < n && Character.isDigit(str.charAt(idx[0]))){
            long digit = str.charAt(idx[0]) - '0';
            val = val * 10 + digit;
            idx[0]++;
        }
        
        // step 4 : constructing the tree
        TreeNode root = new TreeNode((int) val);

        root.left = buildTree(str, idx, depth + 1, n);
        root.right = buildTree(str, idx, depth + 1, n);

        return root;
    }

    public TreeNode recoverFromPreorder(String traversal) {
        int n = traversal.length();

        // building the tree
        return buildTree(traversal, new int[]{0}, 0, n);
    }
}







// Approach 2 - Using Stack (Simulating the DFS)
// Since, recursion also uses the stack space, so we removed the recursion
// by using stack for simulating the recursion
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public TreeNode buildTree(String str, int i, int n){
        Stack<TreeNode> stack = new Stack<>();

        while(i < n){
            int dashCount = 0;

            while(i < n && str.charAt(i) == '-'){
                dashCount++;
                i++;
            }

            int depth = stack.size();

            while(!stack.isEmpty() && depth > dashCount){
                stack.pop();
                depth--;
            }

            // finding the value
            int val = 0;
            while(i < n && Character.isDigit(str.charAt(i))){
                val = val * 10 + (str.charAt(i) - '0');
                i++;
            }

            TreeNode root = new TreeNode(val);

            if(!stack.isEmpty()){
                if(stack.peek().left == null){
                    stack.peek().left = root;
                }
                else{
                    stack.peek().right = root;
                }
            }

            // push the node in the stack
            stack.push(root);
        }

        // return the root
        while(stack.size() > 1){
            stack.pop();
        }

        return stack.peek();
    }

    public TreeNode recoverFromPreorder(String traversal) {
        int n = traversal.length();

        // building the tree
        return buildTree(traversal, 0, n);
    }
}