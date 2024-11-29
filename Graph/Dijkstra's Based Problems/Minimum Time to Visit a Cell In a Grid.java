// LeetCode - 2577 - Hard




// O(E * log(V))
// E = number of Edges - m*n
// V = number of vertices - m*n
class Solution {
    public int minimumTime(int[][] grid) {
        int m = grid.length; 
        int n = grid[0].length;

        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        if(grid[0][1] > 1 && grid[1][0] > 1){
            return -1;
        }

        int[][] result = new int[m][n];
        for(int[] row : result){
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        result[0][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); //min-heap
        pq.offer(new int[]{0, 0, 0}); // {time, i, j}

        // visited matrix
        boolean[][] vis = new boolean[m][n];

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int currTime = curr[0];
            int i = curr[1];
            int j = curr[2];

            if(i == m-1 && j == n-1){
                return result[m-1][n-1];
            }

            if(vis[i][j] == true){
                continue;
            }

            vis[i][j] = true;

            // processing neighbours
            for(int[] dir : directions){
                int x = i + dir[0];
                int y = j + dir[1];

                // out of bound conditions
                if(x < 0 || x >= m || y < 0 || y >= n || vis[x][y]){
                    continue;
                }

                int newTime = -1;

                if(currTime + 1 >= grid[x][y]){
                    // pq.offer(new int[]{currTime + 1, x, y});
                    // result[x][y] = currTime + 1;
                    newTime = currTime + 1;
                }
                else if((grid[x][y] - currTime) % 2 == 0){ // moving back and forth to maintain time
                    // pq.offer(new int[]{grid[x][y] + 1, x, y});
                    // result[x][y] = grid[x][y] + 1;
                    newTime = grid[x][y] + 1;
                }
                else{
                    // moving back and forth to maintain time
                    // pq.offer(new int[]{grid[x][y], x, y}); 
                    // result[x][y] = grid[x][y];
                    newTime = grid[x][y];
                }

                if(newTime < result[x][y]){
                    result[x][y] = newTime;
                    pq.offer(new int[]{newTime, x, y});
                }
            }
        }

        return result[m-1][n-1];
    }
}
