// LeetCode - 2924 - Medium


// Approach 1 - In-degree
class Solution {
    public int findChampion(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i<n; i++){
            adj.add(new ArrayList<>());
        }

        // making adjacency list
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            adj.get(u).add(v);
        }

        // Finding in-degree
        int[] indegree = new int[n];

        for(int i = 0; i<n; i++){
            for(int node : adj.get(i)){
                indegree[node]++;
            }
        }

        int count = 0;
        int strongTeam = -1;
        for(int i = 0; i<n; i++){
            if(indegree[i] == 0){
                strongTeam = i;
                count++;
            }
        }

        return count > 1 ? -1 : strongTeam;
    }
}



// Approach 2 - In-degree - O(total edges + n)
class Solution {
    public int findChampion(int n, int[][] edges) {
        int[] indegree = new int[n];

        // Iterating on edges and filling in-degree array
        for(int[] edge : edges){ // O(total edges)
            int u = edge[0];
            int v = edge[1];

            indegree[v]++;
        }

        int count = 0;
        int strongTeam = -1;
        for(int i = 0; i<n; i++){
            if(indegree[i] == 0){
                strongTeam = i;
                count++;

                if(count > 1) return -1;
            }
        }

        return strongTeam;
    }
}