// LeetCode Medium - 2740


// Approach 1 - Sorting + Greedy
// T.C. - O(nlog(n) + n)
// S.C. - O(1)
class Solution {
    public int findValueOfPartition(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int  minValue = Integer.MAX_VALUE;

        for(int i = 0; i<n-1; i++){
            int value = Math.abs(nums[i] - nums[i+1]);
            minValue = Math.min(minValue, value);
        }

        return minValue;
    }
}