// LeetCode - 2577 - Hard



// Approach 1 - Dijkstra's Algorithm
// T.C. - O(m.n * log(m.n))
// S.C. - O(m.n)
class Solution {
    public int minimumTime(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // base case
        if(grid[0][1] > 1 && grid[1][0] > 1){
            return -1;
        }

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] visited = new boolean[m][n];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0, 0}); // {currTime, i, j}

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int currTime = curr[0];
            int i = curr[1];
            int j = curr[2];

            if(visited[i][j]) continue;
            visited[i][j] = true;

            if(i == m-1 && j == n-1){
                return currTime;
            }

            // exploring neighbours
            for(int[] dir : directions){
                int ni = i + dir[0];
                int nj = j + dir[1];

                if(ni < m && ni >= 0 && nj < n && nj >= 0 && !visited[ni][nj]){
                    int newTime = 0;

                    if(currTime + 1 >= grid[ni][nj]){
                        newTime = currTime + 1;
                    }

                    // if diff between gridTime and currTime is even
                    // then gridTime will be the newTime else 
                    // newTime = gridTime + 1;
                    else if((grid[ni][nj] - currTime) % 2 == 0){
                        newTime = grid[ni][nj] + 1;
                    }
                    else{
                        newTime = grid[ni][nj];
                    }

                    pq.offer(new int[]{newTime, ni, nj});
                }
            }
        }

        return -1;
    }
}




// Approach 2 - Dijkstra's Algorithm using result array
// T.C. - O(m.n * log(m.n))
// S.C. - O(m.n)
class Solution {
    public int minimumTime(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // base case
        if(grid[0][1] > 1 && grid[1][0] > 1){
            return -1;
        }

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0, 0}); // {currTime, i, j}


        int[][] result = new int[m][n];
        for(int[] row : result){
            Arrays.fill(row, Integer.MAX_VALUE); // not reacheable
        }

        result[0][0] = 0;

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int currTime = curr[0];
            int i = curr[1];
            int j = curr[2];

            if(i == m-1 && j == n-1){
                return currTime;
            }

            if(currTime > result[i][j]) continue; // Optional

            // exploring neighbours
            for(int[] dir : directions){
                int ni = i + dir[0];
                int nj = j + dir[1];

                if(ni < m && ni >= 0 && nj < n && nj >= 0){
                    int newTime = 0;

                    if(currTime + 1 >= grid[ni][nj]){
                        newTime = currTime + 1;
                    }

                    // if diff between gridTime and currTime is even
                    // then gridTime will be the newTime else 
                    // newTime = gridTime + 1;
                    else if((grid[ni][nj] - currTime) % 2 == 0){
                        newTime = grid[ni][nj] + 1;
                    }
                    else{
                        newTime = grid[ni][nj];
                    }

                    if(newTime < result[ni][nj]){
                        pq.offer(new int[]{newTime, ni, nj});
                        result[ni][nj] = newTime;
                    }
                }
            }
        }

        return -1;
    }
}