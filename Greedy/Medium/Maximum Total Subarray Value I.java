// LeetCode - 3689



// Approach 1 - Greedy
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;

        for(int i = 0; i<n; i++){
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }

        return (max - min) * k;
    }
}