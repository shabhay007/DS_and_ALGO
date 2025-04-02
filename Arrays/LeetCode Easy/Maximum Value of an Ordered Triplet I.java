// LeetCode Easy - 2873


// Approach 1 (Brute Force)
// T.C. - O(n^3)
// S.C. - O(1)
class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        long maxValue = 0;

        for(int i = 0; i<n-2; i++){
            for(int j = i+1; j<n-1; j++){
                for(int k = j+1; k<n; k++){
                    // explicitly casting one operand to long to avoid overflow
                    // upon multiplication
                    maxValue = Math.max(maxValue, (long) (nums[i] - nums[j]) * nums[k]);
                }
            }
        }

        return maxValue;
    }
}




// Approach 2 (Better) - prefix sum concept
// T.C. - O(3n)
// S.C. - O(2n)
class Solution {
    public long maximumTripletValue(int[] nums) {
        int n = nums.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // populating leftMax
        // leftMax[0] will be 0 as there is no element in its left
        for(int i = 1; i<n; i++){
            leftMax[i] = Math.max(leftMax[i-1], nums[i-1]);
        }

        // populating rightMax
        // rightMax[n-1] will be 0 as there is no element in its right
        for(int i = n-2; i >= 0; i--){
            rightMax[i] = Math.max(nums[i+1], rightMax[i+1]);
        }

        // now finding the max value of triplet
        long maxValue = 0;

        for(int i = 1; i<n-1; i++){
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

        for(int k = 0; k<n; k++){
            maxValue = Math.max(maxValue, maxDiff * nums[k]);
            maxDiff = Math.max(maxDiff, max_i - nums[k]);
            max_i = Math.max(max_i, nums[k]);
        }

        return maxValue;
    }
}