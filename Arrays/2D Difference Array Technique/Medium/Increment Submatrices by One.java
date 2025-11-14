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





// Approach 2 - Difference Array Technique
// T.C. - O(q*n + n^2); q = queries.length
// S.C. - O(1)
class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] diff = new int[n][n];

        // populating arr using Diff. Array Technique
        for(int[] query : queries){
            int startRow = query[0];
            int startCol = query[1];
            int endRow = query[2];
            int endCol = query[3];

            for(int i = startRow; i<=endRow; i++){
                diff[i][startCol] += 1;

                if(endCol + 1 < n){
                    diff[i][endCol + 1] -= 1;
                }
            }
        }

        // row wise cummulative sum
        for(int i = 0; i<n; i++){
            for(int j = 1; j<n; j++){
                diff[i][j] += diff[i][j-1];
            }
        }

        return diff;
    }
}