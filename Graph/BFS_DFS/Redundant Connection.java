// LeetCode Medium - 684




// Approach 1 - DFS
// T.C. - O(n * (v + e))
// S.C. - O(n + v + e)
class Solution {
    public boolean containsEdge(int src, int dest, List<List<Integer>> adjList, boolean[] vis){
        vis[src] = true;

        if(src == dest){
            return true;
        }

        for(int nextNode : adjList.get(src)){
            if(!vis[nextNode] && containsEdge(nextNode, dest, adjList, vis)){
                return true;
            }
        }

        return false;
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        List<List<Integer>> adjList = new ArrayList<>();

        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            boolean[] visited = new boolean[n + 1];

            if(containsEdge(u, v, adjList, visited)){
                return edge;
            }

            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        return new int[]{};
    }
}





// Approach 2 - DFS -> Using map for graph
// T.C. - O(n * (v + e))
// S.C. - O(n + v + e)
class Solution {
    public boolean containsEdge(int src, int dest, HashMap<Integer, List<Integer>> adjList, boolean[] vis){
        if(src == dest){
            return true;
        }

        vis[src] = true;

        for(int nextNode : adjList.get(src)){
            if(vis[nextNode]){
                continue;
            }

            if(!vis[nextNode] && containsEdge(nextNode, dest, adjList, vis)){
                return true;
            }
        }

        return false;
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        HashMap<Integer, List<Integer>> adjList = new HashMap<>();

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            boolean[] visited = new boolean[n + 1];

            if(adjList.containsKey(u) && adjList.containsKey(v)){
                if(containsEdge(u, v, adjList, visited)){
                    return edge;
                }
            }

            adjList.putIfAbsent(u, new ArrayList<>());
            adjList.putIfAbsent(v, new ArrayList<>());
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        return new int[]{-1, -1};
    }
}





// Approach 3 - BFS
// T.C. - O(n * (v + e))
// S.C. - O(n + v + e)
class Solution {
    public boolean containsEdge(int src, int dest, List<List<Integer>> adjList, int n){
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> redundantNode = new LinkedList<>();
        redundantNode.offer(src);

        while(!redundantNode.isEmpty()){
            int currNode = redundantNode.poll();
            visited[currNode] = true;

            if(currNode == dest){
                return true;
            }

            for(int nextNode : adjList.get(currNode)){
                if(!visited[nextNode]){
                    visited[nextNode] = true;
                    redundantNode.offer(nextNode);
                }
            }
        }

        return false;
    }

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        List<List<Integer>> adjList = new ArrayList<>();

        for(int i = 0; i <= n; i++){
            adjList.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];


            if(containsEdge(u, v, adjList, n)){
                return edge;
            }

            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        return new int[]{};
    }
}