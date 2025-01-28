// LeetCode Medium - 2658


// Approach 1 - DFS
// T.C. - O(m * n)
// S.C. - O(m * n)
class Solution {
    public void dfs(int row, int col, int[][] grid, boolean[][] visited, int m, int n, int[] numOfFish){
        if(row >= m || row < 0 || col >= n || col < 0 || grid[row][col] == 0 || visited[row][col]){
            return;
        }

        // marking true, the visited cell
        visited[row][col] = true;

        // adding the number of fishes in any cell water along with fish
        numOfFish[0] += grid[row][col];

        dfs(row + 1, col, grid, visited, m, n, numOfFish); // checking in down direction
        dfs(row - 1, col, grid, visited, m, n, numOfFish); // checking in up direction
        dfs(row, col + 1, grid, visited, m, n, numOfFish); // checking in right direction
        dfs(row, col - 1, grid, visited, m, n, numOfFish); // checking in left direction
    }

    public int findMaxFish(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        boolean visited[][] = new boolean[m][n];
        int maxFish = 0;

        for(int i = 0; i<m; i++){
            for(int j = 0; j < n; j++){
                int numOfFish[] = new int[1];

                if(!visited[i][j] && grid[i][j] != 0){
                    dfs(i, j, grid, visited, m, n, numOfFish);

                    maxFish = Math.max(maxFish, numOfFish[0]);
                }
            }
        }

        return maxFish;
    }
}





// Approach 2 - BFS
// T.C. - O(m * n); we are visiting every cell once
// S.C. - O(m * n); we are storing every cell in the queue once
class Solution {
    public int bfs(int row, int col, int[][] grid, int m, int n, int[][] directions){
        int fishCount = grid[row][col];
        grid[row][col] = 0; // marked visited

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{row, col});

        while(!q.isEmpty()){
            int[] currCell = q.poll();
            int i = currCell[0];
            int j = currCell[1];

            for(int[] dir : directions){
                int newRow = i + dir[0];
                int newCol = j + dir[1];

                if(newRow < m && newRow >= 0 && newCol < n && newCol >= 0 && grid[newRow][newCol] != 0){
                    fishCount += grid[newRow][newCol];
                    q.offer(new int[]{newRow, newCol});
                    grid[newRow][newCol] = 0; // marking the new cell
                }
            }
        }

        return fishCount;
    }

    public int findMaxFish(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int directions[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int maxFish = 0;

        for(int i = 0; i<m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] != 0){
                    maxFish = Math.max(maxFish, bfs(i, j, grid, m, n, directions));
                }
            }
        }

        return maxFish;
    }
}





// Approach 3 - DFS (with reduced time complexity)
// T.C. - O(m * n)
// S.C. - O(Recursion Stack)
class Solution {
    public void dfs(int row, int col, int[][] grid, int m, int n, int[] numOfFish){
        if(row >= m || row < 0 || col >= n || col < 0 || grid[row][col] == 0){
            return;
        }

        // adding the number of fishes in any cell water along with fish
        numOfFish[0] += grid[row][col];

        // marking the visited cell
        grid[row][col] = 0; 

        dfs(row + 1, col, grid, m, n, numOfFish); // checking in down direction
        dfs(row - 1, col, grid, m, n, numOfFish); // checking in up direction
        dfs(row, col + 1, grid, m, n, numOfFish); // checking in right direction
        dfs(row, col - 1, grid, m, n, numOfFish); // checking in left direction
    }

    public int findMaxFish(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int maxFish = 0;

        for(int i = 0; i<m; i++){
            for(int j = 0; j < n; j++){
                int numOfFish[] = new int[1];

                if(grid[i][j] != 0){
                    dfs(i, j, grid, m, n, numOfFish);

                    maxFish = Math.max(maxFish, numOfFish[0]);
                }
            }
        }

        return maxFish;
    }
}