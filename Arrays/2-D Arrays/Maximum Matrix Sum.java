// LeetCode - 1975 - Medium


// O(n^2)
class Solution {
    public long maxMatrixSum(int[][] matrix) {
        int n = matrix.length;
        int negativeCount = 0;
        long absoluteSum = 0;
        int min = Integer.MAX_VALUE;

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(matrix[i][j] < 0){
                    negativeCount++;
                }

                absoluteSum += Math.abs(matrix[i][j]);
                min = Math.min(min, Math.abs(matrix[i][j]));
            }
        }

        // If there is even number of -ve numbers then it can
        // be made positive
        if(negativeCount % 2 == 0){
            return absoluteSum;
        }

        // subtracting min 2 times because min is already summed up in absolute sum
        // and then we also have to subtract that min from rest of the sum
        return absoluteSum - (2 * min);
    }
}
