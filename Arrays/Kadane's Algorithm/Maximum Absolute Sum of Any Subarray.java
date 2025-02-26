// LeetCode Medium - 1749



// Brute Force - Gives TLE
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int n = nums.length;
        int maxAbsSum = 0;

        for(int i = 0; i<n; i++){
            int sum = 0;

            for(int j = i; j<n; j++){
                sum += nums[j];

                maxAbsSum = Math.max(maxAbsSum, Math.abs(sum));
            }
        }

        return maxAbsSum;
    }
}





// Better - Kadane's Algorithm
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public int getMaxSum(int[] nums, int n){
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;

        for(int i = 0; i<n; i++){
            sum += nums[i];

            sum = Math.max(sum, 0);
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    public int getMinSum(int[] nums, int n){
        int sum = 0;
        int minSum = Integer.MAX_VALUE;

        for(int i = 0; i<n; i++){
            sum += nums[i];

            sum = Math.min(sum, 0);
            minSum = Math.min(minSum, sum);
        }

        return minSum;
    }

    public int maxAbsoluteSum(int[] nums) {
        int n = nums.length;
        
        // step 1 : find max subarray sum
        int maxSum = Math.abs(getMaxSum(nums, n));

        // step 2 : find min subarray sum
        int minSum = Math.abs(getMinSum(nums, n));

        // step 3 : return the absolute max value
        return Math.max(maxSum, minSum);
    }
}






// Optimal - Kadane's Algorithm
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int n = nums.length;

        int currMaxSum = 0;
        int maxSum = Integer.MIN_VALUE;

        int currMinSum = 0;
        int minSum = Integer.MAX_VALUE;

        for(int i = 0; i<n; i++){
            // finding max subarray sum
            currMaxSum = Math.max(nums[i], currMaxSum + nums[i]);
            maxSum = Math.max(maxSum, currMaxSum);

            // finding min subarray sum
            currMinSum = Math.min(nums[i], currMinSum + nums[i]);
            minSum = Math.min(minSum, currMinSum);
        }
        
        return Math.max(Math.abs(maxSum), Math.abs(minSum));
    }
}