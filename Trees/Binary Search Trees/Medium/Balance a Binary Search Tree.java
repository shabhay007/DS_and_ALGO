// LeetCode - 1382



// Approach 1 - Inorder
// T.C. - O(2n)
//S.C. - O(n)
class Solution {
    public void inorder(TreeNode root, List<Integer> list){
        if(root == null){
            return;
        }

        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    public TreeNode construct(int l, int r, List<Integer> list){
        if(l > r){
            return null;
        }

        int mid = l + (r - l)/2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = construct(l, mid-1, list);
        root.right = construct(mid+1, r, list);

        return root;
    }

    public TreeNode balanceBST(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);

        int l = 0;
        int r = list.size() - 1;

        return construct(l, r, list);
    }
}