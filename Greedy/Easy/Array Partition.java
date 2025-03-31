// LeetCode Easy - 561


// Approach 1 - Sorting
// T.C. - O(nlog(n) + n)
// S.C. - O(1)
class Solution {
    public int arrayPairSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int maxSum = 0;

        // after sorting, adjacent elements pair will give the 
        // sum maximized
        for(int i = 0; i<n; i += 2){
            maxSum += Math.min(nums[i], nums[i+1]);
        }

        return maxSum;
    }
}