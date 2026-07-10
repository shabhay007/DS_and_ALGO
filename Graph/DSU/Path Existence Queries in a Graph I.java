// LeetCode - 3532



// Approach 1 - DSU (Gives TLE)
// T.C. - O(n + n^2.α(n) + q.α(n))
// S.C. - O(2n)
class Solution {
    class DSU {
        int[] parent;
        int[] size;

        public DSU(int n){
            this.parent = new int[n];
            this.size = new int[n];
            
            for(int i = 0; i<n; i++){
                parent[i] = i; // initially every node will be parent of itself
                size[i] = 1;  // initially size of each component will be 1
            }
        }

        public int find(int x){
            if(x == parent[x]){
                return x;
            }

            return parent[x] = find(parent[x]); // path compression
        }

        public void union(int x, int y){
            int parentOfX = find(x);
            int parentOfY = find(y);

            if(parentOfX == parentOfY){
                return;
            }

            if(size[parentOfX] > size[parentOfY]){
                parent[parentOfY] = parentOfX;
                size[parentOfX] += size[parentOfY];
            }
            else{
                parent[parentOfX] = parentOfY;
                size[parentOfY] += size[parentOfX];
            }
        }
    }

    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        DSU dsu = new DSU(n);

        for(int i = 0; i<n; i++){ // O(n^2)
            for(int j = 0; j<n; j++){
                int diff = Math.abs(nums[i] - nums[j]);

                if(diff <= maxDiff){
                    dsu.union(i, j); // O(α(n)) -> due to path compression and union by size
                }
            }
        }


        // processing the queries using DSU
        boolean[] result = new boolean[queries.length];
        int i = 0;
        
        for(int[] query : queries){ // O(q)
            int px = dsu.find(query[0]); // O(α(n))
            int py = dsu.find(query[1]); // O(α(n))

            if(px == py){
                result[i] = true;
            }

            i++;
        }

        return result;
    }
}






// Approach 2 (Optimal) - DSU (Slight but major change in Approach 1)
// T.C. - O(n.α(n) + q.α(n))
// S.C. - O(2n)
class Solution {
    class DSU {
        int[] parent;
        int[] size;

        public DSU(int n){
            this.parent = new int[n];
            this.size = new int[n];
            
            for(int i = 0; i<n; i++){
                parent[i] = i; // initially every node will be parent of itself
                size[i] = 1;  // initially size of each component will be 1
            }
        }

        public int find(int x){
            if(x == parent[x]){
                return x;
            }

            return parent[x] = find(parent[x]); // path compression
        }

        public void union(int x, int y){
            int parentOfX = find(x);
            int parentOfY = find(y);

            if(parentOfX == parentOfY){
                return;
            }

            if(size[parentOfX] > size[parentOfY]){
                parent[parentOfY] = parentOfX;
                size[parentOfX] += size[parentOfY];
            }
            else{
                parent[parentOfX] = parentOfY;
                size[parentOfY] += size[parentOfX];
            }
        }
    }

    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        DSU dsu = new DSU(n);

        // since nums is sorted that means, if diff between any two adjacent elements
        // in nums > maxDiff then it means disconnected component
        for(int i = 0; i<n-1; i++){ // O(n^2)
            int diff = Math.abs(nums[i] - nums[i+1]);

            if(diff <= maxDiff){
                dsu.union(i, i+1); // O(α(n)) -> due to path compression and union by size
            }
        }


        // processing the queries using DSU
        boolean[] result = new boolean[queries.length];
        int i = 0;
        
        for(int[] query : queries){ // O(q)
            int px = dsu.find(query[0]); // O(α(n))
            int py = dsu.find(query[1]); // O(α(n))

            if(px == py){
                result[i] = true;
            }

            i++;
        }

        return result;
    }
}






// Approach 3 - Using rank instead of size array
// T.C. - O(n.alpha(n) + n.alpha(q))
// S.C. - O(n)
class DSU{
    int[] parent;
    int[] rank;

    public DSU(int n){
        this.parent = new int[n];
        this.rank = new int[n];

        for(int i = 0; i<n; i++){
            parent[i] = i; // initially every node is parent of itself
            rank[i] = 1;
        }
    }

    public int find(int x){
        if(x == parent[x]){
            return x;
        }

        return parent[x] = find(parent[x]); // path compression
    }

    public void union(int x, int y){
        int xParent = find(x);
        int yParent = find(y);

        if(xParent == yParent){
            return;
        }

        // union by rank
        if(rank[xParent] > rank[yParent]){
            parent[yParent] = xParent;
        }
        else if(rank[xParent] < rank[yParent]){
            parent[xParent] = yParent;
        }
        else{
            parent[yParent] = xParent;
            rank[xParent]++;
        }
    }
}

class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        DSU dsu = new DSU(n);

        for(int i = 0; i<n-1; i++){
            int diff = Math.abs(nums[i] - nums[i+1]);

            if(diff <= maxDiff){
                dsu.union(i, i+1);
            }
        }

        // processing the queries
        int q =queries.length;
        boolean[] result = new boolean[q];

        for(int i = 0; i<q; i++){
            int uParent = dsu.find(queries[i][0]);
            int vParent = dsu.find(queries[i][1]);

            if(uParent == vParent){
                result[i] = true;
            }
        }

        return result;
    }
}