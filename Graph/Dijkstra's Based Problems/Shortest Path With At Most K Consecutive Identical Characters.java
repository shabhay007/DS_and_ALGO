// LeetCode Medium - 3970



// Approach 1 - Dijikstra's
// T.C. - O(nk + eklog(nk)); e = edges.length;
// S.C. - O(ek + nk)
class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<List<int[]>> adj = new ArrayList<>();

        for(int i = 0; i<n; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];
            
            // directed graph u -> v with edge weight wt.
            adj.get(u).add(new int[]{v, wt});
        }

        // Dijikstra's -> {accumulatedWeight, destNode, consecutiveCount}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        int[][] dist = new int[n][k + 1];

        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        dist[0][1] = 0; // src - dest with k = 1; dest is src initially
        pq.offer(new int[]{0, 0, 1});

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int currPathWt = curr[0];
            int currNode = curr[1];
            int currConsecutive = curr[2];
            char currLabel = labels.charAt(currNode);

            // already stored wt is less than current
            if (dist[currNode][currConsecutive] < currPathWt) {
                continue;
            }

            // Destination reached
            if (currNode == n - 1) {
                return currPathWt;
            }

            // neighbours
            for(int[] neigh : adj.get(currNode)){
                int next = neigh[0];
                int nextWt = neigh[1];
                char nextLabel = labels.charAt(next);

                int newConsecutive = (nextLabel == currLabel)
                    ? currConsecutive + 1
                    : 1;

                if(newConsecutive > k){
                    continue;
                }

                int newPathWt = currPathWt + nextWt;
                
                if (newPathWt < dist[next][newConsecutive]) {
                    dist[next][newConsecutive] = newPathWt;
                    pq.offer(new int[]{newPathWt, next, newConsecutive});
                }
            }
        }

        return -1;
    }
}