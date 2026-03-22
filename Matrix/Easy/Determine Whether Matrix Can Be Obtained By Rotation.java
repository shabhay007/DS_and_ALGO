// LeetCode - 1886



// Approach 1 - Matrix Property to rotate (transpose + row reverse)
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public boolean isEqual(int[][] mat, int[][] target, int n){
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(mat[i][j] != target[i][j]){
                    return false;
                }
            }
        }

        return true;
    }

    public void rotate90(int[][] mat, int n){
        // transpose
        for(int i = 0; i<n; i++){
            for(int j = i; j<n; j++){
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }

        // reversing row for 90 degree rotation
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n/2; j++){
                int temp = mat[i][j];
                mat[i][j] = mat[i][n-j-1];
                mat[i][n-j-1] = temp;
            }
        }
    }

    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;
        int rotation = 0;

        while (rotation < 4) {
            if(isEqual(mat, target, n)){
                return true;
            }

            rotate90(mat, n);

            rotation++;
        }

        return false;
    }
}