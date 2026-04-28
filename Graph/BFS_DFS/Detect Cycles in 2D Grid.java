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







// Approach 2 - BFS
// T.C. - O(m * n)
// S.C. - O(m * n)
class Solution {
    int m;
    int n;
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean bfs(int i, int j, char[][] grid, boolean[][] visited){
        Queue<int[]> q = new LinkedList<>();

        // {curr_i, curr_j, prev_i, prev_j}
        q.add(new int[]{i, j, -1, -1});
        visited[i][j] = true;

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];
            int prevX = curr[2];
            int prevY = curr[3];

            for(int[] dir : directions){
                int ni = x + dir[0];
                int nj = y + dir[1];
                
                if(ni >= 0 && ni < m && nj >= 0 && nj < n && grid[ni][nj] == grid[x][y]){
                    if(ni == prevX && nj == prevY){
                        continue;
                    }

                    if(visited[ni][nj]){
                        return true;
                    }

                    q.offer(new int[]{ni, nj, x, y});
                    visited[ni][nj] = true;
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
                if(!visited[i][j] && bfs(i, j, grid, visited)){
                    return true;
                }
            }
        }

        return false;
    }
}