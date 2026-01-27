// LeetCode Medium - 3660



// Approach 1 - Shortest Path (Dijkstra's Algorithm)
// T.C. - O(e * log(n)); e = edges.length, n = vertices
// S.C. - O(n + e)
class Solution {
    public int minCost(int n, int[][] edges) {
        List<List<int[]>> adj = new ArrayList<>();

        // initialization
        for(int i = 0; i<n; i++){
            adj.add(new ArrayList<>());
        }

        // creating directed graph
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            adj.get(u).add(new int[]{v, wt});

            // adding reversed edges
            adj.get(v).add(new int[]{u, 2 * wt});
        }

        // Dijkstra
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        int[] dist = new int[n];

        // dist from src to other is infinity at start
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        // src = 0, {dest. node, dist}
        pq.offer(new int[]{0, 0}); // dist from src -> destination

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int node = curr[0];
            int d = curr[1];

            if(node == n-1){
                return d; // as d will be the shortest distance from src -> dest
            }

            // now going for neighbours
            for(int[] neigh : adj.get(node)){
                int newNode = neigh[0];
                int newD = neigh[1];

                if(d + newD < dist[newNode]){
                    dist[newNode] = d + newD;
                    pq.offer(new int[]{newNode, d + newD});
                }
            }
        }

        return -1;
    }
}