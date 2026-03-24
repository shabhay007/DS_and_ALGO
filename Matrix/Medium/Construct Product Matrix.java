// LeetCode - 2906



// Approach 1 - Prefix & Suffix Multiplication
// T.C. - O(n * m)
// S.C. - O(n * m)
class Solution {
    int mod = 12345;

    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        long prefixProduct = 1;
        long[][] prefix = new long[n][m];

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                prefix[i][j] = prefixProduct;
                prefixProduct = (prefixProduct * grid[i][j]) % mod;
            }
        }

        long suffixProduct = 1;
        long[][] suffix = new long[n][m];

        for(int i = n-1; i >= 0; i--){
            for(int j = m-1; j >= 0; j--){
                suffix[i][j] = suffixProduct;
                suffixProduct = (suffixProduct * grid[i][j]) % mod;
            }
        }

        int[][] result = new int[n][m];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                result[i][j] = (int) ((prefix[i][j] * suffix[i][j]) % mod);
            }
        }

        return result;
    }
}






// Approach 2 - Suffix Multiplication + On the Fly Prefix Multiplication
// T.C. - O(n * m)
// S.C. - O(n * m)
class Solution {
    int mod = 12345;

    public int[][] constructProductMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        long suffixProduct = 1;
        long[][] suffix = new long[n][m];

        for(int i = n-1; i >= 0; i--){
            for(int j = m-1; j >= 0; j--){
                suffix[i][j] = suffixProduct;
                suffixProduct = (suffixProduct * grid[i][j]) % mod;
            }
        }

        long prefixProduct = 1;
        int[][] result = new int[n][m];

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                result[i][j] = (int) ((prefixProduct * suffix[i][j]) % mod);
                prefixProduct = (prefixProduct * grid[i][j]) % mod;
            }
        }

        return result;
    }
}