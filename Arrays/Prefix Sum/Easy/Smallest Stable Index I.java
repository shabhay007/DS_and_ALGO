// LeetCode - 3903



// Approach 1 - Brute Force
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        for(int i = 0; i<n; i++){
            // for max in 0...i
            int max = Integer.MIN_VALUE;

            for(int j = 0; j<=i; j++){
                max = Math.max(max, nums[j]);
            }

            // for min in i...n-1;
            int min = Integer.MAX_VALUE;

            for(int m = i; m<n; m++){
                min = Math.min(min, nums[m]);
            }

            if(max - min <= k){
                return i;
            }
        }

        return -1;
    }
}





// Approach 2 - Prefix Manipulation
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