// LeetCode Medium - 802


// Approach 1 - Using cycle detection in directed cyclic graph
// T.C. - O(V + E)
// S.C. - O(2V)
class Solution {
    public boolean isCycleDFS(int u, boolean[] visited, boolean[] inRecursion, int[][] graph){
        visited[u] = true;
        inRecursion[u] = true;

        for(int v : graph[u]){
            if(!visited[v] && isCycleDFS(v, visited, inRecursion, graph)){
                return true;
            }
            else if(inRecursion[v]){
                return true;
            }
        }

        inRecursion[u] = false;
        return false;
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int V = graph.length;

        boolean[] visited = new boolean[V];
        boolean[] inRecursion = new boolean[V];
        
        // calling dfs to fill inRecursion to find safe nodes
        for(int node = 0; node < V; node++){
            if(!visited[node]){
                isCycleDFS(node, visited, inRecursion, graph);
            }
        }

        List<Integer> safeNodes = new ArrayList<>();

        // iterating in inRecursion array to find safe nodes
        for(int node = 0; node < V; node++){
            if(inRecursion[node] == false){
                safeNodes.add(node);
            }
        }

        return safeNodes; // return the safe nodes
    }
}






// Approach 2 - Using BFS - Kahn's Algorithm
// T.C. - O(2(V + E))
// S.C. - O(2(V + E))
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int V = graph.length;

        // making a new graph with reversed edges
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < V; i++){
            adj.add(new ArrayList<>());
        }

        // making a new graph & populating the indegree array
        int[] indegree = new int[V];

        for(int u = 0; u < V; u++){
            for(int v : graph[u]){
                adj.get(v).add(u);
                indegree[u]++;
            }
        }

        // now pushing the node in the queue whose indegree is 0
        Queue<Integer> safeNodes = new LinkedList<>();

        for(int i = 0; i < V; i++){
            if(indegree[i] == 0){
                safeNodes.offer(i);
            }
        }

        // now applying BFS
        boolean isSafe[] = new boolean[V];
 
        while(!safeNodes.isEmpty()){
            int u = safeNodes.poll();
            isSafe[u] = true;

            // checking its neighbours
            for(int v : adj.get(u)){
                indegree[v]--;

                if(indegree[v] == 0){
                    safeNodes.offer(v);
                }
            }
        }

        // finding the safe nodes
        List<Integer> safeNodesList = new ArrayList<>();

        for(int i = 0; i < V; i++){
            if(isSafe[i]){
                safeNodesList.add(i);
            }
        }

        return safeNodesList;
    }
}