// LeetCode Medium - 2467


// Approach 1 - DFS + Map
// T.C. - O(n + n)
// S.C. - O(n + n)
class Solution {
    public boolean dfs(int currNode, List<List<Integer>> adj, boolean[] visited, HashMap<Integer, Integer> map, int time){
        visited[currNode] = true;
        map.put(currNode, time);

        if(currNode == 0){ // reached the target
            return true;
        }

        for(int neigh : adj.get(currNode)){
            if(!visited[neigh]){
                // calling dfs for neighbours
                if(dfs(neigh, adj, visited, map, time+1)){
                    return true;
                }
            }
        }

        // now backtrack as we do not find target from the currNode
        map.remove(currNode);
        visited[currNode] = false;

        return false;
    }

    public void dfsForAlice(int currNode, int time, List<List<Integer>> adj, boolean[] visited, HashMap<Integer, Integer> map, int[] amount, int income, int[] maxIncome){
        visited[currNode] = true;

        if(!map.containsKey(currNode) || time < map.get(currNode)){
            income += amount[currNode];
        }
        else if(map.get(currNode) == time){
            income += amount[currNode] / 2;
        }

        // here we can say that if a node has only one neighbour
        // then it is a leaf node
        if(currNode != 0 && adj.get(currNode).size() == 1){
            maxIncome[0] = Math.max(maxIncome[0], income);
        }

        for(int neigh : adj.get(currNode)){
            if(!visited[neigh]){
                dfsForAlice(neigh, time+1, adj, visited, map, amount, income, maxIncome);
            }
        }
    }

    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = edges.length + 1;

        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i<n; i++){
            adj.add(new ArrayList<>());
        }

        for(int edge[] : edges){
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[n];
        HashMap<Integer, Integer> map = new HashMap<>();
        int time = 0;

        // filling the map by dfs to know in which time
        // bob will reach at node which is in it's path
        dfs(bob, adj, visited, map, time); // O(n)

        // for alice
        time = 0;
        Arrays.fill(visited, false);
        int[] maxIncome = new int[]{Integer.MIN_VALUE};
        int income = 0;
        dfsForAlice(0, time, adj, visited, map, amount, income, maxIncome); // O(n)

        return maxIncome[0];        
    }
}





// Approach 2 - DFS + BFS + Map
// T.C. - O(n + n + n)
// S.C. - O(n + n + n)
class Solution {
    public boolean dfs(int currNode, List<List<Integer>> adj, boolean[] visited, HashMap<Integer, Integer> map, int time){
        visited[currNode] = true;
        map.put(currNode, time);

        if(currNode == 0){ // reached the target
            return true;
        }

        for(int neigh : adj.get(currNode)){
            if(!visited[neigh]){
                // calling dfs for neighbours
                if(dfs(neigh, adj, visited, map, time+1)){
                    return true;
                }
            }
        }

        // now backtrack as we do not find target from the currNode
        map.remove(currNode);

        return false;
    }

    public int bfs(int n, List<List<Integer>> adj, HashMap<Integer, Integer> map, int[] amount, boolean[] visited){
        // int[] = {currNode, reached time, currIncome}
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0});

        int maxIncome = Integer.MIN_VALUE;

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int currNode = curr[0];
            int currTime = curr[1];
            int currIncome = curr[2];

            if(!map.containsKey(currNode) || currTime < map.get(currNode)){
                currIncome += amount[currNode];
            }
            else if(currTime == map.get(currNode)){
                currIncome += amount[currNode] / 2;
            }

            if(currNode != 0 && adj.get(currNode).size() == 1){
                maxIncome = Math.max(maxIncome, currIncome);
            }

            for(int neigh : adj.get(currNode)){
                if(!visited[neigh]){
                    q.offer(new int[]{neigh, currTime + 1, currIncome});
                }
            }
            
            visited[currNode] = true;
        }

        return maxIncome;
    }

    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = edges.length + 1;

        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i<n; i++){
            adj.add(new ArrayList<>());
        }

        for(int edge[] : edges){
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[n]; // O(n)
        HashMap<Integer, Integer> map = new HashMap<>(); // O(n)
        int time = 0;

        // filling the map by dfs to know in which time
        // bob will reach at node which is in it's path
        dfs(bob, adj, visited, map, time); // O(n)

        // for alice
        Arrays.fill(visited, false); // O(n)
        return bfs(n, adj, map, amount, visited); // O(n)
    }
}