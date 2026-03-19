// LeetCode - 3212



// Approach 1 - Grid Prefix Sum
// T.C. - O(m * n)
// S.C. - O(m * n)
class Solution {
    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] mat = new int[m][n][2];
        int count = 0;

        for(int row = 0; row<m; row++){
            for(int col = 0; col<n; col++){
                int x = 0;
                int y = 0;

                if(grid[row][col] == 'X'){
                    x = 1;
                }
                else if(grid[row][col] == 'Y'){
                    y = 1;
                }

                // processing prefix sum
                if(row > 0){
                    x += mat[row-1][col][0];
                    y += mat[row-1][col][1];
                }

                if(col > 0){
                    x += mat[row][col-1][0];
                    y += mat[row][col-1][1];
                }

                if(row > 0 && col > 0){
                    x -= mat[row-1][col-1][0];
                    y -= mat[row-1][col-1][1];
                }

                mat[row][col][0] = x;
                mat[row][col][1] = y;

                // processing count
                if(mat[row][col][0] > 0 && mat[row][col][0] == mat[row][col][1]){
                    count++;
                }
            }
        }

        return count;
    }
}





