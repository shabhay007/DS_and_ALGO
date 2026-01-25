// LeetCode - 1984



// Approach 1 - Sorting + Sliding Window
// T.C. - O(nlog(n) + n)
// S.C. - O(1)
class Solution {
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;
        if(n == 1){
            return 0;
        }

        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;
        int i = 0;
        int j = i+k-1;

        while(j < n){
            min = Math.min(min, nums[j] - nums[i]);
            i++;
            j++;
        }

        return min;
    }
}