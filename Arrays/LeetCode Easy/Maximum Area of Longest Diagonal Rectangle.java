// LeetCode - 3000



// Approach 1
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int n = dimensions.length;
        double maxDiagonal = 0;
        int maxArea = 0;

        for(int i = 0; i<n; i++){
            int x = dimensions[i][0];
            int y = dimensions[i][1];

            double diagonal = Math.sqrt((x*x) + (y*y));

            if(diagonal > maxDiagonal){
                maxDiagonal = diagonal;
                maxArea = x * y;
            }
            else if(diagonal == maxDiagonal){
                maxArea = Math.max(maxArea, x * y);
            }
        }

        return maxArea;
    }
}