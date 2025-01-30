// LeetCode Hard - 2493


// DFS + BFS + Bipartite
// T.C. - O(n + (n + E) + 3 * n(n + E))
// S.C. - O(n + n + E)
class Solution {
    // 1 - Red
    // 0 - Green
    public boolean isBipartite(int curr, List<List<Integer>> adjList, int[] colors, int currColor){
        colors[curr] = currColor;

        for(int neigh : adjList.get(curr)){
            if(colors[neigh] == colors[curr]){
                return false;
            }

            if(colors[neigh] == -1){
                if(!isBipartite(neigh, adjList, colors, 1 - currColor)){
                    return false;
                }
            }
        }

        return true;
    }

    public int BFS(int currNode, List<List<Integer>> adjList, int n){
        boolean[] visited = new boolean[n+1];

        Queue<Integer> levelNodes = new LinkedList<>();
        levelNodes.offer(currNode);

        visited[currNode] = true;
        int level = 1; // starts from level/group 1

        while(!levelNodes.isEmpty()){
            int size = levelNodes.size();

            for(int i = 0; i < size; i++){
                int curr = levelNodes.poll();
                
                for(int neigh : adjList.get(curr)){
                    if(!visited[neigh]){
                        visited[neigh] = true;
                        levelNodes.offer(neigh);
                    }
                }
            }

            level++; // here one extra level/group gets added
        }

        // subtracting that extra group
        // OR you can start level count from 0
        return level - 1;
    }

    public int getMaxFromEachComponent(int currNode, List<List<Integer>> adjList, boolean[] visited, int[] levels){
        int maxGroup = levels[currNode];
        visited[currNode] = true;

        for(int neigh : adjList.get(currNode)){
            if(!visited[neigh]){
                int newGroups = getMaxFromEachComponent(neigh, adjList, visited, levels);
                maxGroup = Math.max(maxGroup, newGroups);
            }
        }

        return maxGroup;
    }

    public int magnificentSets(int n, int[][] edges) {
        List<List<Integer>> adjList = new ArrayList<>();

        // initializing the adjList
        for(int i = 0; i<=n; i++){ // O(n)
            adjList.add(new ArrayList<>());
        }

        // connecting the edges
        for(int[] edge : edges){ // O(n + E)
            int u = edge[0];
            int v = edge[1];

            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        // checking for bi-partite
        int[] colors = new int[n+1];
        Arrays.fill(colors, -1);

        // not bipartite, division into groups is not possible
        for(int i = 1; i<=n; i++){ // O(n)
            if(colors[i] == -1){
                if(!isBipartite(i, adjList, colors, 1)){ // O(n + E)
                    return -1;
                }
            }
        }

        // now graph is bipartite
        // So, find the max levels using BFS, as each level denotes a group
        int[] levels = new int[n+1];

        for(int node = 1; node <= n; node++){ // O(n)
            levels[node] = BFS(node, adjList, n); // O(n + E)
        }

        // now finding max groups from each component
        int maxGroupFromEachComponent = 0;
        boolean[] vis = new boolean[n+1];

        for(int node = 1; node <= n; node++){ // O(n * (n + E))
            if(!vis[node]){
                maxGroupFromEachComponent += getMaxFromEachComponent(node, adjList, vis, levels);
            }
        }

        return maxGroupFromEachComponent;
    }
}