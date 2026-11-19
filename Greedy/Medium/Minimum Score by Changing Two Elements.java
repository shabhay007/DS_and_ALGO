// LeetCode - 2567



// Approach 1 - Greedy
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;

        if(n < 3){
            return 0;
        }

        // sorting
        Arrays.sort(nums);
        
        // first - last is changed
        int pair1 = nums[n-2] - nums[1];
        
        // first 2 is changed
        int pair2 = nums[n-1] - nums[2];

        // last 2 is changed
        int pair3 = nums[n-3] - nums[0];

        return Math.min(pair1, Math.min(pair2, pair3));
    }
}