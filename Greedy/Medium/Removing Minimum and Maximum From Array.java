// LeetCode - 2091



// Approach 1 - Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int min = Integer.MAX_VALUE;
        int minIdx = -1;

        int max = Integer.MIN_VALUE;
        int maxIdx = -1;

        for(int i = 0; i<n; i++){
            if(nums[i] < min){
                min = nums[i];
                minIdx = i;
            }

            if(nums[i] > max){
                max = nums[i];
                maxIdx = i;
            }
        }

        int deletionFromLeft = Math.max(maxIdx, minIdx) + 1;
        int deletionFromRight = n - Math.min(maxIdx, minIdx);
        int deletionFromLeftAndRight = Math.min(maxIdx, minIdx) + 1 + n - Math.max(maxIdx, minIdx);

        return Math.min(deletionFromRight, Math.min(deletionFromLeft, deletionFromLeftAndRight));
    }
}