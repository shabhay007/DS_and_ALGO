// LeetCode Medium - 3381



// Approach 1 - Prefix Sum + Kadane's Algorithm
// T.C. - O(k * n/k) ~ O(n)
// S.C. - O(n)
class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;

        // prefix sum
        long[] cummSum = new long[n];
        cummSum[0] = nums[0];

        for(int i = 1; i<n; i++){
            cummSum[i] = cummSum[i-1] + nums[i];
        }

        long result = Long.MIN_VALUE;

        for(int start = 0; start < k; start++){ // O(k)
            long currSum = 0;
            int i = start;

            while(i < n && i+k-1 < n){ // O(n/k)
                int j = i+k-1;

                long subSum = cummSum[j] - (i-1 >= 0 ? cummSum[i-1] : 0);
                
                // should I start with curr (new sum i.e. sumSum) or add 
                // in the prev subSum i.e. subSum + currSum
                currSum = Math.max(subSum, subSum + currSum);
                result = Math.max(result, currSum);

                // incrementing k so that length % k == 0
                i += k;
            }
        }

        return result;
    }
}