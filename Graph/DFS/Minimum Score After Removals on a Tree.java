// LeetCode Hard - 2322



// Approach 1 - Using DFS
// T.C : O(n^2)
// S.C : O(V+E), V = number of vertices and E = number of edges
class Solution {
    public void dfs(int curr, int parent, List<List<Integer>> adj, int[] nums, int[] timer, int[] intime, int[] outtime, int[] xor){
        xor[curr] = nums[curr];
        intime[curr] = timer[0];
        timer[0]++;

        for(int neigh : adj.get(curr)){
            if(neigh != parent){
                dfs(neigh, curr, adj, nums, timer, intime, outtime, xor);
                xor[curr] ^= xor[neigh];
            }
        }

        outtime[curr] = timer[0]++;
    }

    public boolean isAncestor(int u, int v, int[] intime, int[] outtime){
        return intime[v] >= intime[u] && outtime[u] >= outtime[v];
    }

    public int getScore(int xor1, int xor2, int xor3){
        int max = Math.max(xor1, Math.max(xor2, xor3));
        int min = Math.min(xor1, Math.min(xor2, xor3));

        return max - min;
    }

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;

        // 1. Creating adj list
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i<n; i++){
            adj.add(new ArrayList<>());
        }

        for(int edge[] : edges){
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // 2. Processing Ancestors (intTime and outTime) and XOR's
        int[] intime = new int[n];
        int[] outtime = new int[n];
        int[] subtreeXor = new int[n];
        int[] timer = new int[1];

        dfs(0, -1, adj, nums, timer, intime, outtime, subtreeXor);

        // 3. Processing the components
        int result = Integer.MAX_VALUE;

        for(int u = 1; u<n; u++){
            for(int v = u+1; v<n; v++){
                int xor1 = 0;
                int xor2 = 0;
                int xor3 = 0;

                if(isAncestor(u, v, intime, outtime)){
                    xor1 = subtreeXor[v];
                    xor2 = subtreeXor[u] ^ xor1;
                    xor3 = subtreeXor[0] ^ xor1 ^ xor2;
                }
                else if(isAncestor(v, u, intime, outtime)){
                    xor1 = subtreeXor[u];
                    xor2 = subtreeXor[v] ^ xor1;
                    xor3 = subtreeXor[0] ^ xor1 ^ xor2;
                }
                else{
                    xor1 = subtreeXor[u];
                    xor2 = subtreeXor[v];
                    xor3 = subtreeXor[0] ^ xor1 ^ xor2;
                }

                result = Math.min(result, getScore(xor1, xor2, xor3));
            }
        }

        return result;
    }
}