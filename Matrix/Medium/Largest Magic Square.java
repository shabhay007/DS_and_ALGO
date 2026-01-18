// LeetCode - 1895



// Approach 1 - Brute Force
// T.C. - O(m*n*k)
// S.C. - O(1)
class Solution {
    public boolean isMagicSquare(int row, int col, int k, int[][] grid) {
        if (row + k - 1 >= grid.length || col + k - 1 >= grid[0].length) {
            return false;
        }

        // checking row sum
        int prev = -1;

        for (int i = row; i <= row+k-1; i++) {
            int currRowSum = 0;

            for (int j = col; j <= col+k-1; j++) {
                currRowSum += grid[i][j];
            }

            if (prev != -1 && currRowSum != prev) {
                return false;
            }

            prev = currRowSum;
        }

        // checking col sum
        for (int j = col; j <= col+k-1; j++) {
            int currColSum = 0;

            for (int i = row; i <= row+k-1; i++) {
                currColSum += grid[i][j];
            }

            if (currColSum != prev) {
                return false;
            }

            prev = currColSum;
        }

        // checking diagonal sum
        int diag1 = 0, diag2 = 0;

        for (int i = 0; i < k; i++) {
            diag1 += grid[row + i][col + i];
            diag2 += grid[row + i][col + k - 1 - i];
        }

        if(diag1 != prev || diag2 != prev){
            return false;
        }

        return true;
    }

    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int size = Math.min(m, n);

        for (int k = size; k >= 2; k--) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (isMagicSquare(i, j, k, grid)) {
                        return k;
                    }
                }
            }
        }

        return 1;
    }
}





// Approach 2 - (Trying all squares using prefix sums)
// T.C : O(rows * cols * min(rows, cols)^2)
// S.C : O(rows * cols)
class Solution {
    public int largestMagicSquare(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Row-wise prefix sum
        int[][] rowCumsum = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            rowCumsum[i][0] = grid[i][0];
            for (int j = 1; j < cols; j++) {
                rowCumsum[i][j] = rowCumsum[i][j - 1] + grid[i][j];
            }
        }

        // Column-wise prefix sum
        int[][] colCumsum = new int[rows][cols];
        for (int j = 0; j < cols; j++) {
            colCumsum[0][j] = grid[0][j];
            for (int i = 1; i < rows; i++) {
                colCumsum[i][j] = colCumsum[i - 1][j] + grid[i][j];
            }
        }

        // Try square sizes from largest to smallest
        for (int side = Math.min(rows, cols); side >= 2; side--) {

            // Top-left corner of square
            for (int i = 0; i + side - 1 < rows; i++) {
                for (int j = 0; j + side - 1 < cols; j++) {

                    int targetSum =
                        rowCumsum[i][j + side - 1] - (j > 0 ? rowCumsum[i][j - 1] : 0);

                    boolean allSame = true;

                    // Check all rows
                    for (int r = i + 1; r < i + side; r++) {
                        int rowSum =
                            rowCumsum[r][j + side - 1] - (j > 0 ? rowCumsum[r][j - 1] : 0);
                        if (rowSum != targetSum) {
                            allSame = false;
                            break;
                        }
                    }
                    if (!allSame) continue;

                    // Check all columns
                    for (int c = j; c < j + side; c++) {
                        int colSum =
                            colCumsum[i + side - 1][c] - (i > 0 ? colCumsum[i - 1][c] : 0);
                        if (colSum != targetSum) {
                            allSame = false;
                            break;
                        }
                    }
                    if (!allSame) continue;

                    // Check diagonals
                    int diag = 0;
                    int antiDiag = 0;
                    for (int k = 0; k < side; k++) {
                        diag += grid[i + k][j + k];
                        antiDiag += grid[i + k][j + side - 1 - k];
                    }

                    if (diag == targetSum && antiDiag == targetSum) {
                        return side;
                    }
                }
            }
        }

        return 1;
    }
}