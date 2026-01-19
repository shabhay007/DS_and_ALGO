// LeetCode - 1292



// Approach 1 - Brute Force (checking all squares) (Gives TLE)
// T.C. - O(n^5)
// S.C. - O(1)
class Solution {
    public boolean isValid(int row, int col, int[][] mat, int threshold, int k){
        // boundary check
        if(row+k > mat.length || col+k > mat[0].length){
            return false;
        }

        int sum = 0;
        for(int i = row; i<row+k; i++){
            for(int j = col; j<col+k; j++){
                sum += mat[i][j];
            }
        }

        System.out.println(sum);
        return sum <= threshold;
    }

    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int size = Math.min(m, n);

        for(int k = size; k >= 1; k--){
            for(int i = 0; i < m; i++){
                for(int j = 0; j<n; j++){
                    if(isValid(i, j, mat, threshold, k)){
                        return k;
                    }
                }
            }
        }

        return 0;
    }
}






// Approach 2 - Slight optimised using row wise prefix sum (Still TLE)
// T.C. - O(n^4)
// S.C. - O(m*n)
class Solution {
    public boolean isValid(int row, int col, int[][] rowSum, int threshold, int k){
        // boundary check
        if(row+k > rowSum.length || col+k > rowSum[0].length){
            return false;
        }

        int sum = 0;
        for(int i = row; i<row+k; i++){
            sum += rowSum[i][col+k-1] - ((col > 0) ? rowSum[i][col-1] : 0);
        }

        System.out.println(sum);
        return sum <= threshold;
    }

    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int size = Math.min(m, n);

        // prefix sum - row wise
        int[][] rowSum = new int[m][n];
        for(int i = 0; i<m; i++){
            rowSum[i][0] = mat[i][0];

            for(int j = 1; j<n; j++){
                rowSum[i][j] += mat[i][j] + rowSum[i][j-1];
            }
        }

        for(int k = size; k >= 1; k--){
            for(int i = 0; i < m; i++){
                for(int j = 0; j<n; j++){
                    if(isValid(i, j, rowSum, threshold, k)){
                        return k;
                    }
                }
            }
        }

        return 0;
    }
}





// Approach 3 - Slight Optimised using row wise prefix sum
// T.C. - O(m * n * Math.min(m,n)^2)
// S.C. - O(m*n)
class Solution {
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int size = Math.min(m, n);

        // prefix sum - row wise
        int[][] rowSum = new int[m][n];
        for(int i = 0; i<m; i++){
            rowSum[i][0] = mat[i][0];

            for(int j = 1; j<n; j++){
                rowSum[i][j] = mat[i][j] + rowSum[i][j-1];
            }
        }

        for(int k = size; k >= 1; k--){
            for(int i = 0; i <= m-k; i++){
                for(int j = 0; j <= n-k; j++){
                    int sum = 0;

                    // sum k rows using row-wise prefix sum
                    for (int r = i; r < i + k; r++) {
                        sum += rowSum[r][j + k - 1] - (j > 0 ? rowSum[r][j - 1] : 0);
                    }

                    if (sum <= threshold) {
                        return k;
                    }
                }
            }
        }

        return 0;
    }
}