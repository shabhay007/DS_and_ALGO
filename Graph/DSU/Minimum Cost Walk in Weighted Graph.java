// LeetCode Hard - 3108



// Approach 1 - DSU (Gives TLE) (codeStoryWithMIK)
// T.C. - O(E + Q.Alpha(n));
// S.C. - O(2n)

/*
    The time complexity of the union-find operations 
    (with path compression) is approximately O(α(n)), 
    where α is the inverse Ackermann function, 
    which is practically constant for all reasonable values of n.

    The time complexity of processing each edge and each query is O(1).

    Therefore, the overall time complexity of the algorithm is O(E + Qα(n)), 
    where E is the number of edges, Q is the number of queries, 
    and α(n) is the inverse Ackermann function.
*/

class Solution {
    public int find(int x, int[] parent){
        if(parent[x] == x){
            return x;
        }

        return find(parent[x], parent);
    }

    public void union(int x, int y, int[] parent){
        parent[y] = x;
    }

    public int[] minimumCost(int n, int[][] edges, int[][] queries) {
        int parent[] = new int[n];
        int cost[] = new int[n];

        // initializing the parent of all nodes with itself
        for(int i = 0; i<n; i++){
            parent[i] = i;
            cost[i] = -1;
        }

        // processing all edges
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            int parentU = find(u, parent);
            int parentV = find(v, parent);

            if(parentU != parentV){
                union(parentU, parentV, parent);
                cost[parentU] &= cost[parentV];
            }

            // processing the new edge parentU -> parentV
            cost[parentU] &= wt;
        }

        // step 3 : processing the qeury
        int[] result = new int[queries.length];
        int i = 0;

        for(int[] query : queries){
            int start = query[0];
            int target = query[1];
            
            int pStart = find(start, parent);
            int pTarget = find(target, parent);

            if(start == target){
                result[i] = 0;
                // i++;
            }
            else if(pStart == pTarget){
                result[i] = cost[pStart];
                // i++;
            }
            else{
                result[i] = -1;
                // i++;
            }

            i++;
        }

        return result;
    }
}





// Approach 2 - DSU using Path Compression
// T.C. - O(E + Q.Alpha(n));
// S.C. - O(2n)
class Solution {
    public int find(int x, int[] parent){
        if(parent[x] == x){
            return x;
        }

        return parent[x] = find(parent[x], parent);
    }

    public void union(int x, int y, int[] parent){
        parent[y] = x;
    }

    public int[] minimumCost(int n, int[][] edges, int[][] queries) {
        int parent[] = new int[n];
        int cost[] = new int[n];

        // initializing the parent of all nodes with itself
        for(int i = 0; i<n; i++){
            parent[i] = i;
            cost[i] = -1;
        }

        // processing all edges
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int wt = edge[2];

            int parentU = find(u, parent);
            int parentV = find(v, parent);

            if(parentU != parentV){
                union(parentU, parentV, parent);
                cost[parentU] &= cost[parentV];
            }

            // processing the new edge parentU -> parentV
            cost[parentU] &= wt;
        }

        // step 3 : processing the qeury
        int[] result = new int[queries.length];
        int i = 0;

        for(int[] query : queries){
            int start = query[0];
            int target = query[1];
            
            int pStart = find(start, parent);
            int pTarget = find(target, parent);

            if(start == target){
                result[i] = 0;
            }
            else if(pStart == pTarget){
                result[i] = cost[pStart];
            }
            else{
                result[i] = -1;
            }

            i++;
        }

        return result;
    }
}