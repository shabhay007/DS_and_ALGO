// LeetCode - 2536




// Approach 1 - Brute Force
// T.C. - O(q * n * n); q = queries.length
// S.C. - O(1)
class Solution {
    public void incrementSubgrid(int startRow, int endRow, int startCol, int endCol, int[][] grid){
        for(int i = startRow; i <= endRow; i++){
            for(int j = startCol; j <= endCol; j++){
                grid[i][j]++;
            }
        }
    }

    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] grid = new int[n][n];

        for(int[] query : queries){
            int startRow = query[0];
            int startCol = query[1];
            int endRow = query[2];
            int endCol = query[3];

            incrementSubgrid(startRow, endRow, startCol, endCol, grid);
        }

        return grid;
    }
}