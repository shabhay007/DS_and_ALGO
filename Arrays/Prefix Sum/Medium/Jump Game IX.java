// LeetCode - 3660



// Approach 1 - Precomputation of left max and right min
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int[] maxValue(int[] nums) {
        int n = nums.length;

        int[] leftMax = new int[n];
        leftMax[0] = nums[0];

        for(int i = 1; i<n; i++){
            leftMax[i] = Math.max(nums[i], leftMax[i-1]);
        }

        int[] rightMin = new int[n];
        rightMin[n-1] = nums[n-1];

        for(int i = n-2; i >= 0; i--){
            rightMin[i] = Math.min(nums[i], rightMin[i+1]);
        }

        // now processing ans
        int[] ans = new int[n];
        ans[n-1] = leftMax[n-1];

        for(int i = n-2; i >= 0; i--){
            // in this case, can't go to right
            if(leftMax[i] <= rightMin[i+1]){
                ans[i] = leftMax[i];
            }
            else{
                ans[i] = ans[i+1];
            }
        }

        return ans;
    }
}