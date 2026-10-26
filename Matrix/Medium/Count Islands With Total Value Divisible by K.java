// LeetCode - 3619



// Approach 1 - DFS
// T.C. - O(m * n)
// S.C. - O(m * n)
class Solution {
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean[][] visited;

    public long dfs(int i, int j, int[][] grid, int m, int n){
        if(i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || grid[i][j] == 0){
            return 0;
        }

        visited[i][j] = true;
        long sum = grid[i][j];

        for(int[] dir : directions){
            int ni = i + dir[0];
            int nj = j + dir[1];

            if(ni >= 0 && ni < m && nj >= 0 && nj < n && 
                !visited[ni][nj] && grid[ni][nj] > 0){
                sum += dfs(ni, nj, grid, m, n);
            }
        }

        return sum;
    }

    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        this.visited = new boolean[m][n];

        int result = 0;

        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(grid[i][j] != 0 && !visited[i][j]){
                    long islandSum = dfs(i, j, grid, m, n);

                    if(islandSum % k == 0){
                        result++;
                    }
                }
            }
        }

        return result;
    }
}