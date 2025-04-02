// LeetCode Medium - 2874


// Approach 1 (Brute Force) - Gives TLE
// T.C. - O(n^3)
// S.C. - O(1)
class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long maxValue = 0;

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                for(int k = j+1; k<n; k++){
                    long val = (long) (nums[i] - nums[j]) * nums[k];
                    maxValue = Math.max(maxValue, val);
                }
            }
        }

        return maxValue;
    }
}



// Approach 2 (Better) - Prefix sum concept
// T.C. - O(3n)
// S.C. - O(2n)
class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        long maxValue = 0;

        // populating leftMax
        // leftMax[0] = 0; as there is no elements in its left
        for(int i = 1; i<n; i++){
            leftMax[i] = Math.max(leftMax[i-1], nums[i-1]);
        }

        // populating rightMax
        // rightMax[n-1] = 0; as there is no elements in its right
        for(int i = n-2; i >= 0; i--){
            rightMax[i] = Math.max(rightMax[i+1], nums[i+1]);
        }

        // finding the max value
        for(int i = 0; i<n; i++){
            long val = (long) (leftMax[i] - nums[i]) * rightMax[i];
            maxValue = Math.max(maxValue, val);
        }

        return maxValue;
    }
}




// Approach 3 (Optimal) - Two pointer concept
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long max_i = 0;
        long maxDiff = 0;
        long maxValue = 0;

        // finding the max value
        for(int k = 0; k<n; k++){
            maxValue = Math.max(maxValue, maxDiff * nums[k]);
            maxDiff = Math.max(maxDiff, max_i - nums[k]);
            max_i = Math.max(max_i, nums[k]);
        }

        return maxValue;
    }
}