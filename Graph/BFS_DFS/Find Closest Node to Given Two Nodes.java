// LeetCode Medium - 2359



// Approach 1 - BFS
// T.C. - O(4n)
// S.C. - O(3n)
class Solution {
    public int[] bfs(int src, Map<Integer, Integer> adj, int n){
        int[] result = new int[n];
        Arrays.fill(result, -1);

        boolean[] visited = new boolean[n];
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{src, 0});
        visited[src] = true;

        while(!q.isEmpty()){
            int[] node = q.poll();
            int curr = node[0];
            int d = node[1];

            result[curr] = d;

            int next = adj.getOrDefault(curr, -1);

            if(next != -1 && !visited[next]){
                visited[next] = true;
                q.offer(new int[]{next, d+1});
            }
        }

        return result;
    }

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;

        // there is only one to one connection i.e. there is only 1 outgoing edge
        // between two nodes, so we don't need List<Integer>

        // graph construction
        Map<Integer, Integer> adj = new HashMap<>();

        for(int i = 0; i<n; i++){
            if(edges[i] != -1){
                adj.put(i, edges[i]);
            }
        }

        int[] dist1 = bfs(node1, adj, n);
        int[] dist2 = bfs(node2, adj, n);

        // now processing
        int minDist = Integer.MAX_VALUE;
        int result = -1;

        for(int i = 0; i<n; i++){
            if(dist1[i] == -1 || dist2[i] == -1){
                continue;
            }

            int tempMax = Math.max(dist1[i], dist2[i]);

            if(tempMax < minDist){
                minDist = tempMax;
                result = i;
            }
        }

        return result;
    }
}





// Approach 2 - DFS
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public void dfs(int currNode, Map<Integer, Integer> adj, int n, boolean[] visited, int[] dist, int d){
        visited[currNode] = true;
        dist[currNode] = d;

        int next = adj.getOrDefault(currNode, -1);

        if(next != -1 && !visited[next]){
            dfs(next, adj, n, visited, dist, d+1);
        }
    }

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;

        // there is only one to one connection i.e. there is only 1 outgoing edge
        // between two nodes, so we don't need List<Integer>

        // graph construction
        Map<Integer, Integer> adj = new HashMap<>();

        for(int i = 0; i<n; i++){
            if(edges[i] != -1){
                adj.put(i, edges[i]);
            }
        }

        int[] dist1 = new int[n];
        int[] dist2 = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist1, -1);
        Arrays.fill(dist2, -1);
 
        dfs(node1, adj, n, visited, dist1, 0);

        visited = new boolean[n];
        dfs(node2, adj, n, visited, dist2, 0);

        // now processing
        int minDist = Integer.MAX_VALUE;
        int result = -1;

        for(int i = 0; i<n; i++){
            if(dist1[i] == -1 || dist2[i] == -1){
                continue;
            }

            int tempMax = Math.max(dist1[i], dist2[i]);

            if(tempMax < minDist){
                minDist = tempMax;
                result = i;
            }
        }

        return result;
    }
}





// Approach 3 (Optimal) - DFS
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public void dfs(int currNode, int[] edges, boolean[] visited, int[] dist, int d){
        visited[currNode] = true;
        dist[currNode] = d;

        int next = edges[currNode];

        if(next != -1 && !visited[next]){
            dfs(next, edges, visited, dist, d+1);
        }
    }

    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int n = edges.length;

        int[] dist1 = new int[n];
        int[] dist2 = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist1, -1);
        Arrays.fill(dist2, -1);
 
        dfs(node1, edges, visited, dist1, 0);

        visited = new boolean[n];
        dfs(node2, edges, visited, dist2, 0);

        // now processing
        int minDist = Integer.MAX_VALUE;
        int result = -1;

        for(int i = 0; i<n; i++){
            if(dist1[i] == -1 || dist2[i] == -1){
                continue;
            }

            int tempMax = Math.max(dist1[i], dist2[i]);

            if(tempMax < minDist){
                minDist = tempMax;
                result = i;
            }
        }

        return result;
    }
}




// Approach 4 - Change dfs to bfs in Approach 3