// LeetCode Hard - 3620



// Approach - Dijkstra's + Binary Search on Answers
// T.C. - O(elog(v) * log(M)); M = r - l
// S.C. - O(n)
class Pair{
    int node;
    long cost;

    public Pair(int node, long cost){
        this.node = node;
        this.cost = cost;
    }
}

class Solution {
    // Dijkstra's -> O(elog(v)); e = edges, v = vertices
    public boolean check(long mid, List<List<Pair>> adj, long k, int n){
        long[] result = new long[n];
        Arrays.fill(result, Long.MAX_VALUE);

        // min heap - sorted by cost
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> Long.compare(a.cost, b.cost));
        result[0] = 0;
        pq.offer(new Pair(0, 0)); // cost from src to src is 0

        while(!pq.isEmpty()){
            Pair curr = pq.poll();
            int node = curr.node;
            long cost = curr.cost; // cost to reach till curr node

            if(cost > k){
                return false;
            }

            if(node == n-1){
                return true;
            }

            // it's an important optimization in Dijkstra's
            // if we have already found a smaller cost path to this node, we can skip processing
            // total cost from src to node = cost
            if(result[node] < cost){
                continue; // skipping as we already have smaller cost path
            }

            for(Pair neigh : adj.get(node)){
                int nextNode = neigh.node;
                long nextCost = neigh.cost;

                // it's not that path which I have sent this mid
                // this path might be valid but not for this mid
                if(nextCost < mid){
                    continue;
                }

                if(cost + nextCost < result[nextNode]){
                    result[nextNode] = cost + nextCost;
                    pq.offer(new Pair(nextNode, result[nextNode]));
                }
            }
        }

        return false;
    }

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        long l = Long.MAX_VALUE;
        long r = Long.MIN_VALUE;

        List<List<Pair>> adj = new ArrayList<>();

        for(int i = 0; i<n; i++){
            adj.add(new ArrayList<>());
        }

        // making directed graph
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            long cost = edge[2];

            // skipping invalid path i.e. offline nodes
            if(!online[u] || !online[v]){
                continue;
            }

            l = Math.min(l, cost);
            r = Math.max(r, cost);
            adj.get(u).add(new Pair(v, cost))   ; 
        }

        // binary search
        long result = -1;

        while(l <= r){
            long mid = l + (r - l)/2;

            if(check(mid, adj, k, n)){
                result = mid;
                l = mid + 1; // goal is to maximize
            }
            else{
                r = mid - 1;
            }
        }

        return (int) result;
    }
}