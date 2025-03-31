// LeetCode Medium - 1877


// Approach 1 - Sorting + Greedy + Two Pointers
// T.C. - O(nlog(n) + n)
// S.C. - O(1)
class Solution {
    public int minPairSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int minimizesMaxSum = 0;
        int l = 0;
        int r = n-1;

        while(l < r){
            minimizesMaxSum = Math.max(minimizesMaxSum, nums[l] + nums[r]);

            // updating pointers
            l++;
            r--;
        }

        return minimizesMaxSum;
    }
}