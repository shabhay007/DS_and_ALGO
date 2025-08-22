// LeetCode - 3195



// Approach 1 - Finding minRow, maxRow, minCol, maxCol
// T.C. - O(m*n)
// S.C. - O(1)
class Solution {
    public int minimumArea(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int minRow = Integer.MAX_VALUE;
        int maxRow = -1;
        int minCol = Integer.MAX_VALUE;
        int maxCol = -1;

        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(grid[i][j] == 1){
                    minRow = Math.min(minRow, i);
                    maxRow = Math.max(maxRow, i);
                    minCol = Math.min(minCol, j);
                    maxCol = Math.max(maxCol, j);
                }
            }
        }

        int side1 = maxRow - minRow + 1;
        int side2 = maxCol - minCol + 1;

        return side1 * side2;
    }
}