// LeetCode Medium - 3372



// BFS
// T.C. - O(n.(e1 + n) + m.(e2 + m) + m + n)
// S.C. - O(e + n)
class Solution {
    public int bfs(int currNode, Map<Integer, List<Integer>> adj, int k, int n){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{currNode, 0}); // {node, distance} -> dist. bet. self node = 0

        boolean[] visited = new boolean[n];
        visited[currNode] = true;

        int count = 0;

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int node = curr[0];
            int d = curr[1];

            if(d > k){
                continue;
            }

            count++;

            // exploring neighbours
            for(int next : adj.getOrDefault(node, new ArrayList<>())){
                if(!visited[next]){
                    visited[next] = true;
                    q.offer(new int[]{next, d + 1});
                }
            }
        }

        return count;
    }

    public int[] countNodes(int[][] edges, int k){
        int n = edges.length + 1; // no of nodes as edges = total_nodes - 1

        // tree 1
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adj.putIfAbsent(u, new ArrayList<>());
            adj.get(u).add(v);

            adj.putIfAbsent(v, new ArrayList<>());
            adj.get(v).add(u);
        }

        int[] result = new int[n];

        for(int i = 0; i<n; i++){
            result[i] = bfs(i, adj, k, n);
        }

        return result;
    }

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int n = edges1.length + 1; // no of nodes as edges = total_nodes - 1

        int[] tree1NodeCount = countNodes(edges1, k); // O(n.(E1 + n))

        // why k-1
        // as we need to find nodes at a distance <= k
        // and we need to connect one node from tree1 to tree2
        // so, we have one node in tree 1
        int[] tree2NodeCount = countNodes(edges2, k-1); // O(m.(E2 + m))
        
        // finding max in tree 2 to get max benefit
        int max = 0;
        for(int count : tree2NodeCount){ // O(m)
            max = Math.max(max, count);
        }

        // now finding the result
        int[] result = new int[n];

        for(int i = 0; i<n; i++){ // O(n)
            result[i] = tree1NodeCount[i] + max;
        }

        return result;
    }
}







// DFS
// T.C. - O(n.(e1 + n) + m.(e2 + m) + m + n)
// S.C. - O(e + n)
class Solution {
    public int dfs(int currNode, Map<Integer, List<Integer>> adj, int k, int n, boolean[] visited){
        visited[currNode] = true;

        if(k < 0){
            return 0;
        }

        int count = 1;

        // exploring neighbours
        for(int next : adj.getOrDefault(currNode, new ArrayList<>())){
            if(!visited[next]){
                count += dfs(next, adj, k-1, n, visited);
            }
        }

        return count;
    }

    public int[] countNodes(int[][] edges, int k){
        int n = edges.length + 1; // no of nodes as edges = total_nodes - 1

        // tree 1
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adj.putIfAbsent(u, new ArrayList<>());
            adj.get(u).add(v);

            adj.putIfAbsent(v, new ArrayList<>());
            adj.get(v).add(u);
        }

        int[] result = new int[n];

        for(int i = 0; i<n; i++){
            boolean[] visited = new boolean[n];
            result[i] = dfs(i, adj, k, n, visited);
        }

        return result;
    }

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int n = edges1.length + 1; // no of nodes as edges = total_nodes - 1

        int[] tree1NodeCount = countNodes(edges1, k); // O(n.(E1 + n))

        // why k-1
        // as we need to find nodes at a distance <= k
        // and we need to connect one node from tree1 to tree2
        // so, we have one node in tree 1
        int[] tree2NodeCount = countNodes(edges2, k-1); // O(m.(E2 + m))
        
        // finding max in tree 2 to get max benefit
        int max = 0;
        for(int count : tree2NodeCount){ // O(m)
            max = Math.max(max, count);
        }

        // now finding the result
        int[] result = new int[n];

        for(int i = 0; i<n; i++){ // O(n)
            result[i] = tree1NodeCount[i] + max;
        }

        return result;
    }
}