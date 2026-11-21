// LeetCode - 3331



// Approach 1 - DFS
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    int[] result;

    public void dfs(int curr, String s, int[] parent, int[] last, List<List<Integer>> adj){
        int c = s.charAt(curr) - 'a';
        int nearestAncestor = last[c];
        last[c] = curr;

        // visiting neighbours
        for(int child : adj.get(curr)){
            dfs(child, s, parent, last, adj);
        }

        // back to curr
        last[c] = nearestAncestor;

        // if curr node is root, it has no ancestor
        if(curr != 0){
            int newParent = parent[curr]; // old parent

            if(nearestAncestor != -1){
                newParent = nearestAncestor;
            }

            parent[curr] = newParent;

            result[newParent] += result[curr];
        }
    }

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        // making adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i<n; i++){
            adj.add(new ArrayList<>());
        }

        // parent[i] = node
        // node is the parent of ith node
        // node -> i
        // parent of 0 is -1, so starting with 1
        for(int i = 1; i<n; i++){
            int u = parent[i];
            int v = i;

            adj.get(u).add(v);
        }

        result = new int[n];
        Arrays.fill(result, 1);

        int[] last = new int[26];
        Arrays.fill(last, -1);

        dfs(0, s, parent, last, adj);

        return result;
    }
}