// LeetCode - 2778



// Approach 1 - Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int sumOfSquares(int[] nums) {
        int n = nums.length;
        int result = 0;

        for(int i = 1; i<=n; i++){
            if(n % i == 0){
                result += nums[i-1] * nums[i-1];
            }
        }

        return result;
    }
}