// LeetCode - 3243 - Medium



// Approach 1 - Dijkstra's Algorithm
class Solution {
    public class Pair {
        int node;
        int wt;

        public Pair(int node, int wt){
            this.node = node;
            this.wt = wt;
        }
    }

    public int dijkstra(List<List<Pair>> adj, int n){
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>(){
            public int compare(Pair p1, Pair p2){
                return p1.wt - p2.wt;
            }
        });
        
        // dist[i] = shortest distance from src to ith node
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        boolean vis[] = new boolean[n];
        pq.add(new Pair(0, 0)); // distance from src to src

        while(!pq.isEmpty()){
            Pair curr = pq.poll();
            int currNode = curr.node;
            int currDist = curr.wt;

            for(Pair neighbour : adj.get(currNode)){
                int neigh = neighbour.node;
                int weight = neighbour.wt;

                if(currDist + weight < dist[neigh]){
                    dist[neigh] = currDist + weight;
                    pq.offer(new Pair(neigh, dist[neigh]));
                }
            }
        }

        return dist[n-1]; // shortest path from src to n-1
    }
    
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        List<List<Pair>> adj = new ArrayList<>();

        // 0 to n-1 nodes
        for(int i = 0; i<n; i++){
            adj.add(new ArrayList<>());
        }

        // 0 -> 1 -> 2 ...
        for(int i = 0; i<n-1; i++){
            adj.get(i).add(new Pair(i+1, 1));
        }

        int len = queries.length;
        int[] result = new int[len];

        for(int i = 0; i<len; i++){ // O(len * E * log(n))
            int u = queries[i][0];
            int v = queries[i][1];

            adj.get(u).add(new Pair(v, 1));
            
            // calling SPA
            result[i] = dijkstra(adj, n);
        }

        return result;
    }
}




// Approach 2 - BFS - T.C : O(len * (n + E)),  S.C : O(n + E)
class Solution {
    public int bfs(int n, List<List<Integer>> adj){
        Queue<Integer> q = new LinkedList<>();
        q.offer(0); // source

        boolean visited[] = new boolean[n]; // Space O(n)
        visited[0] = true;

        int dist = 0;

        while(!q.isEmpty()){
            int currLevelSize = q.size();

            for(int i = 0; i<currLevelSize; i++){
                int node = q.poll();

                if(node == n-1){ // reached the destination
                    return dist;
                }

                for(int neigh : adj.get(node)){
                    if(!visited[neigh]){
                        q.offer(neigh);
                        visited[neigh] = true;
                    }
                }
            }

            dist++;
        }

        return -1;
    }
    
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        List<List<Integer>> adj = new ArrayList<>(); // Space O(n + E)

        // 0 to n-1 nodes
        for(int i = 0; i<n; i++){
            adj.add(new ArrayList<>());
        }

        // 0 -> 1 -> 2 ...
        for(int i = 0; i<n-1; i++){
            adj.get(i).add(i+1);
        }

        int len = queries.length;
        int[] result = new int[len];
        int i = 0;

        for(int[] query : queries){
            int u = query[0];
            int v = query[1];

            adj.get(u).add(v);
            
            // calling SPA
            if(i < len){
                result[i] = bfs(n, adj);
                i++;
            }
        }

        return result;
    }
}