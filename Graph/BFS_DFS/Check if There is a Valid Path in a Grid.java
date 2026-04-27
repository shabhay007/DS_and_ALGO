// LeetCode Medium - 1391



// Approach 1 - DFS
// T.C. - O(m * n)
// S.C. - O(m * n)
class Solution {
    int m;
    int n;

    Map<Integer, int[][]> directions = new HashMap<>();

    // can't do it like this
    // In Java, you cannot execute statements (put) at class level
    // Only declarations are allowed there
    // directions.put(1, new int[][]{{0, -1}, {0, 1}});

    public Solution(){
        directions.put(1, new int[][]{{0, -1}, {0, 1}});
        directions.put(2, new int[][]{{-1, 0}, {1, 0}});
        directions.put(3, new int[][]{{0, -1}, {1, 0}});
        directions.put(4, new int[][]{{0, 1}, {1, 0}});
        directions.put(5, new int[][]{{0, -1}, {-1, 0}});
        directions.put(6, new int[][]{{-1, 0}, {0, 1}});
    }

    public boolean hasPath(int i, int j, int[][] grid, boolean[][] visited){
        if(i == m-1 && j == n-1){
            return true;
        }

        visited[i][j] = true;

        for(int[] dir : directions.get(grid[i][j])){
            int ni = i + dir[0];
            int nj = j + dir[1];

            if(ni < 0 || ni >= m || nj < 0 || nj >= n || visited[ni][nj]){
                continue; // skipping
            }

            // checking backward path i.e. from curr, can go back to the parent or not
            for(int[] backDir : directions.get(grid[ni][nj])){
                if(ni + backDir[0] == i && nj + backDir[1] == j){
                    if(hasPath(ni, nj, grid, visited)){
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean hasValidPath(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        boolean[][] visited = new boolean[m][n];

        return hasPath(0, 0, grid, visited);
    }
}