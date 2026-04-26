// LeetCode Medium - 1559



// Approach 1 - DFS
// T.C. - O(m * n)
// S.C. - O(m * n)
class Solution {
    int m;
    int n;
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean isCycle(int i, int j, int prevI, int prevJ, char[][] grid, boolean[][] visited){
        if(visited[i][j]){
            return true;
        }

        visited[i][j] = true;

        for(int[] dir : directions){
            int newI = i + dir[0];
            int newJ = j + dir[1];

            if(newI >= 0 && newI < m && newJ >= 0 && newJ < n && grid[i][j] == grid[newI][newJ]){
                if(newI == prevI && newJ == prevJ){
                    continue; // can't go to parent
                }

                if(isCycle(newI, newJ, i, j, grid, visited)){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean containsCycle(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(!visited[i][j] && isCycle(i, j, i, j, grid, visited)){
                    return true;
                }
            }
        }

        return false;
    }
}