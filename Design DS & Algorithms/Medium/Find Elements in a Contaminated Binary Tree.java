// LeetCode Medium - 1261


// Approach 1 - DFS
// T.C. - O(2n)
// S.C. - O(n)
class FindElements {
    TreeNode head;

    public void recoverTree(TreeNode root){
        if(root == null){
            return;
        }

        if(root.left != null){
            root.left.val = (2 * root.val) + 1;
        }

        if(root.right != null){
            root.right.val = (2 * root.val) + 2;
        }

        recoverTree(root.left);
        recoverTree(root.right);
    }

    public FindElements(TreeNode root) {
        root.val = 0;

        head = root;

        recoverTree(root); // O(n)
    }

    public boolean isExists(TreeNode head, int target){
        if(head == null){
            return false;
        }

        if(head.val == target){
            return true;
        }

        return (isExists(head.left, target) || isExists(head.right, target));
    }
    
    public boolean find(int target) {
        return isExists(head, target); // O(n)
    }
}






// Approach 2 - BFS
// T.C. - O(2n)
// S.C. - O(n)
class FindElements {
    TreeNode head;

    public void bfs(TreeNode root){
        if(root == null){
            return;
        }

        Queue<TreeNode> levelTree = new LinkedList<>();
        levelTree.offer(root);

        while(!levelTree.isEmpty()){
            int currLevelSize = levelTree.size();

            for(int i = 0; i<currLevelSize; i++){
                TreeNode curr = levelTree.poll();

                if(curr.left != null){
                    curr.left.val = (2 * curr.val) + 1;

                    levelTree.offer(curr.left);
                }

                if(curr.right != null){
                    curr.right.val = (2 * curr.val) + 2;

                    levelTree.offer(curr.right);
                }
            }
        }
    }

    public FindElements(TreeNode root) {
        root.val = 0;

        head = root;

        bfs(root);
    }

    public boolean isExists(TreeNode head, int target){
        if(head == null){
            return false;
        }

        if(head.val == target){
            return true;
        }

        return (isExists(head.left, target) || isExists(head.right, target));
    }
    
    public boolean find(int target) {
        return isExists(head, target);
    }
}






// Approach 3 (Optimal) - BFS + Set
// T.C. - O(n)
// S.C. - O(2n)
class FindElements {
    TreeNode head;
    HashSet<Integer> set;

    public void bfs(TreeNode root){
        if(root == null){
            return;
        }

        Queue<TreeNode> levelTree = new LinkedList<>();
        levelTree.offer(root);

        while(!levelTree.isEmpty()){
            int currLevelSize = levelTree.size();

            for(int i = 0; i<currLevelSize; i++){
                TreeNode curr = levelTree.poll();

                if(curr.left != null){
                    curr.left.val = (2 * curr.val) + 1;

                    set.add(curr.left.val); // adding the value in set
                    levelTree.offer(curr.left);
                }

                if(curr.right != null){
                    curr.right.val = (2 * curr.val) + 2;

                    set.add(curr.right.val); // adding the value in set
                    levelTree.offer(curr.right);
                }
            }
        }
    }

    public FindElements(TreeNode root) {
        root.val = 0;

        this.head = root;
        this.set = new HashSet<>();

        bfs(root);
    }
    
    public boolean find(int target) {
        if(target == 0){
            return true;
        }
        
        return set.contains(target); // set for O(1) lookup
    }
}






// Approach 4 (Optimal) - DFS + Set
// T.C. - O(n)
// S.C. - O(2n)
class FindElements {
    TreeNode head;
    HashSet<Integer> set;

    public void recoverTree(TreeNode root){
        if(root == null){
            return;
        }

        if(root.left != null){
            root.left.val = (2 * root.val) + 1;
            set.add(root.left.val);
        }

        if(root.right != null){
            root.right.val = (2 * root.val) + 2;
            set.add(root.right.val);
        }

        recoverTree(root.left);
        recoverTree(root.right);
    }

    public FindElements(TreeNode root) {
        root.val = 0;

        this.head = root;
        this.set = new HashSet<>();

        recoverTree(root); // O(n)
    }
    
    public boolean find(int target) {
        if(target == 0){
            return true;
        }

        return set.contains(target); // O(1)
    }
}







// Approach 5 (Optimal) - Constructing the tree using DFS instead of traversing to the contaminated tree + Set
// T.C. - O(n)
// S.C. - O(2n)
class FindElements {
    TreeNode head;
    HashSet<Integer> set;

    public void buildTree(TreeNode root, int val){
        if(root == null){
            return;
        }

        set.add(val);

        buildTree(root.left, val * 2 + 1);
        buildTree(root.right, val * 2 + 2);
    }

    public FindElements(TreeNode root) {
        this.head = root;
        this.set = new HashSet<>();

        buildTree(root, 0); // O(n)
    }
    
    public boolean find(int target) {
        return set.contains(target); // O(1)
    }
}