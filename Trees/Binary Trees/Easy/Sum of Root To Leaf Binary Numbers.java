// LeetCode - 1022



// Approach 1 - Brute Force (Generating All paths)
// T.C. - O(n + (m.k)); m = all paths; k = avg path size
// S.C. - O(n + (m.k))
class Solution {
    public void getAllPaths(StringBuilder sb, TreeNode root, List<String> list){
        if(root == null){
            return;
        }
        
        sb.append(root.val);

        if(root.left == null && root.right == null){
            list.add(sb.toString());
        }
        else{
            getAllPaths(sb, root.left, list);
            getAllPaths(sb, root.right, list);
        }

        sb.deleteCharAt(sb.length() - 1);
    }

    public int getSum(String str){
        int n = str.length();
        int sum = 0;

        for(int i = 0; i < n; i++){
            sum = (sum << 1) + (str.charAt(i) - '0');
        }

        return sum;
    }

    public int sumRootToLeaf(TreeNode root) {
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        getAllPaths(sb, root, list);

        int sum = 0;
        for(String str : list){
            // System.out.println(str + "-" + getSum(str));
            sum += getSum(str);
        }

        return sum;
    }
}






// Approach 2 - Optimal (Recursion)
// T.C. - O(n)
// S.C. - O(n); -> in case of skewed tree, height == no of nodes in the tree
class Solution {
    public int getPathSum(TreeNode root, int sum){
        if(root == null){
            return 0;
        }
        
        // appending at 0th place in binary -> 2^0 * root.val = 1 * root.val
        // and to place at 0th place, we need to left shift i.e. sum * 2
        sum = (2 * sum) + (1 * root.val);

        if(root.left == null && root.right == null){
            return sum;
        }

        return getPathSum(root.left, sum) + getPathSum(root.right, sum);
    }

    public int sumRootToLeaf(TreeNode root) {
        return getPathSum(root, 0);
    }
}