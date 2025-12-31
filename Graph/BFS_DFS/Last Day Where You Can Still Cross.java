// LeetCode Hard - 1970



// Approach 1 - Brute Force (DFS)
// T.C. - O(row ^2 * col^3)
// S.C. - O(row * col)
class Solution {
    public boolean dfs(int row, int col, int[][] grid, int m, int n, int[][] directions, boolean[][] visited){
        if(row < 0 || row >= m || col < 0 || col >= n || grid[row][col] == 1){
            return false;
        }

        // destination
        if(row == m-1){
            return true;
        }

        visited[row][col] = true;

        for(int[] dir : directions){
            int newX = row + dir[0];
            int newY = col + dir[1];

            if(newX >= 0 && newX < m && newY >= 0 && newY < n && !visited[newX][newY] && grid[newX][newY] != 1){
                if(dfs(newX, newY, grid, m, n, directions, visited)){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isReachable(int[][] grid, int row, int col){
        int[][] directions = {{0,1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] visited = new boolean[row][col];

        for(int cell = 0; cell < col; cell++){ // O(col)
            if(dfs(0, cell, grid, row, col, directions, visited)){ // O(row * col)
                return true;
            }
        }

        return false;
    }

    public int latestDayToCross(int row, int col, int[][] cells) {
        int[][] grid = new int[row][col];
        int lastDay = 0;

        for(int[] cell : cells){ // O(row * col)
            // making zero based index
            int x = cell[0]-1;
            int y = cell[1]-1;

            grid[x][y] = 1;

            if(isReachable(grid, row, col)){
                lastDay++;
            }
            else{
                break;
            }
        }

        return lastDay;
    }
}





// Approach 2 - Binary Search + DFS
// T.C. - O(log(row * col) * (row * col))
// S.C. - O(row * col)
class Solution {
    public boolean dfs(int row, int col, int[][] grid, int m, int n, int[][] directions){
        if(row < 0 || row >= m || col < 0 || col >= n || grid[row][col] == 1){
            return false;
        }

        // destination
        if(row == m-1){
            return true;
        }

        grid[row][col] = 1;

        for(int[] dir : directions){
            int newX = row + dir[0];
            int newY = col + dir[1];

            if(newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] != 1){
                if(dfs(newX, newY, grid, m, n, directions)){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isReachable(int[][] cells, int mid, int n, int row, int col){
        // filling water till mid
        int[][] grid = new int[row][col];

        for(int i = 0; i<=mid; i++){ // O(row * col)
            // making zero based index
            int x = cells[i][0] - 1;
            int y = cells[i][1] - 1;

            grid[x][y] = 1;
        }

        // now will try with all the cells of the 1st row
        int[][] directions = {{0,1}, {0, -1}, {1, 0}, {-1, 0}};

        for(int cell = 0; cell < col; cell++){ // O(col)
            if(dfs(0, cell, grid, row, col, directions)){ // O(row * col)
                return true;
            }
        }

        return false;
    }

    public int latestDayToCross(int row, int col, int[][] cells) {
        int[][] grid = new int[row][col];
        int lastDay = 0;

        int n = cells.length;
        int l = 0;
        int r = n - 1;

        while(l <= r){ // O(log(n))
            int mid = l + (r-l)/2;

            if(isReachable(cells, mid, n, row, col)){
                lastDay = mid+1;
                l = mid+1;
            }
            else{
                r = mid-1;
            }
        }

        return lastDay;
    }
}






// Approach 3 - Binary Search + BFS
// T.C. - O(log(row * col) * (row * col))
// S.C. - O(row * col)
class Solution {
    public boolean bfs(int row, int col, int[][] grid, int m, int n, int[][] directions){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{row, col});

        boolean[][] visited = new boolean[m][n];
        visited[row][col] = true; // filling water to make it unreachable

        while(!q.isEmpty()){
            int[] cell = q.poll();
            int x = cell[0];
            int y = cell[1];

            // destination
            if(x == m-1){
                return true;
            }

            for(int[] dir : directions){
                int newX = x + dir[0];
                int newY = y + dir[1];

                if(newX >= 0 && newX < m && newY >= 0 && newY < n && !visited[newX][newY] && grid[newX][newY] != 1){
                    q.offer(new int[]{newX, newY});
                    visited[newX][newY] = true;
                }
            }
        }

        return false;
    }

    public boolean isReachable(int[][] cells, int mid, int n, int row, int col){
        // filling water till mid
        int[][] grid = new int[row][col];

        for(int i = 0; i<=mid; i++){ // O(row * col)
            // making zero based index
            int x = cells[i][0] - 1;
            int y = cells[i][1] - 1;

            grid[x][y] = 1;
        }

        // now will try with all the cells of the 1st row
        int[][] directions = {{0,1}, {0, -1}, {1, 0}, {-1, 0}};

        for(int cell = 0; cell < col; cell++){ // O(col)
            if(grid[0][cell] == 0 && bfs(0, cell, grid, row, col, directions)){ // O(row * col)
                return true;
            }
        }

        return false;
    }

    public int latestDayToCross(int row, int col, int[][] cells) {
        int[][] grid = new int[row][col];
        int lastDay = 0;

        int n = cells.length;
        int l = 0;
        int r = n - 1;

        while(l <= r){ // O(log(n))
            int mid = l + (r-l)/2;

            if(isReachable(cells, mid, n, row, col)){
                lastDay = mid+1;
                l = mid+1;
            }
            else{
                r = mid-1;
            }
        }

        return lastDay;
    }
}