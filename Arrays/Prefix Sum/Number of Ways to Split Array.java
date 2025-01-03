// LeetCode Medium - 2270


// Approach 1 - Brute Force - Gives TLE
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int waysToSplitArray(int[] nums) {
        int n = nums.length;
        int ways = 0;
        int sum = 0;

        for(int i = 0; i<n-1; i++){
            sum += nums[i];
            int newSum = 0;

            for(int j = i+1; j<n; j++){
                newSum += nums[j];
            }

            if(sum >= newSum){
                ways++;
            }
        }

        return ways;
    }
}




// Optimal Approach 2 - Prefix Sum
// T.C. - O(2 * n)
// S.C. - O(n)
class Solution {
    public int waysToSplitArray(int[] nums) {
        int n = nums.length;
        int ways = 0;
        long sum = 0;
        long[] prefixSum = new long[n];

        for(int i = 0; i<n; i++){
            sum += nums[i];

            prefixSum[i] = sum;
        }

        // at least one element should always be in the right
        // so, looping from 0 - (n-1)
        for(int i = 0; i<n-1; i++){
            if(prefixSum[i] >= sum - prefixSum[i]){
                ways++;
            }
        }

        return ways;
    }
}