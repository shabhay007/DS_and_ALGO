// LeetCode - 778



// Approach 1 - DFS (Gives TLE)
// T.C. - O(4^(n^2))
// S.C. - O(n^2)
class Solution {
    int n;

    public void dfs(int row, int col, int[][] grid, int[][] directions, List<Integer> list, List<Integer> temp, boolean[][] visited){
        if(row < 0 || row >= n || col < 0 || col >= n || visited[row][col]){
            return;
        }

        visited[row][col] = true;
        temp.add(grid[row][col]);

        if(row == n-1 && col == n-1){
            int max = -1;

            for(int elevation : temp){
                max = Math.max(elevation, max);
            }

            list.add(max);

            temp.remove(temp.size() - 1);
            visited[row][col] = false;

            return;
        }

        for(int[] dir : directions){
            int x = row + dir[0];
            int y = col + dir[1];

            if(x >= 0 && x < n && y >= 0 && y < n && !visited[x][y]){
                dfs(x, y, grid, directions, list, temp, visited);
            }
        }

        temp.remove(temp.size() - 1);
        visited[row][col] = false;
    }

    public int swimInWater(int[][] grid) {
        this.n = grid.length;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        List<Integer> maxList = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        boolean[][] visited = new boolean[n][n];

        dfs(0, 0, grid, directions, maxList, temp, visited);

        int minTime = Integer.MAX_VALUE;

        for(int time : maxList){
            minTime = Math.min(time, minTime);
        }

        return minTime;
    }
}





// Approach 2 - DFS + Space optimised (Gives TLE)
// T.C. - O(4^(n^2))
// S.C. - O(n^2); recursion space
class Solution {
    int n;
    int minTime = Integer.MAX_VALUE;

    public void dfs(int row, int col, int[][] grid, int[][] directions, int currMax, boolean[][] visited){
        if(row < 0 || row >= n || col < 0 || col >= n || visited[row][col]){
            return;
        }

        visited[row][col] = true;
        currMax = Math.max(currMax, grid[row][col]);

        if(row == n-1 && col == n-1){
            minTime = Math.min(minTime, currMax);
            visited[row][col] = false;

            return;
        }

        for(int[] dir : directions){
            int x = row + dir[0];
            int y = col + dir[1];

            if(x >= 0 && x < n && y >= 0 && y < n && !visited[x][y]){
                dfs(x, y, grid, directions, currMax, visited);
            }
        }

        visited[row][col] = false;
    }

    public int swimInWater(int[][] grid) {
        this.n = grid.length;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int currMax = -1;
        boolean[][] visited = new boolean[n][n];

        dfs(0, 0, grid, directions, currMax, visited);

        return minTime;
    }
}