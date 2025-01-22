// LeetCode Medium - 1765



// Approach 1 - Using multi source BFS
// T.C. - O(2(m * n))
// S.C. - O(2(m * n))
class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;

        int[][] maxHeight = new int[m][n]; // result map grid

        // setting the water cells with 0
        // now pushing multi sources in the queue for multi src BFS
        Queue<int[]> multiSrcQ = new LinkedList<>();

        // not recommended to take as it increases space complexity
        boolean[][] visited = new boolean[m][n];

        for(int i = 0; i < m; i++){ // O(m * n)
            for(int j = 0; j < n; j++){
                if(isWater[i][j] == 1){
                    maxHeight[i][j] = 0;
                    multiSrcQ.offer(new int[]{0, i, j}); // push the every source
                    visited[i][j] = true; // mark visited after pushing the source
                }
                else{
                    maxHeight[i][j] = -1;
                }
            }
        }

        // now performing BFS
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while(!multiSrcQ.isEmpty()){ // O(m * n)
            int[] src = multiSrcQ.poll();
            int val = src[0];
            int i = src[1];
            int j = src[2];

            for(int[] dir : directions){
                int new_i = i + dir[0];
                int new_j = j + dir[1];

                // instead of visited, you can use if maxHeight == 0
                if(new_i >= 0 && new_i < m && new_j >= 0 && new_j < n && !visited[new_i][new_j]){
                    visited[new_i][new_j] = true;
                    maxHeight[new_i][new_j] = val + 1;
                    multiSrcQ.offer(new int[]{val + 1, new_i, new_j});
                }
            }
        }

        return maxHeight;
    }
}






// Approach 2 - Using multi source BFS with slight change in approach 1
// we are performing bfs for a entire level, this time
// And use of visited array is not required, even in 1st approach
// T.C. - O(2(m * n))
// S.C. - O(m * n)
class Solution {
    public int[][] highestPeak(int[][] isWater) {
        int m = isWater.length;
        int n = isWater[0].length;

        int[][] maxHeight = new int[m][n]; // result map grid

        // setting the water cells with 0
        // now pushing multi sources in the queue for multi src BFS
        Queue<int[]> multiSrcQ = new LinkedList<>();

        for(int i = 0; i < m; i++){ // O(m * n)
            for(int j = 0; j < n; j++){
                if(isWater[i][j] == 1){
                    maxHeight[i][j] = 0;
                    multiSrcQ.offer(new int[]{0, i, j}); // push the every source
                }
                else{
                    maxHeight[i][j] = -1;
                }
            }
        }

        // now performing BFS
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while(!multiSrcQ.isEmpty()){ // O(m * n)
            int currLevelSize = multiSrcQ.size();

            for(int curr = 0; curr < currLevelSize; curr++){
                int[] src = multiSrcQ.poll();
                int val = src[0];
                int i = src[1];
                int j = src[2];

                for(int[] dir : directions){
                    int new_i = i + dir[0];
                    int new_j = j + dir[1];

                    if(new_i >= 0 && new_i < m && new_j >= 0 && new_j < n && maxHeight[new_i][new_j] == -1){
                        maxHeight[new_i][new_j] = val + 1;
                        multiSrcQ.offer(new int[]{val + 1, new_i, new_j});
                    }
                }
            }
        }

        return maxHeight;
    }
}