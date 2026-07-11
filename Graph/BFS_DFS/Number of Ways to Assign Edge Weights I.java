// LeetCode Medium - 3558



// Approach 1 - Height of Tree using DFS + Iterative Binary Exponentiation
// T.C. - O(V + E)
// S.C. - O(V)
class Solution {
    int mod = (int) 1e9 + 7;
    int maxEdge = 0;

    public void dfs(Map<Integer, List<Integer>> adj, int curr, int parent, int dist){
        maxEdge = Math.max(maxEdge, dist);

        for(int neigh : adj.getOrDefault(curr, new ArrayList<>())){
            if(neigh != parent){
                dfs(adj, neigh, curr, dist+1);
            }
        }
    }

    public int getMaxEdgeInAPath(Map<Integer, List<Integer>> adj){
        dfs(adj, 1, -1, 0);
        
        return maxEdge;
    }

    public long ways(int maxEdge){
        long result = 1;

        for(int i = 1; i<maxEdge; i++){
            result = (result * 2) % mod;
        }

        return result;
    }

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length;
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adj.putIfAbsent(u, new ArrayList<>());
            adj.get(u).add(v);

            adj.putIfAbsent(v, new ArrayList<>());
            adj.get(v).add(u);
        }

        int maxEdge = getMaxEdgeInAPath(adj);

        return (int) ways(maxEdge);
    }
}






// Approach 2 - Height of Tree using DFS + Recursive Binary Exponentiation
// T.C. - O(V + E)
// S.C. - O(V)
class Solution {
    int mod = (int) 1e9 + 7;
    int maxEdge = 0;

    public void dfs(Map<Integer, List<Integer>> adj, int curr, int parent, int dist){
        maxEdge = Math.max(maxEdge, dist);

        for(int neigh : adj.getOrDefault(curr, new ArrayList<>())){
            if(neigh != parent){
                dfs(adj, neigh, curr, dist+1);
            }
        }
    }

    public int getMaxEdgeInAPath(Map<Integer, List<Integer>> adj){
        dfs(adj, 1, -1, 0);
        
        return maxEdge;
    }

    // Recursive
    public long binaryExponentiation(int a, int b){
        if(b == 0){
            return 1;
        }

        long half = binaryExponentiation(a, b/2);
        long result = (half * half) % mod;

        if(b % 2 == 1){
            result = (result * a) % mod;
        }

        return result;
    }

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length;
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adj.putIfAbsent(u, new ArrayList<>());
            adj.get(u).add(v);

            adj.putIfAbsent(v, new ArrayList<>());
            adj.get(v).add(u);
        }

        int maxEdge = getMaxEdgeInAPath(adj);

        return (int) binaryExponentiation(2, maxEdge-1);
    }
}






// Approach 3 - Depth of Graph + Binary Exponentiation
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    int mod = (int) 1e9 + 7;

    public int binaryExponentiation(int num, int power){
        if(power == 0){
            return 1;
        }

        long half = binaryExponentiation(num, power/2);
        long result = (half * half) % mod;

        if(power % 2 != 0){
            result = (result * num) % mod;
        }

        return (int) result;
    }

    public int getMaxDepth(int root, List<List<Integer>> adj, int parent){
        int maxDepth = 0;

        for(int ngbr : adj.get(root)){
            if(ngbr == parent){
                continue;
            }

            maxDepth = Math.max(maxDepth, getMaxDepth(ngbr, adj, root) + 1);
        }

        return maxDepth;
    }

    public int assignEdgeWeights(int[][] edges) {
        int n = edges.length + 1;

        // building the graph
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i<=n; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges){ // O(n)
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // finding the max depth
        int d = getMaxDepth(1, adj, -1); // O(n)

        /**
            So, there will be total 2^d ways including odd and even
            so, odd + even = total
                -> 2odd = 2^d
                -> odd = 2^(d-1)
        */

        // max depth will be d and in worst case (skewed tree) it's O(log(n))
        int result = binaryExponentiation(2, d-1); // O(log(n))

        return result;
    }
}