// LeetCode - 2574



// Approach 1 - Prefix Sum
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] leftSum = new int[n];
        int[] rightSum = new int[n];

        // populating leftSum and rightSum
        for(int i = 1; i<n; i++){
            leftSum[i] = leftSum[i-1] + nums[i-1];
        }

        // populating rightSum
        for(int i = n-2; i >= 0; i--){
            rightSum[i] = rightSum[i+1] + nums[i+1];
        }

        // processing result
        int[] result = new int[n];
        for(int i = 0; i<n; i++){
            result[i] = Math.abs(leftSum[i] - rightSum[i]);
        }

        return result;
    }
}





// Approach 2 - Two Pointers
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;

        // calculating total sum
        int totalSum = 0;
        for(int i = 0; i<n; i++){
            totalSum += nums[i];
        }

        // processing result
        int leftSum = 0;
        int rightSum = totalSum;
        int[] result = new int[n];

        for(int i = 0; i<n; i++){
            leftSum += nums[i];
            result[i] = Math.abs(leftSum - rightSum);
            rightSum -= nums[i];
        }

        return result;
    }
}