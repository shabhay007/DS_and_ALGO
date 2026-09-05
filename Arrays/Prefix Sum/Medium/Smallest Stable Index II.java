// LeetCode - 3904



// Approach 1 - Prefix Manipulation
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] maxToLeft = new int[n];
        int[] minToRight = new int[n];

        // processing max to the left of i
        maxToLeft[0] = nums[0];

        for(int i = 1; i<n; i++){
            maxToLeft[i] = Math.max(maxToLeft[i-1], nums[i]);
        }

        // processing min to the right of i
        minToRight[n-1] = nums[n-1];

        for(int i = n-2; i >= 0; i--){
            minToRight[i] = Math.min(minToRight[i+1], nums[i]);
        }

        // now processing for the result
        for(int i = 0; i<n; i++){
            if(maxToLeft[i] - minToRight[i] <= k){
                return i;
            }
        }

        return -1;
    }
}