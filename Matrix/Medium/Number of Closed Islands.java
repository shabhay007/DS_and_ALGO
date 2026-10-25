// LeetCode - 1254



// Approach 1 - DFS
// T.C. - O(m*n)
// S.C. - O(m*n)
class Solution {
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean[][] visited;

    public boolean dfs(int i, int j, int[][] grid, int m, int n){
        if(i >= m || i < 0 || j >= n || j < 0){
            return false;
        }

        if(grid[i][j] == 1 || visited[i][j]){ // found water in one side
            return true;
        }

        visited[i][j] = true;
        boolean isClosed = true;

        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];

            if(!dfs(x, y, grid, m, n)){
                isClosed = false;
            }
        }

        return isClosed;
    }

    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        this.visited = new boolean[m][n];
        int count = 0;

        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(grid[i][j] == 0 && !visited[i][j] && dfs(i, j, grid, m, n)){
                    count++;
                }
            }
        }

        return count;
    }
}