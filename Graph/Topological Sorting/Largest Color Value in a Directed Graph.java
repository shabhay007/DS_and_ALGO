// LeetCode Hard - 1857



// Approach 1 - Topological Sort (Kahn's Algorithm) + DP
// T.C. - O(V + E)
// S.C. - O(V + E)
class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        Map<Integer, List<Integer>> adj = new HashMap<>();
        int[] indegree = new int[n];

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj.putIfAbsent(u, new ArrayList<>());
            adj.get(u).add(v);
            
            // indegree
            indegree[v]++;
        }

        Queue<Integer> q = new LinkedList<>();
        int[][] dp = new int[n][26];

        // populating queue
        for(int i = 0; i<n; i++){
            if(indegree[i] == 0){
                char ch = colors.charAt(i);

                q.offer(i);
                dp[i][ch - 'a'] = 1;
            }
        }

        // now processing
        int result = 0;
        int totalNodes = 0;

        while(!q.isEmpty()){
            int u = q.poll();
            totalNodes++;

            char ch = colors.charAt(u);
            result = Math.max(result, dp[u][ch - 'a']);

            for(int neigh : adj.getOrDefault(u, new ArrayList<>())){
                for(int c = 0; c < 26; c++){
                    int newCh = colors.charAt(neigh) - 'a';

                    dp[neigh][c] = Math.max(dp[neigh][c], dp[u][c] + ((newCh == c) ? 1 : 0));
                }

                indegree[neigh]--;

                if(indegree[neigh] == 0){
                    q.offer(neigh);
                }
            }
        }

        return (totalNodes < n) ? -1 : result;
    }
}
