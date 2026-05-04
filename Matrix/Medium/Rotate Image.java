// LeetCode - 48



// Approach 1 - Swapping Elements above/below diagonal
// T.C. - O(n * n)
// S.C. - O(1)
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // 1. swap
        for(int i = 0; i<n; i++){
            for(int j = i; j<n; j++){
                if(i == j) continue;

                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // 2. row reverse
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n/2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n-1-j] = temp;
            }
        }
    }
}