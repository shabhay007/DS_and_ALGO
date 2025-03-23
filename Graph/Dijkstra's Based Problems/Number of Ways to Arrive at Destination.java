// LeetCode Medium - 1976


// Approach 1 - Dijkstras
// T.C. - O(E + (V + E).log(V))
// S.C. - O(V + E + 3V)
class Solution {
    public int countPaths(int n, int[][] roads) {
        int mod = (int) 1e9 + 7;
        Map<Integer, List<int[]>> adj = new HashMap<>();

        for(int[] road : roads){ // O(E)
            int u = road[0];
            int v = road[1];
            int wt = road[2];

            adj.putIfAbsent(u, new ArrayList<>());
            adj.putIfAbsent(v, new ArrayList<>());

            // now making the connections
            adj.get(u).add(new int[]{v, wt});
            adj.get(v).add(new int[]{u, wt});
        }

        long[] shortestTime = new long[n];
        int[] waysCount = new int[n];

        // initially can't be reached i.e. infinite time
        Arrays.fill(shortestTime, Long.MAX_VALUE);
        shortestTime[0] = 0; // time to reach src from src
        waysCount[0] = 1; // ways to reach src from src

        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
        pq.add(new long[]{0, 0}); // {src, timeToReach}

        int dest = n-1;

        while(!pq.isEmpty()){ // O((V + E).log(V))
            long[] curr = pq.poll();
            int currNode = (int) curr[0];
            long currTime = curr[1];

            for(int[] neigh : adj.getOrDefault(currNode, new ArrayList<>())){
                int ngbr = neigh[0];
                int time = neigh[1];

                if(currTime + time < shortestTime[ngbr]){
                    shortestTime[ngbr] = currTime + time;
                    pq.offer(new long[]{ngbr, shortestTime[ngbr]});

                    // ways count
                    waysCount[ngbr]= waysCount[currNode];
                }
                else if(currTime + time == shortestTime[ngbr]){
                    waysCount[ngbr] = (waysCount[ngbr] + waysCount[currNode]) % mod;
                }
            }
        }

        return waysCount[n-1];
    }
}