// LeetCode - 628



// Approach 1 - Sorting
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public int maximumProduct(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int result = nums[n-1] * nums[n-2] * nums[n-3];
        result = Math.max(result, nums[0] * nums[1] * nums[n-1]);

        return result;
    }
}