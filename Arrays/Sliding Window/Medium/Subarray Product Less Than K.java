// LeetCode - 713


// Optimal approach - Sliding Window (Refer. - 2302)
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k <= 1) return 0;

        int n = nums.length;
        int count = 0;
        int l = 0;
        int r = 0;
        int product = 1;

        while(r < n){
            product *= nums[r];

            while(product >= k){
                product /= nums[l];
                l++;
            }

            count += r - l + 1;
            r++;
        }

        return count;
    }
}