// LeetCode Medium - 2492



// Approach 1 - DFS
// T.C. - O(V + E); E = roads.length
// S.C. - O(V + E)
class Solution {
    public void dfs(int curr, List<List<int[]>> adj, boolean[] visited, int[] min, int n){
        visited[curr] = true;

        for(int[] ngbr : adj.get(curr)){
            int city = ngbr[0];
            int dist = ngbr[1];
            min[0] = Math.min(min[0], dist);

            if(!visited[city]){
                dfs(city, adj, visited, min, n);
            }
        }
    }

    public int minScore(int n, int[][] roads) {
        List<List<int[]>> adj = new ArrayList<>();
        
        for(int i = 0; i<=n; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] road : roads){
            int u = road[0];
            int v = road[1];
            int d = road[2];

            adj.get(u).add(new int[]{v, d});
            adj.get(v).add(new int[]{u, d});
        }

        boolean[] visited = new boolean[n+1];
        int[] result = {Integer.MAX_VALUE};
        dfs(1, adj, visited, result, n);

        return result[0];
    }
}





// Approach 2 - BFS
// T.C. - O(V + E); E = roads.length
// S.C. - O(V + E)
class Solution {
    public int bfs(List<List<int[]>> adj, int n){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{1, 0});

        boolean[] visited = new boolean[n+1];
        visited[1] = true;

        int min = Integer.MAX_VALUE;

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int node = curr[0];
            int d = curr[1];

            for(int[] ngbr : adj.get(node)){
                int next = ngbr[0];
                int nd = ngbr[1];
                min = Math.min(min, nd);

                if(!visited[next]){
                    visited[next] = true;
                    q.offer(new int[]{next, nd});
                }
            }
        }

        return min;
    }

    public int minScore(int n, int[][] roads) {
        List<List<int[]>> adj = new ArrayList<>();
        
        for(int i = 0; i<=n; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] road : roads){
            int u = road[0];
            int v = road[1];
            int d = road[2];

            adj.get(u).add(new int[]{v, d});
            adj.get(v).add(new int[]{u, d});
        }

        return bfs(adj, n);
    }
}