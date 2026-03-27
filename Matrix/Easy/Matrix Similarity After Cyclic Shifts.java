// LeetCode - 2946



// Approach 1 - Brute Force
// T.C. - O(k * m * n)
// S.C. - O(m * n)
class Solution {
    public boolean areSimilar(int[][] mat, int K) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] grid = new int[m][n];

        for(int i = 0; i<m; i++){
            grid[i] = mat[i].clone();
        }

        for(int k = 1; k <= K; k++){
            for(int row = 0; row < m; row++){
                if(row % 2 == 0){
                    int temp = grid[row][0];

                    for(int col = 1; col<n; col++){
                        grid[row][col-1] = grid[row][col];
                    }

                    grid[row][n-1] = temp;
                }
                else{
                    int temp = grid[row][n-1];

                    for(int col = n-2; col >= 0; col--){
                        grid[row][col+1] = grid[row][col];
                    }

                    grid[row][0] = temp;
                }
            }
        }

        return Arrays.deepEquals(mat, grid);
    }
}





// Approach 2 - Using Cyclic properties
// T.C. - O(m * n)
// S.C. - O(m * n)
class Solution {
    public boolean areSimilar(int[][] mat, int K) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] grid = new int[m][n];

        for(int i = 0; i<m; i++){
            grid[i] = mat[i].clone();
        }

        int shift = K % n;

        for(int row = 0; row < m; row++){
            int[] newRow = new int[n];

            for(int col = 0; col<n; col++){
                if(row % 2 == 0){
                    newRow[col] = mat[row][(col + shift) % n];
                }
                else{
                    newRow[col] = mat[row][(col - shift + n) % n];
                }
            }

            grid[row] = newRow;
        }

        return Arrays.deepEquals(mat, grid);
    }
}







// Approach 3 - Using Cyclic properties
// T.C. - O(m * n)
// S.C. - O(1)
class Solution {
    public boolean areSimilar(int[][] mat, int K) {
        int m = mat.length;
        int n = mat[0].length;
        int k = K % n;

        for(int row = 0; row < m; row++){
            for(int col = 0; col<n; col++){
                int curr = col;
                int future;

                if(row % 2 == 0){
                    future = (curr + k) % n;
                }
                else{ // curr - k can be -ve and to avoid it add n
                    future = (curr - k + n) % n;
                }

                if(mat[row][curr] != mat[row][future]){
                    return false;
                }
            }
        }

        return true;
    }
}