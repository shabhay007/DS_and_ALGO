// LeetCode Hard - 3373



// Approach 1 - BFS (Gives TLE)
// T.C. - O(n^2 + m^2 + m + n)
// S.C. - O(n + m)
class Solution {
    public int bfs(int currNode, Map<Integer, List<Integer>> adj, int n, int parity){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{currNode, 0}); // {node, distance}

        boolean[] visited = new boolean[n];
        visited[currNode] = true;

        int count = 0;

        while(!q.isEmpty()){
            int[] node = q.poll();
            int curr = node[0];
            int d = node[1];

            if(d % 2 == parity){
                count++;
            }

            for(int next : adj.getOrDefault(curr, new ArrayList<>())){
                if(!visited[next]){
                    visited[next] = true;
                    q.offer(new int[]{next, d+1});
                }
            }
        }

        return count;
    }

    public int[] findCount(int[][] edges, int n, int parity){
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        int[] result = new int[n];

        for(int i = 0; i<n; i++){ // Because of this it will give TLE
            result[i] = bfs(i, adj, n, parity);
        }

        return result;
    }

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;

        int[] count1 = findCount(edges1, n, 0);
        int[] count2 = findCount(edges2, m, 1);

        // finding the node with max odd count or with even edges
        int max = 0;
        for(int num : count2){
            max = Math.max(max, num);
        }

        // now processing to find the max nodes
        int[] result = new int[n];

        for(int i = 0; i<n; i++){
            result[i] = max + count1[i];
        }

        return result;
    }
}







// Approach 2 (Optimal) - DFS
// T.C. - O(4n + 4m + m + n) = (5n + 5m)
// S.C. - O(m + n)
class Solution {
    public void dfs(int currNode, Map<Integer, List<Integer>> adj, boolean[] visited, int[] depth, int d){
        visited[currNode] = true;
        depth[currNode] = d;

        for(int next : adj.getOrDefault(currNode, new ArrayList<>())){
            if(!visited[next]){
                dfs(next, adj, visited, depth, d+1);
            }
        }
    }

    public int[] findCount(int[][] edges, int n, int parity){
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for(int[] edge : edges){ // O(n)
            int u = edge[0];
            int v = edge[1];

            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        boolean[] visited = new boolean[n];
        int[] depth = new int[n];

        // dfs - finding the depth for all nodes
        dfs(0, adj, visited, depth, 0); // O(n)

        // now finding the
        int evenCount = 0;
        int oddCount = 0;

        for(int d : depth){ // O(n)
            if(d % 2 == 0){
                evenCount++;
            }
            else{
                oddCount++;
            }
        }


        // if node's depth is odd then it have oddCount target
        // and vice versa
        int[] result = new int[n];

        for(int i = 0; i<n; i++){ // O(n)
            if(depth[i] % 2 == 0){
                result[i] = evenCount;
            }
            else{
                result[i] = oddCount;
            }
        }

        return result;
    }

    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int n = edges1.length + 1;
        int m = edges2.length + 1;

        int[] count1 = findCount(edges1, n, 0);
        int[] count2 = findCount(edges2, m, 1);

        // finding the node with max odd count or with even edges
        int max = 0;
        for(int num : count2){ // O(m)
            max = Math.max(max, num);
        }

        // now processing to find the max nodes
        int[] result = new int[n];

        for(int i = 0; i<n; i++){ // O(n)
            result[i] = max + count1[i];
        }

        return result;
    }
}