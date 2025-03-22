// LeetCode Medium - 2685


// Approach 1 - DFS
// T.C. - O(V + E)
// S.C. - O(V + E + n)
class Solution {
    public void dfs(int curr, int[] info, Map<Integer, List<Integer>> adj, boolean[] visited){
        visited[curr] = true;
        info[0]++;
        info[1] += adj.getOrDefault(curr, new ArrayList<>()).size();

        for(int neigh : adj.getOrDefault(curr, new ArrayList<>())){
            if(!visited[neigh]){
                dfs(neigh, info, adj, visited);
            }
        }
    }

    public int countCompleteComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adj.putIfAbsent(u, new ArrayList<>());
            adj.get(u).add(v);

            adj.putIfAbsent(v, new ArrayList<>());
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[n];
        int compConnComp = 0;

        for(int i = 0; i<n; i++){
            if(!visited[i]){
                int[] info = new int[2];

                dfs(i, info, adj, visited);

                int noOfEdges = info[0] * (info[0] - 1) / 2;
                int reqNumOfEdges = info[1] / 2; // here edges will be counted twice

                if(noOfEdges == reqNumOfEdges){
                    compConnComp++;
                }
            }
        }

        return compConnComp;
    }
}




// Approach 2 - BFS
// T.C. - O(V + E)
// S.C. - O(V + E + 2n)
class Solution {
    public void bfs(int curr, int[] info, Map<Integer, List<Integer>> adj, boolean[] visited){
        Queue<Integer> q = new LinkedList<>();
        q.offer(curr);
        visited[curr] = true;

        while(!q.isEmpty()){
            int currNode = q.poll();
            
            // incrementing the vertices as a node is encountered
            info[0]++;
            info[1] += adj.getOrDefault(currNode, new ArrayList<>()).size();

            for(int neigh : adj.getOrDefault(currNode, new ArrayList<>())){
                if(!visited[neigh]){
                    q.offer(neigh);
                    visited[neigh] = true;
                }
            }
        }
    }

    public int countCompleteComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adj.putIfAbsent(u, new ArrayList<>());
            adj.get(u).add(v);

            adj.putIfAbsent(v, new ArrayList<>());
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[n];
        int compConnComp = 0;

        for(int i = 0; i<n; i++){
            if(!visited[i]){
                int[] info = new int[2];

                bfs(i, info, adj, visited);

                int noOfEdges = info[0] * (info[0] - 1) / 2;
                int reqNumOfEdges = info[1] / 2; // here edges will be counted twice

                if(noOfEdges == reqNumOfEdges){
                    compConnComp++;
                }
            }
        }

        return compConnComp;
    }
}




// Approach 3 - DSU
// T.C. - O(n + 2E + alpha)
// S.C. - O(2n + k); k = size of map i.e. no of connected components
class Solution {
    class DSU{
        int[] parent;
        int[] size;

        public DSU(int n){
            this.parent = new int[n];
            this.size = new int[n];
            
            for(int i = 0; i<n; i++){
                parent[i] = i; // initially each node is parent of itself
            }

            Arrays.fill(size, 1); // initially size of each component is 1
        }

        public int find(int x){
            if(x == parent[x]){
                return x;
            }

            return parent[x] = find(parent[x]); // path compression
        }

        public void union(int x, int y){
            int parentOfX = find(x);
            int parentOfY = find(y);

            if(parentOfX == parentOfY){
                return;
            }

            if(size[parentOfX] > size[parentOfY]){
                parent[parentOfY] = parentOfX;
                size[parentOfX] += size[parentOfY];
            }
            else{
                parent[parentOfX] = parentOfY;
                size[parentOfY] += size[parentOfX];
            }
        }
    }

    public int countCompleteComponents(int n, int[][] edges) {
        Map<Integer, Integer> edgeCount = new HashMap<>();

        DSU dsu = new DSU(n);

        for(int[] edge : edges){
            dsu.union(edge[0], edge[1]);
        }

        // counting the edges in different components
        for(int[] edge : edges){
            int parent = dsu.find(edge[0]);
            edgeCount.put(parent, edgeCount.getOrDefault(parent, 0) + 1);
        }

        int  compConnComp = 0;
        for(int vertex = 0; vertex < n; vertex++){
            // if same component
            if(dsu.find(vertex) == vertex){
                int nodeCount = dsu.size[vertex];
                int expectedEdges = nodeCount * (nodeCount - 1) / 2;

                if(edgeCount.getOrDefault(vertex, 0) == expectedEdges){
                    compConnComp++;
                }
            }
        }

        return compConnComp;
    }
}