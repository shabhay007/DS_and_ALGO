// LeetCode - 2257 - Medium




// Approach 1 - Matrix Simulation
// T.C. - O(m * n)
// S.C. - O(m * n)
class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];

        // for guard
        for(int[] cell : guards){
            int row = cell[0];
            int col = cell[1];

            grid[row][col] = 1;
        }

        // for wall 
        for(int[] cell : walls){
            int row = cell[0];
            int col = cell[1];

            grid[row][col] = -1;
        }

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        for(int[] guard : guards){
            int x = guard[0];
            int y = guard[1];

            for(int[] dir : directions){
                int nx = x + dir[0];
                int ny = y + dir[1];

                while(nx >= 0 && nx < m && ny >= 0 && ny < n 
                    && grid[nx][ny] != -1 && grid[nx][ny] != 1){
                    if(grid[nx][ny] == 0){
                        grid[nx][ny] = 2; // marking guarded
                    }

                    nx += dir[0];
                    ny += dir[1];
                }
            }
        }

        int notGuardedCells = 0;

        for(int row = 0; row < m; row++){
            for(int col = 0; col < n; col++){
                if(grid[row][col] != 1 && grid[row][col] != -1 && 
                   grid[row][col] != 2)
                    notGuardedCells++;
            }
        }

        return notGuardedCells;
    }
}





// Optimal - Matrix Simulation
class Solution {
    public void markForGuarded(int row, int col, int[][] grid){
        //north
        for(int i = row-1; i>=0; i--){
            if(grid[i][col] == 1 || grid[i][col] == 2){
                break;
            }

            grid[i][col] = 3; // marked as guarded
        }

        // east
        for(int i = col+1; i<grid[0].length; i++){
            if(grid[row][i] == 1 || grid[row][i] == 2){
                break;
            }

            grid[row][i] = 3; // marked as guarded
        }

        // south
        for(int i = row+1; i < grid.length; i++){
            if(grid[i][col] == 1 || grid[i][col] == 2){
                break;
            }

            grid[i][col] = 3; // marked as guarded
        }

        // west
        for(int i = col-1; i>=0; i--){
            if(grid[row][i] == 1 || grid[row][i] == 2){
                break;
            }

            grid[row][i] = 3; // marked as guarded
        }
    }

    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];
        boolean[][] vis = new boolean[m][n];

        // up/north, rigth/east, down/south, left/west
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        // Marking 1 in the grid where guards are present
        for(int[] guard : guards){
            grid[guard[0]][guard[1]] = 1;
        }

        // Marking 2 in the grid where walls are present
        for(int[] wall : walls){
            grid[wall[0]][wall[1]] = 2;
        }


        for(int[] guard : guards){
            int old_x = guard[0];
            int old_y = guard[1];

            markForGuarded(old_x, old_y, grid);
        }

        int unguardedCells = 0;
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(grid[i][j] == 0){
                    unguardedCells++;
                }
            }
        }

        return unguardedCells;
    }
}



// Optimal - DFS
class Solution {
    public void dfs(int m, int n, int i, int j, int[][] grid, int valForDirection){
        if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 1 || grid[i][j] == 2){
            return;
        }

        grid[i][j] = 3; // marked as guarded

        // up/north
        if(valForDirection == 1){
            dfs(m, n, i-1, j, grid, valForDirection);
        }

        // east
        else if(valForDirection == 2){
            dfs(m, n, i, j+1, grid, valForDirection);
        }

        // south
        else if(valForDirection == 3){
            dfs(m, n, i+1, j, grid, valForDirection);
        }

        // west
        else{
            dfs(m, n, i, j-1, grid, valForDirection);
        }
    }

    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];

        // Marking 1 in the grid where guards are present
        for(int[] guard : guards){
            grid[guard[0]][guard[1]] = 1;
        }

        // Marking 2 in the grid where walls are present
        for(int[] wall : walls){
            grid[wall[0]][wall[1]] = 2;
        }


        for(int[] guard : guards){
            int i = guard[0];
            int j = guard[1];

            dfs(m, n, i-1, j, grid, 1); // north
            dfs(m, n, i, j+1, grid, 2); // east
            dfs(m, n, i+1, j, grid, 3); // south
            dfs(m, n, i, j-1, grid, 4); // west
        }

        int unguardedCells = 0;
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(grid[i][j] == 0){
                    unguardedCells++;
                }
            }
        }

        return unguardedCells;
    }
}