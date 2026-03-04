// LeetCode - 1542



// Approach 1 - Brute Force
// T.C. - O(m*n * (m+n))
// S.C. - O(1)
class Solution {
    public boolean isSpecial(int i, int j, int[][] mat, int m, int n){
        // checking column
        for(int row = 0; row<m; row++){
            if(row != i && mat[row][j] != 0){
                return false;
            }
        }

        // checking row
        for(int col = 0; col < n; col++){
            if(col != j && mat[i][col] != 0){
                return false;
            }
        }

        return true;
    }

    public int numSpecial(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int count = 0;

        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(mat[i][j] == 1 && isSpecial(i, j, mat, m, n)){
                    count++;
                }
            }
        }

        return count;
    }
}






// Approach 2
// T.C. - O(m*n)
// S.C. - O(m+n)
class Solution {
    public int numSpecial(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[] rows = new int[m];
        int[] cols = new int[n];
        int count = 0;

        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(mat[i][j] == 1){
                    rows[i]++;
                    cols[j]++;
                }
            }
        }
        
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(mat[i][j] == 1 && rows[i] == 1 && cols[j] == 1){
                    count++;
                }
            }
        }

        return count;
    }
}