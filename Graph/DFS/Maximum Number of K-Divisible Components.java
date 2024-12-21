// LeetCode Hard - 2872


// Approach 1 - dfs
// T.C. - O(m + n)
// S.C. - O(m + n); m no. of edges + n no. of nodes
class Solution {
    public long dfs(int currNode, int parentNode, ArrayList<ArrayList<Integer>> adj, int[] values, int k, int[] componentsCount){
        long componentSum = 0;

        for(int neighbour : adj.get(currNode)){
            if(neighbour != parentNode){
                // checking the sum
                componentSum += dfs(neighbour, currNode, adj, values, k, componentsCount);
            }
        }

        // now loop ends and we are on leaf node
        // OR
        // node whose childrens have already been taken care
        componentSum += values[currNode];

        if(componentSum % k == 0){
            componentsCount[0]++;
            componentSum = 0;
        }

        // returning this sum to the neighbours(parents)
        return componentSum;
    }

    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i<n; i++){
            adj.add(new ArrayList<>());
        }

        // O(m)
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        int[] componentCount = new int[1];

        // starting dfs from node 0
        dfs(0, -1, adj, values, k, componentCount); // O(n)
        
        return componentCount[0];
    }
}