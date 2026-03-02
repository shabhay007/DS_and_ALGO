// LeetCode - 1536



// Approach 1 - Simulation
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public void swap(int j, int i, int[] endZeros){
        int temp = endZeros[j];
        endZeros[j] = endZeros[i];
        endZeros[i] = temp;
    }

    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] endZeros = new int[n];

        for(int row = 0; row<n; row++){
            int col = n-1; // starting from end
            int zero = 0;

            while(col >= 0 && grid[row][col] == 0){
                zero++;
                col--;
            }

            endZeros[row] = zero;
        }

        // now processing
        int minSteps = 0;
        for(int row = 0; row < n; row++){
            int need = n - row - 1;
            int j = row;

            while(j < n && endZeros[j] < need){
                j++;
            }

            if(j == n){
                return -1;
            }

            minSteps += j - row;

            while(j > row){
                swap(j, j-1, endZeros);
                j--;
            }
        }

        return minSteps;
    }
}